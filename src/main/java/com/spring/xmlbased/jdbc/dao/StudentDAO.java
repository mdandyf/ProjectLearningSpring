package com.spring.xmlbased.jdbc.dao;

import com.spring.model.Student;

import java.util.Optional;

public interface StudentDAO extends DAO<Student,Integer> {
    Optional<Student> findByName(String name);
    Optional<Student> findByAddress(String address);
}
