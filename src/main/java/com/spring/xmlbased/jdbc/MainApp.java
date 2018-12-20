package com.spring.xmlbased.jdbc;

import com.spring.xmlbased.jdbc.implementation.StudentDAOImpl;
import com.spring.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainApp {

    public static void main(String[] args) {

        /* Setting spring first connection */
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansJdbc.xml");

        StudentDAOImpl studentDAO =
                (StudentDAOImpl) context.getBean("studentDAO");

       /* Start inserting data */
        System.out.println("------Records Creation--------" );
        studentDAO.insert(new Student("Bayu Giri", 30, "Belitung"));
        studentDAO.insert(new Student("Samas", 25, "Yogyakarta"));
        studentDAO.insert(new Student("Zulfikar", 26, "Bandung"));
        studentDAO.insert(new Student("Diyan", 28, "Jakarta"));
        studentDAO.insert(new Student("Joko", 29, "Solo"));
        studentDAO.insert(new Student("Putri", 26, "Samarinda"));
        studentDAO.insert(new Student("Onde Mande", 24, "Papua"));
        studentDAO.insert(new Student("Firman", 22, "Jakarta"));
        studentDAO.insert(new Student("Julia", 23, "Bali"));
        studentDAO.insert(new Student("Nanda", 21, "Surabaya"));

        System.out.println("------Listing Multiple Records--------" );
        List<Student> students = studentDAO.findAll();
        for(Student student : students) {
            System.out.printf("\nStudent record: name:%s, age:%d, address:%s", student.getName(), student.getAge(), student.getAddress());
        }

        System.out.printf("\n------Find by name--------" );
        Optional<Student> student = studentDAO.findByName("Joko");
        if(student.isPresent()) {
            System.out.printf("\n Student with name " + student.get().getName() + " age is " + student.get().getAge());
        }

        System.out.printf("\n------Find by address--------" );
        Optional<Student> student2 = studentDAO.findByAddress("Surabaya");
        if(student2.isPresent()) {
            System.out.printf("\nStudent who lives in Surabaya is " + student.get().getName());
        }

        System.out.printf("\n------Batch Update--------" );
        System.out.printf("\nBoth Student ages have been updated to 100 years");
        List<Student> studentBatchUpdate = new ArrayList<Student>();
        student.get().setAge(100);student2.get().setAge(100);
        studentBatchUpdate.add(student.get());studentBatchUpdate.add(student2.get());
        studentDAO.updateBatch(studentBatchUpdate);


        System.out.printf("\n------Update Record--------" );
        student.get().setName("Jokowi");
        studentDAO.update(student.get());
        System.out.printf("\nStudent name Joko has been updated to Jokowi");

    }
}
