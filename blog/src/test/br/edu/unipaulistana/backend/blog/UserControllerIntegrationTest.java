package br.edu.unipaulistana.backend.blog;

import br.edu.unipaulistana.backend.blog.controller.UserController;
import br.edu.unipaulistana.backend.blog.domainmodel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

//Test file
//We need to write @SpringBootTest annotation to inform spring that is a test class
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Random port deploy test in a random port, not the 8080 port we always use

@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    //We test all endpoints and folders, and it's called "Integration test", when we integrate all system at once and test
    //@Autowired = annotation to inject all classes
    @Autowired
    private UserController userController;

   @Autowired
    private MockMvc mockMvc;
   //Create false HTTPS requests

    @Autowired
    private ObjectMapper objectMapper;
    //Object mapper pass object to json and json to object

    @Test //We need to declare this method as method test //We use annotation @Test
    //Get test
    //We need an annotation to see how it's works inside IDE and describe it
    @DisplayName("Full flow : POST -> GET ALL -> GET_BY_ID -> PUT -> PATH -> GET -> ERROR404")
    public void fullFlow() throws Exception{
        UUID id = UUID.randomUUID();//Attributes id variable as UUID random id

        //We can use var type variable when we inside a method and using a class that don't have ambiguous
        //Here we pass all parameters from a user through a body
        var bodyCreate = new User(id, "Gabriel", "gabriel@gmail.com", "kurioso", null, null);
        var postResult = mockMvc.perform(        //perform an action
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)//content type -> mediatype -> APPLICATION_JSON
                                //Now we inform what content is inside our body
                                //And we pass the object that is written in Java and pass it to Json, and we use objectMapper class to do it(Object to Json and Json to object)
                                .content(objectMapper.writeValueAsString(bodyCreate)))//objectMapper convert our object(bodyCreate) to String value
                .andExpect(status().isCreated())//Expect is used to verify if a request was generated in right way, in our case, 201 request(Created)

                .andExpect(jsonPath("$.id", is(id)))//Expect my object.id = id
                // Json path is a consult language for json,used here to navigate trough json response object
                //$ character is used to start navigation in json root, in this case, we say "Go to json root, and search id property and return its value"

                .andExpect(jsonPath("$.name", is("Gabriel")))//Expect my object.name = name
                .andReturn();//Invocate and verify if result is the same as expected

        var created = objectMapper.readValue(postResult.getResponse().getContentAsByteArray(), User.class);//This method need a signature class, declared as .class
        var returnedId = created.getId();

    }
}
