package com.artbyte.resources;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.artbyte.model.Message;

@Path("/resource")
@RequestScoped
public class ResourceRest {

    @GET
    @Path("/user")
    @RolesAllowed("USER")
    @Produces(MediaType.APPLICATION_JSON)
    public Response user(){
        return Response.ok(new Message("content for user")).build();
    }
    

    @GET
    @Path("/admin")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response admin(){
        return Response.ok(new Message("Content for admin")).build();
    }


    @GET
    @Path("/user-or-admin")
    @RolesAllowed({"USER", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response userOrAdmin(){
        return Response.ok(new Message("Content for user or admin")).build();
    }
}
