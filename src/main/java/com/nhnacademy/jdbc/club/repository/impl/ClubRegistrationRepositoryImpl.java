package com.nhnacademy.jdbc.club.repository.impl;

import com.nhnacademy.jdbc.club.domain.ClubStudent;
import com.nhnacademy.jdbc.club.repository.ClubRegistrationRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
public class ClubRegistrationRepositoryImpl implements ClubRegistrationRepository {

    @Override
    public int save(Connection connection, String studentId, String clubId) {
        //todo#11 - 핵생 -> 클럽 등록, executeUpdate() 결과를 반환
        String sql = "insert into jdbc_club_registrations (student_id, club_id) values (?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, studentId);
            psmt.setString(2, clubId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByStudentIdAndClubId(Connection connection, String studentId, String clubId) {
        //todo#12 - 핵생 -> 클럽 탈퇴, executeUpdate() 결과를 반환
        String sql = "delete from jdbc_club_registrations where student_id=? and club_id=?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, studentId);
            psmt.setString(2, clubId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClubStudent> findClubStudentsByStudentId(Connection connection, String studentId) {
        //todo#13 - 핵생 -> 클럽 등록, executeUpdate() 결과를 반환
        String sql = "select a.id as student_id, a.name as student_name, c.club_id, c.club_name " +
                "from jdbc_students a inner join jdbc_club_registrations b on a.id=b.student_id " +
                "inner join jdbc_club c on b.club_id=c.club_id where a.id=?";
        ResultSet rs = null;
        List<ClubStudent> clubStudentList;
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, studentId);
            rs = psmt.executeQuery();
            clubStudentList = new ArrayList<>();
            while (rs.next()) {
                clubStudentList.add(new ClubStudent(
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("club_id"),
                        rs.getString("club_name"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clubStudentList;
    }

    @Override
    public List<ClubStudent> findClubStudents(Connection connection) {
        //todo#21 - join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_left_join(Connection connection) {
        //todo#22 - left join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_right_join(Connection connection) {
        //todo#23 - right join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_full_join(Connection connection) {
        //todo#24 - full join = left join union right join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_left_excluding_join(Connection connection) {
        //todo#25 - left excluding join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_right_excluding_join(Connection connection) {
        //todo#26 - right excluding join
        return Collections.emptyList();
    }

    @Override
    public List<ClubStudent> findClubStudents_outher_excluding_join(Connection connection) {
        //todo#27 - outher_excluding_join = left excluding join union right excluding join
        return Collections.emptyList();
    }

}