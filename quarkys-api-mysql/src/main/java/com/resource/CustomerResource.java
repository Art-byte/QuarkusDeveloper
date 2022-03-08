package com.resource;

import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dto.CustomerDTO;
import com.service.CustomerService;



import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/api/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GET
    @APIResponses(
            value = {
                @APIResponse(
                    responseCode = "200",
                    description = "Get all customers",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(type = SchemaType.ARRAY, implementation = CustomerDTO.class)))
            }
    )
    public Response get(){
        return Response.ok(customerService.findAll()).build();
    }


    @GET
    @Path("/{customerId}")
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Get customer by customerId",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerDTO.class))),
            @APIResponse(
                responseCode = "404",
                description = "No customer found for customerId provided",
                content = @Content(mediaType = "application/json"))
        }
    )
    public Response getById(@PathParam("customerId") Integer customerId){
        Optional<CustomerDTO> optional = customerService.findById(customerId);
        return !optional.isEmpty() 
        ? Response.ok(optional.get()).build() 
        : Response.status(Response.Status.NOT_FOUND).build();
    }



    @POST
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "201",
                description = "Customer created",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(type = SchemaType.OBJECT,  implementation = CustomerDTO.class))),

            @APIResponse(
                responseCode = "400",
                description = "Customer already exists for customerId",
                content = @Content(mediaType = "application/json")
            )
        }
    )
    public Response post(@Valid CustomerDTO customerDTO){
        final CustomerDTO saved = customerService.save(customerDTO);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }



    @PUT
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "Customer updated",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(type = SchemaType.OBJECT, implementation = CustomerDTO.class))),
            
            @APIResponse(
                responseCode = "400",
                description = "Customer already exists for customerId",
                content = @Content(mediaType = "application/json"))
        }
    )
    public Response put(@Valid CustomerDTO customerDTO){
        final CustomerDTO saved = customerService.update(customerDTO);
        return Response.ok(saved).build();
    }
    
}
