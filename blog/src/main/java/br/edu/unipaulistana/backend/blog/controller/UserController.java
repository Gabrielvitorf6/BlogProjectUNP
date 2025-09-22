package br.edu.unipaulistana.backend.blog.controller;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import br.edu.unipaulistana.backend.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//http://localhost:8080/users
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
        //use debug action in this celula and acess via browser previous link, to show all users
        //this is our first endpoint, is a web service that request something and response something
    }
    //htt://localhost:8080/users/{id}
    @GetMapping("/{id}")
    public User findUserbyID(@PathVariable UUID id) {
        //public user indicates that only one user is returned
        //@PathVariable indicates that is a variable from URL
        //spring is going to understand that is a variable and will search in method search
        //this variable will be placed in URL after /users

        return this.userService.findbyID(id);
    }
    //Indicates that function is going to be requested via delete
    //htt://localhost:8080/users/{id}
    //Delete anotation to delete objects or information in spring
    @DeleteMapping("/{id}")
    public void deleteUserbyID(@PathVariable UUID id){
        this.userService.deleteByID(id);
    }
    //Indicates that function is going to be requested via post
    //Method to create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userService.create(user);
    }

    }
