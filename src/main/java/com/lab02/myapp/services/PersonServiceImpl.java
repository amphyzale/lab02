package com.lab02.myapp.services;

//import com.lab02.myapp.dao.PersonRepository;
import com.lab02.myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServiceImpl implements PersonService {

    //private final PersonRepository personRepository;

    private static final Map<Long, Person> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicLong CLIENT_ID_HOLDER = new AtomicLong();

    /*@Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }*/

    @Override
    public Person create(Person person) {
        //return personRepository.create(person);
        person.setId(CLIENT_ID_HOLDER.incrementAndGet());
        CLIENT_REPOSITORY_MAP.put(CLIENT_ID_HOLDER.get(), person);
        return CLIENT_REPOSITORY_MAP.get(CLIENT_ID_HOLDER.get());
    }

    @Override
    public Person update(Person person) {
        //return personRepository.update(person);
        return null;
    }

    //to remove
    public Person update(Long id, Person person) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            person.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, person);
            return CLIENT_REPOSITORY_MAP.get(id);
        }
        return create(person);
    }

    @Override
    public boolean delete(Long id) {
        //if (getById(id) != null) personRepository.delete(id);
        CLIENT_REPOSITORY_MAP.remove(id);
        return CLIENT_REPOSITORY_MAP.containsKey(id);
    }

    @Override
    public Person getById(Long id) {
        //return personRepository.getById(id);
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public List<Person> getAll() {
        //return personRepository.getAll();
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }
}
