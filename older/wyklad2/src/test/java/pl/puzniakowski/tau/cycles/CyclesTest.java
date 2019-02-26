package pl.puzniakowski.tau.cycles;

import static org.junit.Assert.*;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class CyclesTest {

    @BeforeClass
    public static void beforeClassCreation() {
        //System.out.println("Przed stworzeniem obiektu");
    }
    @AfterClass
    public static void afterClassDestruction() {
        //System.out.println("Po usunięciu obiektu");
    }
    @Before
    public void before() {
        //System.out.println("Przed testem");
    }
    @After
    public void after() {
        //System.out.println("Po tescie");
    }

    @Test
    public void checkMatchers() {
        String txt = "raz dwa trzy";
        assertThat("sprawdzamy czy ciag znakow zawiera tekst dwa", txt, containsString("dwa"));
        assertThat( txt, either(containsString("jeden")).or(containsString("raz")));
        assertThat( 4, is(4));
    }

    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Test
    public void checkSimpleCycle() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        l.add(1);
        l.add(0);
        int v = Cycles.maxCycleSize(l);
        assertThat(v, is(2));
    }

}