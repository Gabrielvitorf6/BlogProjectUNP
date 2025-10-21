package br.edu.unipaulistana.backend.blog.domainmodel.repositories;

import br.edu.unipaulistana.backend.blog.domainmodel.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRepositoryImp implements UserRepositoryCustom <User, UUID> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<User> findByIdWithProfileAndPostsCriteria(UUID id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        Fetch<User, ?> profileFetch = root.fetch("profile", JoinType.LEFT);
        Fetch<User, ?> postsFetch = root.fetch("posts", JoinType.LEFT);

        criteriaQuery.select(root)
                .distinct(true)
                .where(criteriaBuilder.equal(root.get("id"), id));

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        List<User> users = query.getResultList();

        //retorna usuário por id com profile e posts
        return users.stream().findFirst();
    }

    @Override
    public List<User> findByMinRolesAndNameLikeCriteria(int minRoles, String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
    //raiz da query, recebe criteria.query(Indica que a query é de lá)

        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.size(root.get("roles")), minRoles),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name + "%")
                ))
                .orderBy(criteriaBuilder.asc(root.get("name")));

                //toda vez que faço root.get referencio objeto na query
//Fetch = join das tabelas
        //retorna usuário por minimo de roles e nome tipo
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }
}