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

public class SubjectTestDao extends Dao {

	public List<Test> filter(Integer entYearStr, Integer classNum, String subject, School school) throws Exception {
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
                "AND subject.cd = ? ";

		try {
		statement = connection.prepareStatement(sql);
        statement.setInt(1, entYearStr);
        statement.setInt(2, classNum);
        statement.setString(3, subject);

        rSet = statement.executeQuery();
        list = postFilter(rSet, school);
		} catch (Exception e) {
	        // ここでログを出すか、再スローしても良い
	        e.printStackTrace();  // 開発中はこれでもOK
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
}

