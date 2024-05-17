package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AccessUsers;
import model.BillList;
import model.ClientsList;
import model.Inventory;
import model.User;

public class Login {
	
	/**
	 * Initalization of data lists we need
	 */
	Inventory inventari=new Inventory();
	BillList faturat=new BillList();
	ClientsList klientet=new ClientsList();
	
	/**
	 * 
	 * @param primaryStage
	 * @return
	 * @throws FileNotFoundException
	 * 
	 */
	
	public Scene exec(Stage primaryStage) throws FileNotFoundException{
		
		StackPane root=new StackPane();
    	Label loginLabel=new Label("Welcome, please log in.");
    	loginLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
    	
    	GridPane loginText=new GridPane();
    	loginText.setPadding(new Insets(60, 10, 10, 10)); 
    	
    	loginText.add(loginLabel, 0, 0);
    	loginText.setAlignment(Pos.TOP_CENTER);
    	
    	Text text1 = new Text("Username");  
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11));
                
        Text text2 = new Text("Password");
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 11)); 
        
        TextField textField1 = new TextField();              
        TextField textField2 = new TextField();  
		
		Text err = new Text("Wrong Credentials!");
		err.setStroke(Color.RED);
		err.getStyleClass().add("error");
		err.setVisible(false);
		
		Button login = new Button("Login");
		Button cancel = new Button("Cancel");
		Button register = new Button("Register");
		
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
        gridPane.add(text1, 0, 0); 
        gridPane.add(textField1, 1, 0); 
        gridPane.add(text2, 0, 1);       
        gridPane.add(textField2, 1, 1); 
        gridPane.add(login, 0, 2); 
        gridPane.add(cancel, 1, 2);  
        gridPane.add(register, 2, 2); 
		
		HBox hb_err = new HBox(err);
		hb_err.setAlignment(Pos.BOTTOM_CENTER);
		
		Text welcome=new Text("Welcome to CEN 213 Project");
		welcome.setId("welcome-text");
		welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		
		 HBox hbox = new HBox();
	     FileInputStream input = new FileInputStream("res\\images\\bglib.png");
			
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
         
         root.getChildren().add(hbox);
         root.getChildren().add(gridPane);
         root.getChildren().add(loginText);
         root.getChildren().add(hb_err);
         
         
		
		login.setOnAction(e -> {
			String username = textField1.getText();
			String password = textField2.getText();
			
			File file = new File(AccessUsers.filename);
			boolean found = false;
			if(file.exists() && !file.isDirectory())
			{
				AccessUsers fileUsers = new AccessUsers();
				
				User user = fileUsers.checkUser(username, password);
				if(user != null)
				{	
					System.out.println("check per statusin "+user.getStatus());
					if(user.getStatus().equals("Cashier")) {
						System.out.println("Logged in");
						primaryStage.close();
						try {
							primaryStage.setScene((new MainMenu(user)).exec(primaryStage,inventari,faturat,klientet));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						primaryStage.show();
					}
					else if(user.getStatus().equals("Manager")) {
						System.out.println("Logged in");
						primaryStage.close();
						try {
							primaryStage.setScene((new MainMenuManager(user)).exec(primaryStage,inventari,faturat,klientet));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						primaryStage.show();
					}
					else if(user.getStatus().equals("Administrator")) {
						System.out.println("Logged in");
						primaryStage.close();
						try {
							primaryStage.setScene((new MainMenuAdministrator(user)).exec(primaryStage,inventari,faturat));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						primaryStage.show();
					}
					
				}
			}
			
			if(username.equals("admin") && password.equals("admin"))
			{
				System.out.println("Logged in");
				
				User overrideUser = new User(0, "admin", "admin", "Administrator");
				try {
					primaryStage.setScene((new MainMenu(overrideUser)).exec(primaryStage,inventari,faturat,klientet));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//primaryStage.show();
			} else {
				err.setVisible(true);
			}
		});
		
		register.setOnAction(e -> {
			primaryStage.setScene((new AddUser(new User())).exec(primaryStage,inventari,false,null,faturat));
		});
		
		
		cancel.setOnAction(e -> {
			primaryStage.close();
		});
		Scene scene = new Scene(root,600,300);
		
		primaryStage.setTitle("CENSuperMarket");
		
		return scene;
	}

}
