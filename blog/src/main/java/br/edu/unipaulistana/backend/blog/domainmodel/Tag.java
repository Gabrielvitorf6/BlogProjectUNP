package br.edu.unipaulistana.backend.blog.domainmodel;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_TAG", indexes = {@Index(name = "IDX_NAME", columnList = "name"),
@Index(name = "IDX_POST", columnList = "posts")})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter UUID id;

    @Column(nullable = false)
    private  @Getter @Setter String name;

    //arrumar
    @ManyToOne
    private  @Getter @Setter Set<Post> posts;


}
