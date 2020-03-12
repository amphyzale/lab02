package com.lab02.myapp.dao;

import com.lab02.myapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
