package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    public List<Subject> getAll() throws Exception {

        List<Subject> subjectList = new ArrayList<>();

        String sql = "SELECT s.cd, s.name, sc.cd AS school_cd, sc.name AS school_name "

                   + "FROM subject s "

                   + "JOIN school sc ON s.school_cd = sc.cd";

        try (Connection con = getConnection();

             PreparedStatement stmt = con.prepareStatement(sql);

             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Subject subject = new Subject();

                School school = new School();

                school.setCd(rs.getString("school_cd"));

                school.setName(rs.getString("school_name"));

                subject.setCd(rs.getString("cd"));

                subject.setName(rs.getString("name"));

                subject.setSchool(school);

                subjectList.add(subject);

            }

        }

        return subjectList;

    }

    public Subject get(String cd) throws Exception {

        String sql = "SELECT s.cd, s.name, sc.cd AS school_cd, sc.name AS school_name "

                   + "FROM subject s "

                   + "JOIN school sc ON s.school_cd = sc.cd "

                   + "WHERE s.cd = ?";

        try (Connection con = getConnection();

             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, cd);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Subject subject = new Subject();

                    School school = new School();

                    school.setCd(rs.getString("school_cd"));

                    school.setName(rs.getString("school_name"));

                    subject.setCd(rs.getString("cd"));

                    subject.setName(rs.getString("name"));

                    subject.setSchool(school);

                    return subject;

                }

            }

        }

        return null; // 見つからなかった

    }


/*    public boolean save(Subject subject) throws Exception {

        String sql = "INSERT INTO subject(cd, name, school_cd) "

                   + "VALUES (?, ?, ?) "

                   + "ON DUPLICATE KEY UPDATE name = ?, school_cd = ?";

        try (Connection con = getConnection();

             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, subject.getCd());

            stmt.setString(2, subject.getName());

            stmt.setString(3, subject.getSchool().getCd());

            // ON DUPLICATE KEY UPDATE

            stmt.setString(4, subject.getName());

            stmt.setString(5, subject.getSchool().getCd());

            return stmt.executeUpdate() > 0;

        }

    }*/

    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(

            "SELECT * FROM subject WHERE school_cd = ? ORDER BY cd"

        );

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Subject subject = new Subject();

            subject.setSchool(school);

            subject.setCd(rs.getString("cd"));

            subject.setName(rs.getString("name"));

            list.add(subject);

        }

        st.close();

        con.close();

        return list;

    }

    public boolean save(Subject subject) throws Exception {

		Connection connection = getConnection();

		PreparedStatement statement = null;

		int count = 0;


		try {

			Subject old = get(subject.getCd());

			if (old == null) {

				statement = connection.prepareStatement("insert into subject(school_cd,cd,name) values(?, ?, ?)");

				statement.setString(1, subject.getSchool().getCd());

				statement.setString(2, subject.getCd());

				statement.setString(3, subject.getName());

			} else {

				statement = connection.prepareStatement("update subject set school_cd=?, cd=?, name=? where cd=?");

				statement.setString(1, subject.getSchool().getCd());

				statement.setString(2, subject.getCd());

				statement.setString(3, subject.getName());

				statement.setString(4, subject.getCd());

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

	}

}
