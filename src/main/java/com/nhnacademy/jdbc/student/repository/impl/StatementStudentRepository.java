package com.nhnacademy.jdbc.student.repository.impl;

import static com.nhnacademy.jdbc.util.DbUtils.getConnection;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
public class StatementStudentRepository implements StudentRepository {
    Connection connection = getConnection();
    @Override
    public int save(Student student) throws SQLException {
        //todo#1 insert student
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO jdbc_students (id, name, gender, age) VALUES ('"
                + student.getId() + "', '"
                + student.getName() + "', '"
                + student.getGender() + "', "
                + student.getAge() + ")";
        int result = statement.executeUpdate(sql);
        return result;
    }

    @Override
    public Optional<Student> findById(String id) throws SQLException {
        //todo#2 student 조회
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM jdbc_students WHERE id='" + id + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Student student = new Student(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    Student.GENDER.valueOf(resultSet.getString("gender")),
                    resultSet.getInt("age"),
                    resultSet.getTimestamp("created_at").toLocalDateTime()
            );
            return Optional.of(student);
        }
        return Optional.empty();
    }

    @Override
    public int update(Student student) throws SQLException {
        //todo#3 student 수정, name <- 수정합니다.
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "UPDATE jdbc_students SET "
                    + "name='" + student.getName() + "', "
                    + "gender='" + student.getGender().name() + "', "
                    + "age=" + student.getAge() + " "
                    + "WHERE id='" + student.getId() + "'";
            int result = statement.executeUpdate(sql);
            return result;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public int deleteById(String id) throws SQLException {
        //todo#4 student 삭제
        Statement statement = connection.createStatement();
        String sql = "DELETE FROM jdbc_students WHERE id='" + id + "'";
        int result = statement.executeUpdate(sql);
        return result;
    }

}
