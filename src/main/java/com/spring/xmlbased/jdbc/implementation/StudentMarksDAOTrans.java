package com.spring.xmlbased.jdbc.implementation;

/* This is student DAO with Transaction */

import com.spring.model.Student;
import com.spring.model.StudentMarks;
import com.spring.xmlbased.jdbc.dao.MarksDAO;
import com.spring.xmlbased.jdbc.mapper.StudentMarksMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class StudentMarksDAOTrans implements MarksDAO {

    private PlatformTransactionManager transactionManager;
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void create(Student student, StudentMarks studentMarks) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String query = "insert into Student (name, age, address) values (?,?,?)";
            jdbcTemplateObject.update(query, student.getName(), student.getAge(), student.getAddress());
            System.out.println("Create Record " + "name: " + student.getName() + " age: " + student.getAge() + " address: " + student.getAddress());

            // Get the latest student id to be used in Marks table
            String query2 = "select max(id) from Student";
            int sid = jdbcTemplateObject.queryForObject(query2, Integer.class);

            String query3 = "insert into Marks(sid, marks, year) " + "values (?, ?, ?)";
            jdbcTemplateObject.update( query3, sid, studentMarks.getMarks(), studentMarks.getYear());

            transactionManager.commit(status);
            System.out.println("Created Name = " + student.getName() + ", Marks = " + studentMarks.getMarks());
        } catch (Exception e) {
            System.out.println("Error in creating record with message: " + e);
            System.out.println("Rolling back");
            transactionManager.rollback(status);
        }
    }

    @Override
    public boolean delete(Student student, StudentMarks studentMarks) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            int sid = student.getId();

            String query = "delete from Marks where sid = ?";
            jdbcTemplateObject.update(query, sid);

            String query2 = "delete from Student where id=?";
            jdbcTemplateObject.update(query2, sid);

            transactionManager.commit(status);
        } catch (Exception e) {
            System.out.println("Error in deleting record with message: " + e);
            System.out.println("Rolling back");
            transactionManager.rollback(status);
        }

        return status.isCompleted();
    }

    @Override
    public boolean update(Student student, StudentMarks studentMarks) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String query = "update Student set name = ?, age = ?, address = ? where id = ?";
            jdbcTemplateObject.update(query, student.getName(), student.getAge(),student.getAddress(), student.getId());

            String query2 = "update Marks set marks=?, year = ? where sid = ?";
            jdbcTemplateObject.update(query2, studentMarks.getMarks(), studentMarks.getYear(), studentMarks.getSid());

            transactionManager.commit(status);
        } catch (Exception e) {
            System.out.println("Error in updating record with message: " + e);
            System.out.println("Rolling back");
            transactionManager.rollback(status);
        }

        return status.isCompleted();
    }

    @Override
    public List<StudentMarks> findBySpecificMarks(Integer mark) {
        String query = "select * from Student,Marks where Student.id = Marks.sid and Marks.marks = ?";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(query, new Object[]{mark}, new StudentMarksMapper());
        return studentMarks;
    }

    @Override
    public List<StudentMarks> findByYear(Integer year) {
        String query = "select * from Student,Marks where Marks.year = ?";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(query, new Object[]{year}, new StudentMarksMapper());
        return studentMarks;
    }

    @Override
    public Optional<StudentMarks> findByTheHighestMark() {
        String query = "select * from Student,Marks where Student.id = Marks.sid Order by Marks.marks Desc";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(query, new StudentMarksMapper());
        return Optional.of(studentMarks.get(0));
    }

    @Override
    public Optional<StudentMarks> findByTheLowestMark() {
        String query = "select * from Student,Marks where Student.id = Marks.sid Order by Marks.marks";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(query, new StudentMarksMapper());
        return Optional.of(studentMarks.get(0));
    }

    @Override
    public List<StudentMarks> findAllStudentMarks() {
        String query = "select * from Student, Marks where Student.id = Marks.sid";
        return jdbcTemplateObject.query(query, new StudentMarksMapper());
    }
}
