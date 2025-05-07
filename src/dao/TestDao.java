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

/*	//学生情報の取得
	public Test get(Student student, Subject subject, Integer no) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("select * from student where no=?");
			statement.setString(1, no);
			ResultSet rSet = statement.executeQuery();

			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				student = null;
			}
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

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return test;
	}*/

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


/*	public boolean save(Student student) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			Student old = get(student.getNo());
			if (old == null) {
				statement = connection.prepareStatement("insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.getAttend());
				statement.setString(6, student.getSchool().getCd());
			} else {
				statement = connection.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.getAttend());
				statement.setString(5, student.getNo());
			}

			count = statement.executeUpdate();
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

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}*/
}