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
  private TableColumn<String, String> colId;
  @FXML
  private TableColumn<String, String> colNameBoat;
  @FXML
  private TableColumn<String, String> colLengthBoat;
	
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
    tvData.getItems().clear();
  }
  
  private void closeStage(final ActionEvent event) {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
  
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
	colNameBoat.setCellValueFactory(new PropertyValueFactory<>("name_product"));
	colLengthBoat.setCellValueFactory(new PropertyValueFactory<>("name_factory"));
	tvData.setItems(tvObservableList);
  }
}
