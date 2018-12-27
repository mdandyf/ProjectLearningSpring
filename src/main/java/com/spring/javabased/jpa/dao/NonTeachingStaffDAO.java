package com.spring.javabased.jpa.dao;

import com.spring.javabased.jpa.entity.NonTeachingStaff;

import javax.persistence.TypedQuery;
import java.util.List;

public class NonTeachingStaffDAO extends Connection implements DAO<NonTeachingStaff, Integer> {

    private NonTeachingStaffDAO() {}

    public static NonTeachingStaffDAO getInstance() {
        return new NonTeachingStaffDAO();
    }

    @Override
    public void insert(NonTeachingStaff nt) {
        try {
            em.persist(nt);
        } catch (Exception e) {
            System.out.println("Insert Exception with message " + e);
        }

    }

    @Override
    public boolean update(NonTeachingStaff nt) {
        try {
            em.merge(nt);
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
    public NonTeachingStaff find(Integer integer) {
        return em.find(NonTeachingStaff.class, integer);
    }

    @Override
    public List<NonTeachingStaff> findAll() {
        String query = "SELECT t FROM NonTeachingStaff t";
        TypedQuery<NonTeachingStaff> tq = em.createQuery(query, NonTeachingStaff.class);
        return tq.getResultList();
    }
}
