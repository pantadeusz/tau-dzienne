package pl.tau.dbdemo.dao;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tau.dbdemo.domain.Person;
import java.sql.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;




@RunWith(MockitoJUnitRunner.class)
public class PersonDaoTest {
    Logger LOGGER = Logger.getLogger(PersonDaoTest.class.getCanonicalName());
    Random random;
    static List<Person> initialDatabaseState;


    /**
     * Tylko na potrzeby testów! Przygotujmy odpowiedni ResultSet.
     *
     * UWAGA: Moglibyśmy zaimplementować cały ResultSet, ale wtedy musimy przygotować wszystkie metody które są w nim zadeklarowane.
     */
    abstract class AbstractResultSet implements ResultSet {
        int i; // automatycznie bedzie 0

        @Override
        public int getInt(String s) throws SQLException {
            return initialDatabaseState.get(i-1).getYob();
        }
        @Override
        public long getLong(String s) throws SQLException {
            return initialDatabaseState.get(i-1).getId();
        }
        @Override
        public String getString(String columnLabel) throws SQLException {
            return initialDatabaseState.get(i-1).getName();
        }
        @Override
        public boolean next() throws SQLException {
            i++;
            if (i > initialDatabaseState.size())
                return false;
            else
                return true;
        }
    }

    // to będzie nasz Mock!!
    @Mock
    Connection connection;
    @Mock
    PreparedStatement selectStatementMock;
    @Mock
    PreparedStatement insertStatementMock;


    @Before
    public void setup() throws SQLException {
        random = new Random();
        initialDatabaseState = new LinkedList<>();
        for (int i = 0; i < 10;i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("janek"+random.nextInt(1000));
            person.setYob(random.nextInt(50)+1950);
            initialDatabaseState.add(person);
        }
        Mockito.when(
                connection.
                        prepareStatement("SELECT id, name, yob FROM Person ORDER BY id")
                ).thenReturn(selectStatementMock);
        Mockito.when(
                connection
                        .prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)",
                                Statement.RETURN_GENERATED_KEYS)
        ).thenReturn(insertStatementMock);
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
        // nagrajmy zachowanie mocka
        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        Mockito.verify(connection).prepareStatement("SELECT id, name, yob FROM Person ORDER BY id");
        verify(connection).prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        assertNotNull(dao.preparedStatementGetAll);
    }

    @Test
    public void getAllCheck() throws SQLException {
        // szykujemy mocki

        AbstractResultSet mockedResultSet = mock(AbstractResultSet.class);
        when(mockedResultSet.next()).thenCallRealMethod();
        when(mockedResultSet.getLong("id")).thenCallRealMethod();
        when(mockedResultSet.getString("name")).thenCallRealMethod();
        when(mockedResultSet.getInt("yob")).thenCallRealMethod();
        when(selectStatementMock.executeQuery()).thenReturn(mockedResultSet);

        // robimy test

        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        List<Person> retrievedPersons = dao.getAllPersons();
        assertThat(retrievedPersons, equalTo(initialDatabaseState));

        // weryfikujeymy wykonanie mockow

        verify(selectStatementMock, times(1)).executeQuery();
        verify(mockedResultSet, times(initialDatabaseState.size())).getLong("id");
        verify(mockedResultSet, times(initialDatabaseState.size())).getString("name");
        verify(mockedResultSet, times(initialDatabaseState.size())).getInt("yob");
        verify(mockedResultSet, times(initialDatabaseState.size()+1)).next();
    }

    @Test
    public void checkAddingInOrder() throws Exception {
        // przygotujmy mocki
        // tym razem weryfikujemy takze kolejnosc wykonania

        InOrder inorder = inOrder(insertStatementMock);
        when(insertStatementMock.executeUpdate()).thenReturn(1);

        // nasza testowana metoda

        PersonDaoJdbcImpl dao = new PersonDaoJdbcImpl();
        dao.setConnection(connection);
        Person person = new Person();
        person.setName("Waclaw");
        person.setYob(1980);
        dao.addPerson(person);

        // sprawdzamy wykonanie mocka

        inorder.verify(insertStatementMock, times(1)).setString(1, "Waclaw");
        inorder.verify(insertStatementMock, times(1)).setInt(2, 1980);
        inorder.verify(insertStatementMock).executeUpdate();
    }
}
