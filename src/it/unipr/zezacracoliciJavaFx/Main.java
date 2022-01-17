package it.unipr.zezacracoliciJavaFx;
	
/**
 * Libraries JavaFX, control Exceptions, reading files
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Here we run the application
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		// Read file fxml and draw interface.
	    Parent root = FXMLLoader.load(getClass().getResource("/it/unipr/zezacracoliciJavaFx/Login.fxml"));
	
	    primaryStage.setTitle("Login");
	    primaryStage.setScene(new Scene(root));
		primaryStage.show();
				
	}
	
	/**
	 * Launch the application
	 * 
	 * @param args arguments
	 * 
	 * @since    1.0
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
