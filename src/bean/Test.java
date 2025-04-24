package bean;

public class Test implements java.io.Serializable {
	private boolean isAuthenticated;

	public boolean getIsAuthenticated(){
		return this.isAuthenticated;
	}

	public void setIsAuthenticated(boolean isAuthenticated){
		this.isAuthenticated=isAuthenticated;
	}

}