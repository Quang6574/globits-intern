package com.globits.demo.repository;

import com.globits.demo.model.Person;

import java.util.List;

public interface PersonRepository {

    Person create(Person person);

    List<Person> getAll(int page, int pageSize);
    Person get(int id);

    Person save(Person person);
    void delete(int id);

        Person createOrUpdate(Person person);

}
