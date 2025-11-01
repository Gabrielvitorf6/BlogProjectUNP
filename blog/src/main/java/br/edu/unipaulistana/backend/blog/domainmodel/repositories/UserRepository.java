package br.edu.unipaulistana.backend.blog.domainmodel.repositories;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends
        JpaRepository<User, UUID>,
        UserRepositoryCustom<User , UUID>
    //QuerydslPredicateExecutor<User>
{
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String username);

    Optional<User> findByNameStartingWithAndNameEndingWith(String name1, String name2);

    //
    @Query("""
    SELECT DISTINCT u FROM User u LEFT JOIN fetch u.profile
    left join fetch u.roles
    WHERE u.id = :id
""")
    Optional<User> findByIdWithProfileAndRoles(@Param("id") UUID id);

    @Query("""
    select u from User u where size(u.roles) >= : minRoles
    and lower(u.name) like lower(concat('%',:namePart, '%') )
    order by u.name asc
""")
    List<User> findMinPostAndNameLike(@Param("minPosts")int minPosts, @Param("namePart")String namePart);
}
