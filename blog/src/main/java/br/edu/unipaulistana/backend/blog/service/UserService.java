package br.edu.unipaulistana.backend.blog.service;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface UserService {
    List<User> findAll();

    User findbyID(UUID id);

    void deleteByID(UUID id);

    User create(User user);
}
