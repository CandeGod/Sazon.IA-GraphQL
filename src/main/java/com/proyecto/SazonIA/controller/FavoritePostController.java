package com.proyecto.SazonIA.controller;

import com.proyecto.SazonIA.model.FavoritePost;
import com.proyecto.SazonIA.model.FavoritePostId;
import com.proyecto.SazonIA.model.Post;
import com.proyecto.SazonIA.service.FavoritePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;

@Controller
public class FavoritePostController {

    @Autowired
    private FavoritePostService favoritePostService;

    // Consulta para obtener el contenido de una publicación favorita de un usuario
    @QueryMapping
    public Post getContentFavoritePostByUserIdAndPostId(
            @Argument Integer userId, 
            @Argument String postId) {
        return favoritePostService.getContentFavoritePostByUserIdAndPostId(userId, postId);
    }

    // Consulta para obtener todas las publicaciones favoritas de un usuario con paginación
    @QueryMapping
    public Page<FavoritePost> getFavoritePostsByUserId(
            @Argument Integer userId, 
            @Argument int page, 
            @Argument int pageSize) {
        // Pasar los parámetros directamente al servicio
        return favoritePostService.getFavoritePostsByUserId(userId, page, pageSize);
    }

    // Mutación para guardar una publicación como favorita
    @MutationMapping
    public FavoritePost saveFavoritePost(@Argument Integer userId, @Argument String postId) {
        FavoritePost favoritePost = new FavoritePost(new FavoritePostId(userId, postId));
        return favoritePostService.saveFavoritePost(favoritePost);
    }

    // Mutación para eliminar una publicación de favoritos
    @MutationMapping
    public String removeFavoritePost(@Argument Integer userId, @Argument String postId) {
        favoritePostService.removeFavoritePost(userId, postId);
        return "Favorite post removed successfully";
    }
}
