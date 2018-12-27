package com.spring.javabased.jpa.dao;

import com.spring.javabased.jpa.entity.TeachingStaff;

import javax.persistence.TypedQuery;
import java.util.List;

public class TeachingStaffDAO extends Connection implements DAO<TeachingStaff, Integer> {

    private TeachingStaffDAO() {}

    public static TeachingStaffDAO getInstance() {
        return new TeachingStaffDAO();
    }

    @Override
    public void insert(TeachingStaff ts) {
        try {
            em.persist(ts);
        } catch (Exception e) {
            System.out.println("Insert Exception with message " + e);
        }

    }

    @Override
    public boolean update(TeachingStaff ts) {
        try {
            em.merge(ts);
            em.flush();
            return true;
        } catch (Exception e) {
            System.out.println("Update Exception with message " + e);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            em.remove(find(id));
        } catch (Exception e) {
            System.out.println("Delete Exception with message " + e);
        }
        return false;
    }

    @Override
    public TeachingStaff find(Integer id) {
        return em.find(TeachingStaff.class, id);
    }

    @Override
    public List<TeachingStaff> findAll() {
        String query = "SELECT t FROM TeachingStaff t";
        TypedQuery<TeachingStaff> tq = em.createQuery(query, TeachingStaff.class);
        return tq.getResultList();
    }
}
