package com;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.entity.User;

import org.bson.types.ObjectId;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/api/users")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersController {

  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> userList(){
        return User.streamAllUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findUserById(@PathParam("id") String id){
        return User.findUserById(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser(User user){
        return user.persist().map(r -> Response.accepted().build());
    }


    @PUT
    @Path("/{id}")
    public Uni<Response> updateUserById(@PathParam("id") String id, User user){
        user.setId(new ObjectId(id));
        return user.update().map(r -> Response.accepted().build());
    }


    @DELETE
    @Path("/{id}")
    public Uni<Boolean> deleteUserById(@PathParam("id") String id){
        return User.deleteById(new ObjectId(id));
    }

}