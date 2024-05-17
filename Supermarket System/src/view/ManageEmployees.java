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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AccessUsers;
import model.BillList;
import model.Cashier;
import model.Inventory;
import model.User;

public class ManageEmployees {
	
	User currentUser;
	Stage primaryStage;
	
	public ManageEmployees(User currentUser)
	{
		this.currentUser = currentUser;
	}
	public Scene exec(Stage ps,Inventory inventari,AccessUsers userat,BillList faturat) throws FileNotFoundException
	{	
		Cashier kashieri1=new Cashier(currentUser.getUser(),currentUser.getPass(),currentUser.getStatus());
		
		StackPane root2=new StackPane();
		Label loginLabel=new Label("Manage Employees.");
    	loginLabel.setFont(Font.font("veranda", FontWeight.BOLD, FontPosture.REGULAR, 13));
    	
    	GridPane loginText=new GridPane();
    	loginText.setPadding(new Insets(20, 10, 10, 10)); 
    	
    	loginText.add(loginLabel, 0, 0);
    	loginText.setAlignment(Pos.CENTER);
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
        
        VBox root = new VBox();
        
        Button button1 = new Button("Add Employee"); 
        
       
        button1.setOnAction(e -> {
        			ps.setScene((new AddUser(new User(0, "admin", "admin", "Administrator"))).exec(ps,inventari,true,userat,faturat));
        		});
		
        
        
        Button button2 = new Button("Edit Employees"); 
        Stage newWindow1 = new Stage();
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	GridPane gp=new GridPane();

				Scene thirdScene = new Scene(gp, 400, 400);
				
				Text text1 = new Text("Username");  
		        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		        Text text2 = new Text("Status");  
		        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		        Text text3 = new Text("Salary");  
		        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		        Text text4 = new Text("Phone Number");  
		        text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		        
		        TextField textField1 = new TextField(); 
		        TextField textField2 = new TextField(); 
		        TextField textField3 = new TextField(); 
		        TextField textField4 = new TextField(); 
		        
		        gp.add(text1, 1, 0);
		        gp.add(textField1, 2, 0);
		        gp.add(text2, 1, 1);
		        gp.add(textField2, 2, 1);
		        gp.add(text3, 1, 2);
		        gp.add(textField3, 2, 2);
		        gp.add(text4, 1, 3);
		        gp.add(textField4, 2, 3);

		        
		        Button button7 = new Button("Confirm");
		        EventHandler<ActionEvent> event7 = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {	
                    	int pos=userat.getPosition(textField1.getText());
                    	User newUser=userat.getUser(pos);
                    	newUser.setStatus(textField2.getText());
                    	newUser.setUser(textField1.getText());
                    	newUser.setSalary(Double.parseDouble(textField3.getText()));
                    	newUser.setPhone(textField4.getText());
                    	userat.editUser(pos,newUser);
                    }
                    
                };
                
                Button button8 = new Button("Back");
                button8.setOnAction(ev -> {
        			newWindow1.close();
        		});
                button7.setOnAction(event7);
            	gp.add(button7,3,7);
            	gp.add(button8,3,9);
            	gp.setAlignment(Pos.CENTER);
				// New window (Stage)

				newWindow1.setTitle("Edit Employee");
				newWindow1.setScene(thirdScene);
				
				// Set position of second window, related to primary window.
				newWindow1.setX(ps.getX() + 200);
				newWindow1.setY(ps.getY() + 100);

				newWindow1.show();
            }
        };
        
        button2.setOnAction(event2);
        
        Button button3 = new Button("Delete Employees");
        Stage newWindow = new Stage();
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	GridPane gp=new GridPane();

				Scene thirdScene = new Scene(gp, 400, 400);
				
				Text text1 = new Text("Username");  
		        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10)); 
		        TextField textField1 = new TextField(); 

		        gp.add(text1, 1, 0);
		        gp.add(textField1, 2, 0);

		        
		        Button button7 = new Button("Confirm");
		        EventHandler<ActionEvent> event7 = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {	
                    	userat.rm(textField1.getText());
                    }
                    
                };
                
                Button button8 = new Button("Back");
                button8.setOnAction(ev -> {
        			newWindow.close();
        		});
                button7.setOnAction(event7);
            	gp.add(button7,3,7);
            	gp.add(button8,3,9);
            	gp.setAlignment(Pos.CENTER);
				// New window (Stage)

				newWindow.setTitle("Delete Employee");
				newWindow.setScene(thirdScene);
				
				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            }
            	
            };
        
        button3.setOnAction(event3);
        
        
        Button button5 = new Button("Back");
        button5.setOnAction(ev -> {
			try {
				ps.setScene(new MainMenuAdministrator(currentUser).exec(ps,inventari,faturat));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        GridPane gridbg=new GridPane();
        HBox hbox = new HBox();
	    FileInputStream input = new FileInputStream("C:\\Users\\user\\Downloads\\bglib6.png");
			
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
        gridPane.add(button3,1,2);
        gridPane.add(button5, 1, 6);
        root2.getChildren().addAll(loginText,gridPane2);
        root.getChildren().addAll(root2,gridPane);
        gridbg.getChildren().add(root);
        Scene scene = new Scene(gridbg); 
        
		primaryStage = ps;
		
		BorderPane mainPane = new BorderPane();
		Accordion panes = new Accordion(); //Accordion is a group of titled panes
		panes.getStyleClass().add("titledpane");
		
		MenuBar menuBar = new MenuBar();

		ArrayList<TitledPane> tipanes = new ArrayList<TitledPane>();
		ArrayList<Menu> items = new ArrayList<Menu>();
		
		VBox userButtons = new VBox();
		userButtons.getStyleClass().add("bluebox");
		Button register = new Button("Register Employee");
		Button view = new Button("View Employees");
		userButtons.getChildren().addAll(register, view);
		
		register.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				//primaryStage.setScene((new ManageEmployee(currentUser)).exec(primaryStage));
				
			}
		});
		
		view.setOnAction(e -> {
			//primaryStage.setScene((new ListEmployee(currentUser)).exec(primaryStage));
		});
		
		Menu employee = new Menu("Employees");
		MenuItem addNew = new MenuItem("Register Employee");
		MenuItem viewAll = new MenuItem("View Employees");
		MenuItem salaryItem = new MenuItem("Manage Salary");
		MenuItem statItem = new MenuItem("Statistics");
		
		salaryItem.setDisable(true);
		statItem.setDisable(true);
		
		employee.getItems().addAll(addNew, viewAll, salaryItem, statItem);
		
		addNew.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				//primaryStage.setScene((new ManageEmployee(currentUser)).exec(primaryStage));
				
			}
		});
		
		viewAll.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				//primaryStage.setScene((new ListEmployee(currentUser)).exec(primaryStage));
			}
		});
		
		salaryItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				//primaryStage.setScene((new ManageSalary(currentUser)).exec(primaryStage));				
			}
		});
		
		statItem.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				//primaryStage.setScene((new EmployeeStatistics(currentUser)).exec(primaryStage));
			}
		});
		
		items.add(employee);
		userButtons.setAlignment(Pos.CENTER);
		VBox.setMargin(register, new Insets(0,0,10,0));
		TitledPane userWorks = new TitledPane("User Works", userButtons);
		
		tipanes.add(userWorks);
		
		
		panes.getPanes().addAll(tipanes);
		
		panes.setExpandedPane(userWorks);
		
		Label changePassLab = new Label("Change Password");
		Label logoutLab = new Label("Log Out");
		
		Menu changePass = new Menu("", changePassLab);
		Menu logout = new Menu("", logoutLab);
		
		
		menuBar.getMenus().addAll(items);
		
		menuBar.getMenus().add(changePass);
		menuBar.getMenus().add(logout);
		
		mainPane.setCenter(panes);
		mainPane.setTop(menuBar);
		
		Scene scene2 = new Scene(mainPane,600,400);
		
	
		
		primaryStage.setTitle("Main Menu - "+currentUser.getUser());
		
		return scene;
	}
	
	private void showPasswordChange()
	{
		Stage pChangeStage = new Stage();
		pChangeStage.initModality(Modality.APPLICATION_MODAL);
		
		Label oldPassword = new Label("Old Password: ");
		Label newPassword = new Label("New Password: ");
		Label repPassword = new Label("New Password Again: ");
		
		PasswordField oldField = new PasswordField();
		PasswordField newField = new PasswordField();
		PasswordField repField = new PasswordField();
		
		Button change = new Button("Submit");
		Button cancel = new Button("Cancel");
		
		GridPane gp = new GridPane();
		gp.addColumn(0, oldPassword, newPassword, repPassword, change);
		gp.addColumn(1, oldField, newField, repField, cancel);
		
		Scene scn = new Scene(gp);
		pChangeStage.setScene(scn);
		pChangeStage.setTitle("Change Password");
		pChangeStage.show();
		
		change.setOnAction(e -> {
			System.out.println("Password is changed");
		});
		
		cancel.setOnAction(e -> {
			pChangeStage.close();
		});
	}
}

