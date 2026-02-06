package com.globits.demo.dao;

import com.globits.demo.model.Person;

import java.util.List;

public interface PersonDAO {

    Person create(Person person);

    List<Person> getAll();
    Person get(int id);

    Person save(Person person);
    void delete(int id);

}
