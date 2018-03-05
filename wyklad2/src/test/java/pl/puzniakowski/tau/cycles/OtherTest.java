package pl.puzniakowski.tau.cycles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class OtherTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 100 }, { 1000 }, { 10000 } });
    }

    int s; ///< size of the test array
    public OtherTest(int e) {
        s = e;
    }

    @Test
    public void checkAdvancedCycle() {
        ArrayList<Integer> l = new ArrayList<Integer>(s);
        for (int i = 0; i < s; i++) {
            l.add((i + 1) % s);
        }
        int v = Cycles.maxCycleSize(l);
        assertThat(v, is(s));
    }
}