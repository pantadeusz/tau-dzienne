package pl.puzniakowski.tau;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import pl.puzniakowski.tau.cycles.CyclesTest;
import pl.puzniakowski.tau.cycles.OtherTest;

@RunWith(Suite.class)
@SuiteClasses({CyclesTest.class, OtherTest.class})
public class GraphLibSuite {

}

