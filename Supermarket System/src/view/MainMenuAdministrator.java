package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import model.AccessUsers;
import model.Bill;
import model.BillList;
import model.Cashier;
import model.Inventory;
import model.User;

public class MainMenuAdministrator {
	User currentUser;
	Stage primaryStage;
	AccessUsers userat=new AccessUsers();
	public MainMenuAdministrator(User currentUser)
	{
		this.currentUser = currentUser;
	}
	
	public Scene exec(Stage ps,Inventory inventari,BillList faturat) throws FileNotFoundException
	{	
		Cashier kashieri1=new Cashier(currentUser.getUser(),currentUser.getPass(),currentUser.getStatus());
		
		StackPane root2=new StackPane();
		Label loginLabel=new Label("Logged in as an Administrator.");
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
        
        Button button1 = new Button("Show Products"); 
        Stage newWindow = new Stage();
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
            	ArrayList<String> message=inventari.showProducts();
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
					newWindow.close();
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
        
        Button button2 = new Button("Manage Employees"); 
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	try {
					ps.setScene((new ManageEmployees(currentUser).exec(ps,inventari,userat,faturat)));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        };
        
        button2.setOnAction(event2);
        
        Button button3 = new Button("Show Users");
        
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {	
            	Stage newWindow = new Stage();
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 450);
            	ArrayList<String> message=userat.showUsers();
            	for(int i=0;i<message.size();i++) {
            		Text err = new Text(message.get(i));
            		err.setStroke(Color.GREEN);
            		err.setVisible(false);
                	HBox hb_err = new HBox(err);
            		hb_err.setAlignment(Pos.CENTER_LEFT);
            		gridPane3.add(hb_err,0,i);
            		gridPane3.setPadding(new Insets(10, 10, 10, 10));
            		gridPane3.setAlignment(Pos.CENTER_LEFT);
            		err.setVisible(true);
            		Button button2 = new Button("Back");
                    button2.setOnAction(ev -> {
            			try {
            				ps.setScene(new MainMenuAdministrator(currentUser).exec(ps,inventari,faturat));
            				newWindow.close();
            			} catch (FileNotFoundException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            		});
                    gridPane3.add(button2, 10, 4);
            	}


				// New window (Stage)
				
				newWindow.setTitle("Product");
				newWindow.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow.setX(ps.getX() + 200);
				newWindow.setY(ps.getY() + 100);

				newWindow.show();
            	
            }
        };
        
        button3.setOnAction(event3);
        
        Button button4 = new Button("Show Income");
        Stage newWindow3 = new Stage();
        EventHandler<ActionEvent> event4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
            	GridPane gridPane3 = new GridPane();

				Scene secondScene = new Scene(gridPane3, 400, 400);
        		gridPane3.setPadding(new Insets(10, 10, 10, 10));
        		gridPane3.setAlignment(Pos.CENTER);
        		
        		
            	Button button2 = new Button("Back");
                button2.setOnAction(ev -> {
        			newWindow3.close();
        		});
                gridPane3.add(button2, 0, 5);
				// New window (Stage)
                ObservableList<PieChart.Data> pieChartData =
                        FXCollections.observableArrayList();
                ArrayList<String> names=new ArrayList<String>();
                ArrayList<Integer> qnt=new ArrayList<Integer>();
                ArrayList<Double> amountEach=new ArrayList<Double>();
                ArrayList<Bill> fat=faturat.faturat;
                System.out.println(fat.size());
                for(int i=0;i<fat.size();i++) {
                	 ArrayList<String> names2=fat.get(i).getProductsSold();
                     ArrayList<Integer> qnt2=fat.get(i).getSoldQnt();
                     ArrayList<Double> amountEach2=fat.get(i).getPrices();
                	for(int j=0;j<names2.size();j++) {
                		Boolean alreadyIs=false;
                		for(int y=0;y<names.size();y++) {
                			if(names.get(y).equals(names2.get(j))) {
                				alreadyIs=true;
                				qnt.set(y, qnt.get(y)+qnt2.get(j));
                				amountEach.set(y, amountEach.get(y)+amountEach2.get(j));
                			}
                		}
                		if(alreadyIs==false) {
                			names.add(names2.get(j));
                			qnt.add(qnt2.get(j));
                			amountEach.add(amountEach2.get(j));
                		}
                	}
                }
                System.out.println(names.size());
                for(int i=0;i<names.size();i++) {
                	pieChartData.add(new PieChart.Data(names.get(i), qnt.get(i)));
                }
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Sold Products");
                gridPane3.add(chart, 0, 0);
                
                ObservableList<PieChart.Data> pieChartData2 =
                        FXCollections.observableArrayList();
                for(int i=0;i<names.size();i++) {
                	
                	pieChartData2.add(new PieChart.Data(names.get(i), amountEach.get(i)));
                }
                final PieChart chart2 = new PieChart(pieChartData2);
                chart.setTitle("Income");
                gridPane3.add(chart2, 0, 4);
                
				newWindow3.setTitle("Incomes");
				newWindow3.setScene(secondScene);

				// Set position of second window, related to primary window.
				newWindow3.setX(ps.getX() + 200);
				newWindow3.setY(ps.getY() + 100);

				newWindow3.show();
            }
        };
        
        button4.setOnAction(event4);
        
        Button button5 = new Button("LogOut");
        button5.setOnAction(ev -> {
			try {
				ps.setScene((new Login()).exec(ps));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        GridPane gridbg=new GridPane();
        HBox hbox = new HBox();
	    FileInputStream input = new FileInputStream("res\\bglib5.png");
			
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
        gridPane.add(button4,1,3);
        gridPane.add(button5,1,6);
        root2.getChildren().addAll(loginText,gridPane2);
        root.getChildren().addAll(root2,gridPane);
        gridbg.getChildren().add(root);
        Scene scene = new Scene(gridbg); 
        return scene;
        
}
}
