package pl.tau.dbdemo.service;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
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
    Connection mockedConnection;

    @Mock
    PreparedStatement preparedInsert;
    @Mock
    PreparedStatement preparedSelect;

    @Before
    public void setupDatabase() throws SQLException {
        when(mockedConnection.prepareStatement("INSERT INTO Person (name, yob) VALUES (?, ?)"))
        .thenReturn(preparedInsert);
        when(mockedConnection.prepareStatement("SELECT id, name, yob FROM Person"))
        .thenReturn(preparedSelect);

        personManager = new PersonManagerImpl();
        personManager.setConnection(mockedConnection);
    }

    @Test
    public void checkAdding() throws Exception {
        when(preparedInsert.executeUpdate()).thenReturn(1);
        Person person = new Person();
        person.setName("Waclaw");
        person.setYob(1980);

        assertEquals(1, personManager.addPerson(person));

        verify(preparedInsert).setString(1,"Waclaw");
        verify(preparedInsert).setInt(2,1980);
        verify(preparedInsert).executeUpdate();
    }

}
