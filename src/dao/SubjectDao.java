package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

/**
 * Subject（科目）テーブルを操作するDAOクラス
 */
public class SubjectDao extends Dao {

    /**
     * 全ての科目を取得する
     */
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

    /**
     * 科目コードで科目を1件取得
     */
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

    /**
     * 科目情報を保存（新規登録または更新）
     * @param subject 保存する科目オブジェクト
     * @return 成功：true / 失敗：false
     */
    public boolean save(Subject subject) throws Exception {
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
    }

}
