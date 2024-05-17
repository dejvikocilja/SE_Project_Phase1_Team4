package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class AccessAdministrators {
	public static final String filename = "Employees.ser";

	private ArrayList<Administrator> administrators = new ArrayList<Administrator>();
	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessAdministrators() {
		readF();
	}


	public void addAdministrator(Administrator adm) {
		administrators.add(adm);
		writeF();
	}

	public String readS() {
		readF();
		String read = "";
		for (Administrator x: administrators)
			read += "\n-------------User " + x.getId()
			+ "--------------------\n" + x.toString()
			+ "\n>---------------------------<\n";
		return read;
	}

	public ArrayList<Administrator> getAdministrators()
	{
		/*this.readF();
		this.closeFile();*/
		return administrators;
	}
	
	public void rm(int id) {
		boolean rm = false;
		for (Administrator x: administrators)
			if (x.getId() == id) {
				rm = true;
				administrators.remove(administrators.indexOf(x));
			}
		if (!rm)
			System.out.println("Not Found");
	}
	
	public int getPosition(Administrator adm)
	{
		for(int i=0; i<administrators.size(); i++)
		{
			if(administrators.get(i).toString().equals(adm.toString()))
				return i;
		}
		
		return -1;
	}
	
	public void setSalary(Administrator adm, double salary)
	{
		int pos = getPosition(adm);
		
		administrators.get(pos).setSalary(salary);
		
		this.writeF();
	}
	
	public void editAdministrator(int pos, Administrator adm)
	{
		administrators.set(pos,adm);
		this.writeF();
	}

	
	@SuppressWarnings("unchecked")
	private void readF() {
		try {
			// use buffering
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			// deserialize the List
			administrators = (ArrayList<Administrator>) input.readObject();
			// display its data
			for (Administrator adm: administrators) {
				System.out.println("Data: " + adm.toString());
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("File Not well defined. Creating new file"
					+ ex.toString());
			addAdministrator(new Administrator("test","test","+32366653456"));
		} catch (IOException ex) {
			System.out.println("Cannot perform input." + ex.toString());
		}
		closeFile();
	}

	private void writeF() {
		// serialize the List
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(administrators);
		} catch (IOException ex) {
			System.out.println("Cannot perform output." + ex.toString());
		}
		closeFile();
	}

	public void closeFile() {
		try {
			if (input != null) {
				input.close();
				buffer.close();
				file.close();
			}
			if (output != null) {
				output.close();
				bf.close();
				fl.close();
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}

