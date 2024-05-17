package view;

import java.io.FileNotFoundException;
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
import model.User;
import model.Product;

public class ManageProducts {
	protected static Scene managerProducts(Inventory inventari,BillList faturat,Stage primaryStage,User currentUser,ClientsList klientet) {
		
		StackPane root=new StackPane();
		Text text1 = new Text("Name");  
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        //creating label password 
        Text text2 = new Text("Original Price");
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        Text text3 = new Text("Category");  
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        Text text4 = new Text("Quantity");  
        text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        Text text6 = new Text("Supplier");  
        text6.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        Text text7 = new Text("Date of Purchase");  
        text7.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        Text text8 = new Text("Selling Price");
        text8.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
        
        //Creating Text Filed for email        
        TextField textField1 = new TextField();       
        
        //Creating Text Filed for password        
        TextField textField2 = new TextField(); 
        TextField textField3 = new TextField(); 
        TextField textField4 = new TextField(); 
        TextField textField6 = new TextField(); 
        TextField textField7 = new TextField();
        TextField textField8 = new TextField();
        GridPane gridPane = new GridPane();    
        
        //Setting size for the pane  
        gridPane.setMinSize(400, 400); 
         
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(5); 
        gridPane.setHgap(5);       
        
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.CENTER); 
         
        //Arranging all the nodes in the grid 
       // gridPane.add(loginLabel, 2, 0);
        gridPane.add(text1, 0, 0); 
        gridPane.add(textField1, 1, 0); 
        gridPane.add(text2, 0, 1);       
        gridPane.add(textField2, 1, 1); 
        gridPane.add(text3, 0, 2); 
        gridPane.add(textField3, 1, 2); 
        gridPane.add(text4, 0, 3);       
        gridPane.add(textField4, 1, 3); 
        gridPane.add(text6, 0, 4);       
        gridPane.add(textField6, 1, 4); 
        gridPane.add(text7, 0, 5);       
        gridPane.add(textField7, 1, 5); 
        gridPane.add(text8, 0, 6);       
        gridPane.add(textField8, 1, 6);
        
        Button button1 = new Button("Create"); 
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	inventari.addProduct(new Product("123",textField1.getText(),Double.parseDouble(textField2.getText()),textField3.getText(),Integer.parseInt(textField4.getText()),textField6.getText(), textField7.getText(),Double.parseDouble(textField8.getText()) ));
            }
        };
        
        button1.setOnAction(event);
        
        Button button2 = new Button("Back");
        button2.setOnAction(e -> {
			try {
				primaryStage.setScene(new MainMenuManager(currentUser).exec(primaryStage,inventari,faturat,klientet));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
        button1.setOnAction(event);
        gridPane.add(button1, 1, 9);
        gridPane.add(button2, 1, 10);
        root.getChildren().add(gridPane);
        
        Scene scene = new Scene(root); 
		return scene;
		
	}
}
