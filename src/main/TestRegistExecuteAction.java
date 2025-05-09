package main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 教師・学校情報
    	Teacher teacher = (Teacher)session.getAttribute("teacher");
    	session.setAttribute("user", teacher);

    	String regist = request.getParameter("regist");
    	Integer count = (Integer.parseInt(request.getParameter("count")));
    	String subject = request.getParameter("subject");


        // パラメータ取得
        /*String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");

        // バリデーション
        Map<String, String> errors = new HashMap<>();
        int entYear = 0;
        boolean hasError = false;

        if (entYearStr == null || entYearStr.equals("0")) {
            errors.put("f1", "入学年度を選択してください。");
            hasError = true;
        } else {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "入学年度が不正です。");
                hasError = true;
            }
        }

        if (classNum == null || classNum.equals("0")) {
            errors.put("f2", "クラスを選択してください。");
            hasError = true;
        }

        // エラーがある場合はフォームに戻す
        if (hasError) {
            // クラス番号再取得
            ClassNumDao cNumDao = new ClassNumDao();
            List<String> classNumSet = cNumDao.filter(school);

            // 科目一覧取得（必要なら）
            SubjectDao subjectDao = new SubjectDao();
            List subjects = subjectDao.filter(school);

            // エラーと元データをセットして戻す
            request.setAttribute("errors", errors);
            request.setAttribute("f1", entYearStr);
            request.setAttribute("f2", classNum);
            request.setAttribute("class_num_set", classNumSet);
            request.setAttribute("subjects", subjects);

            request.getRequestDispatcher("/main/test_regist.jsp").forward(request, response);
            return;
        }

        // 検索実行
        StudentDao sDao = new StudentDao();
        boolean isAttend = false;
        List<Student> students = sDao.filter(school, entYear, classNum, isAttend);

        // 検索結果をセットして結果JSPへ
        request.setAttribute("students", students);
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);

        request.getRequestDispatcher("/main/test_regist_done.jsp").forward(request, response);*/
    }
}
