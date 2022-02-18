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
 * The class {@code RemoveRaceDialogController} supports
 * the remove of a race.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class RemoveRaceDialogController implements Initializable {
  @FXML
  private TableView<Race> tvData;
  @FXML
  private TableColumn<Race, String> colId;
  @FXML
  private TableColumn<Race, String> colNameRace;
  @FXML
  private TableColumn<Race, String> colPlaceRace;
  @FXML
  private TableColumn<Race, String> colDateRace;
	
  private ObservableList<Race> tvObservableList = FXCollections.observableArrayList();
  

  /**
   * Removes a race.
   *
   * @param event the event that Removes the race.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void deleteRace(final ActionEvent event) throws IOException, SQLException
  { 
    if (tvData.getSelectionModel().getSelectedItem() != null) {
        Race selectedRace = tvData.getSelectionModel().getSelectedItem();
        int id = selectedRace.getIdrace();
		
        Staff staff = new Staff();
		
		staff.removeRace(id);
	}
	else {
		Alert alert = new Alert(AlertType.WARNING,"Nothing selected",ButtonType.OK);
		alert.showAndWait();
	}
    closeStage(event);
    tvObservableList.clear();
  }
  
  /**
   * Show races.
   *
   * @param event the event that Shows the races.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void showRace(final ActionEvent event) throws IOException, SQLException
  { 	  
	colId.setCellValueFactory(new PropertyValueFactory<Race, String>("idrace"));
	colNameRace.setCellValueFactory(new PropertyValueFactory<Race, String>("name"));
	colPlaceRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
	colDateRace.setCellValueFactory(new PropertyValueFactory<Race, String>("date"));
	
	tvData.setItems(tvObservableList); 
  }
  
  private void closeStage(final ActionEvent event) {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  /**
   * Sets the race observable list.
   *
   * @param ol  the observable list.
   *
   * @since       1.0
   */
  public void setAppMainObservableList(final ObservableList<Race> ol) {
  	this.tvObservableList = ol;
	}
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {	 
	colId.setCellValueFactory(new PropertyValueFactory<Race, String>("idrace"));
	colNameRace.setCellValueFactory(new PropertyValueFactory<Race, String>("name"));
	colPlaceRace.setCellValueFactory(new PropertyValueFactory<Race, String>("place"));
	colDateRace.setCellValueFactory(new PropertyValueFactory<Race, String>("date"));
	
	tvData.setItems(tvObservableList); 	
  }
}
