package pl.tau.dbdemo.dao;

import pl.tau.dbdemo.dao.PersonDao;
import pl.tau.dbdemo.domain.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDaoJdbcImpl implements PersonDao
{

    public PreparedStatement preparedStatementGetAll;
    public PreparedStatement preparedStatementInsert;

    Connection connection;
    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        preparedStatementGetAll = connection.prepareStatement(
                "SELECT id, name, yob FROM Person ORDER BY id");
        preparedStatementInsert= connection.prepareStatement(
                "INSERT INTO Person (name, yob) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public List<Person> getAllPersons() {
        try {
            List<Person> ret = new LinkedList<>();
            ResultSet result = preparedStatementGetAll.executeQuery();
            while(result.next()) {
                Person p = new Person();
                p.setId(result.getLong("id"));
                p.setName(result.getString("name"));
                p.setYob(result.getInt("yob"));
                ret.add(p);
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addPerson(Person person) throws SQLException {
        preparedStatementInsert.setString(1, person.getName());
        preparedStatementInsert.setInt(2, person.getYob());
        int r = preparedStatementInsert.executeUpdate();
        return r;
    }
}
