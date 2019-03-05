package pl.edu.pjwstk.lab2.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonTest {
    @Test
    public void createObjectTest() {
        Person p = new Person();
        assertNotNull(p);
    }

    @Test
    public void personGettersAndSettersTest() {
        Person p = new Person();
        p.setId(1);
        p.setName("Janek");
        assertEquals(1, p.getId());
        assertEquals("Janek", p.getName());
    }
}
