package com.artbyte.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.artbyte.utilmethods.UpdatePost;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

public class Post extends ReactivePanacheMongoEntity {

    public String title;
    public String content;
    public String author;
    public LocalDateTime creationDate;
    public List<Comment> comments;

    // GetAll
    public static Multi<Post> streamAllPost() {
        return streamAll();
    }

    // POST
    public static Uni<Post> addCommentPost(Comment comment, String postId) {
        Uni<Post> postUni = findById(new ObjectId(postId));
        return postUni.onItem().transform(post -> {
            if (post.comments == null) {
                post.comments = List.of(comment);
            } else {
                post.comments.add(comment);
            }
            comment.creationDate = LocalDateTime.now();
            comment.postId = postId;
            return post;
        }).call(post -> comment.persist().chain(() -> post.persistOrUpdate()));
    }


    // PUT
    public static Uni<Post> updatePost(String id, UpdatePost updatePost) {
        Uni<Post> postUni = findById(new ObjectId(id));
        return postUni.onItem().transform(post -> {
            post.content = updatePost.getContent();
            post.title = updatePost.getTitle();
            return post;
        }).call(post -> post.persistOrUpdate());
    }

    
    // DELETE
    public static Uni<Void> deletePost(String postId) {
        Uni<Post> postUni = findById(new ObjectId(postId));
        Multi<Comment> commentsUni = Comment.streamAllCommentsByPostId(postId);
        return postUni.call(post -> commentsUni.onItem().call(comment -> comment.delete())
                .collect().asList()).chain(post -> {
                    if (post == null) {
                        throw new NotFoundException();
                    }
                    return post.delete();
                });
    }
}
