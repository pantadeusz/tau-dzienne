package pl.edu.pjwstk.lab2.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import pl.edu.pjwstk.lab2.domain.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonInMemoryDaoTest {

    PersonInMemoryDao dao;

    @Before
    public void setup() {
        Person p1 = new Person();
        Person p2 = new Person();
        p1.setId(1L);
        p1.setName("Janusz");
        p2.setId(2L);
        p2.setName("Grażyna");
        dao = new PersonInMemoryDao();
        dao.persons = new HashMap<Long, Person>();
        dao.persons.put(1L,p1);
        dao.persons.put(2L,p2);
    }

    @Test
    public void createDaoObjectTest() {
        assertNotNull(dao);
    }

    @Test
    public void getPersonThatExistsTest() {
        Optional<Person> p = dao.get(2L);
        assertThat(p.get().getName(), is("Grażyna"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNotExisitingPersonShouldThrowTest() {
        Person p1 = new Person();
        p1.setId(9);
        p1.setName("Xyz");
        dao.update(p1);
    }

    @Test
    public  void updateOneRecordTest() {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("Sebastian");
        Person p2 = new Person();
        p2.setId(2);
        p2.setName("Grażyna");

        Collection<Person> listExpected = dao.persons.values();
        for (Person p:listExpected) if (p.getId()==1) p.setName("Sebastian");

        dao.update(p1);

        Collection<Person> listAfter = dao.persons.values();
        assertArrayEquals(listExpected.toArray(), listAfter.toArray());
    }





}
