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

public class ClientsList {
	public static ArrayList<Client> klientet=new ArrayList<Client>();
	
	public static final String filename = "Clients.ser";

	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	public static File uf = new File(filename);
	
	public 	ClientsList() {
		readF();
	}

	public void addClient(Client newClient) {
		klientet.add(newClient);
		writeF();
	}
	public ArrayList<String> showClients() {
		ArrayList<String> allB=new ArrayList<String>();
		for(int i=0;i<klientet.size();i++) {
			
				allB.add(klientet.get(i).toString());

		}
		return allB;
	}
	
	public ArrayList<Client> getClients() {
		ArrayList<Client> allB=new ArrayList<Client>();
		for(int i=0;i<klientet.size();i++) {
			
				allB.add(klientet.get(i));

		}
		return allB;
	}
	////////////////////////
	public int getPositionByPhoneNumber(String phoneNumber)
	{
		for(int i=0; i<klientet.size(); i++)
		{
			if(klientet.get(i).phoneNumber.equals(phoneNumber))
				return i;
		}
		
		return -1;
	}
	public Client getClient(int pos)
	{
		
		return klientet.get(pos);
	}

	public boolean isThere(String phoneNumber) {
		for(int i=0; i<klientet.size(); i++)
		{
			if(klientet.get(i).phoneNumber.equals(phoneNumber))
				return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	private void readF() {
		try {
			// use buffering
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			// deserialize the List
			klientet = (ArrayList<Client>) input.readObject();
			// display its data
			
		} catch (ClassNotFoundException ex) {
			System.out.println("File Not well defined. Creating new file"
					+ ex.toString());
		} catch (IOException ex) {
			System.out.println("Err1");
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
			output.writeObject(klientet);
		} catch (IOException ex) {
			System.out.println("Err2");
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
	
	public int getDiscountPercentage(String phoneNumber) {
		Client klienti = getClient(getPositionByPhoneNumber(phoneNumber));
		if(klienti.fidelityPoints>=30) {
			return 5;
		}
		else if(klienti.fidelityPoints>=300) {
			return 10;
		}
		else if(klienti.fidelityPoints>=550) {
			return 15;
		}
		else {
			return 1;
		}
	}
	
	public void addFidelityPoints(String phoneNumber,int points) {
		System.out.println("edhe ke piket");
		int pos = getPositionByPhoneNumber(phoneNumber);
		klientet.get(pos).addPoints(points);
		this.writeF();
		return;
	}

	}
	
