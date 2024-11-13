package com.proyecto.SazonIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import com.proyecto.SazonIA.model.Post;
import com.proyecto.SazonIA.service.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    // Consulta para obtener publicaciones aleatorias
    @QueryMapping
    public List<Post> getRandomPosts(@Argument int count) {
        return postService.getRandomPosts(count);
    }

    // Consulta para obtener una publicación por ID
    @QueryMapping
    public Post getPostById(@Argument String postId) {
        return postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + postId));
    }

    // Consulta para obtener publicaciones de un usuario con paginación
    @QueryMapping
    public List<Post> getPostsByUser(@Argument Integer userId, @Argument int page, @Argument int pageSize) {
        return postService.getPostsByUser(userId, page, pageSize);
    }

    // Mutación para crear una nueva publicación
    @MutationMapping
    public Post createPost(@Argument Integer userId, @Argument String title, @Argument String content) {
        return postService.createPost(userId, title, content);
    }

    // Mutación para actualizar una publicación
    @MutationMapping
    public Post updatePost(@Argument String postId, @Argument String title, @Argument String content) {
        return postService.updatePost(postId, title, content);
    }

    // Mutación para eliminar una publicación
    @MutationMapping
    public String deletePost(@Argument String postId) {
        boolean success = postService.deletePost(postId);
        return success ? "Post deleted successfully" : "Post not found or could not be deleted";
    }
}
