package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;


public class SubjectListAction extends Action  {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
        try {
            List<Subject> subjectList = getSubjectList();

            request.setAttribute("subjectList", subjectList);
            request.getRequestDispatcher("/main/subject_list.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    private List<Subject> getSubjectList() throws Exception {
        List<Subject> subjectList = new ArrayList<>();

        String sql = "SELECT s.cd, s.name, sc.cd AS school_cd, sc.name AS school_name "
                   + "FROM subject s "
                   + "JOIN school sc ON s.school_cd = sc.cd";

        try (Connection con = new SubjectDao().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                School school = new School();
                school.setCd(rs.getString("school_cd"));
                school.setName(rs.getString("school_name"));

                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setName(rs.getString("name"));
                subject.setSchool(school);

                subjectList.add(subject);
            }
        }

        return subjectList;
    }
}
