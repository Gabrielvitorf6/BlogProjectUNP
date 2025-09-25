package br.edu.unipaulistana.backend.blog.controller;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import br.edu.unipaulistana.backend.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/users
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
       return   ResponseEntity.ok(this.userService.findAll());
        //use debug action in this celula and access via browser previous link, to show all users
        //this is our first endpoint, is a web service that request something and response something
    }

    //htt://localhost:8080/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> findUserbyID(@PathVariable UUID id) {
        //public user indicates that only one user is returned
        //@PathVariable indicates that is a variable from URL
        //spring is going to understand that is a variable and will search in method search
        //this variable will be placed in URL after /users

        //Now doing integration tests, we see that HTTP response is 200(Ok response) and not 201(Created response)
        //To solve this we use ResponseEntity class, that show us a full HTTP response send to client when this method is invocated
        //Using this is important when we create end points, to send full responses to various cases
        return ResponseEntity.ok(this.userService.findbyID(id));
    }

    //Indicates that function is going to be requested via delete
    //htt://localhost:8080/users/{id}
    //Delete anotation to delete objects or information in spring
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserbyID(@PathVariable UUID id) {
        this.userService.deleteByID(id);
        return ResponseEntity.notFound().build();
        //We don't return anything because this method only delete, so we can delete normally but nothing can be returned
    }

    //Indicates that function is going to be requested via post
    //Method to create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
    }

    //We create a user normally, but we don't pass anything through ResponseEntity, we just say that it's a create http request
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> patchUser(@RequestBody User user){
        return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
    }

    }
