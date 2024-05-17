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

public class AccessCashiers {
	public static final String filename = "Employees.ser";

	private ArrayList<Cashier> cashiers = new ArrayList<Cashier>();
	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessCashiers() {
		readF();
	}


	public void addCashier(Cashier kashieri) {
		cashiers.add(kashieri);
		writeF();
	}

	public String readS() {
		readF();
		String read = "";
		for (Cashier x : cashiers)
			read += "\n-------------User " + x.getId()
			+ "--------------------\n" + x.toString()
			+ "\n>---------------------------<\n";
		return read;
	}

	public ArrayList<Cashier> getCashiers()
	{
		/*this.readF();
		this.closeFile();*/
		return cashiers;
	}
	
	public void rm(int id) {
		boolean rm = false;
		for (Cashier x : cashiers)
			if (x.getId() == id) {
				rm = true;
				cashiers.remove(cashiers.indexOf(x));
			}
		if (!rm)
			System.out.println("Not Found");
	}
	
	public int getPosition(Cashier kashieri)
	{
		for(int i=0; i<cashiers.size(); i++)
		{
			if(cashiers.get(i).toString().equals(kashieri.toString()))
				return i;
		}
		
		return -1;
	}
	
	public void setSalary(Cashier lib, double salary)
	{
		int pos = getPosition(lib);
		
		cashiers.get(pos).setSalary(salary);
		
		this.writeF();
	}
	
	public void editLibrarian(int pos, Cashier kashieri)
	{
		cashiers.set(pos,kashieri);
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
			cashiers = (ArrayList<Cashier>) input.readObject();
			// display its data
			for (Cashier kashieri: cashiers) {
				System.out.println("Data: " + kashieri.toString());
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("File Not well defined. Creating new file"
					+ ex.toString());
			addCashier(new Cashier("test","test","+32366653456"));
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
			output.writeObject(cashiers);
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
