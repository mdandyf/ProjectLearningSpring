package com.spring.xmlbased.hibernate.dao;

import com.spring.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO extends DAO<Employee,Integer> {
    void setSessionFactory(SessionFactory sessionFactory);
    void setTransactionManager(PlatformTransactionManager txManager);
    List<Employee> findByLastName(String name);
    List<Employee> findBySalary(int salary);
}
