package pl.puzniakowski.shdemo.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.puzniakowski.shdemo.domain.Person;
import pl.puzniakowski.shdemo.service.LibraryManager;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan({"pl.puzniakowski"})
//@PropertySource("classpath:jdbc.properties")
//@Rollback
@AutoConfigureMockMvc
public class PersonControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryManager service;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(mockMvc);
    }

    @Test
    public void greetingShouldReturnHelloMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));
    }


    @Test
    public void getAllShouldReturnEmptyResults() throws Exception {
        when(service.findAllClients()).thenReturn(new LinkedList<Person>());
        this.mockMvc.perform(get("/persons"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllShouldReturnSomeResults() throws Exception {
        List<Person> expectedResult = new LinkedList<Person>();
        Person np = new Person();
        np.setId(123l);
        np.setFirstName("Waclawa");
        expectedResult.add(np);
        when(service.findAllClients()).thenReturn(expectedResult);
        this.mockMvc.perform(get("/persons"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":123,\"firstName\":\"Waclawa\"}]"));
    }
    @Test
    public void postNewPersonShouldReallyAddItToDatabase() throws Exception {
        Person p = new Person();
        p.setFirstName("Nowislaw");
        when(service.addClient(p)).thenReturn(2l);
        this.mockMvc.perform(post("/person")
                    .content("{\"firstName\":\"Nowislaw\"}")
                    .contentType("application/json"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nowislaw")))
                .andExpect(content().string(containsString("\"id\":2")));
        p.setId(2l);
        verify(service).addClient(p);
    }
}
