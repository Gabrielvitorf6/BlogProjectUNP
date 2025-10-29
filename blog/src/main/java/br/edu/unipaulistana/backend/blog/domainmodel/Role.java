package br.edu.unipaulistana.backend.blog.domainmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_ROLE", indexes = {@Index(name = "IDX_BIO", columnList = "bio"),
@Index(name = "IDX_PICTUREURL", columnList = "pictureUrl"),
@Index(name = "IDX_USER", columnList = "users")
})

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private @Getter @Setter UUID id;

    @Column(nullable = false)
        private  @Getter @Setter String bio;

    @Column(nullable = false, unique = true)
        private  @Getter @Setter String pictureUrl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private @Getter @Setter Set<User> users = new HashSet<>();

}
