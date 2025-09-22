package br.edu.unipaulistana.backend.blog.service;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import br.edu.unipaulistana.backend.blog.domainmodel.repositories.NonPersistentUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    //constructor that implements the list of users, named as repository
        private final NonPersistentUserRepository repository;
    @Override
    public List<User> findAll() {
        return this.repository.findAll();//As this metod doesn't exist, we need to create and implement this, in userrepository
        //Alt + Enter, and implement in user repository
    }

    @Override
    public User findbyID(UUID id) {
        return this.repository.findById(id);
    }

    @Override
    public void deleteByID(UUID id) {
        this.repository.deleteUserByID(id);
    }

    @Override
    public User create(User user) {
        if(user.getId() == null)
            user.setId(UUID.randomUUID());
        //The decision to search if the user already exists is a crucial business rule, and always exists in service folder or domain model
        //Everytime I do a checkage or update in the first few lines of a method, it's called defensive programming
        //RequestBody is needed because in any another system that access this API, we need to create a body with this object
        //Here we pass a user parameter because we're creating a user, and not only using ID
        return this.repository.create(user);
    }
}
