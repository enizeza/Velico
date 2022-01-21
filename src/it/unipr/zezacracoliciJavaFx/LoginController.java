package it.unipr.zezacracoliciJavaFx;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;

/**
 * Libraries JavaFX, control Exceptions, reading files
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
	 * @throws SQLException query error
     *
     * @since       1.0
     */
	public void controlUser(ActionEvent event) throws IOException, SQLException {
		String Username = loginUsername.getText().trim();
		String Password = loginPassword.getText().trim();
				
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		Statement state = null;
	    ResultSet result;
	    String Role = "";
	    String Name = "";
        String Surname = "";
        String Address = "";
        String FiscalCode = "";
        int IdPerson = 0;
			
		try{
            state = conn.createStatement();
            result = state.executeQuery("select * from person where username='"+Username+"' && password='"+Password+"'");
            while(result.next()){
                Name = result.getString("name");
                Surname = result.getString("surname");
                Address = result.getString("address");
                FiscalCode = result.getString("fiscalcode");
                Role = result.getString("role");
                IdPerson = result.getInt("idperson");
            	}
            pool.releaseConnection(conn);
       
			switch(Role) {
				case "Member":
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MemberMenu.fxml"));
				
				    Parent parent = fxmlLoader.load();
				    
				    MemberMenuController control = fxmlLoader.<MemberMenuController>getController();
				    control.setUser(IdPerson);
				
				    closeStage(event);
				    Scene scene = new Scene(parent, 800, 800);
				    Stage stage = new Stage();
				    stage.setTitle("Member Menu");
				    stage.initModality(Modality.APPLICATION_MODAL);
				    stage.setScene(scene);
				    
				    
				    stage.show();
				    
				    break;
					
				case "Staff":
					FXMLLoader fxmlLoaderEmployee = new FXMLLoader(getClass().getResource("EmployeeMenu.fxml"));
					
				    Parent parentEmployee = fxmlLoaderEmployee.load();
				
				    closeStage(event);
				    Scene sceneEmployee = new Scene(parentEmployee, 800, 800);
				    Stage stageEmployee = new Stage();
				    stageEmployee.setTitle("Employee Menu");
				    stageEmployee.initModality(Modality.APPLICATION_MODAL);
				    stageEmployee.setScene(sceneEmployee);
				    stageEmployee.show();
				    
				    break; 
			}
		}
        catch(SQLException e){
        	Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
			alert.showAndWait();
            }
        catch(NullPointerException e){
        	Alert alert = new Alert(AlertType.WARNING,"Error in Username or Password!!",ButtonType.OK);
			alert.showAndWait();
            }
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
