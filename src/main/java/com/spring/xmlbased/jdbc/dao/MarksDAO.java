package com.spring.xmlbased.jdbc.dao;

import com.spring.model.Student;
import com.spring.model.StudentMarks;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MarksDAO {
    public void setDataSource(DataSource ds);
    void setTransactionManager(PlatformTransactionManager transactionManager);
    List<StudentMarks> findBySpecificMarks(Integer mark);
    List<StudentMarks> findByYear(Integer year);
    Optional<StudentMarks> findByTheHighestMark();
    Optional<StudentMarks> findByTheLowestMark();
    List<StudentMarks> findAllStudentMarks();
    void create(Student student, StudentMarks studentMarks);
    boolean delete(Student student, StudentMarks studentMarks);
    boolean update(Student student, StudentMarks studentMarks);
}
