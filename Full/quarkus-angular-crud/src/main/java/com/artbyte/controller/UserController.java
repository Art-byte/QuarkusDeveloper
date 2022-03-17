package com.artbyte.controller;

import java.util.List;

import javax.inject.Inject;
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

import com.artbyte.dao.UserDao;
import com.artbyte.model.User;

import org.bson.types.ObjectId;


@Path("/api/users")
public class UserController {

    @Inject
    private UserDao userDao;

    @GET
    public List<User> getUsers(){
        return userDao.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id){
        return userDao.findById(new ObjectId(id));
    }


    @POST
    public Response addUser(User user){
        userDao.persist(user);
        return Response.status(Response.Status.CREATED).build();
    }


    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(@PathParam("id") String id, User user){
        user.setId(new ObjectId(id));
        userDao.update(user);
        
    }


    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") String id){
        User user = userDao.findById(new ObjectId(id));
        userDao.delete(user);
    }
    
}
