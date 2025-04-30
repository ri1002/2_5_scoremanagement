package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;


public class StudentCreateExecuteAction extends Action {

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
        boolean hasError = false;

        String student_ent_year = null;
	    String student_number = null;
	    String student_name = null;


		if (ent_year == 0) {
			student_ent_year = "入学年度を選択してください。";
			hasError = true;
		}

		if (no == null || no.trim().isEmpty()) {
			student_number = "このフィールドを入力してください。";
			hasError = true;
		}

		if (name == null || name.trim().isEmpty()) {
			student_name = "このフィールドを入力してください。";
			hasError = true;
		}

        // エラーがあった場合は再入力画面へ戻す
        if (hasError) {
        	request.setAttribute("student_ent_year", student_ent_year);
            request.setAttribute("student_number", student_number);
            request.setAttribute("student_name", student_name);

            // `StudentCreateAction` から渡されたクラス情報を再度セット
            ClassNumDao cNumDao = new ClassNumDao();
            Teacher teacher = (Teacher) request.getSession().getAttribute("user");
            List<String> classNumList = cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num_set", classNumList);

            request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);
            return;
        }

        StudentDao studentdao = new StudentDao();
        Student existingStudent = studentdao.get(no);


		if ( existingStudent != null) {
			String student_duplication = "学生番号が重複しています";
			hasError = true;

			// エラー表示の準備
			request.setAttribute("student_duplication", student_duplication);

			// クラス情報の再取得とセット
			ClassNumDao cNumDao = new ClassNumDao();
			Teacher teacher = (Teacher) request.getSession().getAttribute("user");
			List<String> classNumList = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num_set", classNumList);

			// フォワード
			request.getRequestDispatcher("/main/student_create.jsp").forward(request, response);
			return;
		}

			Student student = new Student();
			student.setNo(no);              // 学生番号
			student.setName(name);       	// 名前
			student.setEntYear(ent_year);          // 入学年度
			student.setClassNum(class_num);         // クラス番号
			student.setAttend(true);           // 在学中フラグ

			School school = new School();      // 学校情報（省略してたらここ必要！）
			school.setCd("tes");
			student.setSchool(school);         // 学校コードをセット

			StudentDao dao = new StudentDao();
			boolean result = dao.save(student);  // ← 登録・更新の実行



        //登録完了後にリダイレクト(仮の成功画面)
		response.sendRedirect("../main/student_create_done.jsp");
		return;
	}

}


