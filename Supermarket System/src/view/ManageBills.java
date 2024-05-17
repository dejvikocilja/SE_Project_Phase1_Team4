package view;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bill;
import model.BillList;
import model.ClientsList;
import model.Inventory;
import model.Product;
import model.User;

public class ManageBills {
	protected static Scene clientBill(Inventory inventari,BillList faturat,Stage primaryStage,User currentUser,ClientsList klientat) {
		
		int bookCnt=1;
		StackPane root=new StackPane();	
		
		Text loyalClient = new Text("Loyal Client (Phone Number)");
        loyalClient.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
		Text text1 = new Text("Bill Number");  
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        ArrayList<Text> productsQnt = new ArrayList<Text>();
        //creating label password 
        productsQnt.add(new Text("Quantity of product "+(productsQnt.size()+1)));
        productsQnt.get(0).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        ArrayList<TextField> productsQntField = new ArrayList<TextField>();
        productsQntField.add(new TextField());
        
        Text text4 = new Text("Date of Transaction");  
        text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
  	  	
        ArrayList<Text> productsName = new ArrayList<Text>();
        productsName.add(new Text("Name of product "+(productsName.size()+1)));  
        productsName.get(0).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
      
        TextField loyalClientField = new TextField();
        TextField textField1 = new TextField();        

        TextField textField4 = new TextField(); 
        
        ArrayList<TextField> productsFields=new ArrayList<TextField>();
        productsFields.add(new TextField());
        GridPane gridPane = new GridPane();    
         
        gridPane.setMinSize(400, 400); 
          
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        gridPane.setAlignment(Pos.CENTER); 
         
        //Arranging all the nodes in the grid 
       // gridPane.add(loginLabel, 2, 0);
        gridPane.add(loyalClient, 0, 0); 
        gridPane.add(loyalClientField, 1, 0); 
        gridPane.add(text1, 0, 1); 
        gridPane.add(textField1, 1, 1); 
        gridPane.add(productsName.get(0), 0, 2);       
        gridPane.add(productsFields.get(0), 1, 2); 
        gridPane.add(productsQnt.get(0), 0, 3);       
        gridPane.add(productsQntField.get(0), 1, 3); 
        gridPane.add(text4, 0, 9);       
        gridPane.add(textField4, 1, 9); 
       
        
        Button addBook=new Button("Add Product");
        Button button1 = new Button("Create"); 
        
        addBook.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent h) {
        		productsName.add(new Text("Name of product "+(productsName.size()+1)));
          		productsName.get(productsName.size()-1).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
                productsFields.add(new TextField());
                gridPane.add(productsName.get(productsName.size()-1), 0, productsName.size()-1+2);
                gridPane.add(productsFields.get(productsFields.size()-1), 1, productsFields.size()-1+2);
                
                productsQnt.add(new Text("Quantity of Product "+(productsQnt.size()+1)));
                productsQnt.get(productsQnt.size()-1).setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
                productsQntField.add(new TextField());
                gridPane.add(productsQnt.get(productsQnt.size()-1), 0, productsQnt.size()-1+4);
                gridPane.add(productsQntField.get(productsQntField.size()-1), 1, productsQntField.size()-1+4);
               
        	}
        	
        });
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	ArrayList<Integer> pos=new ArrayList<Integer>();
            	ArrayList<Product> produktet=new ArrayList<Product>();
            	ArrayList<Double> prices=new ArrayList<Double>();
            	ArrayList<String> emrat=new ArrayList<String>();
            	for(int i=0;i<productsName.size();i++) {
            		pos.add(inventari.getPosition(productsFields.get(i).getText()));
            		produktet.add(inventari.getProduct(pos.get(i)));
            		inventari.updateStock(Integer.parseInt(productsQntField.get(i).getText())-(Integer.parseInt(productsQntField.get(i).getText())*2),productsFields.get(i).getText());
            		prices.add(produktet.get(i).getSellingPrice());
            		emrat.add(productsFields.get(i).getText());
            	}
            	

            	Bill fatur=new Bill(textField1.getText(),productsQntField,prices,textField4.getText(),emrat);
            	
            	/**
            	 * Implementing Discount process based on the fidelity point of the Client
            	 * (At the moment, for every purchase process, the client gets 10 fidelity Points
            	 */
            	if(loyalClientField.getText()!=null) {
            		if(klientat.getPositionByPhoneNumber(loyalClientField.getText())!=-1) {
            			klientat.addFidelityPoints(loyalClientField.getText(), 10);
            			if(klientat.getDiscountPercentage(loyalClientField.getText())>1) {
            			fatur.setClient(loyalClientField.getText());
            			fatur.setDiscountPercentage(klientat.getDiscountPercentage(loyalClientField.getText()));
            			}
            		}
            	}
            	faturat.createBill(fatur);
            	
            	Button button3=new Button("Print Bill");
            	EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                    	  try {
                              FileWriter writer = new FileWriter("Bill"+fatur.getBillNo()+".txt");
                              BufferedWriter bufferedWriter = new BufferedWriter(writer);
                              ArrayList<String> titles=fatur.getProductsSold();
                              ArrayList<Double> prc=fatur.getPrices();
                              ArrayList<Integer> qnt=fatur.getSoldQnt();
                              bufferedWriter.write("              CEN GROUP SHPK \n       -----------------------   \n   Name                  Quantity  |   Price\n\n");
                              for(int i=0;i<emrat.size();i++) {
                            	  bufferedWriter.write("   "+titles.get(i)+"                  "+qnt.get(i)+"   |    "+prc.get(i)+"\n");
                              }
                              
                              bufferedWriter.newLine();
                              bufferedWriter.write("       -----------------------   \n");
                              bufferedWriter.write("   Total Amount                        "+fatur.totalBill());
                              if(fatur.getDiscountPercentage()>1) {
                            	  bufferedWriter.write("\n   Discount                             "+fatur.getDiscountPercentage()+"%");
                              }
                              bufferedWriter.write("\n       -----------------------   \n");
                              bufferedWriter.write("\n           "+fatur.getDateOfTrans());
                              bufferedWriter.newLine();
                   
                              bufferedWriter.close();
                          } catch (IOException err) {
                              err.printStackTrace();
                          }
                    	
                    }
                };
                button3.setOnAction(event2);
                gridPane.add(button3, 1, 7);
            }
        };
        
        button1.setOnAction(event);
        
        Button button2 = new Button("Back");
        button2.setOnAction(e -> {
			try {
				primaryStage.setScene(new MainMenu(currentUser).exec(primaryStage,inventari,faturat,klientat));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        gridPane.add(addBook, 1, 11);
        gridPane.add(button1, 1, 12);
        gridPane.add(button2, 1, 13);
        
        root.getChildren().add(gridPane);
        
        Scene scene = new Scene(root,600,600); 
		return scene;
		
	}
}
