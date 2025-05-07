package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;


public class TeacherDao extends Dao {
	public Teacher login(String id,String password) throws Exception{
		Teacher teacher=null;

		Connection con =getConnection();

		PreparedStatement st;
		st=con.prepareStatement("select * from teacher where Id=? and password = ?");
		st.setString(1,id);
		st.setString(2,password);
		ResultSet rs=st.executeQuery();

		if (rs.next()) {
			String schoolCode = rs.getString("school_cd");
			SchoolDao schoolDao = new SchoolDao();
			School school = schoolDao.get(schoolCode); // 学校コードからSchoolオブジェクトを取得

			teacher= new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setPassword(rs.getString("password"));
			teacher.setName(rs.getString("name"));
			teacher.setSchool(school);
		}

		st.close();
		con.close();

		return teacher;
	}
}
