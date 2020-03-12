package com.lab02.myapp.services;

import com.lab02.myapp.dao.PersonRepository;
import com.lab02.myapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private static final Map<Long, Person> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicLong CLIENT_ID_HOLDER = new AtomicLong();

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
        /*person.setId(CLIENT_ID_HOLDER.incrementAndGet());
        CLIENT_REPOSITORY_MAP.put(CLIENT_ID_HOLDER.get(), person);
        return CLIENT_REPOSITORY_MAP.get(CLIENT_ID_HOLDER.get());*/
    }

    @Override
    public Person update(Long id, Person person) {
        Person oldPerson = getById(id);
        if (oldPerson != null) {
            oldPerson.setBirthDay(person.getBirthDay());
            oldPerson.setCash(person.getCash());
            oldPerson.setEmail(person.getEmail());
            oldPerson.setName(person.getName());
            return personRepository.save(oldPerson);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        personRepository.deleteById(id);
        return getById(id) == null;
        /*CLIENT_REPOSITORY_MAP.remove(id);
        return CLIENT_REPOSITORY_MAP.containsKey(id);*/
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
        //return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
        //return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }
}
