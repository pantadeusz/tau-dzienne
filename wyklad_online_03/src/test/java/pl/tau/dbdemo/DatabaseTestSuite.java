package pl.tau.dbdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.tau.dbdemo.dao.PersonDaoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses(PersonDaoTest.class)
public class DatabaseTestSuite {
}
