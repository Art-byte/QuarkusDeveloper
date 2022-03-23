package com.artbyte.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.artbyte.entity.Comment;
import com.artbyte.utilmethods.UpdateComment;

import org.bson.types.ObjectId;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/comments")
public class ReactiveCommentResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Comment> list() {
        return Comment.streamAllComments();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Comment> getComment(@PathParam("id") String id) {
        return Comment.findById(new ObjectId(id));
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Comment> updateComment(@PathParam("id") String id, UpdateComment updateComment) {
        return Comment.updateComment(id, updateComment);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> deleteComment(@PathParam("id") String id) {
        return Comment.deleteComment(id);
    }
}
