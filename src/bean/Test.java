package bean;

public class Test implements java.io.Serializable {
	private boolean isAuthenticated;
	private Student student;
	private String classNum;
	private Subject subject;
	private School school;
	private Integer no;
	private Integer point;

	public boolean getIsAuthenticated(){
		return this.isAuthenticated;
	}

	public void setIsAuthenticated(boolean isAuthenticated){
		this.isAuthenticated=isAuthenticated;
	}

	public Student getStudent(){
		return this.student;
	}

	public void setStudent(Student student){
		this.student=student;
	}
	public String getClassNum(){
		return this.classNum;
	}

	public void setClassNum(String classNum){
		this.classNum=classNum;
	}

	public Subject getSubject() {
	    return this.subject;
	}


	public void setSubject(Subject subject){
		this.subject=subject;
	}

	public School getSchool(){
		return this.school;
	}

	public void setSchool(School school){
		this.school=school;
}

	public Integer getNo(){
		return this.no;
	}

	public void setNo(int no){
		this.no=no;
}

	public Integer getPoint(){
		return this.point;
	}

	public void setPoint(Integer point){
		this.point=point;
}
}