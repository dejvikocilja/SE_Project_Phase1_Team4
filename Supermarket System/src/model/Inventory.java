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

public class Inventory {
	ArrayList<Product> produktet=new ArrayList<Product>();
	
	public static final String filename = "Products.ser";

	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	public static File uf = new File(filename);
	
	public Inventory() {
		readF();
	}

	public void addProduct(Product newProduct) {
		produktet.add(newProduct);
		writeF();
	}
	public ArrayList<String> showProducts() {
		ArrayList<String> allB=new ArrayList<String>();
		for(int i=0;i<produktet.size();i++) {
			
				allB.add(produktet.get(i).toString());

		}
		return allB;
	}
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> allB=new ArrayList<Product>();
		for(int i=0;i<produktet.size();i++) {
			
				allB.add(produktet.get(i));

		}
		return allB;
	}
	public int getPosition(String title)
	{
		for(int i=0; i<produktet.size(); i++)
		{
			if(produktet.get(i).getName().equals(title))
				return i;
		}
		
		return -1;
	}
	public Product getProduct(int pos)
	{
		return produktet.get(pos);
	}
	public double showTotalSpent() {
		ArrayList<String> allB=new ArrayList<String>();
		double total=0.0;
		for(int i=0;i<produktet.size();i++) {
				
			total+=(produktet.get(i).getOrigPrice());
			
		}
		allB.add("The total amount of money of books bought : "+total);
		return total;
	}
	
	public void updateStock(int qnt,String name) {
		
		for(int i=0;i<produktet.size();i++) {
			if(produktet.get(i).name.equals(name)) {
				produktet.get(i).setStock(produktet.get(i).getStock()+qnt);
				writeF();
				break;
			}
		}
	}
	
	//we check for missing products so that we are notified that we have to get new ones
	public ArrayList<String> checkForMissing() {
		ArrayList<String> missing=new ArrayList<String>();
		for(int i=0;i<produktet.size();i++) {
			if(produktet.get(i).stock<5) {
				missing.add("Produkti : "+produktet.get(i).name+" has less than 5 pieces in stock");
			}
		}
		return missing;
	}
	
	@SuppressWarnings("unchecked")
	private void readF() {
		try {
			// use buffering
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			// deserialize the List
			produktet = (ArrayList<Product>) input.readObject();
			// display its data
			
		} catch (ClassNotFoundException ex) {
			System.out.println("File Not well defined. Creating new file"
					+ ex.toString());
			//addBook(new Book("test",23.3,"test",3));
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
			output.writeObject(produktet);
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
	public boolean isThere(String name) {
		for(int i=0; i<produktet.size(); i++)
		{
			if(produktet.get(i).getName().equals(name))
				return true;
		}
		
		return false;
	}
}
