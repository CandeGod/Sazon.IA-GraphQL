package com.proyecto.SazonIA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.proyecto.SazonIA.exception.PostNotFoundException;
import com.proyecto.SazonIA.model.Post;
import com.proyecto.SazonIA.model.User;
import com.proyecto.SazonIA.repository.CommentPostRepository;
import com.proyecto.SazonIA.repository.PostRepository;
import com.proyecto.SazonIA.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository; // Repositorio de MySQL para User

    @Autowired
    private PostRepository postRepository; // Repositorio de MongoDB para Post

    @Autowired
    private CommentPostRepository commentPostRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // Método para obtener publicaciones aleatorias
    public List<Post> getRandomPosts(int count) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.sample(count));

        AggregationResults<Post> results = mongoTemplate.aggregate(aggregation, "Posts", Post.class);
        return results.getMappedResults();
    }

    // Obtener publicaciones de un usuario con paginación
    public List<Post> getPostsByUser(Integer userId, int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Post> postsPage = postRepository.findByUserId(userId, pageReq);
        return postsPage.getContent();
    }

    // Obtener una publicación por ID
    public Optional<Post> getPostById(String postId) {
        return postRepository.findById(postId);
    }

    // Crear una nueva publicación
    public Post createPost(Integer userId, String title, String content/* , List<String> mediaUrls */) {
        // Verificar si el usuario existe en MySQL
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Crea y guarda el Post en MongoDB con el userId de MySQL
        Post post = new Post(user.getUserId(), title, content);
        // post.setMediaUrls(mediaUrls); // Establecer mediaUrls
        post.setPostId(UUID.randomUUID().toString()); // Generar el ID antes de guardar
        return postRepository.save(post);
    }

    // Actualizar una publicación existente
    public Post updatePost(String postId, String title, String content) {
        Optional<Post> existingPost = postRepository.findById(postId);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setTitle(title);
            post.setContent(content);
            return postRepository.save(post);
        }
        throw new PostNotFoundException(postId); // Lanza la excepción si el post no existe
    }

    // Eliminar una publicación
    public boolean deletePost(String postId) {
        if (postRepository.existsById(postId)) {
            // Eliminar comentarios relacionados
            commentPostRepository.deleteByPostId(postId); // Elimina comentarios por postId
            postRepository.deleteById(postId);
            return true;
        }
        return false; // Maneja el caso donde el post no existe
    }
}
