package br.edu.unipaulistana.backend.blog.domainmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor

public class User {
    private @Getter @Setter UUID id;
    private @Getter @Setter String nome;
    private @Getter @Setter String email;
    private @Getter @Setter String password;
    private @Getter @Setter List<Role> roles;
    private @Getter @Setter Profile profile;

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
