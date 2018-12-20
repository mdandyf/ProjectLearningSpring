package com.spring.xmlbased.jdbc.dao;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface DAO<T,ID> {
    public void setDataSource(DataSource ds);
    public Optional<T> find(ID id);
    public List<T> findAll();
    public void insert(T t);
    public boolean update(T t);
    public int[] updateBatch(List<T> tList);
    public boolean delete(ID id);
}
