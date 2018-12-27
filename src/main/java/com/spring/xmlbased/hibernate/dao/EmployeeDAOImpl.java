package com.spring.xmlbased.hibernate.dao;

import com.spring.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    private SessionFactory sessionFactory;
    private TransactionTemplate transactionTemplate;

    private static class SingletonHelper
    {
        private static final EmployeeDAOImpl INSTANCE = new EmployeeDAOImpl();
    }

    public static EmployeeDAOImpl getInstance() {return SingletonHelper.INSTANCE;}

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void setTransactionManager(PlatformTransactionManager txManager) {
        this.transactionTemplate = new TransactionTemplate(txManager);
    }

    /* ================================================================================ */
    /* This section is using Spring Hibernate Transaction */
    /* ================================================================================ */

    @Override
    @Transactional
    public void insert(Employee employee) {
        Session session = this.sessionFactory.openSession();
        try {
            this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    session.save(employee);
                }
            });
        }catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void insertBatch(List<Employee> employees) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    for(Employee employee : employees) {
                        session.persist(employee);
                    }
                }
            });
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public boolean update(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    session.update(employee);
                }
            });
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        try {
            this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    Employee employee = session.get(Employee.class, id);
                    session.delete(employee);
                }
            });
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /* ================================================================================ */
    /* This section is using Non Spring Hibernate Transaction */
    /* ================================================================================ */

    @Override
    public List<Employee> findAll() {
        List<Employee> employees;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            tx.commit();
        } catch (HibernateException e) {
            if(tx!=null) {tx.rollback();}
            e.printStackTrace();
            return null;
        }
        return employees;
    }

    @Override
    public Optional<Employee> findByKey(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = null;
        Employee employee;
        try {
            tx = session.beginTransaction();
            employee = session.get(Employee.class, id);
            return Optional.of(employee);
        } catch (HibernateException e) {
            if(tx!=null) {tx.rollback();}
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findByLastName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = null;
        Employee employee;
        try {
            tx = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("SELECT * FROM test.Employee WHERE test.employee.last_name LIKE ?");
            return  query.setString("last_name", name).list();
        } catch (HibernateException e) {
            if(tx!=null) {tx.rollback();}
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findBySalary(int salary) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction tx = null;
        Employee employee;
        try {
            tx = session.beginTransaction();
            NativeQuery query = session.createSQLQuery("SELECT * FROM test.Employee WHERE test.employee.last_name LIKE ?");
            return query.setInteger("salary", salary).list();
        } catch (HibernateException e) {
            if(tx!=null) {tx.rollback();}
            e.printStackTrace();
        }
        return null;
    }

}
