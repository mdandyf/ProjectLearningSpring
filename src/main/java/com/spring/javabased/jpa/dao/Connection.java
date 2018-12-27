package com.spring.javabased.jpa.dao;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Connection {
    protected EntityManagerFactory emf;
    protected EntityManager em;

    private static final String USER = "root";
    private static final String USER_PASS = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBNAME = "masterdb";
    private static final int PORT = 3306;
    private static final String HOST = "127.0.0.1";
    private static final String URL = "jdbc:mysql://";

    public Connection() {
        this.emf = Persistence.createEntityManagerFactory("Eclipselink_JPA", getJPAProperties());
        this.em = emf.createEntityManager();
    }

    private EntityManagerFactory getEntityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean lemf = new LocalContainerEntityManagerFactoryBean();
        return lemf.getNativeEntityManagerFactory();
    }

    private Map getJPAProperties() {
        final Map properties = new HashMap<String, String>();
        properties.put("javax.persistence.jdbc.url", URL + HOST + ":" + PORT + "/" + DBNAME);
        properties.put("javax.persistence.jdbc.user", USER);
        properties.put("javax.persistence.jdbc.password", USER_PASS);
        properties.put("javax.persistence.jdbc.driver", DRIVER);
        properties.put(PersistenceUnitProperties.LOGGING_LEVEL , "SEVERE");
        properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
        properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
        return properties;
    }

    public void beginTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
    }

    public void closeConnection() {
        em.close();
        emf.close();
    }

}
