package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String sql = "SELECT * " +
                     "FROM student " +
                     "JOIN test ON student.no = test.student_no " +
                     "JOIN subject ON subject.cd = test.subject_cd " +
                     "WHERE student.ent_year = ? " +
                     "AND student.class_num = ? " +
                     "AND subject.cd = ? " +
                     "AND test.no = ? " +
                     "ORDER BY test.no ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } finally {
            if (statement != null) statement.close();
            if (rSet != null) rSet.close();
        }

        return list;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        while (rSet.next()) {
            Test test = new Test();

            Student student = new Student();
            student.setNo(rSet.getString("student_no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));

            Subject subject = new Subject();
            subject.setCd(rSet.getString("cd"));
            subject.setName(rSet.getString("subject.name"));

            test.setStudent(student);
            test.setSubject(subject);
            test.setClassNum(rSet.getString("class_num"));
            test.setNo(rSet.getInt("test.no"));
            test.setPoint(rSet.getInt("point"));
            test.setSchool(school);

            list.add(test);
        }
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        boolean success = true;
        Connection connection = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            for (Test test : list) {
                save(test, connection);
            }

            connection.commit();
        } catch (Exception e) {
            success = false;
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            if (connection != null) connection.close();
        }

        return success;
    }

    public boolean save(Test test, Connection connection) throws Exception {
        boolean result = false;

        String checkSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND no = ?";
        String insertSql = "INSERT INTO test (student_no, subject_cd, no, point) VALUES (?, ?, ?, ?)";
        String updateSql = "UPDATE test SET point = ? WHERE student_no = ? AND subject_cd = ? AND no = ?";
        String deleteSql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND no = ?";

        try (
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);
            PreparedStatement insertStmt = connection.prepareStatement(insertSql);
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)
        ) {
            checkStmt.setString(1, test.getStudent().getNo());
            checkStmt.setString(2, test.getSubject().getCd());
            checkStmt.setInt(3, test.getNo());

            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (test.getPoint() == null) {
                if (count > 0) {
                    deleteStmt.setString(1, test.getStudent().getNo());
                    deleteStmt.setString(2, test.getSubject().getCd());
                    deleteStmt.setInt(3, test.getNo());
                    result = deleteStmt.executeUpdate() == 1;
                } else {
                    result = true;
                }
            } else {
                if (count > 0) {
                    updateStmt.setInt(1, test.getPoint());
                    updateStmt.setString(2, test.getStudent().getNo());
                    updateStmt.setString(3, test.getSubject().getCd());
                    updateStmt.setInt(4, test.getNo());
                    result = updateStmt.executeUpdate() == 1;
                } else {
                    insertStmt.setString(1, test.getStudent().getNo());
                    insertStmt.setString(2, test.getSubject().getCd());
                    insertStmt.setInt(3, test.getNo());
                    insertStmt.setInt(4, test.getPoint());
                    result = insertStmt.executeUpdate() == 1;
                }
            }
        }

        return result;
    }
}
