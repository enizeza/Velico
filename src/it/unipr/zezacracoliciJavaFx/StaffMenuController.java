package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions and list
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import it.unipr.zezacracolici.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The class {@code StaffMenuController} supports
 * the choice between show members, add race and send notification to the members.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version     1.0
 * @since       1.0
 */
public class StaffMenuController implements Initializable {	
	private int userId;
	
	
	/**
	 * Handles the open dialog event log out.
     *
     * @param e  the event.
	 * @throws IOException input output
     *
     * @since	1.0
     */
	@FXML
	public void onOpenLogOut(final ActionEvent e) throws IOException {
		FXMLLoader fxmlLoaderLogin = new FXMLLoader(getClass().getResource("Login.fxml"));
		
	    Parent parentLogin= fxmlLoaderLogin.load();
	
	    closeStage(e);
	    Scene sceneLogin = new Scene(parentLogin, 300, 200);
	    Stage stageLogin = new Stage();
	    stageLogin.setTitle("Login");
	    stageLogin.initModality(Modality.APPLICATION_MODAL);
	    stageLogin.setScene(sceneLogin);
	    stageLogin.show();
	}
	
	/**
	 * Handles the open dialog event for showing the members.
     *
     * @param e  the event.
	 * @throws IOException input output
     *
     * @since       1.0
     */
	@FXML
	public void onOpenDialogShowMember(final ActionEvent e) throws IOException {
		/*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBoatDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    AddBoatDialogController control = fxmlLoader.<AddBoatDialogController>getController();
	    control.setUser(userId);
	
	    Scene scene = new Scene(parent, 300, 200);
	    Stage stage = new Stage();
	    stage.setTitle("Add Boat");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();*/
	}
	
	/**
	 * Handles the open dialog event for adding a race.
     *
     * @param e  the event.
	 * @throws IOException input output
	 * @throws SQLException query error
     *
     * @since       1.0
     */
	@FXML
	public void onOpenDialogRegistrationRace(final ActionEvent e) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddRaceDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	
	    Scene scene = new Scene(parent, 300, 200);
	    Stage stage = new Stage();
	    stage.setTitle("Add Race");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
   	 * Handles event of sending notifications.
   	 *
   	 * @param event  the event.
   	 * @throws IOException input output
	 * @throws SQLException query error
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onSendNotification(final ActionEvent event) throws IOException, SQLException{
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
	    ResultSet result;
			      
        try{
        	state = conn.createStatement();
            result = state.executeQuery("select * from person");
            pool.releaseConnection(conn);
            boolean val = result.next();
            
            if(!val){
                Alert alert = new Alert(AlertType.WARNING,"Non ci sono membri!!",ButtonType.OK);
                alert.showAndWait();
             }
            else {
            	userId = result.getInt("idperson"); 
            	Staff staff = new Staff();
            	
            	staff.sendNotificationOrganization(userId);
            	staff.sendNotificationStorage(userId);
            }
          }
      catch(SQLException e){
            Alert alert = new Alert(AlertType.WARNING,"Query error!!!",ButtonType.OK);
		    alert.showAndWait();
          }
      catch(NullPointerException e){
            Alert alert = new Alert(AlertType.INFORMATION,"La quota non � scaduta ancora!!",ButtonType.OK);
		    alert.showAndWait();
          }
	}

	/** {@inheritDoc} **/
    @Override
  	public void initialize(final URL location, final ResourceBundle resources) {
  	}
    
    private void closeStage(final ActionEvent event) {
	    Node  source = (Node) event.getSource();
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
	}
    
    /**
   	 * Handles the communication between controllers of the Idperson.
   	 *
   	 * @param userId  userId the id of the person.
   	 *
   	 * @since       1.0
   	 */
    public void setUser(int userId){
        this.userId = userId;
    }
}