package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.Comment;
import com.informatorio.myblog.model.User;
import com.informatorio.myblog.repository.CommentRepository;
import com.informatorio.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CommentController {

    //private Map<Integer, String> postRepository = inicializarRepo();
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    //GET Todos los Comentarios
    @GetMapping("/comment")
    public ResponseEntity<?> getComment() {
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    //GET comentario por id
    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentRepository.findById(id).get(), HttpStatus.OK);
    }

    //PUT modificar un Comentario
    @PutMapping("user/{user_id}/comment/{comment_id}")
    public ResponseEntity<?> editComment(@PathVariable Long comment_id, @RequestBody Comment comment) {
        Comment commentEdit = commentRepository.findById(comment_id).get();
        commentEdit.setComment(comment.getComment());
        return new ResponseEntity<>(commentRepository.save(commentEdit), HttpStatus.CREATED);
    }

    //POST Crear un Comentario
    @PostMapping("/user/{id_user}/comment")
    public ResponseEntity<?> createComment(@PathVariable Long id_user, @RequestBody Comment comment) {
        User user = userRepository.findById(id_user).get();
        comment.setUser(user);
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

}
