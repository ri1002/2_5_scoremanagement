package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
	public ClassNum get(String class_num, School school)throws Exception {
		 String sql = "SELECT school_cd, class_num"
	               + " FROM CLASS_NUM "
	               + " WHERE class_num = ? AND school_cd = ?";

	    try (Connection con = getConnection();
	    		PreparedStatement stmt = con.prepareStatement(sql)) {
	        	stmt.setString(1, class_num);
	        	stmt.setString(2, school.getCd());

	        try (ResultSet rSet = stmt.executeQuery()) {
	            if (rSet.next()) {

	             // クラス情報セット
	                ClassNum classNum = new ClassNum();
	                classNum.setClass_num(rSet.getString("class_num"));
	             // 学校コードだけセットする場合
	                School s = new School();
	                s.setCd(rSet.getString("school_cd"));
	                classNum.setSchool(s);

	                return classNum;// 取得成功時
	            }
	        }

	        return null;
	    }
	}

	public List<ClassNum> filter() throws Exception {
	    List<ClassNum> classNumList = new ArrayList<>();
	    String sql = "SELECT class_num FROM class_num";

	    try (Connection con = new StudentDao().getConnection();
	         PreparedStatement stmt = con.prepareStatement(sql);
	         ResultSet rSet = stmt.executeQuery()) {

	        while (rSet.next()) {
	            ClassNum classnum = new ClassNum();
	            classnum.setClass_num(rSet.getString("class_num"));
	            classNumList.add(classnum);
	        }
	    }
	    return classNumList;
	/*public boolean save(ClassNum classNum) throws Exception {

	}
<<<<<<< master
//
//	public boolean save(ClassNum classNum) throws Exception {
//	}
//
//	public boolean save(ClassNum classNum,String newClassNum) throws Exception {
//	}
}
=======

	public boolean save(ClassNum classNum, String newClassNum) throws Exception {
*/
	}
}
>>>>>>> b52bd92 login画面とerror画面を作り、学生一覧のjava,jspと、フロントコントローラー、Actionを修正
