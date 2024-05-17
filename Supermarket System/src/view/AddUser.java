package view;

import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AccessAdministrators;
import model.AccessCashiers;
import model.AccessUsers;
import model.Administrator;
import model.BillList;
import model.Cashier;
import model.Inventory;
import model.User;
//import view.AddUser.CancelHandler;

public class AddUser {
	User currentUser;
	User seluser; //Used to generate user from employee
	
	Button create1;
	Label usernField;
	Label passwdField;
	
	public AddUser(User cUser)
	{
		currentUser = cUser;
	}
	
	public Scene exec(Stage primaryStage,Inventory inventari,Boolean check,AccessUsers userat,BillList faturat)
	{
		
		Accordion panes = new Accordion();
		panes.getStyleClass().add("titledpane");
		
		Text successfulUser = new Text("User successfully created!");
		successfulUser.setStroke(Color.RED);
		successfulUser.setVisible(false);
		
		Label username = new Label("Username: ");
		Label password = new Label("Password: ");
		TextField userField = new TextField();
		TextField passField = new TextField();
		
		Button create = new Button("Create");
		Button cancel = new Button("Cancel");
		
		HBox buttons = new HBox();
		buttons.getChildren().addAll(create, cancel);
		buttons.setAlignment(Pos.CENTER);
		buttons.setMargin(create, new Insets(0, 10, 0, 0));
		
		ToggleGroup position = new ToggleGroup();
		RadioButton normal = new RadioButton("Cashier");
		RadioButton editor = new RadioButton("Manager");
		RadioButton admin = new RadioButton("Administrator");
		position.getToggles().addAll(normal, editor, admin);
		position.selectToggle(normal);
		
		HBox radios = new HBox(normal, editor, admin);
		radios.setMargin(editor, new Insets(0, 10, 0, 10));
		radios.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(radios, buttons);
		
		GridPane fieldPane = new GridPane();
		fieldPane.addRow(0, username, userField);
		fieldPane.addRow(1, password, passField);
		fieldPane.setAlignment(Pos.CENTER);
	    fieldPane.setMinSize(300, 200);
		fieldPane.setPadding(new Insets(10,10,10,10));
		fieldPane.setHgap(5);
		fieldPane.setVgap(5);
		
		BorderPane topPane = new BorderPane();
		topPane.setCenter(fieldPane);
		topPane.setBottom(vbox);
		TitledPane newUser = new TitledPane("Register New User", topPane);

		 create1 = new Button("Create");
		 Button cancel1 = new Button("Cancel");
		
		Label userNn = new Label("Username: ");
		Label passwd = new Label("Password: ");
		
		usernField = new Label("");
		passwdField = new Label("");
		
		Button generate = new Button("Generate User from Employee");
				
		create1.setDisable(true);
		
		GridPane grid = new GridPane();
		grid.addRow(0, userNn, usernField);
		grid.addRow(1, passwd, passwdField);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		//grid.setAlignment(Pos.CENTER);
		
		HBox buttons2 = new HBox();
		buttons2.getChildren().addAll(create1, cancel1);
		buttons2.setAlignment(Pos.CENTER);
		buttons2.setMargin(create1, new Insets(0, 10, 0, 0));
		
		BorderPane bottomPane = new BorderPane();
		bottomPane.setTop(generate);
		bottomPane.setCenter(grid);
		bottomPane.setBottom(buttons2);
		bottomPane.setAlignment(generate, Pos.CENTER);
		
		panes.setExpandedPane(newUser);
		
		
		cancel.setOnAction(new CancelHandler(this.currentUser, primaryStage,inventari,check,userat,faturat));
		cancel1.setOnAction(new CancelHandler(this.currentUser, primaryStage,inventari,check,userat,faturat));
		
		create.setOnAction(e -> {
			String usernameData = userField.getText();
			String passwordData = passField.getText();
			
			String type = "";
			
			String posit = ((RadioButton) position.getSelectedToggle()).getText(); 
			
			if(posit == "Administrator") type = "Administrator";
			else if(posit == "Manager") type = "Manager";
			else if(posit == "Cashier") type = "Cashier";
			
			AccessUsers userFile = new AccessUsers();
			userFile.addUser(new User(User.x++, usernameData, passwordData, type));
			
			successfulUser.setVisible(true);
			
			System.out.println("User: "+usernameData+", "+passwordData+" -> "+posit);
		});
		
		generate.setOnAction(e -> {
			genFromLib();			
		});
		
		create1.setOnAction(e -> {
			
			
			AccessUsers userFile = new AccessUsers();
			userFile.addUser(seluser);
			
			successfulUser.setVisible(true);
			
			System.out.println(seluser);
		});
		
		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(topPane);
		mainPane.setBottom(successfulUser);
		mainPane.setAlignment(successfulUser, Pos.CENTER);
		
		Scene scene = new Scene(mainPane);
		//scene.getStylesheets().addAll(this.getClass().getResource("../resources/css/style.css").toString());
		primaryStage.setTitle("User Registration");
		return scene;
	}
	
	public void genFromLib()
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		AccessCashiers casFile = new AccessCashiers();
		
		ObservableList<Cashier> cashiersList = FXCollections.observableArrayList(casFile.getCashiers());
		
		TableView casTable = new TableView();
		
		casTable.setEditable(true);
		
		TableColumn nameC = new TableColumn("Name");
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn snameC = new TableColumn("Surname");
		snameC.setCellValueFactory(new PropertyValueFactory<>("sname"));
		
		TableColumn phoneC = new TableColumn("Phone");
		phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		
		casTable.setItems(cashiersList);
		casTable.getColumns().addAll(nameC, snameC, phoneC);
		
		Scene scene = new Scene(casTable);
		
		stage.setScene(scene);
		stage.setTitle("Double click a record to select the employee");
		stage.show();
		
		//Handle the double click
		casTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) 
		        {
		            Cashier cas = (Cashier) casTable.getSelectionModel().getSelectedItem();        
		            stage.close();
		            
		            seluser = cas.genUser();
					
					usernField.setText(seluser.getUser());
					passwdField.setText(seluser.getPass());
					
					create1.setDisable(false);
					
		        }
		    }
		});
		
	}
	
	public void genFromAdm()
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		AccessAdministrators admFile = new AccessAdministrators();
		
		ObservableList<Administrator> administratorList = FXCollections.observableArrayList(admFile.getAdministrators());
		
		TableView admTable = new TableView();
		
		admTable.setEditable(true);
		
		TableColumn nameC = new TableColumn("Name");
		nameC.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn snameC = new TableColumn("Surname");
		snameC.setCellValueFactory(new PropertyValueFactory<>("sname"));
		
		TableColumn phoneC = new TableColumn("Phone");
		phoneC.setCellValueFactory(new PropertyValueFactory<>("phone"));
		
		
		admTable.setItems(administratorList);
		admTable.getColumns().addAll(nameC, snameC, phoneC);
		
		Scene scene = new Scene(admTable);
		
		stage.setScene(scene);
		stage.setTitle("Double click a record to select the employee");
		stage.show();
		
		//Handle the double click
		admTable.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override 
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) 
		        {
		            Administrator adm = (Administrator) admTable.getSelectionModel().getSelectedItem();        
		            stage.close();
		            
		            seluser = adm.genUser();
					
					usernField.setText(seluser.getUser());
					passwdField.setText(seluser.getPass());
					
					create1.setDisable(false);
					
		        }
		    }
		});
		
	}
	
	private class CancelHandler implements EventHandler<ActionEvent> 
	{
		User currentUser;
		Stage primaryStage;
		Inventory inventari;
		Boolean checkAdm;
		AccessUsers userat;
		BillList faturat;
		public CancelHandler(User u, Stage p,Inventory inventari,Boolean check,AccessUsers userat,BillList faturat)
		{
			currentUser = u;
			primaryStage = p;
			this.inventari=inventari;
			this.checkAdm=check;
			this.userat=userat;
			this.faturat=faturat;
		}
		
		public void handle(ActionEvent e) 
		{
			try {
				if(checkAdm==true) {
					primaryStage.setScene((new ManageEmployees(currentUser).exec(primaryStage,inventari,userat,faturat)));
				}
				else {
					primaryStage.setScene((new Login()).exec(primaryStage));
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
