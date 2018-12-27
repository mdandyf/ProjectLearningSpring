package com.spring.javabased.jpa.dao;

import java.util.List;

public interface DAO<T, ID> {
    void insert(T t);
    boolean update(T t);
    boolean delete(ID id);
    T find(ID id);
    List<T> findAll();
}
