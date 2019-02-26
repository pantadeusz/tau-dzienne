package pl.edu.pjatk.tau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class VectorsTest {
    Vectors vec;

    @Before
    public void init() {
       vec = new Vectors();
    }

    @Test
    public void vectorsClassExistsCheck() {
        assertNotNull(vec);
    }

    @Test
    public void calculateLengthCheck() {
        List<Double> v = new ArrayList<Double>();
        v.add(3.0);
        v.add(4.0);
        double l = vec.calculateLength(v);
        assertEquals(5.0, l,0.001);
    }
}