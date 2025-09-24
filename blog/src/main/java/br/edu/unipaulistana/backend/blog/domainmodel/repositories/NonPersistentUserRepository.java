package br.edu.unipaulistana.backend.blog.domainmodel.repositories;

import br.edu.unipaulistana.backend.blog.domainmodel.User;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

//Inform Spring that is going to be injected in another place
@Component
public class NonPersistentUserRepository {
    //Create list of users named as internalState
    //Linked List is a list where all information is linked with previous info and subsequent info , like a chain
    private List<User> internalState = new LinkedList<>();

    //create a constructor, ALL CLASSES NEED A CONSTRUCTOR, this is where all information are placed automatically
    public NonPersistentUserRepository() {
        Faker faker = new Faker();//loop to create 100 users
        for (int i=0; i<100;i++){
            User user = new User(
                    UUID.randomUUID(),//create id for users using UUID, that is almost impossible to repeat
                    faker.name().fullName(),
                    faker.internet().emailAddress(),//inside internet package, create valid emails
                    faker.internet().password(),//inside internet package, create vali passwords
                    null,//for now, both campuses are null, later add roles and profiles
                    null
            );
            this.internalState.add(user);
        }
    }

    public List<User> findAll() {
            return this.internalState.stream().toList();
            //To not use original version of users, use .stream.tolist. to create a copy of internalstate

    }

    public User findById(UUID id) {
        //For each user from variable type User inside internalState
        //If user.getId is the same id in internalState
        for(User user : this.internalState) {
            if (user.getId().equals(id))
                return user;
        }
        //if any user was not found, return null and a message
            return null;
    }
    //The code below is following above logic, but to be more fluent use warning from IDE to automatically create the code
    //public void removeByID(UUID id) {
        //for(User user : this.internalState){
            //if(user.getId().equals(id))
                //this.internalState.remove(user)

    public void deleteUserByID(UUID id) {
        this.internalState.removeIf(user -> user.getId().equals(id));
        //Inside users, remove if received id is the same as id from repository
        //Need to test this in terminal, because browser cant handle post requests easily
        }

    public User create(User user) {
        this.internalState.add(user);
        return user;
    }
    public User update(User user) {
        this.internalState.remove(user);
        this.internalState.add(user);
        return user;
        //On update, we simply exclude the user and add with the same id
    }
}

