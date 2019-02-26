package pl.tau.dbdemo.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.tau.dbdemo.domain.Person;
import java.sql.*;

import java.sql.SQLException;

@Ignore
@RunWith(JUnit4.class)
public class PersonManagerTest {
    PersonManager personManager;

    public PersonManagerTest() throws SQLException {
        String url = "jdbc:hsqldb:hsql://localhost/workdb";
        personManager = new PersonManagerImpl(DriverManager.getConnection(url));
    }

    @Test
    public void checkAdding() throws Exception {
        Person person = new Person();
        person.setName("Janek");
        person.setYob(1939);

        assertEquals(1, personManager.addPerson(person));
    }

}
