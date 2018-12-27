package com.spring.xmlbased.hibernate.dao;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {
    void insert(T t);
    void insertBatch(List<T> t);
    boolean update(T t);
    boolean delete(ID id);
    List<T> findAll();
    Optional<T> findByKey(ID id);
}
