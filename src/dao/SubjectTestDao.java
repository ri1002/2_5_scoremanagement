package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class SubjectTestDao extends Dao {

    public List<Test> filter(Integer entYearStr, Integer classNum, String subject, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String sql = "SELECT s.ent_year, s.class_num, s.no AS student_no, s.name AS student_name, " +
                "t1.point AS point1, t2.point AS point2 " +
                "FROM student s " +
                "LEFT JOIN test t1 ON s.no = t1.student_no AND t1.no = 1 AND t1.subject_cd = ? " +
                "LEFT JOIN test t2 ON s.no = t2.student_no AND t2.no = 2 AND t2.subject_cd = ? " +
                "WHERE s.ent_year = ? AND s.class_num = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject); // t1.subject_cd
            statement.setString(2, subject); // t2.subject_cd
            statement.setInt(3, entYearStr);
            statement.setInt(4, classNum);

            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
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

            test.setStudent(student);
            test.setPoint1(rSet.getObject("point1") != null ? rSet.getInt("point1") : null);
            test.setPoint2(rSet.getObject("point2") != null ? rSet.getInt("point2") : null);
            test.setSchool(school);

            list.add(test);
        }
        return list;
    }
}