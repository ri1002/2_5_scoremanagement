package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {
	private String baseSql = "select * from student where school_cd=?";

	
	public get(Student)

	//一覧表示
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
	    List<Test> list = new ArrayList<>();
	    while (rSet.next()) {
	        Test test = new Test();

	     // Sutudentオブジェクトを作成して設定
	        Student student = new Student();
	        student.setNo(rSet.getString("student_no"));
	        student.setName(rSet.getString("name"));
	        student.setEntYear(rSet.getInt("ent_year"));
	        student.setClassNum(rSet.getString("class_num"));

	        // Subjectオブジェクトを作成して設定
	        Subject subject = new Subject();
	        subject.setCd(rSet.getString("cd"));
	        subject.setName(rSet.getString("subject.name")); // subjectテーブルから取得できる場合

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


	//入学年度、クラス、科目、学校で検索
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		//SQL文
		String condition = "SELECT * " +
	             "FROM student " +
	             "JOIN test ON student.no = test.student_no " +
	             "JOIN subject ON subject.cd = test.subject_cd " +
	             "WHERE student.ent_year = ? " +
	             "AND student.class_num = ? " +
	             "AND subject.cd = ? " +
	             "AND test.no = ? " +
	             "ORDER BY test.no ASC";

		String conditionIsAttend = "";

		try {
			statement = connection.prepareStatement(condition);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			rSet = statement.executeQuery();

			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}


	public boolean save(List<Test> list) throws Exception {
		boolean b = false;
		return b;
	}

	public boolean save(Test test, Connection connection) throws Exception {
		boolean b = false;
		return b;
	}
}