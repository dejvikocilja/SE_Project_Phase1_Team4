package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.BillList;
import model.Cashier;
import model.ClientsList;
import model.Inventory;
import model.User;

public class MainMenuManager {
	User currentUser;
	Stage primaryStage;

	public MainMenuManager(User currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Scene exec(Stage ps,Inventory inventari,BillList faturat,ClientsList klientet) throws FileNotFoundException
	{	
		Cashier kashieri1=new Cashier(currentUser.getUser(),currentUser.getPass(),currentUser.getStatus());
		
		StackPane root2=new StackPane();
		Label loginLabel=new Label("Logged in as a Manager.");
    	loginLabel.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 13));
    	
    	GridPane loginText=new GridPane();
    	loginText.setPadding(new Insets(20, 10, 10, 10)); 
    	
    	loginText.add(loginLabel, 0, 0);
    	loginText.setAlignment(Pos.CENTER);
    	Text welcome=new Text("Welcome "+currentUser.getUser());
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
		
		//gridPane.getChildren().add(welcome);
        GridPane gridPane2=new GridPane();
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
		gridPane2.setAlignment(Pos.TOP_CENTER);
		gridPane2.add(welcome,0,0);
        
        VBox root = new VBox();
        
        Button button1 = new Button("Show Products Sold"); 
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=faturat.showProductsSold();
            	for(int i=0;i<message.size();i++) {
            		Text err = new Text(message.get(i));
            		err.setStroke(Color.GREEN);
            		err.setVisible(false);
                	HBox hb_err = new HBox(err);
            		hb_err.setAlignment(Pos.CENTER_LEFT);
            		gridPane3.add(hb_err,0,i);
            		gridPane3.setPadding(new Insets(10, 10, 10, 10));
            		gridPane3.setAlignment(Pos.CENTER);
            		err.setVisible(true);
            	}

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Bills");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            }
        };
        
        button1.setOnAction(event);
        
        Button button2 = new Button("Create Product"); 
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	ps.setScene(ManageProducts.managerProducts(inventari,faturat,ps,currentUser,klientet));
            }
        };
        
        button2.setOnAction(event2);
        
        Button button4 = new Button("Show Bills");
        
        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=faturat.showBills();
            	for(int i=0;i<message.size();i++) {
            		Text err = new Text(message.get(i));
            		err.setStroke(Color.GREEN);
            		err.setVisible(false);
                	HBox hb_err = new HBox(err);
            		hb_err.setAlignment(Pos.CENTER_LEFT);
            		gridPane3.add(hb_err,0,i);
            		gridPane3.setPadding(new Insets(10, 10, 10, 10));
            		gridPane3.setAlignment(Pos.CENTER);
            		err.setVisible(true);
            	}

				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Bills");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            	
            }
        };
        
        button4.setOnAction(event4);
        
        Button button5 = new Button("Check Missing Products");
        
        GridPane gridPane3=new GridPane();
        EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=inventari.checkForMissing();
            	for(int i=0;i<message.size();i++) {
            		Text err = new Text(message.get(i));
            		err.setStroke(Color.RED);
            		err.getStyleClass().add("error");
            		err.setVisible(false);
                	HBox hb_err = new HBox(err);
            		hb_err.setAlignment(Pos.CENTER);
            		gridPane3.add(hb_err,0,i);
            		gridPane3.setPadding(new Insets(10, 10, 10, 10));
            		gridPane3.setAlignment(Pos.CENTER);
            		err.setVisible(true);
            		
            		Button button6 = new Button("Buy Products");
            		EventHandler<ActionEvent> event6 = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e)
                        {	
                        	GridPane gp=new GridPane();

            				Scene thirdScene = new Scene(gp, 400, 400);
            				
            				Text text1 = new Text("Product Name");  
            		        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
            		        
            		        //creating label password 
            		        Text text2 = new Text("Quantity to Add");
            		        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
                        	
            		        TextField textField1 = new TextField(); 
            		        TextField textField2 = new TextField(); 
            		        
            		        
            		        gp.add(text1, 1, 0);
            		        gp.add(textField1, 2, 0);
            		        gp.add(text2, 1,1 );
            		        gp.add(textField2, 2, 1);
            		        
            		        Button button7 = new Button("Confirm");
            		        EventHandler<ActionEvent> event7 = new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent e)
                                {	
                                	inventari.updateStock(Integer.parseInt(textField2.getText()),textField1.getText());
                                }
                                
                            };
                            button7.setOnAction(event7);
                        	gp.getChildren().add(button7);
                        	gp.setAlignment(Pos.CENTER);
            				// New window (Stage)
            				Stage newWindow = new Stage();
            				newWindow.setTitle("Add Products");
            				newWindow.setScene(thirdScene);
            				
            				// Set position of second window, related to primary window.
            				newWindow.setX(ps.getX() + 200);
            				newWindow.setY(ps.getY() + 100);

            				newWindow.show();
                        	
                        }
                        
                    };
                    button6.setOnAction(event6);
                    gridPane3.add(button6,1,8);
            	}
            	
            	
				// New window (Stage)
				Stage newWindow = new Stage();
				newWindow.setTitle("Product");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            	
            }
        };
        
        button5.setOnAction(event5);
        
        Button button8 = new Button("LogOut");
        button8.setOnAction(ev -> {
			try {
				ps.setScene((new Login()).exec(ps));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        GridPane gridbg=new GridPane();
        HBox hbox = new HBox();
	    FileInputStream input = new FileInputStream("C:\\Users\\user\\Downloads\\bglib4.png");
			
        // create a image
        Image image = new Image(input);
        
        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         BackgroundRepeat.NO_REPEAT, 
                                         BackgroundPosition.CENTER, 
                                            BackgroundSize.DEFAULT);
        
        Background background = new Background(backgroundimage);
        
        hbox.setBackground(background);
        hbox.setAlignment(Pos.CENTER);
        gridbg.getChildren().add(hbox);
        gridbg.setAlignment(Pos.CENTER);
        
        gridPane.add(button1,1,0);
        gridPane.add(button2,1,1);
        gridPane.add(button4,1,2);
        gridPane.add(button5,1,3);
        gridPane.add(button8,1,6);
        root2.getChildren().addAll(loginText,gridPane2);
        root.getChildren().addAll(root2,gridPane,gridPane3);
        gridbg.getChildren().add(root);
        Scene scene = new Scene(gridbg); 
        return scene;
}
}
