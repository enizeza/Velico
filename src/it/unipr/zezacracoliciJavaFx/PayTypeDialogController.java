package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX and control Exceptions
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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The class {@code PayTypeDialogController} supports
 * the type of a payment to perform.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class PayTypeDialogController implements Initializable {
  @FXML
  private TableView<Boat> tvData;
  @FXML
  private TableColumn<Boat, String> colId;
  @FXML
  private TableColumn<Boat, String> colNameBoat;
  @FXML
  private TableColumn<Boat, String> colLengthBoat;
	
  private ObservableList<Boat> tvObservableList = FXCollections.observableArrayList();
  
  private int userId;

  /**
   * Open the dialog  a boat.
   *
   * @param event the event that Removes the boat.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void payBoatStorage(final ActionEvent event) throws IOException, SQLException
  { 
    /*if (tvData.getSelectionModel().getSelectedItem() != null) {
        Boat selectedBoat = tvData.getSelectionModel().getSelectedItem();
        int id = selectedBoat.getId();
		
        Member member = new Member();
		
		member.removeBoat(id);
	}
	else {
		Alert alert = new Alert(AlertType.WARNING,"Nothing selected",ButtonType.OK);
		alert.showAndWait();
	}
    closeStage(event);*/
  }
  
  /**
   * Show boats.
   *
   * @param event the event that Removes the boat.
   * 
   * @throws IOException input output
 * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void payOrganization(final ActionEvent event) throws IOException, SQLException
  { 
	  /*MysqlConnect pool = new MysqlConnect();
	  Connection conn = pool.getConnection();
		
	  Statement state = null;
	  ResultSet result;
	  String Name = "";
      int Length = 0;
	  int IdBoat = 0;
	     
      
      try{
    	  state = conn.createStatement();
          result = state.executeQuery("SELECT * FROM organization_sum as os WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) > os.date && person="+userId+"");
          pool.releaseConnection(conn);
          }
      catch(SQLException e){
          Alert alert = new Alert(AlertType.WARNING,"Query error!!!",ButtonType.OK);
		  alert.showAndWait();
          }
      catch(NullPointerException e){
          Alert alert = new Alert(AlertType.INFORMATION,"La quota non � scaduta ancora!!",ButtonType.OK);
		  alert.showAndWait();
          }*/
  }
  
  /**
   * Show boats.
   *
   * @param event the event that Removes the boat.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void showBoat(final ActionEvent event) throws IOException, SQLException
  { 
	colId.setCellValueFactory(new PropertyValueFactory<Boat, String>("id"));
	colNameBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
	colLengthBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
	
	tvData.setItems(tvObservableList); 
  }
  
  private void closeStage(final ActionEvent event) {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  /**
   * Sets the boats' observable list.
   *
   * @param ol  the observable list.
   *
   * @since       1.0
   */
  public void setAppMainObservableList(final ObservableList<Boat> ol) {
  	this.tvObservableList = ol;
	}
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {	 
	/*colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdString()));
	colNameBoat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
	colLengthBoat.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLengthString()));*/
	  
	colId.setCellValueFactory(new PropertyValueFactory<Boat, String>("id"));
	colNameBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
	colLengthBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
	
	tvData.setItems(tvObservableList); 	
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
