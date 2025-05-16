package main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

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
		HttpSession session = request.getSession();

		Integer ent_year = Integer.parseInt(request.getParameter("ent_year"));
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String class_num = request.getParameter("class_num");
        Boolean is_attend = Boolean.parseBoolean(request.getParameter("is_attend"));
        boolean hasError = false;


        Teacher teacher = (Teacher)session.getAttribute("teacher");

	    String student_name = null;

		if (name == null || name.trim().isEmpty()) {
			student_name = "このフィールドを入力してください。";
			hasError = true;
		}

        // エラーがあった場合は再入力画面へ戻す
        if (hasError) {

    		// リクエストパラメータから学生番号を取得
    	    String studentNo = request.getParameter("no");


    	    // そのIDに対応する学生情報をDBから取得する
    	    StudentDao studentdao = new StudentDao();
    	    Student student = studentdao.get(studentNo);

    	    //ClassNumDaoのインスタンスを作成
    	    ClassNumDao cNumDao = new ClassNumDao();

    		//ビジネスロジック

    		//DBからデータ取得
    		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
    		List<String> list = cNumDao.filter(teacher.getSchool());

    		//リクエストに変更したい学生情報をセット
    		request.setAttribute("student", student);

    		//リクエストにクラス番号をセット
    		request.setAttribute("class_num_set", list);
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

			StudentDao dao = new StudentDao();
			boolean result = dao.save(student);  // ← 登録・更新の実行



        //登録完了後にリダイレクト(仮の成功画面)
		response.sendRedirect("../main/student_update_done.jsp");
		return;
	}

}


