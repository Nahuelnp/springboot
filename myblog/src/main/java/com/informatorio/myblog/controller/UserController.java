package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.User;
import com.informatorio.myblog.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //GET Todos los Usuarios
    @GetMapping
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    //GET Usuario by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/filter/{city}")
    public ResponseEntity<?> getUserCity(@PathVariable String city) {
        return new ResponseEntity<>(userRepository.findAllByCity(city), HttpStatus.OK);
    }

    @GetMapping("/dateFilter/{date}")
    public List<User> getUserDate(@PathVariable(value = "date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return userRepository.findAllByRegistDateIsAfter(date);
    }

    //Post Crear un Usuario
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> editUser(@PathVariable Long user_id, @Valid @RequestBody User user) {
        User userEdit = userRepository.findById(user_id).get();
        userEdit.setName(user.getName());
        userEdit.setSurname(user.getSurname());
        userEdit.setCity(user.getCity());
        userEdit.setCountry(user.getCountry());
        userEdit.setProvince(user.getProvince());
        userEdit.setEmail(user.getEmail());
        return new ResponseEntity<>(userRepository.save(userEdit), HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long user_id) {
        User userDelete = userRepository.getOne(user_id);
        userRepository.delete(userDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
