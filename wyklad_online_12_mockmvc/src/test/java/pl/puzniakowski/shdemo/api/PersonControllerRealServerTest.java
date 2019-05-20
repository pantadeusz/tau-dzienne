package pl.puzniakowski.shdemo.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;
import pl.puzniakowski.shdemo.domain.Book;
import pl.puzniakowski.shdemo.domain.Person;
import org.springframework.transaction.annotation.Transactional;
import pl.puzniakowski.shdemo.service.LibraryManager;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ComponentScan({"pl.puzniakowski"})
@PropertySource("classpath:jdbc.properties")
@ImportResource({"classpath:/beans.xml"})
@Rollback
//@Commit
//@Transactional ("txManager")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private PersonController controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    LibraryManager libraryManager; // manager is needed for direct manipulation with database


    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    public void greetingShouldReturnHelloMessage() throws Exception {
        assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello");
    }

    @Test
    public void getAllShouldReturnSomeResultsFromDatabase() throws Exception {
        assertThat(
                this.restTemplate.getForObject("http://localhost:" + port + "/persons",
                        List.class)).isNotNull();
    }


    @Test
    public void getAllShouldReturnResultsThatWerePreviouslyPutIntoDatabase() throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName("Restowy Rester");
        Long newId = libraryManager.addClient(newPerson);
        List<java.util.LinkedHashMap> personsFromRest =
                this.restTemplate.getForObject("http://localhost:" + port + "/persons", List.class);
        boolean found = false;
        for (LinkedHashMap p: personsFromRest) {
            if (p.get("id").toString().equals(newId.toString())) found = true;
        }
        assertTrue(found);
    }
}
