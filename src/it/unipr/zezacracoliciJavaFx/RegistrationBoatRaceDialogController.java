package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX and control Exceptions
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
 * The class {@code RegistrationBoatRaceDialogController} supports
 * the registation of a boat to a race.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class RegistrationBoatRaceDialogController implements Initializable {
  @FXML
  private TableView<Boat> tvData;
  @FXML
  private TableColumn<Boat, String> colId;
  @FXML
  private TableColumn<Boat, String> colNameBoat;
  @FXML
  private TableColumn<Boat, String> colLengthBoat;
  
  @FXML
  private TableView<Race> tvDataRace;
  @FXML
  private TableColumn<Race, String> colIdRace;
  @FXML
  private TableColumn<Race, String> colNameRace;
  @FXML
  private TableColumn<Race, String> colPlaceRace;
  @FXML
  private TableColumn<Race, String> colDateRace;
	
  private ObservableList<Boat> tvObservableList = FXCollections.observableArrayList();
  
  private ObservableList<Race> tvObservableListRace = FXCollections.observableArrayList();

  /**
   * Registration of a boat to a race.
   *
   * @param event the event that register the boat to a race.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void registrationBoatRace(final ActionEvent event) throws IOException, SQLException
  { 
    if (tvData.getSelectionModel().getSelectedItem() != null && tvDataRace.getSelectionModel().getSelectedItem() != null) {
        Boat selectedBoat = tvData.getSelectionModel().getSelectedItem();
        Race selectedRace = tvDataRace.getSelectionModel().getSelectedItem();
        
        int id = selectedBoat.getId();
        int idRace = selectedRace.getIdrace();
        int price = 100; //Prezzo standard gara
        String type = "Enroll";
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PaymentSelectionCardTransfer.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    PaySelectionCardTransferDialogController dialogController = fxmlLoader.<PaySelectionCardTransferDialogController> getController();
	    dialogController.initData(price,type);
	    dialogController.initDataEnroll(id,idRace);
	
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
   * Show boats and races.
   *
   * @param event the event that Shows the boats and races.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void showData(final ActionEvent event) throws IOException, SQLException
  { 
	colId.setCellValueFactory(new PropertyValueFactory<Boat, String>("id"));
	colNameBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
	colLengthBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
	
	tvData.setItems(tvObservableList); 
	
	colIdRace.setCellValueFactory(new PropertyValueFactory<Race, String>("idrace"));
	colNameRace.setCellValueFactory(new PropertyValueFactory<Race, String>("name"));
	colPlaceRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
	colDateRace.setCellValueFactory(new PropertyValueFactory<Race, String>("date"));
	
	tvDataRace.setItems(tvObservableListRace); 
  }
  
  private void closeStage(final ActionEvent event) {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  /**
   * Sets the boats' and races observable list.
   *
   * @param ol  the observable list boat.
   * @param olrace  the observable list race.
   *
   * @since       1.0
   */
  public void setAppMainObservableList(final ObservableList<Boat> ol, final ObservableList<Race> olrace) {
  	this.tvObservableList = ol;
  	this.tvObservableListRace = olrace;
	}
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {	   
	colId.setCellValueFactory(new PropertyValueFactory<Boat, String>("id"));
	colNameBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
	colLengthBoat.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
	
	tvData.setItems(tvObservableList); 
	
	colIdRace.setCellValueFactory(new PropertyValueFactory<Race, String>("idrace"));
	colNameRace.setCellValueFactory(new PropertyValueFactory<Race, String>("name"));
	colPlaceRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
	colDateRace.setCellValueFactory(new PropertyValueFactory<Race, String>("date"));
		
	tvDataRace.setItems(tvObservableListRace); 
  }
}
