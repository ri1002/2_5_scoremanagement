package bean;

import java.io.Serializable;

public class Student implements Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;


	public String getNo(){
		return this.no;
	}
	public String getName(){
		return this.name;
	}
	public int getEntYear(){
		return this.entYear;
	}
	public String getClassNum(){
		return this.classNum;
	}
<<<<<<< HEAD
	public boolean getAttend(){
=======
	public boolean getIsAttend(){
>>>>>>> branch 'master' of https://github.com/ri1002/2_5_scoremanagement.git
		return this.isAttend;
	}
	public School getSchool(){
		return this.school;
	}


	public void setNo(String no){
		this.no = no;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEntYear(int entYear){
		this.entYear = entYear;
	}
	public void setClassNum(String classNum){
		this.classNum = classNum;
	}
	public void setIsAttend(boolean isAttend){
		this.isAttend = isAttend;
	}
	public void setSchool(School school){
		this.school = school;
	}
}