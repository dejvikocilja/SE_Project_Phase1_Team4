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

public class AccessUsers {
	public static final String filename = "Users.ser";

	private ArrayList<User> users = new ArrayList<User>();
	
	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);

	public AccessUsers() {
		readF();
	}

	public User checkUser(String user, String pass) {
		for (User x : users) {
			if (x.getUser().equals(user) && x.getPass().equals(pass)) {
				return x;
			}
		}

		return null;
	}
	
	public ArrayList<String> showUsers() {
		ArrayList<String> allB=new ArrayList<String>();
		for(int i=0;i<users.size();i++) {
			
				allB.add(users.get(i).toString());
				allB.add("----------------------------");

		}
		return allB;
	}
	
	public void addUser(User user) {
		users.add(user);
		writeF();
	}

	public String readS() {
		readF();
		String read = "";
		for (User x : users)
			read += "\n-------------User " + x.getid()
			+ "--------------------\n" + x.toString()
			+ "\n>---------------------------<\n";
		return read;
	}

	public ArrayList<User> getUsers()
	{
		this.readF();
		return this.users;
	}
	
	public void rm(String username) {
		boolean rm = false;
		for (User x : users)
			if (x.getUser().equals(username)) {
				rm = true;
				users.remove(users.indexOf(x));
				writeF();
			}
		if (!rm)
			System.out.println("Not Found");
	}

	public int getPosition(String username)
	{
		for(int i=0; i<users.size(); i++)
		{
			if(users.get(i).getUser().equals(username))
				return i;
		}
		
		return -1;
	}
	public User getUser(int pos)
	{
		
		return users.get(pos);
	}
	
	public void editUser(int pos, User u)
	{
		System.out.println(">>>>"+pos);
		if(pos < 0 || pos >= users.size())
		{
			System.out.println("Cannot find User");
			return;
		}
		else
		{
			users.set(pos, u);
			this.writeF();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void readF() {
		try {
			// use buffering
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			// deserialize the List
			users = (ArrayList<User>) input.readObject();
			// display its data
			for (User user : users) {
				System.out.println("Data: " + user.toString());
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("Not found. Creating new file"
					+ ex.toString());
			addUser(new User(0,"admin","admin","Administrator"));
		} catch (IOException ex) {
			System.out.println("Cannot perform input." + ex.toString());
		}
		closeFile();
	}

	private void writeF() {
		// TODO Auto-generated method stub
		// serialize the List
		try {
			fl = new FileOutputStream(uf);
			bf = new BufferedOutputStream(fl);
			output = new ObjectOutputStream(bf);
			output.writeObject(users);
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
