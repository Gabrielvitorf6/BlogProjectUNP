package br.edu.unipaulistana.backend.blog.domainmodel;


import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Tag {
    private @Getter @Setter Long id;
    private  @Getter @Setter String name;
    private  @Getter @Setter Set<Post> posts;

}
