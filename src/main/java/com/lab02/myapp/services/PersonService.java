package com.lab02.myapp.services;

import com.lab02.myapp.model.Person;

import java.util.List;

public interface PersonService {
    Person create(Person person);
    Person update(Person person);
    boolean delete(Long id);
    Person getById(Long id);
    List<Person> getAll();
    Person update(Long id, Person person);
}
