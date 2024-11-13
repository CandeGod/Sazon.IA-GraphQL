package com.proyecto.SazonIA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import com.proyecto.SazonIA.model.CommentPost;
import com.proyecto.SazonIA.service.CommentPostService;

import java.util.List;

@Controller
public class CommentPostController {

    @Autowired
    private CommentPostService commentPostService;

    // Consulta para obtener comentarios paginados
    @QueryMapping
    public List<CommentPost> getCommentsByPostIdWithPagination(
            @Argument String postId, 
            @Argument int page, 
            @Argument int pageSize) {
        return commentPostService.getCommentsByPostId(postId, page, pageSize);
    }

    // Mutación para agregar un comentario
    @MutationMapping
    public CommentPost addCommentPost(@Argument String postId, @Argument CommentPost comment) {
        comment.setPostId(postId); // Establecer el postId desde la consulta GraphQL
        return commentPostService.addComment(comment);
    }

    // Mutación para editar un comentario
    @MutationMapping
    public CommentPost editCommentPost(
            @Argument String postId, 
            @Argument String commentId, 
            @Argument String content) {
        return commentPostService.editComment(postId, commentId, content);
    }

    // Mutación para eliminar un comentario
    @MutationMapping
    public String deleteCommentPost(@Argument String postId, @Argument String commentId) {
        boolean success = commentPostService.deleteComment(postId, commentId);
        return success ? "Comment deleted successfully" : "Comment not found or could not be deleted";
    }
}
