package model;

import java.io.Serializable;

public class Administrator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int x = -1;
	private final int id;
	private String name, sname;
	//private BDate birthday;
	private String phone;
	private double salary;
	
	public Administrator(String name, String sname, String phone) {
		super();
		
		if(x < 0) {
			x = 1;
		}
		else
		{
			x++;
		}
		
		this.id = x;
		this.name = name;
		this.sname = sname;
		
		this.phone = phone;
		this.salary = 0;
		
	}
	
	public Administrator(String name, String sname) {
		super();
		
		if(x < 0) {
			x = 1;
		}
		else
		{
			x++;
		}
		
		this.id = x;
		
		this.name = name;
		this.sname = sname;
		
		this.salary = 0;
		
		this.phone = "N/A";
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public User genUser() {
		User x = new User();
		x.setUser((this.name.charAt(0)+this.sname).toLowerCase());
		x.setPass((this.sname.substring(0, 2)+"02").toLowerCase());
		x.setStatus("Administrator");
		return x;
	}
	public String getName() {
		return name;
	}
	public String getSname() {
		return sname;
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
	

	@Override
	public String toString() {
		return "Employee name: " + name + "\n Surname: " + sname + "\n Phone: " + phone + "\n salary: " + salary ;
	}

}


