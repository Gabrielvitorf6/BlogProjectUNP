package br.edu.unipaulistana.backend.blog.service;

import br.edu.unipaulistana.backend.blog.domainmodel.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    List<User> findAll();

    User findUserByID(UUID id);

    void deleteByID(UUID id);

    User create(User user);

    User update(User user);

    User partUpdate(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findUserByName(String name);

    Optional<User> findUserByNameStartingWithAndEnding(String name1, String name2);

    List<User> findUserByMinRolesAndNameLike(int minRoles, String name);

    Optional<User> findByIdWithProfilesAndRoles(UUID id);

    List<User> findUserByMinPostsAndNameLike(int minPosts, String name);

    //The type of information returned by method is placed right before the name, that's why only delete is set as void
    //Delete don't return anything
    //findall is set as a list of users because it's return more than one user
}
