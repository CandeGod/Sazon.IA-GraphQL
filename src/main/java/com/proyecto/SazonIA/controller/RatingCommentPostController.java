package com.proyecto.SazonIA.controller;

import com.proyecto.SazonIA.model.RatingCommentPost;
import com.proyecto.SazonIA.service.RatingCommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

@Controller
public class RatingCommentPostController {

    @Autowired
    private RatingCommentPostService ratingCommentPostService;

    // Query para obtener una valoración de un comentario por su ID
    @QueryMapping
    public RatingCommentPost getRatingCommentById(@Argument String ratingId) {
        return ratingCommentPostService.getRatingById(ratingId).orElse(null);
    }

    // Mutation para crear una nueva valoración para un comentario
    @MutationMapping
    public RatingCommentPost createRatingComment(@Argument String commentId, @Argument Integer userId, @Argument Integer value) {
        return ratingCommentPostService.createRatingComment(commentId, userId, value);
    }

    // Mutation para actualizar una valoración existente de un comentario
    @MutationMapping
    public RatingCommentPost updateRatingComment(@Argument String ratingId, @Argument Integer value) {
        return ratingCommentPostService.updateRatingComment(ratingId, value);
    }

    // Mutation para eliminar una valoración de un comentario
    @MutationMapping
    public Boolean deleteRatingComment(@Argument String ratingId) {
        return ratingCommentPostService.deleteRatingComment(ratingId);
    }
}
