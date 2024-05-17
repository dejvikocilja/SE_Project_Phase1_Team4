package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String billNo;
	ArrayList<Integer> soldQnt=new ArrayList<Integer>();
	ArrayList<Double> prices=new ArrayList<Double>();
	String dateOfTrans;
	ArrayList<String> productsSold=new ArrayList<String>();
	String client=null;
	int discountPercentage=1;
	public Bill(String billNo,ArrayList<TextField> soldQnt,ArrayList<Double> prices,String dateOfTrans,ArrayList<String> productsSold) {
		this.billNo=billNo;
		for(int i=0;i<soldQnt.size();i++) {
			this.soldQnt.add(Integer.parseInt(soldQnt.get(i).getText()));
		}
		for(int i=0;i<prices.size();i++) {
			this.prices.add(prices.get(i));
		}
		this.dateOfTrans=dateOfTrans;
		for(int i=0;i<productsSold.size();i++) {
			this.productsSold.add(productsSold.get(i));
		}
	}
	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public ArrayList<Integer> getSoldQnt() {
		return soldQnt;
	}

	public ArrayList<Double> getPrices() {
		return prices;
	}

	public ArrayList<String> getProductsSold() {
		return productsSold;
	}

	public String getDateOfTrans() {
		return dateOfTrans;
	}

	public void setDateOfTrans(String dateOfTrans) {
		this.dateOfTrans = dateOfTrans;
	}
	
	public double totalBill() {
		double amountTotal=0.0;
		for(int i=0;i<prices.size();i++) {
			amountTotal+=prices.get(i)*soldQnt.get(i);
		}

		if(client==null) {
			return amountTotal;
		}
		else {
			return amountTotal-(amountTotal/discountPercentage);
		}

	}
	
	public void setDiscountPercentage(int percentage) {
		this.discountPercentage = percentage;
	}
	public int getDiscountPercentage() {
		return discountPercentage;
	}
	
	public void setClient(String client) {
		this.client=client;
	}
	public String toString() {
		return "Bill Number : "+billNo+" | Quantity : "+soldQnt+" | Prices : "+prices+"\nTotal of Bill : "+totalBill()+" | Date : "+dateOfTrans+"\n--------------------------------\n";
	}
}
