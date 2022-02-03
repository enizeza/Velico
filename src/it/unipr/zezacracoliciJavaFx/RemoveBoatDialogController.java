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
 * The class {@code RemoveBoatDialogController} supports
 * the remove of a boat.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class RemoveBoatDialogController implements Initializable {
  @FXML
  private TableView<Boat> tvData;
  @FXML
  private TableColumn<Boat, String> colId;
  @FXML
  private TableColumn<Boat, String> colNameBoat;
  @FXML
  private TableColumn<Boat, String> colLengthBoat;
	
  private ObservableList<Boat> tvObservableList = FXCollections.observableArrayList();
  

  /**
   * Removes a boat.
   *
   * @param event the event that Removes the boat.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void deleteBoat(final ActionEvent event) throws IOException, SQLException
  { 
    if (tvData.getSelectionModel().getSelectedItem() != null) {
        Boat selectedBoat = tvData.getSelectionModel().getSelectedItem();
        int id = selectedBoat.getId();
		
        Member member = new Member();
		
		member.removeBoat(id);
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
   * @param event the event that Shows the boats.
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
}
