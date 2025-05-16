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

    public Test get(Student student, Subject subject, School school, int no) {
        Test test = new Test();
        return test;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                 "SELECT student.no AS student_no, student.name AS student_name, student.ent_year, student.class_num, " +
                 "test.no AS test_no, test.point, " +
                 "subject.cd AS subject_cd, subject.name AS subject_name " +
                 "FROM student " +
                 "JOIN test ON student.no = test.student_no " +
                 "JOIN subject ON subject.cd = test.subject_cd " +
                 "WHERE student.ent_year = ? " +
                 "AND student.class_num = ? " +
                 "AND subject.cd = ? " +
                 "AND test.no = ? " +
                 "ORDER BY test.no ASC")) {

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);

            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        }
        return list;
    }

    public List<Student> findStudentsWithoutTest(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student " +
                     "WHERE ent_year = ? " +
                     "AND class_num = ? " +
                     "AND NOT EXISTS ( " +
                     "  SELECT * FROM test " +
                     "  WHERE student.no = test.student_no " +
                     "  AND subject_cd = ? AND no = ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    Student student = new Student();
                    student.setNo(rSet.getString("no"));
                    student.setName(rSet.getString("name"));
                    student.setEntYear(rSet.getInt("ent_year"));
                    student.setClassNum(rSet.getString("class_num"));
                    list.add(student);
                }
            }
        }
        return list;
    }

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        while (rSet.next()) {
            Test test = new Test();

            Student student = new Student();
            student.setNo(rSet.getString("student_no"));
            student.setName(rSet.getString("student_name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));

            Subject subject = new Subject();
            subject.setCd(rSet.getString("subject_cd"));
            subject.setName(rSet.getString("subject_name"));

            test.setStudent(student);
            test.setSubject(subject);
            test.setClassNum(rSet.getString("class_num"));
            test.setNo(rSet.getInt("test_no"));
            test.setPoint(rSet.getInt("point"));
            test.setSchool(school);

            list.add(test);
        }
        return list;
    }

    public boolean save(List<Test> list) throws Exception {
        boolean success = true;

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            try {
                for (Test test : list) {
                    save(test, connection);
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                success = false;
                throw e;
            }
        }
        return success;
    }

    public boolean save(Test test, Connection connection) throws Exception {
        boolean result = false;

        String checkSql = "SELECT COUNT(*) FROM test WHERE student_no = ? AND subject_cd = ? AND no = ?";
        String insertSql = "INSERT INTO test (student_no, subject_cd, no, point, class_num, school_cd) VALUES (?, ?, ?, ?, ?, ?)";
        String updateSql = "UPDATE test SET point = ?, class_num = ? WHERE student_no = ? AND subject_cd = ? AND no = ?";
        String deleteSql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND no = ?";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql);
             PreparedStatement insertStmt = connection.prepareStatement(insertSql);
             PreparedStatement updateStmt = connection.prepareStatement(updateSql);
             PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {

            checkStmt.setString(1, test.getStudent().getNo());
            checkStmt.setString(2, test.getSubject().getCd());
            checkStmt.setInt(3, test.getNo());

            try (ResultSet rs = checkStmt.executeQuery()) {
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
                        updateStmt.setString(2, test.getClassNum());
                        updateStmt.setString(3, test.getStudent().getNo());
                        updateStmt.setString(4, test.getSubject().getCd());
                        updateStmt.setInt(5, test.getNo());
                        result = updateStmt.executeUpdate() == 1;
                    } else {
                        insertStmt.setString(1, test.getStudent().getNo());
                        insertStmt.setString(2, test.getSubject().getCd());
                        insertStmt.setInt(3, test.getNo());
                        insertStmt.setInt(4, test.getPoint());
                        insertStmt.setString(5, test.getClassNum());
                        insertStmt.setString(6, test.getSchool().getCd());
                        result = insertStmt.executeUpdate() == 1;
                    }
                }
            }
        }

        return result;
    }
}
