package pl.tau.dbdemo.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.tau.dbdemo.domain.Person;
import java.sql.*;

import java.sql.SQLException;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonManagerMockedTest {
    PersonManager personManager;

    @Mock
    Connection connectionMock;

    @Mock
    PreparedStatement insertStatementMock;

    @Mock
    PreparedStatement selectStatementMock;



    @Before
    public void setupDatabase() throws SQLException {

        when(connectionMock.prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)"))
                .thenReturn(insertStatementMock);
        when(connectionMock.prepareStatement("SELECT id, name, yob FROM Person")).thenReturn(selectStatementMock);
        personManager = new PersonManagerImpl();
        personManager.setConnection(connectionMock);
        verify(connectionMock).prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)");
        verify(connectionMock).prepareStatement("SELECT id, name, yob FROM Person");
    }

    @Test
    public void checkAdding() throws Exception {
        when(insertStatementMock.executeUpdate()).thenReturn(1);

        Person person = new Person();
        person.setName("Waclaw");
        person.setYob(1980);
        assertEquals(1, personManager.addPerson(person));
        verify(insertStatementMock, times(1)).setString(1, "Waclaw");
        verify(insertStatementMock, times(1)).setInt(2, 1980);
        verify(insertStatementMock).executeUpdate();
    }

    abstract class AbstractResultSet implements ResultSet {
        int i = 0;

        @Override
        public int getInt(String s) throws SQLException {
            return 1;
        }
        @Override
        public String getString(String columnLabel) throws SQLException {
            return "Majran";
        }
        @Override
        public boolean next() throws SQLException {
            if (i == 1)
                return false;
            i++;
            return true;
        }
    }

    @Test
    public void checkGetting() throws Exception {
        AbstractResultSet mockedResultSet = mock(AbstractResultSet.class);
        when(mockedResultSet.next()).thenCallRealMethod();
        when(mockedResultSet.getInt("id")).thenCallRealMethod();
        when(mockedResultSet.getString("name")).thenCallRealMethod();
        when(mockedResultSet.getInt("yob")).thenCallRealMethod();
        when(selectStatementMock.executeQuery()).thenReturn(mockedResultSet);

        assertEquals(1, personManager.getAllPersons().size());

        verify(selectStatementMock, times(1)).executeQuery();
        verify(mockedResultSet, times(1)).getInt("id");
        verify(mockedResultSet, times(1)).getString("name");
        verify(mockedResultSet, times(1)).getInt("yob");
        verify(mockedResultSet, times(2)).next();
    }

    @Test
    public void checkAddingInOrder() throws Exception {
        InOrder inorder = inOrder(insertStatementMock);
        when(insertStatementMock.executeUpdate()).thenReturn(1);

        Person person = new Person();
        person.setName("Waclaw");
        person.setYob(1980);
        assertEquals(1, personManager.addPerson(person));

        inorder.verify(insertStatementMock, times(1)).setString(1, "Waclaw");
        inorder.verify(insertStatementMock, times(1)).setInt(2, 1980);
        inorder.verify(insertStatementMock).executeUpdate();
    }

    @Test(expected = IllegalStateException.class)
    public void checkExceptionWhenAddingNullAdding() throws Exception {
        when(insertStatementMock.executeUpdate()).thenThrow(new SQLException());
        Person person = new Person();
        person.setName(null); // ten null!
        person.setYob(1980);
        assertEquals(1, personManager.addPerson(person));
    }
}
