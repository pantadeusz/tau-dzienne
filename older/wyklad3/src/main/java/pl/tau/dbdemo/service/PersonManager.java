package pl.tau.dbdemo.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import pl.tau.dbdemo.domain.Person;

public interface PersonManager {
	public Connection getConnection();

	public void setConnection(Connection connection) throws SQLException;

	public int addPerson(Person person);

	public List<Person> getAllPersons();
}
