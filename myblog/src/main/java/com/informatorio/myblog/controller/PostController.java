package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.Post;
import com.informatorio.myblog.model.User;
import com.informatorio.myblog.repository.PostRepository;
import com.informatorio.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    //private Map<Integer, String> postRepository = inicializarRepo();
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    //GET Todos los POST
    @GetMapping("/post")
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getPosts(@PathVariable Long id) {
        return new ResponseEntity<>(postRepository.findById(id).get(), HttpStatus.OK);
    }

    //POST Crear un POST
    @PostMapping("/user/{id_user}/post")
    public ResponseEntity<?> createComment(@PathVariable Long id_user, @RequestBody Post post) {
        User user = userRepository.findById(id_user).get();
        post.setUser(user);
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/post/{post_id}")
    public ResponseEntity<?> editPost(@PathVariable Long post_id, @Valid @RequestBody Post post) {
        Post postEdit = postRepository.findById(post_id).get();
        postEdit.setTitle(post.getTitle());
        postEdit.setContent(post.getContent());
        postEdit.setPublished(post.getPublished());
        postEdit.setDescription(post.getDescription());
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
