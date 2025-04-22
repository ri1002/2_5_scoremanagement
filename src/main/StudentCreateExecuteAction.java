/*package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
@WebServlet( urlPatterns ={"/main/student"} )

public class StudentCreateExecuteAction extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500); // エラー処理
        }
    }


	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception {

		List<Student> studentList1 = new ArrayList<>();
		List<Student> studentList2 = new ArrayList<>();

	    String sql1 = "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, "
	               + " sc.cd AS school_cd, sc.name AS school_name "
	               + " FROM student as s "
	               + " JOIN school as sc ON s.school_cd = sc.cd";

	    String sql2 = "select distinct ent_year"
	               + " FROM student";

	    try (Connection con = new StudentDao().getConnection();  // DAOの接続だけ使う
	         PreparedStatement stmt = con.prepareStatement(sql1);
	         ResultSet rSet = stmt.executeQuery()) {

	        while (rSet.next()) {
	            School school = new School();
	            school.setCd(rSet.getString("school_cd"));
	            school.setName(rSet.getString("school_name"));

	            Student student = new Student();
	            student.setNo(rSet.getString("no"));
	            student.setName(rSet.getString("name"));
	            student.setEntYear(rSet.getInt("ent_year"));
	            student.setClassNum(rSet.getString("class_num"));
	            student.setIsAttend(rSet.getBoolean("is_attend"));
	            student.setSchool(school);

	            studentList1.add(student);
	        }
	    }
		request.setAttribute("studentList1", studentList1);
		request.getRequestDispatcher("/main/student.jsp").forward(request, response);


	}


}*/