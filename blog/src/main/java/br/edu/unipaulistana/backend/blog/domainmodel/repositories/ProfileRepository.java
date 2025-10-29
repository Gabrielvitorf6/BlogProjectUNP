package br.edu.unipaulistana.backend.blog.domainmodel.repositories;

import br.edu.unipaulistana.backend.blog.domainmodel.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Post, UUID> {
}
