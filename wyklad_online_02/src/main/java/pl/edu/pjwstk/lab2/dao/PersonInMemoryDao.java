package pl.edu.pjwstk.lab2.dao;

import pl.edu.pjwstk.lab2.domain.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonInMemoryDao implements Dao<Person> {
    protected Map<Long,Person> persons;

    @Override
    public Optional<Person> get(Long id) {
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public void save(Person o) {

    }

    @Override
    public void update(Person o) throws IllegalArgumentException {
        if (!persons.containsKey(o.getId()))
            throw new IllegalArgumentException("Key does not exist");
        persons.put(o.getId(), o);
    }

    @Override
    public void delete(Person o) {

    }
}
