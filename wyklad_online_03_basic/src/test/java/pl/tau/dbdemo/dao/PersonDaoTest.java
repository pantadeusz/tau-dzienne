package pl.tau.dbdemo.dao;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;
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
public class PersonDaoTest {
    Logger LOGGER = Logger.getLogger(PersonDaoTest.class.getCanonicalName());
    public static String url = "jdbc:hsqldb:hsql://localhost/workdb";
    Connection connection;
    Random random;
    List<Person> initialDatabaseState;

    @Before
    public void setup() throws SQLException {
        random = new Random();
        connection = DriverManager.getConnection(url);
        initialDatabaseState = new LinkedList<>();
        try {
            connection.createStatement()
                    .executeUpdate("CREATE TABLE " +
                            "Person(id bigint GENERATED BY DEFAULT AS IDENTITY, "
                            + "name varchar(20) NOT NULL, yob integer)");

        } catch (SQLException e) {}
        PreparedStatement insert = connection.prepareStatement(
                "INSERT INTO Person (name, yob) VALUES (?, ?) ",
                Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < 10;i++) {
            Person person = new Person();
            person.setName("janek"+random.nextInt(1000));
            person.setYob(random.nextInt(50)+1950);
            insert.setString(1, person.getName());
            insert.setInt(2, person.getYob());
            insert.executeUpdate();
            ResultSet keys = insert.getGeneratedKeys();
            if (keys .next()) {
                person.setId(keys.getLong(1));
            }
            LOGGER.log(Level.INFO,"inserted "+ person);
            initialDatabaseState.add(person);
        }
    }

    @Test
    public void setConnectionCheck() throws SQLException {
        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        assertNotNull(dao.getConnection());
        assertEquals(dao.getConnection(), connection);
    }

    @Test
    public void setConnectionCreatesQueriesCheck() throws SQLException {
        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        assertNotNull(dao.preparedStatementGetAll);
    }

    @Test
    public void getAllCheck() throws SQLException {
        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        List<Person> retrievedPersons = dao.getAllPersons();
        assertThat(retrievedPersons, equalTo(initialDatabaseState));
    }
    @Test
    public void getAllWithBadDatabaseCheck() throws SQLException {
        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        try {
            connection.createStatement()
                    .executeUpdate("DROP TABLE Person");

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Could not drop table " + e.getMessage());
        }
        List<Person> retrievedPersons = dao.getAllPersons();
        assertNull(retrievedPersons);
    }

    @After
    public void cleanup() throws SQLException{
        Connection connection = DriverManager.getConnection(url);
        try {
            connection.prepareStatement("DELETE FROM Person").executeUpdate();
        } catch (Exception e) {
        }
    }
}
