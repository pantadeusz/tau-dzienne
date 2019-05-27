package pl.puzniakowski.shdemo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.puzniakowski.shdemo.domain.Book;
import pl.puzniakowski.shdemo.domain.Person;
import pl.puzniakowski.shdemo.service.LibraryManager;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    LibraryManager libraryManager;

    @RequestMapping("/persons")
    public java.util.List<Person> getPersons() {
        List<Person> persons = new LinkedList<>();
        for (Person p : libraryManager.findAllClients()) {
            persons.add(p.clone());
        }
        return persons;
    }

    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public Person addClient(@RequestBody Person nperson) {
        nperson.setId(libraryManager.addClient(nperson));
        return nperson;
    }



    @RequestMapping("/")
    public String index() {
        return "Hello";
    }

    @RequestMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getPerson(@PathVariable("id") Long id) throws SQLException {
        return libraryManager.findClientById(id).clone();
    }

    @RequestMapping(value = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> getPersons(@RequestParam(value = "filter", required = false) String f) throws SQLException {
        List<Person> persons = new LinkedList<Person>();
        for (Person p : libraryManager.findAllClients()) {
            if (f == null) {
                persons.add(p.clone());
            } else if (p.getFirstName().contains(f)) {
                persons.add(p);
            }
        }
        return persons;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deletePerson(@PathVariable("id") Long id) throws SQLException {
        libraryManager.deleteClient(libraryManager.findClientById(id));
        return "OK";
    }

}
