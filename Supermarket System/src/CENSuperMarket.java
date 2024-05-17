import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.Login;

public class CENSuperMarket extends Application{
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setScene((new Login()).exec(primaryStage));
	    primaryStage.show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
