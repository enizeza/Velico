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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The class {@code ShowBoatDialogController} supports
 * the viewing of the member boats.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class ShowBoatDialogController implements Initializable {
  @FXML
  private TableView<Boat> tvData;
  @FXML
  private TableColumn<Boat, String> colId;
  @FXML
  private TableColumn<Boat, String> colName;
  @FXML
  private TableColumn<Boat, String> colLength;
  
  private ObservableList<Boat> tvObservableList = FXCollections.observableArrayList();
 

  /**
   * Show members.
   *
   * @param event the event that Shows the members.
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
	colName.setCellValueFactory(new PropertyValueFactory<Boat, String>("name"));
	colLength.setCellValueFactory(new PropertyValueFactory<Boat, String>("length"));
	
	tvData.setItems(tvObservableList); 
	
  }
  
  /**
   * Sets the boats' observable list.
   *
   * @param ol  the observable list boat.
   *
   * @since       1.0
   */
  public void setAppMainObservableList(final ObservableList<Boat> ol) {
  	this.tvObservableList = ol;
	}
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {	   
  }
}
