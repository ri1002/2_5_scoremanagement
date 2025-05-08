package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action{

<<<<<<< HEAD
	@Override
=======

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500); // エラー処理
        }
    }

	private List<ClassNum> getClassNumList() throws Exception {
	    List<ClassNum> classNumList = new ArrayList<>();
	    String sql = "SELECT class_num FROM class_num";
	    try (Connection con = new StudentDao().getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            ClassNum classnum = new ClassNum();
	            classnum.setClass_num(rs.getString("class_num"));
	            classNumList.add(classnum);
	        }
	    }
	    return classNumList;
	}

	private List<Integer> getYearList() {
	    int currentYear = LocalDate.now().getYear();
	    List<Integer> yearList = new ArrayList<>();
	    for (int i = currentYear; i >= currentYear - 10; i--) {
	        yearList.add(i);
	    }
	    return yearList;
	}

	//学生一覧をデータベースから受け取りリストにしてstudent_list.jspに送っている
	public void doPost
		( HttpServletRequest request, HttpServletResponse response
		) throws java.io.IOException {


		List<Student> studentList1 = new ArrayList<>();

	    String sql1 = "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, "
	               + " sc.cd AS school_cd, sc.name AS school_name "
	               + " FROM student as s "
	               + " JOIN school as sc ON s.school_cd = sc.cd";

	    try (Connection con1 = new StudentDao().getConnection();  // DAOの接続だけ使う
	         PreparedStatement stmt1 = con1.prepareStatement(sql1);
	         ResultSet rSet1 = stmt1.executeQuery()) {


	        while (rSet1.next()) {
	            School school = new School();
	            school.setCd(rSet1.getString("school_cd"));
	            school.setName(rSet1.getString("school_name"));

	            Student student = new Student();
	            student.setNo(rSet1.getString("no"));
	            student.setName(rSet1.getString("name"));
	            student.setEntYear(rSet1.getInt("ent_year"));
	            student.setClassNum(rSet1.getString("class_num"));
	            student.setIsAttend(rSet1.getBoolean("is_attend"));
	            student.setSchool(school);

	            studentList1.add(student);
	        }
	    }
		request.setAttribute("studentList1", studentList1);

		request.setAttribute("studentList2", getClassNumList());

		request.setAttribute("YearList", getYearList());


		request.getRequestDispatcher("/main/student_list.jsp").forward(request, response);

	}




>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
	public void execute
		( HttpServletRequest request, HttpServletResponse response
		) throws Exception{
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");

		String entYearStr="";//入力された入学年度
		String classNum=""; //入力されたクラス番号
		String isAttendStr="";//入力された在学フラグ
		int entYear = 0;//入学年度
		boolean isAttend = false;//在学フラグ
		List<Student> students = null;//学生リスト
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメーターの取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");

		if (isAttendStr != null) {
			isAttend = true;
<<<<<<< HEAD
=======

	try {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// パラメータ取得
		String f1 = request.getParameter("f1");  		// 入学年度
		String f2 = request.getParameter("f2");       // クラス
		String f3 = request.getParameter("f3");       // チェックボックス（在学中）
		Boolean isAttend = (f3 != null) ? true : false;

		Integer entYear = null;
		if (f1 != null && !f1.isEmpty()) {
		    entYear = Integer.parseInt(f1);

>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		}

		//ビジネスロジック

		if (entYearStr != null){
			//数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")) {
			// 入学年度とクラス番号を指定
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
				// 入学年度のみ
				students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && (classNum == null ||  classNum.equals("0"))) {
				//指定なしの場合
				//全学生情報を取得
				students = sDao.filter(teacher.getSchool(),isAttend);

		} else {
			errors.put("errors", "検索条件が正しくありません");
			//全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}

		//レスポンス値をセット
		//リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", isAttendStr);

		//リクエストに学生リストをセット
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード
		request.getRequestDispatcher("student_list.jsp").forward(request, response);

	}
}


