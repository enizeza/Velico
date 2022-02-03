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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import it.unipr.zezacracolici.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The class {@code MemberMenuController} supports
 * the choice between add boat, registration to a race of boat and pay.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version     1.0
 * @since       1.0
 */
public class MemberMenuController implements Initializable {
	@FXML
	private TableView<Notification> tvData;
	@FXML
	private TableColumn<Notification, String> colId;
	@FXML
	private TableColumn<Notification, String> colType;
	
	private ObservableList<Notification> tvObservableList = FXCollections.observableArrayList();
	
	private ObservableList<Boat> appMainObservableList = FXCollections.observableArrayList();
	
	private ObservableList<Race> appMainObservableListRace = FXCollections.observableArrayList();
	
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
	 * Handles the open dialog event for adding a boat.
     *
     * @param e  the event.
	 * @throws IOException input output
     *
     * @since       1.0
     */
	@FXML
	public void onOpenDialogAddBoat(final ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBoatDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    AddBoatDialogController control = fxmlLoader.<AddBoatDialogController>getController();
	    control.setUser(userId);
	
	    Scene scene = new Scene(parent, 300, 200);
	    Stage stage = new Stage();
	    stage.setTitle("Add Boat");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
	 * Handles the open dialog event for removing a boat.
     *
     * @param e  the event.
	 * @throws IOException input output
	 * @throws SQLException query error
     *
     * @since       1.0
     */
	@FXML
	public void onOpenDialogRemoveBoat(final ActionEvent e) throws IOException, SQLException {
		appMainObservableList.clear();
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
	    ResultSet result;
	    String Name = "";
	    int Length = 0;
	    int IdBoat = 0;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveBoatDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    RemoveBoatDialogController dialogController = fxmlLoader.<RemoveBoatDialogController> getController();
			
        state = conn.createStatement();
        result = state.executeQuery("select * from boat where owner="+userId+"");
        while(result.next()){
            Name = result.getString("name");
            Length = result.getInt("length");
            IdBoat = result.getInt("idboat");
            Boat boat = new Boat(IdBoat, Name, Length);
            appMainObservableList.add(boat);
        	}
        pool.releaseConnection(conn);
        
		dialogController.setAppMainObservableList(appMainObservableList);
	    
	    Scene scene = new Scene(parent, 400, 400);
	    Stage stage = new Stage();
	    stage.setTitle("Remove Boat");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	  
	/**
   	 * Handles the open dialog event for registration of a boat into a race.
   	 *
   	 * @param e  the event.
   	 * @throws IOException input output
	 * @throws SQLException query error
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onOpenDialogRegistrationRaceBoat(final ActionEvent e) throws IOException, SQLException{
	    appMainObservableList.clear();
	    appMainObservableListRace.clear();
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
	    ResultSet result;
	    String Name = "";
	    int Length = 0;
	    int IdBoat = 0;
	    
	    Date Date;
	    int IdRace = 0;
	    String Place = "";
		
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrationBoatRaceDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    RegistrationBoatRaceDialogController dialogController = fxmlLoader.<RegistrationBoatRaceDialogController> getController();
			
        state = conn.createStatement();
        result = state.executeQuery("select * from boat where owner="+userId+"");
        while(result.next()){
            Name = result.getString("name");
            Length = result.getInt("length");
            IdBoat = result.getInt("idboat");
            Boat boat = new Boat(IdBoat, Name, Length);
            appMainObservableList.add(boat);
        	}
        
        result = state.executeQuery("select * from race where date >= current_date()");
        while(result.next()){
            Name = result.getString("name");
            Place = result.getString("place");
            Date = result.getDate("date");
            IdRace = result.getInt("idrace");
            Race race = new Race(IdRace, Name, Place, Date);
            appMainObservableListRace.add(race);
        	}
        pool.releaseConnection(conn);
        
        
		dialogController.setAppMainObservableList(appMainObservableList,appMainObservableListRace);
	    
	    Scene scene = new Scene(parent, 500, 500);
	    Stage stage = new Stage();
	    stage.setTitle("Registration Boat");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
   	 * Handles the open dialog event for payments.
   	 *
   	 * @param e  the event.
   	 * @throws IOException input output
	 * @throws SQLException query error
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onOpenDialogPayments(final ActionEvent e) throws IOException, SQLException{
		appMainObservableList.clear();
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
	    ResultSet result;
	    String Name = "";
	    int Length = 0;
	    int IdBoat = 0;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentType.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    PayTypeDialogController dialogController = fxmlLoader.<PayTypeDialogController> getController();
			
        state = conn.createStatement();
        result = state.executeQuery("SELECT * FROM boat WHERE boat.owner = "+userId+" && boat.idboat NOT IN (SELECT boat_storage_sum.boat FROM boat_storage_sum)");
        while(result.next()){
            Name = result.getString("name");
            Length = result.getInt("length");
            IdBoat = result.getInt("idboat");
            Boat boat = new Boat(IdBoat, Name, Length);
            appMainObservableList.add(boat);
        	}
        
        result = state.executeQuery("SELECT *\r\n"
        		+ "FROM boat as b join boat_storage_sum as bs on b.idboat = bs.boat\r\n"
        		+ "WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) > bs.date && b.owner = "+userId+" && b.idboat != all (SELECT b1.idboat FROM boat as b1 join boat_storage_sum as bs1 on b1.idboat = bs1.boat\r\n"
        		+ "                                                                                        WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) < bs1.date && b1.owner = "+userId+")\r\n"
        		+ "group by(b.idboat)");
        while(result.next()){
            Name = result.getString("name");
            Length = result.getInt("length");
            IdBoat = result.getInt("idboat");
            Boat boat = new Boat(IdBoat, Name, Length);
            appMainObservableList.add(boat);
        	}
        pool.releaseConnection(conn);
        
		dialogController.setAppMainObservableList(appMainObservableList);
		dialogController.setUser(userId);
	
	    Scene scene = new Scene(parent, 400, 400);
	    Stage stage = new Stage();
	    stage.setTitle("Payment selection");
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
   	 * Handles event of showing notification.
   	 *
   	 * @param e  the event.
   	 * @throws IOException input output
	 * @throws SQLException query error
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onShowNotification(final ActionEvent e) throws IOException, SQLException{
		tvData.getItems().clear();
		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
	    ResultSet result;
	    String Sum_type = "";
	    int Read = 0;
	    int IdNotification = 0;
			
        state = conn.createStatement();
        result = state.executeQuery("select * from notification where person="+userId+"");
        while(result.next()){
        	Sum_type = result.getString("sum_type");
        	Read = result.getInt("message_read");
        	IdNotification = result.getInt("idnotification");
            Notification notification = new Notification(IdNotification, userId, Sum_type, Read);
            if(notification.getRead()==0) {
            	tvObservableList.add(notification);
            }  	
        	}
        pool.releaseConnection(conn);
	}

	/** {@inheritDoc} **/
    @Override
  	public void initialize(final URL location, final ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<Notification, String>("idnotification"));
		colType.setCellValueFactory(new PropertyValueFactory<Notification, String>("type"));
		tvData.setItems(tvObservableList);
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
    
    /**
     * Sets the boats' observable list.
     *
     * @param ol  the observable list.
     *
     * @since       1.0
     */
    public void setAppMainObservableList(final ObservableList<Boat> ol) {
    	this.appMainObservableList = ol;
	}
}
