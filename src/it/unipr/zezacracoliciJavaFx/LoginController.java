package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions, reading files
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * The class {@code LoginController} supports
 * the choice between login or registration of person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 *
 *
 * @version     1.0
 * @since       1.0
 */
public class LoginController implements Initializable {
	@FXML
    private TextField loginUsername;
	@FXML
    private TextField loginPassword;
	
	
	/** {@inheritDoc} **/
	@Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

	/**
     * Distinction about the role giving login credentials.
     *
     * @param event the event of login of a person.
     * @throws IOException input output
     *
     * @since       1.0
     */
	public void controlUser(ActionEvent event) throws IOException {
	}
	 
	private void closeStage(ActionEvent event) {
		Node  source = (Node) event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
     * Registration giving data.
     *
     * @param event the event of registration of a person.
     * @throws IOException input output
     *
     * @since       1.0
     */
	public void userRegistration(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registration.fxml"));
		
	    Parent parent = fxmlLoader.load();
	
	    closeStage(event);
	    Scene scene = new Scene(parent);
	    Stage stage = new Stage();
	    stage.setTitle("Registration");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
}
