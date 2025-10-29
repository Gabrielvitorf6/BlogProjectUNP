package br.edu.unipaulistana.backend.blog.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_PROFILE", indexes = {@Index(name = "IDX_BIO", columnList = "bio"),
@Index(name = "IDX_PICTUREURL", columnList = "pictureUrl"),
@Index(name = "IDX_USER", columnList = "user")})
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter UUID id;

    @Column(nullable = false)
    private  @Getter @Setter String bio;

    @Column(nullable = false)
    private  @Getter @Setter String pictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    private  @Getter @Setter User user;
}
