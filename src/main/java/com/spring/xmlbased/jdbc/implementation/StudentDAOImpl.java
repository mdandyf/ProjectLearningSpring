package com.spring.xmlbased.jdbc.implementation;

import com.spring.xmlbased.jdbc.dao.StudentDAO;
import com.spring.model.Student;
import com.spring.xmlbased.jdbc.mapper.StudentMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/* This is a normal student DAO (without Transaction) */

public class StudentDAOImpl implements StudentDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Student> findByName(String name) {
        String query = "select * from Student where name = ?";
        return Optional.of(jdbcTemplateObject.queryForObject(query, new Object[]{name}, new StudentMapper()));
    }

    @Override
    public Optional<Student> findByAddress(String address) {
        String query = "select * from Student where address = ?";
        return Optional.of(jdbcTemplateObject.queryForObject(query, new Object[]{address}, new StudentMapper()));
    }

    @Override
    public Optional<Student> find(Integer id) {
        String query = "select * from Student where id = ?";
        return Optional.of(jdbcTemplateObject.queryForObject(query, new Object[]{id}, new StudentMapper()));
    }

    @Override
    public List<Student> findAll() {
        String query = "select * from Student";
        return jdbcTemplateObject.query(query, new StudentMapper());
    }

    @Override
    public void insert(Student student) {
        try {
            String query = "insert into Student (name, age, address) values (?,?,?)";
            jdbcTemplateObject.update(query, student.getName(), student.getAge(), student.getAddress());
            System.out.println("Create Record " + "name: " + student.getName() + " age: " + student.getAge() + " address: " + student.getAddress());
        } catch (Exception e) {
            System.out.println("Unhandled Exception with message: " + e);
        }
    }

    @Override
    public boolean update(Student student) {
        try {
            String query = "update Student set name = ?, age = ?, address = ? where id = ?";
            jdbcTemplateObject.update(query, student.getName(), student.getAge(),student.getAddress(), student.getId());
        } catch (Exception e) {
            System.out.println("Unhandled Exception with message: " + e);
            return false;
        }
        return true;
    }

    @Override
    public int[] updateBatch(List<Student> students) {
        String query = "update Student set name = ?, age = ?, address = ? where id = ?";
        return jdbcTemplateObject.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, students.get(i).getName());
                preparedStatement.setInt(2, students.get(i).getAge());
                preparedStatement.setString(3, students.get(i).getAddress());
                preparedStatement.setInt(4, students.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        });
    }

    @Override
    public boolean delete(Integer id) {
        try {
            String query = "delete from Student where id = ?";
            jdbcTemplateObject.update(query, id);
        } catch (Exception e) {
            System.out.println("Unhandled Exception with message: " + e);
            return false;
        }
        return true;
    }
}
