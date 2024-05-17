package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name,surname,phoneNumber;
	int fidelityPoints;
	
	public Client(String name,String surname,String phoneNumber) {
		this.name=name;
		this.surname=surname;
		this.phoneNumber=phoneNumber;
		fidelityPoints=0;
	}
	public void addPoints(int pointsToAdd) {
		fidelityPoints+=pointsToAdd;
	}
	public String toString() {
		return name+" "+surname+" "+phoneNumber+" "+fidelityPoints;
	}
	
}
