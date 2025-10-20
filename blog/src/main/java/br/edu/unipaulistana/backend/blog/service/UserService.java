package br.edu.unipaulistana.backend.blog.service;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface UserService {
    List<User> findAll();

    User findUserByID(UUID id);

    void deleteByID(UUID id);

    User create(User user);

    User update(User user);

    User partUpdate(User user);
    //The type of information returned by method is placed right before the name, that's why only delete is set as void
    //Delete don't return anything
    //findall is set as a list of users because it's return more than one user
}
