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

public class BillList {
	public ArrayList<Bill> faturat=new ArrayList<Bill>();
	public static final String filename = "Bills.ser";

	InputStream file, buffer;
	OutputStream bf, fl;
	ObjectInput input;
	ObjectOutput output;
	File uf = new File(filename);
	
	public BillList() {
		readF();
	}
	public void createBill(Bill newBill) {
		faturat.add(newBill);
		writeF();
	}

	public ArrayList<String> showBills() {
		ArrayList<String> allB=new ArrayList<String>();
		for(int i=0;i<faturat.size();i++) {
			
				allB.add(faturat.get(i).toString());

		}
		return allB;
	}
	
	public double showTotalIncomes() {
		ArrayList<String> allB=new ArrayList<String>();
		double total=0.0;
		for(int i=0;i<faturat.size();i++) {
				
			total+=faturat.get(i).totalBill();
				

		}
		allB.add("The total amount of money from products sold : "+total);
		return	total;
	}
	
	public ArrayList<String> showProductsSold() {
		ArrayList<String> allB=new ArrayList<String>();
		int totalQnt=0;
		for(int i=0;i<faturat.size();i++) {
			ArrayList<Integer> qnt=faturat.get(i).getSoldQnt();
			ArrayList<String> titleSold=faturat.get(i).getProductsSold();
			for(int j=0;j<qnt.size();j++) {
				totalQnt+=qnt.get(j);
				allB.add("Product : "+titleSold.get(j)+" | Quantity : "+qnt.get(j)+"\n------------");
			}
				
		}
		allB.add("Total Products Sold : "+String.valueOf(totalQnt));
		return allB;
	}
	
	
	public double totalOfBills() {
		double total=0.0;
		for(int i=0;i<faturat.size();i++) {
			ArrayList<Double> prices=faturat.get(i).getPrices();
			for(int j=0;j<prices.size();j++) {
				total+=prices.get(j);
			}
		}
		return total;
	}
	
	
	@SuppressWarnings("unchecked")
	private void readF() {
		try {
			// use buffering
			file = new FileInputStream(uf);
			buffer = new BufferedInputStream(file);
			input = new ObjectInputStream(buffer);
			// deserialize the List
			faturat = (ArrayList<Bill>) input.readObject();
			// display its data
			for (Bill fat:faturat) {
				//System.out.println("Data: " + lib.toString());
			}
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
			output.writeObject(faturat);
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
}
