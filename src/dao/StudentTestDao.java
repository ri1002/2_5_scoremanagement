package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.TestListStudent;

public class StudentTestDao extends Dao {

    public List<TestListStudent> filter(String studentNo, School school) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        String sql = "SELECT t.subject_cd, sub.name AS subject_name, t.no AS test_no, t.point " +
                     "FROM test t " +
                     "JOIN subject sub ON t.subject_cd = sub.cd " +
                     "WHERE t.student_no = ? AND sub.school_cd = ? " +
                     "ORDER BY t.subject_cd, t.no";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, studentNo);
            statement.setString(2, school.getCd());
            rSet = statement.executeQuery();

            return postFilter(rSet);
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    protected List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent test = new TestListStudent();
            test.setSubjectCd(rSet.getString("subject_cd"));
            test.setSubjectName(rSet.getString("subject_name"));
            test.setNum(rSet.getInt("test_no"));
            int point = rSet.getInt("point");
            test.setPoint(rSet.wasNull() ? 0 : point);
            list.add(test);
        }
        return list;
    }
}
