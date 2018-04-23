package pl.tau.dbdemo.repository;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.tau.dbdemo.domain.Person;

public interface PersonRepository {
	public Connection getConnection();

	public void setConnection(Connection connection) throws SQLException;
	public int addPerson(Person person);
	public int deletePerson(Person person);
	public int updatePerson(Person person) throws SQLException;
	public Person getPerson(long id) throws SQLException;
	public String introduceSelf();
	public int deleteAll();
	public List<Person> getAllPersons();
}
