package com.artbyte.resources;

import java.net.URI;
import java.util.function.Predicate;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.artbyte.dto.CompanyDTO;
import com.artbyte.entity.Company;
import com.artbyte.utils.CompanyMapper;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.SseElementType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Path("/api/companies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyResources {

    private static final Logger log = LoggerFactory.getLogger(CompanyResources.class);

    @Inject
    CompanyMapper companyMapper;


    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    @RolesAllowed({"ROLE_ADMIN", "ROLE_COMPANY_READ", "COMPANY_DELETE", "ROLE_COMPANY_CREATE"})
    public Multi<CompanyDTO> getAllActiveCompanies(@Context SecurityContext ctx) {
        String name = ctx.getUserPrincipal().getName();
        log.info("hello {}", name);
        Multi<Company> multi = hasRoleAdmin(ctx) ? Company.findActivateCompanies() : Company
                .findActiveCompaniesByUser(name);
        return multi.map(c -> companyMapper.toResource(c));
    }


    @GET
    @Path("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_COMPANY_READ", "ROLE_COMPANY_SAVE"})
    public Uni<Response> getById(@PathParam("id") String id, @Context SecurityContext ctx){
        return getCompanyById(id)
        .onItem().ifNull().failWith(NotFoundException::new)
        .map(c -> {
            if(hasPermissionsToChangeCompany(ctx).test(c)){
                return Response.ok(companyMapper.toResource(c)).build();
            }else{
                throw new ForbiddenException(String.format("User(%s) does not have access to this resource", ctx.getUserPrincipal().getName()));
            }
        });
    }



    @POST
    @RolesAllowed({"ROLE_ADMIN", "ROLE_COMPANY_CREATE"})
    public Uni<Response> create(@Valid CompanyDTO companyDTO, @Context SecurityContext ctx){
        Company company = companyMapper.toModel(companyDTO);
        company.createdByUser = ctx.getUserPrincipal().getName();
        return company.persist()
        .flatMap(i -> company.findById(company.id)
        .map(c -> Response.created(URI.create(String.format("/api/companies/%s", ((Company)c).id)))
        .entity(companyMapper.toResource(((Company)c)))
        .build()));
    }


    @PUT
    @Path("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_COMPANY_CREATE"})
    public Uni<Response> update(@Valid CompanyDTO companyDTO, @PathParam("id") String id, @Context SecurityContext ctx){
        return getCompanyById(id)
                .onItem().ifNull().failWith(NotFoundException::new)
                .map(c -> {
                    c.name = companyDTO.getName();
                    c.lastModifiedByUser = ctx.getUserPrincipal().getName();
                    c.update();
                    return c;
                })
                .map(c -> Response.ok(c).build());
    }



    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_COMPANY_DELETE"})
    public Uni<Response> delete(@PathParam("id") String id, @Context SecurityContext ctx){
        return getCompanyById(id)
        .onItem().ifNull().failWith(NotFoundException::new)
        .map(c -> {
            if(hasPermissionsToChangeCompany(ctx).test(c)){
                return c.delete();
            }else{
                throw new ForbiddenException(String.format("User(%s) does not have access to delete this resource", ctx.getUserPrincipal().getName()));
            }
        }).map(c -> Response.noContent().build());
    }

    /**==================================UTILIDADES ================================ */
    

    private Uni<Company> getCompanyById(String id){
        return Company.findById(new ObjectId());
    }

    private Predicate<Company> hasPermissionsToChangeCompany(SecurityContext ctx){
        return c -> hasRoleAdmin(ctx) || c.createdByUser.equals(ctx.getUserPrincipal().getName());
    }

    private boolean hasRoleAdmin(SecurityContext ctx){
        return ctx.isUserInRole("ROLE_ADMIN");
    }
}
