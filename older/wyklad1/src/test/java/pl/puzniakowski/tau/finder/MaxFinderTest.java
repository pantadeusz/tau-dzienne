package pl.puzniakowski.tau.finder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class MaxFinderTest {
    @Test
    public void testFindMaxInArray() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(10);
        arr.add(100);
        MaxFinder finder = new MaxFinder(arr);
        Integer result = finder.findMaxInArrray();
        assertNotNull(finder);
        assertEquals(result, new Integer(100));
    }
}