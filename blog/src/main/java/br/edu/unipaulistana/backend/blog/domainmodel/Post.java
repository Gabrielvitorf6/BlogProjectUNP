package br.edu.unipaulistana.backend.blog.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name =  "TBL_POST", indexes = {@Index(name = "IDX_TITLE", columnList = "title"),
@Index(name = "IDX_CONTENT", columnList = "content"),
@Index(name = "IDX_USER", columnList = "users"),
@Index(name = "IDX_TAG", columnList = "tags")})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private @Getter @Setter UUID id;

    @Column(nullable = false)
    private  @Getter @Setter String title;

    @Column(nullable = false)
    private  @Getter @Setter String content;

    @OneToOne
    @JoinColumn(name = "user_id")
    private  @Getter @Setter User users;


    //private @Getter @Setter Set<Tag> tags;


}
