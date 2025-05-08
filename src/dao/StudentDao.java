package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

/**
 * Studentテーブルを操作するためのDAOクラス
 * 学生情報の取得・検索・保存を担当。
 */
public class StudentDao extends Dao{

	// 共通SQL用に定義（未使用）将来まとめるときに使う予定
	private String baseSql = "select * from student where school_cd=?";


	//学生情報の取得

	 /**
     * 学生番号を指定して、学生情報を取得する。
     * @param no 学生番号
     * @return Studentオブジェクト / データが無ければnull
     */

	public Student get(String no) throws Exception {
		 String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, "
	               + " sc.cd AS school_cd, sc.name AS school_name "
	               + " FROM student as s "
	               + " JOIN school as sc ON s.school_cd = sc.cd "
	               + " WHERE s.no = ?";

	    try (Connection con = getConnection();
	    		PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, no);// 学生番号セット
	        try (ResultSet rSet = stmt.executeQuery()) {
	            if (rSet.next()) {
	            	// 学校情報セット
	                School school = new School();
	                school.setCd(rSet.getString("school_cd"));
	                school.setName(rSet.getString("school_name"));

	             // 学生情報セット
	                Student student = new Student();
	                student.setNo(rSet.getString("no"));
	                student.setName(rSet.getString("name"));
	                student.setEntYear(rSet.getInt("ent_year"));
	                student.setClassNum(rSet.getString("class_num"));
	                student.setIsAttend(rSet.getBoolean("is_attend"));
	                student.setSchool(school);

	                return student;// 取得成功時
	            }
	        }
	    }
	    return null;// データなし
	}

    /**
     * 結果セットからStudentオブジェクトのリストを作成する。
     * 学校情報が事前に指定されている場合はセットし、指定されていない場合は結果セットから取得する。
     */
	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
	    List<Student> list = new ArrayList<>();
	try{
	    while (rSet.next()) {
	        Student student = new Student();
	        student.setNo(rSet.getString("no"));
	        student.setName(rSet.getString("name"));
	        student.setEntYear(rSet.getInt("ent_year"));
	        student.setClassNum(rSet.getString("class_num"));
	        student.setIsAttend(rSet.getBoolean("is_attend"));
	        student.setSchool(school);

	        list.add(student);
	    }
	}catch(SQLException | NullPointerException e) {
		e.printStackTrace();
	}
	return list;
	}

    /**
     * 学校・入学年・クラス・在籍状態で学生を検索する。
     */
	public List<Student> filter(Integer entYear, String classNum, Boolean isAttend) throws Exception {

	    List<Student> resultList = new ArrayList<>();

	    StringBuilder sql = new StringBuilder(
	        "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, " +
	        "sc.cd AS school_cd, sc.name AS school_name " +
	        "FROM student AS s JOIN school AS sc ON s.school_cd = sc.cd WHERE 1=1"
	    );

	    if (entYear != null) {
	        sql.append(" AND s.ent_year = ?");
	    }
	    if (classNum != null && !classNum.isEmpty()) {
	        sql.append(" AND s.class_num = ?");
	    }
	    if (isAttend != null) {
	        sql.append(" AND s.is_attend = ?");
	    }

	    try (Connection con = getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql.toString())) {

	        int index = 1;

	        if (entYear != null) {
	            stmt.setInt(index++, entYear);
	        }
	        if (classNum != null && !classNum.isEmpty()) {
	            stmt.setString(index++, classNum);
	        }
	        if (isAttend != null) {
	            stmt.setBoolean(index++, isAttend);
	        }

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {

	            Student student = new Student();
	            student.setNo(rs.getString("no"));
	            student.setName(rs.getString("name"));
	            student.setEntYear(rs.getInt("ent_year"));
	            student.setClassNum(rs.getString("class_num"));
	            student.setIsAttend(rs.getBoolean("is_attend"));

	            resultList.add(student);
	        }
	    }

	    return resultList;
	}



	public List<Student> filter(School school,int entYear,boolean isAttend) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String condition = "and ent_year=? ";
		String order = " order by no asc";



    /**
     * 学校・入学年・在籍状態で学生を検索する。（クラス指定なし）
     */
	public List<Student> filter(School school,int entYear, boolean isAttend) throws Exception {
	    String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, "
	               + " sc.cd AS school_cd, sc.name AS school_name "
	               + " FROM student s "
	               + " JOIN school sc ON s.school_cd = sc.cd "
	               + " WHERE s.school_cd = ? AND s.ent_year = ? AND s.is_attend = ?";

	    try (Connection con = getConnection();
	    		PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, school.getCd());
	        stmt.setInt(2, entYear);
	        stmt.setBoolean(3, isAttend);

	        try (ResultSet rSet = stmt.executeQuery()) {
	            return postFilter(rSet, school);
	        }
	    }
	}

    /**
     * 学校・在籍状態で学生を検索する。（入学年・クラス指定なし）
     */
	public List<Student> filter(School school,boolean isAttend) throws Exception {
	    String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, s.is_attend, "
	               + " sc.cd AS school_cd, sc.name AS school_name "
	               + " FROM student s "
	               + " JOIN school sc ON s.school_cd = sc.cd "
	               + " WHERE s.school_cd = ? AND s.is_attend = ?";

	    try (Connection con = getConnection();
	    		PreparedStatement stmt = con.prepareStatement(sql)) {

	        stmt.setString(1, school.getCd());
	        stmt.setBoolean(2, isAttend);

	        try (ResultSet rSet = stmt.executeQuery()) {
	            return postFilter(rSet, school);
	        }
	    }
	}

    /**
     * 学生情報を登録 or 更新する。
     * 主キー重複時はUPDATE、それ以外はINSERTされる。
     * @param student 保存する学生オブジェクト
     * @return 成功：true / 失敗：false
     */
	public boolean save(Student student) throws Exception {
	    String sql = "INSERT INTO student(no, name, ent_year, class_num, is_attend, school_cd) "
	               + " VALUES (?, ?, ?, ?, ?, ?) "
	               + " ON DUPLICATE KEY UPDATE "
	               + " name = ?, ent_year = ?, class_num = ?, is_attend = ?, school_cd = ?";


		try {
			Student old = get(student.getNo());
			if (old == null) {
				statement = connection.prepareStatement("insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
				statement.setString(1, student.getNo());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.getAttend());
				statement.setString(6, student.getSchool().getCd());
			} else {
				statement = connection.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.getAttend());
				statement.setString(5, student.getNo());
			}

	    try (Connection con = getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql)) {


	    	 // INSERT用パラメータ
	        stmt.setString(1, student.getNo());
	        stmt.setString(2, student.getName());
	        stmt.setInt(3, student.getEntYear());
	        stmt.setString(4, student.getClassNum());
	        stmt.setBoolean(5, student.getIsAttend());
	        stmt.setString(6, student.getSchool().getCd());

	        // UPDATE用パラメータ
	        stmt.setString(7, student.getName());
	        stmt.setInt(8, student.getEntYear());
	        stmt.setString(9, student.getClassNum());
	        stmt.setBoolean(10, student.getIsAttend());
	        stmt.setString(11, student.getSchool().getCd());

	        return stmt.executeUpdate() > 0;// 成功判定
	    }
	}


}