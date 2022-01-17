package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions, reading files, SQL
 * 
 * @version     1.0
 * @since       1.0
 */

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.io.IOException;
import it.unipr.zezacracolici.*;
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
 * The class {@code RegistrationController} supports
 * the registration of a person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 *
 *
 * @version     1.0
 * @since       1.0
 */
public class RegistrationController implements Initializable {
	@FXML
    private TextField registrationName;
	@FXML
    private TextField registrationSurname;
	@FXML
    private TextField registrationAddress;
	@FXML
    private TextField registrationFiscalcode;
	@FXML
    private TextField registrationUsername;
	@FXML
    private TextField registrationPassword;
	
	
	/** {@inheritDoc} **/
	@Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
	 
	private void closeStage(ActionEvent event) {
		Node  source = (Node) event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
     * Registration giving credentials.
     *
     * @param event the event of registration of a person.
	 * @throws SQLException 
	 * @throws IOException 
     *
     * @since       1.0
     */
	public void userRegistration(ActionEvent event) throws SQLException, IOException{
		String name  = registrationName.getText().trim();
		String surname  = registrationSurname.getText().trim();
		String address  = registrationAddress.getText().trim();
		String fiscal  = registrationFiscalcode.getText().trim();
		String user  = registrationUsername.getText().trim();
		String password  = registrationPassword.getText().trim();
		
		Member member = new Member(name, surname, address, fiscal, user, password);
		member.registration(member,"Member");
		
		FXMLLoader fxmlLoaderLogin = new FXMLLoader(getClass().getResource("Login.fxml"));
		
	    Parent parentLogin= fxmlLoaderLogin.load();
		
		closeStage(event);
		Scene sceneLogin = new Scene(parentLogin, 300, 200);
	    Stage stageLogin = new Stage();
	    stageLogin.setTitle("Login");
	    stageLogin.initModality(Modality.APPLICATION_MODAL);
	    stageLogin.setScene(sceneLogin);
	    stageLogin.show();
	}
}
