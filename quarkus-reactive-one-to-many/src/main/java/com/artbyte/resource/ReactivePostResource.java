package com.artbyte.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.artbyte.entity.Comment;
import com.artbyte.entity.Post;
import com.artbyte.utilmethods.UpdatePost;

import org.bson.types.ObjectId;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/posts")
public class ReactivePostResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Post> list() {
        return Post.streamAllPost();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Post> getByIdPost(@PathParam("id") String id) {
        return Post.findById(new ObjectId(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> addPost(Post post) {
        post.creationDate = LocalDateTime.now();
        return post.<Post>persist().map(v -> Response.created(URI.create("/posts/" + v.id.toString()))
                .entity(post).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Post> updatePost(@PathParam("id") String id, UpdatePost updatePost) {
        return Post.updatePost(id, updatePost);
    }


    @GET
    @Path("/search")
    public Uni<List<Post>> search(@QueryParam("author") String author, @QueryParam("title") String title,
            @QueryParam("dateFrom") String dateFrom, @QueryParam("dateTo") String dateTo) {
        if (author != null) {
            return Post.find("{'author': ?1, 'title' : ?2}", author, title).list();
        }
        return Post.find("{'creationDate': {$gte: ?1}, 'creationDate' : {$lte: ?2}}",
                ZonedDateTime.parse(dateFrom).toLocalDateTime(),
                ZonedDateTime.parse(dateTo).toLocalDateTime()).list();
    }


    @PUT
    @Path("/{id}/comment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addCommentToPost(@PathParam("id") String id, Comment comment){
        return Post.addCommentPost(comment, id).map(v -> Response.accepted(v).build());
    }
}
