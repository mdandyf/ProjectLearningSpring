package com.spring.javabased.jpa;

import com.spring.javabased.jpa.dao.NonTeachingStaffDAO;
import com.spring.javabased.jpa.dao.TeachingStaffDAO;
import com.spring.javabased.jpa.entity.NonTeachingStaff;
import com.spring.javabased.jpa.entity.TeachingStaff;

public class MainApp {
    public static void main(String[] args) {

        System.out.println("======= Insert Data =======");

        TeachingStaffDAO tsDAO = TeachingStaffDAO.getInstance();

        tsDAO.beginTransaction();
        tsDAO.insert(new TeachingStaff(1, "Harry Hudaya", "M.Sc, Ph.D", "Water Resource Engineering"));
        tsDAO.insert(new TeachingStaff(2, "Nanda", "ST, M.Sc", "Water Structure"));
        tsDAO.insert(new TeachingStaff(3, "Hajrah Bulan", "ST, M.Sc", "Water Planning"));

        NonTeachingStaffDAO ntsDAO = NonTeachingStaffDAO.getInstance();

        ntsDAO.beginTransaction();
        ntsDAO.insert(new NonTeachingStaff(1, "Babah Ayang", "Receptionist"));
        ntsDAO.insert(new NonTeachingStaff(2, "Ambu Nona", "Cleaning Service"));

        tsDAO.commitTransaction();
        ntsDAO.commitTransaction();

        tsDAO.closeConnection();
        ntsDAO.closeConnection();

    }
}
