package model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -5083759422249745403L;
	private String user;
	private String pass;
	private final int id;
	public static int x=0;
	private String name, sname;
	private String status; //1-Administrator, 2-Manager, 3-Cashier
	private String phone;
	private double salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public User() {
		this(x++, "","","Librarian");
	}
	public User(int id,String user, String pass, String status) {
		super();

		this.id=id;
		this.user = user;
		this.pass = pass;
		this.status = status;
	}
	public int getid() {
		return this.id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String toString(){
		return "Id: "+this.getid()+"\nUser: "+this.getUser()+
				"\nPass: "+this.getPass()+"\nStatus: "+this.getStatus()+"\nSalary: "+this.getSalary()+"\nPhone Number: "+this.getPhone();
	}
}
