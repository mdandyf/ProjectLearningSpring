package com.spring.xmlbased.jdbc;

import com.spring.model.Student;
import com.spring.model.StudentMarks;
import com.spring.xmlbased.jdbc.dao.MarksDAO;
import com.spring.xmlbased.jdbc.implementation.StudentMarksDAOTrans;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Optional;

public class MainAppTrans {
    public static void main(String[] args) {
        /* Setting spring first connection */
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansJdbcTrans.xml");

        MarksDAO studentMarksDAO =
                (MarksDAO) context.getBean("studentMarksDAO");

        /* Start inserting data */
       System.out.println("------Records Creation--------" );
        studentMarksDAO.create(new Student("Bayu Giri", 30, "Belitung"),
                                new StudentMarks(80, 4));
        studentMarksDAO.create(new Student("Samas", 25, "Yogyakarta"),
                                new StudentMarks(50, 3));
        studentMarksDAO.create(new Student("Zulfikar", 26, "Bandung"),
                                new StudentMarks(20, 4));
        studentMarksDAO.create(new Student("Diyan", 28, "Jakarta"),
                                new StudentMarks(10, 8));
        studentMarksDAO.create(new Student("Joko", 29, "Solo"),
                                new StudentMarks(80, 4));
        studentMarksDAO.create(new Student("Putri", 26, "Samarinda"),
                                new StudentMarks(40, 5));
        studentMarksDAO.create(new Student("Onde Mande", 24, "Papua"),
                                new StudentMarks(90, 4));
        studentMarksDAO.create(new Student("Firman", 22, "Jakarta"),
                                new StudentMarks(60, 4));
        studentMarksDAO.create(new Student("Julia", 23, "Bali"),
                                new StudentMarks(70, 3));
        studentMarksDAO.create(new Student("Nanda", 21, "Surabaya"),
                                new StudentMarks(40, 2));

       /* System.out.println("------Listing all the records--------" );
        List<StudentMarks> studentMarks = studentMarksDAO.findAllStudentMarks();

        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }

        System.out.printf("\n\n------Finding Lowest Mark--------" );
        Optional<StudentMarks> studentMark = studentMarksDAO.findByTheLowestMark();

        if(studentMark.isPresent()) {
            System.out.printf("\nThe lowest grade is held by %s with mark %d", studentMark.get().getName(), studentMark.get().getMarks());
        }

        System.out.printf("\n\n------Finding Highest Mark--------" );
        studentMark = studentMarksDAO.findByTheHighestMark();

        if(studentMark.isPresent()) {
            System.out.printf("\nThe highest grade is held by %s with mark %d", studentMark.get().getName(), studentMark.get().getMarks());
        }

        System.out.printf("\n\n------Finding Specific Score: 80--------\n" );
        studentMarks = studentMarksDAO.findBySpecificMarks(80);

        for (StudentMarks record : studentMarks) {
            System.out.printf("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }*/


    }
}
