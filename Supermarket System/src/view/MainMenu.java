package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
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


public class MainMenu {
	User currentUser;
	Stage primaryStage;

	public MainMenu(User currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Scene exec(Stage ps,Inventory inventari,BillList faturat,ClientsList klientet) throws FileNotFoundException
	{	
		Cashier kashieri1=new Cashier(currentUser.getUser(),currentUser.getPass(),currentUser.getStatus());
		
		StackPane root2=new StackPane();
		Label loginLabel=new Label("Logged in as a Cashier.");
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
        
        Button button1 = new Button("Show Inventory"); 
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	Stage newWindow = new Stage();
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=inventari.showProducts();
            	Text header= new Text("Name              Price      Category      Stock");
            	HBox headerB=new HBox(header);
            	gridPane3.add(headerB, 0, 0);
            	for(int i=0;i<message.size();i++) {
            		Text err = new Text(message.get(i));
            		err.setStroke(Color.GREEN);
            		err.setVisible(false);
                	HBox hb_err = new HBox(err);
            		hb_err.setAlignment(Pos.CENTER_LEFT);
            		gridPane3.add(hb_err,0,i+1);
            		gridPane3.setPadding(new Insets(10, 10, 10, 10));
            		gridPane3.setAlignment(Pos.CENTER);
            		err.setVisible(true);
            		
            	}
            	Button button2 = new Button("Back");
                button2.setOnAction(ev -> {
        			try {
        				ps.setScene(new MainMenu(currentUser).exec(ps,inventari,faturat,klientet));
        				newWindow.close();
        			} catch (FileNotFoundException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		});
                gridPane3.add(button2, 0, 5);

				// New window (Stage)
				
				newWindow.setTitle("Product");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            }
        };
        
        button1.setOnAction(event);
        
        Button button5 = new Button("Show Clients"); 
        EventHandler<ActionEvent> event5 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	Stage newWindow = new Stage();
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=klientet.showClients();
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
            	Button button2 = new Button("Back");
                button2.setOnAction(ev -> {
        			try {
        				ps.setScene(new MainMenu(currentUser).exec(ps,inventari,faturat,klientet));
        				newWindow.close();
        			} catch (FileNotFoundException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		});
                gridPane3.add(button2, 0, 5);

				// New window (Stage)
				
				newWindow.setTitle("Clients");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            }
        };
        
        button5.setOnAction(event5);
        
        Button button6 = new Button("Add Clients"); 
        EventHandler<ActionEvent> event6 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	ps.setScene(ManageClients.manageClients(inventari,faturat,ps,currentUser,klientet));
            }
        };
        
        button6.setOnAction(event6);
        Button button3 = new Button("Create Bill");
        
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	ps.setScene(ManageBills.clientBill(inventari,faturat,ps,currentUser,klientet));
            	
            }
        };
        
        button3.setOnAction(event3);
        Button button4 = new Button("LogOut");
        button4.setOnAction(ev -> {
			try {
				ps.setScene((new Login()).exec(ps));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        gridPane.add(button1,1,0);
        gridPane.add(button5,1,1);
        gridPane.add(button6,1,2);
        gridPane.add(button3,1,3);
        gridPane.add(button4,1,5);
        
        GridPane gridbg=new GridPane();
        HBox hbox = new HBox();
	    FileInputStream input = new FileInputStream("res/images/bglib3.jpg");
			
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
        root2.getChildren().addAll(loginText,gridPane2);
        root.getChildren().addAll(root2,gridPane);
        gridbg.getChildren().add(root);
        Scene scene = new Scene(gridbg); 
        return scene;
        
}
}
