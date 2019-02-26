package pl.tau.dbdemo;

import java.sql.DriverManager;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import pl.tau.dbdemo.repository.PersonDbunitTest;
import pl.tau.dbdemo.repository.PersonRepositoryJdbcImpl;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        PersonDbunitTest.class
})
public class PersonTests {
    @BeforeClass
    public static void before() throws Exception {
          String url = "jdbc:hsqldb:hsql://localhost/workdb";

        new PersonRepositoryJdbcImpl(DriverManager.getConnection(url));
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.hsqldb.jdbcDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:hsqldb:hsql://localhost/workdb" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "sa" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "" );

        JdbcDatabaseTester databaseTester = new PropertiesBasedJdbcDatabaseTester();

        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(
            PersonTests.class.getClassLoader().
                        getResource("ds-0.xml").openStream()
        );
        
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @AfterClass
    public static void after() {
    }

}
