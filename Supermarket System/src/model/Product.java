package model;
import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String isbn;
	String name;
	String category;
	String supplier;
	String purchDate;
	double purchPrice;
	double origPrice;
	double sellingPrice;
	int stock;
	
	public Product(String isbn,String name,double origPrice,String category,int stock,String supplier,String purchDate,double sellingPrice) {
		this.isbn=isbn;
		this.name=name;
		this.origPrice=origPrice;
		this.category=category;
		this.stock=stock;
		this.supplier=supplier;
		this.purchDate=purchDate;
		this.sellingPrice=sellingPrice;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String iSBN) {
		isbn = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPurchDate() {
		return purchDate;
	}

	public void setPurchDate(String purchDate) {
		this.purchDate = purchDate;
	}

	public double getPurchPrice() {
		return purchPrice;
	}

	public void setPurchPrice(double purchPrice) {
		this.purchPrice = purchPrice;
	}

	public double getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(double origPrice) {
		this.origPrice = origPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String toString() {
		return String.format("%1$"+30+"s",name+"                  | "+sellingPrice+"   | "+category+" |   "+stock+"\n----------------------------------");
	}
	
	/**
	 * 
	 * @return
	 * validator function made just in case we want to add a more solid
	 * validation system
	 * Currently enable to make testing much more user-friendly ;)
	 * 
	 */
	public String isValid() {
        if (!isbn.matches("\\d{13}"))
            return "ISBN must contain exactly 13 digits with no spaces/dashes.";
        if (sellingPrice < 0)
            return "Selling Price cannot be negative.";
        if (origPrice < 0)
            return "Purchased Price cannot be negative.";
        if (!name.matches("([\\p{L}\\p{N}_']\\s*){1,30}"))
            return "Title must contain 1 to 30 lower/upper case letters numbers spaces or underscore.";
        if (stock < 0)
            return "Quantity cannot be negative.";
        return "1";
    }
}
