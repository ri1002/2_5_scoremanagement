package main;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import dao.StudentDao;
import tool.Action;
@WebServlet( urlPatterns ={"/main/student"} )

public class StudentUpdateExecuteAction extends Action {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
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

		Integer ent_year = Integer.parseInt(request.getParameter("ent_year"));
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String class_num = request.getParameter("class_num");
        Boolean is_attend = Boolean.parseBoolean(request.getParameter("is_attend"));
        boolean hasError = false;

	    String student_name = null;

		if (name == null || name.trim().isEmpty()) {
			student_name = "このフィールドを入力してください。";
			hasError = true;
		}

        // エラーがあった場合は再入力画面へ戻す
        if (hasError) {
            request.setAttribute("student_name", student_name);

            request.getRequestDispatcher("/main/student_update.jsp").forward(request, response);
            return;
        }

			Student student = new Student();
			student.setNo(no);              // 学生番号
			student.setName(name);       	// 名前
			student.setEntYear(ent_year);          // 入学年度
			student.setClassNum(class_num);         // クラス番号
			student.setAttend(is_attend);           // 在学中フラグ

			School school = new School();      // 学校情報（省略してたらここ必要！）
			school.setCd("tes");
			student.setSchool(school);         // 学校コードをセット

			StudentDao dao = new StudentDao();
			boolean result = dao.save(student);  // ← 登録・更新の実行



        //登録完了後にリダイレクト(仮の成功画面)
		response.sendRedirect("../main/student_update_done");
		return;
	}

}


