package pl.puzniakowski;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TimeSourceTest {
    @Test
    public void timeSourceInterfaceHaveMethods() {
        TimeSource o = new TimeSource() {@Override
        public Date getCurrentTime() {
            return null;
        }
        };
        assertNotNull(o);
    }
}