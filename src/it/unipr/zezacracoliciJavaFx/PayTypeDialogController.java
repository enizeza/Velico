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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
	  if (tvData.getSelectionModel().getSelectedItem() != null) {
	        Boat selectedBoat = tvData.getSelectionModel().getSelectedItem();
	        
	        int id = selectedBoat.getId();
	        int length = selectedBoat.getLength();
	        int price = length*10; //Prezzo personalizzato in base alla lunghezza
	        String type = "Storage";
	        
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentSelectionCardTransfer.fxml"));
			
		    Parent parent = fxmlLoader.load();
		    
		    PaySelectionCardTransferDialogController dialogController = fxmlLoader.<PaySelectionCardTransferDialogController> getController();
		    dialogController.initData(price,type);
		    dialogController.initDataStorage(id);
		
		    Scene scene = new Scene(parent, 300, 200);
		    Stage stage = new Stage();
		    stage.setTitle("Payment method");
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING,"Nothing selected",ButtonType.OK);
			alert.showAndWait();
		}
	    closeStage(event);
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
	  MysqlConnect pool = new MysqlConnect();
	  Connection conn = pool.getConnection();
		
	  Statement state = null;
	  ResultSet result;
	  int price = 300; //Prezzo standard iscrizione
           
	  try{
			state = conn.createStatement();
			result = state.executeQuery("SELECT * FROM organization_sum as os WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) < os.date && person="+userId+"");
			pool.releaseConnection(conn);
			
			boolean val = result.next();
            if(val){
                Alert alert = new Alert(AlertType.WARNING,"La quota non è scaduta ancora!!",ButtonType.OK);
                alert.showAndWait();
             }
            else {
            	String type = "Organization";
    			
    		    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentSelectionCardTransfer.fxml"));
    		
    		    Parent parent = fxmlLoader.load();
    		 
    		    PaySelectionCardTransferDialogController dialogController = fxmlLoader.<PaySelectionCardTransferDialogController> getController();
    		    dialogController.initData(price,type);
    		    dialogController.initDataOrganization(userId);
    		
    		    Scene scene = new Scene(parent, 300, 200);
    		    Stage stage = new Stage();
    		    stage.setTitle("Payment method");
    		    stage.initModality(Modality.APPLICATION_MODAL);
    		    stage.setScene(scene);
    		    stage.showAndWait();
            }
          }
      catch(SQLException e){
            Alert alert = new Alert(AlertType.WARNING,"Query error!!!",ButtonType.OK);
		    alert.showAndWait();
          }
      catch(NullPointerException e){
            Alert alert = new Alert(AlertType.INFORMATION,"La quota non è scaduta ancora!!",ButtonType.OK);
		    alert.showAndWait();
          }
	  closeStage(event);
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
