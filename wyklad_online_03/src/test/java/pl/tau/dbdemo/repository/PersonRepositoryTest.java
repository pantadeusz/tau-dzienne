package pl.tau.dbdemo.repository;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.tau.dbdemo.domain.Person;
import java.sql.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(JUnit4.class)
public class PersonRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(PersonRepositoryTest.class.getCanonicalName());
    public static String url = "jdbc:hsqldb:hsql://localhost/workdb";
    PersonRepository personManager;
    List<Person> expectedDbState;

    public PersonRepositoryTest() throws SQLException {
        personManager = new PersonRepositoryJdbcImpl(DriverManager.getConnection(url));
    }

    @BeforeClass
    public static void cleanupBeforeTests() throws SQLException{
        new PersonRepositoryJdbcImpl(DriverManager.getConnection(url)).
        deleteAll();
    }

    @Before
    public void setup() {
        Random rand = new Random();
        expectedDbState = new LinkedList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person("Matuzalem" + rand.nextInt(1000), 1000 + rand.nextInt(1000));
            LOGGER.log(Level.INFO,"ADDING with ID = " + p.getId());
            personManager.addPerson(p); // ustawione ID na to co dodano do bazy
            LOGGER.log(Level.INFO,"     + with ID = " + p.getId());
            expectedDbState.add(p);
        }
    }

    @After
    public void cleanup() {
        for (Person p : expectedDbState) {
            personManager.deletePerson(p);
        }
    }

    @Test
    public void checkAdding() throws Exception {
        Person person = new Person();
        person.setName("Janek");
        person.setYob(1939);

        assertEquals(1, personManager.addPerson(person));

        expectedDbState.add(person);
        assertThat(personManager.getAllPersons(), equalTo(expectedDbState));
    }

    @Test
    public void checkGetting() throws Exception {
        Person person = expectedDbState.get(7);
        assertEquals(person, personManager.getPerson(person.getId()));
    }

    @Test(expected = SQLException.class)
    public void checkDeleting() throws SQLException {
        Person p = expectedDbState.get(3);
        expectedDbState.remove(p);
        assertEquals(1, personManager.deletePerson(p));
        assertThat(personManager.getAllPersons(), equalTo(expectedDbState));
        assertNull(personManager.getPerson(p.getId()));
    }

    @Test()
    public void checkUpdatingSuccess() throws SQLException {
        Person p = expectedDbState.get(3);
        p.setName("Janusz");
        expectedDbState.set(3, p);
        assertEquals(1, personManager.updatePerson(p));
        assertThat(personManager.getAllPersons(), equalTo(expectedDbState));
    }

    @Test(expected = SQLException.class)
    public void checkUpdatingFailure() throws SQLException {
        Person p = new Person("Janusz",123);
        assertEquals(1, personManager.updatePerson(p));
//        assertThat(personManager.getAllPersons(), equalTo(expectedDbState));
    }

}
