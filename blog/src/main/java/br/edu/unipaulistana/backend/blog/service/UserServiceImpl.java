package br.edu.unipaulistana.backend.blog.service;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import br.edu.unipaulistana.backend.blog.domainmodel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    //constructor that implements the list of users,  named as repository
        private final UserRepository repository;
    @Override
    public List<User> findAll() {
        return this.repository.findAll();//As this method doesn't exist, we need to create and implement this, in repository
        //Alt + Enter, and implement in user repository
    }

    @Override
    public User findUserByID(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public void deleteByID(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public User create(User user) {
        if(user.getId() == null)
            user.setId(UUID.randomUUID());
        //Everytime I do a checkage or update in the first few lines of a method, it's called defensive programming
        //The decision to search if the user already exists is a crucial business rule, and always exists in service folder or domain model
        //RequestBody is needed because in any another system that access this API, we need to create a body with this object to pass through json
        //Here we pass a user parameter because we're creating a user, and not only using ID
        return this.repository.save(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() == null)
            user.setId(UUID.randomUUID());
        return this.repository.save(user);
    }

    @Override
    public User partUpdate(User user) {
        return this.repository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return this.repository.findByName(name);
    }

    @Override
    public Optional<User> findUserByNameStartingWithAndEnding(String name1, String name2) {
        return this.repository.findByNameStartingWithAndNameEndingWith(name1, name2);
    }

    @Override
    public List<User> findUserByMinRolesAndNameLike(int minRoles, String name) {
        return this.repository.findByMinRolesAndNameLikeCriteria(minRoles, name);
    }

    @Override
    public Optional<User> findByIdWithProfilesAndRoles(UUID id) {
        return this.repository.findByIdWithProfileAndRoles(id);
    }

    @Override
    public List<User> findUserByMinPostsAndNameLike(int minPosts, String name) {
        return this.repository.findMinPostAndNameLike(minPosts, name);
    }
}
