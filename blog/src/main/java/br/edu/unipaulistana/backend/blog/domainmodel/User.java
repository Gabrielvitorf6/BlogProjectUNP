package br.edu.unipaulistana.backend.blog.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_USERS", indexes = {@Index(name = "IDX_NAME_PASS", columnList = "name , password"),
        @Index(name = "IDX_EMAIL", columnList = "email"),
        @Index(name = "IDX_PASSWORD", columnList = "password"),
        @Index(name = "IDX_ROLE", columnList = "roles")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private @Getter
    @Setter UUID id;

    @Column( nullable = false)
    private @Getter
    @Setter String nome;

    @Column(nullable = false, unique = true, length = 40)
    private @Getter
    @Setter String email;

    @Column(nullable = false, length = 20)
    private @Getter
    @Setter String password;


    @ManyToMany
    @JoinTable(name = "tb_user_role",
            joinColumns = @JoinColumn(name  = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private@Getter @Setter Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private @Getter @Setter Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private  @Getter @Setter Post post;

    /*private @Getter @Setter List<Role> roles;
    private @Getter @Setter Profile profile; */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);

        //To update a user using the logic(Delete and add), we need to implement a hash code in user class
        //Alt Insert hash code in only UUID
        //Hash code is used as a .equals, to say that  object is the same as another, but with a faster form
    }
}
