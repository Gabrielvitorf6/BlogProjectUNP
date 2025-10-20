package br.edu.unipaulistana.backend.blog.domainmodel.repositories;

import br.edu.unipaulistana.backend.blog.domainmodel.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryCustom <User,UUID>{
    Optional<br.edu.unipaulistana.backend.blog.domainmodel.User> findByIdWithProfileAndPostsCriteria(java.util.UUID id);

    List<br.edu.unipaulistana.backend.blog.domainmodel.User> findByMinRolesAndNameLikeCriteria(int minRoles, String name);
}
