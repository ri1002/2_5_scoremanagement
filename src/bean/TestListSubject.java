package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable{
	private int entYear;
	private String studentNo;
	private String studentName;
	private String classNum;
	private Map<Integer, Integer> points;
	
	
	public int getEntYear(){
		return this.entYear;
	}
	public String getStudentNo(){
		return this.studentNo;
	}
	public String getStudentName(){
		return this.studentName;
	}
	public String getClassNum(){
		return this.classNum;
	}
	public Map<Integer,Integer> getPoints(){
		return this.points;
	}
	public String getPoint(int key) {
        Integer value = this.points.get(key);
        return value != null ? String.valueOf(value) : null;
    }
	
	
	
	public void setEntYear(int entYear){
		this.entYear=entYear;
	}
	public void setStudentNo(String studentNo){
		this.studentNo=studentNo;
	}
	public void setStudentName(String studentName){
		this.studentName=studentName;
	}
	public void setClassNum(String classNum){
		this.classNum=classNum;
	}
	public void setPoints(Map<Integer,Integer> points){
		this.points=points;
	}
	
	public void putPoint(int key,int value){
		this.points.put(key, value);
	}
	
}
