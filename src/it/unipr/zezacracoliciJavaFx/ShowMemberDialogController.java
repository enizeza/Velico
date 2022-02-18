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
 * The class {@code ShowMemberDialogController} supports
 * the viewing of the members.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class ShowMemberDialogController implements Initializable {
  @FXML
  private TableView<Person> tvData;
  @FXML
  private TableColumn<Person, String> colId;
  @FXML
  private TableColumn<Person, String> colName;
  @FXML
  private TableColumn<Person, String> colSurname;
  
  private ObservableList<Person> tvObservableList = FXCollections.observableArrayList();
  
  private ObservableList<Boat> tvObservableListBoat = FXCollections.observableArrayList();
 

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
	colId.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
	colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
	colSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
	
	tvData.setItems(tvObservableList); 
	
  }
  
  /**
   * Show Boats.
   *
   * @param event the event that Shows the member boats.
   * 
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void showBoat(final ActionEvent event) throws IOException, SQLException
  { 
	  if (tvData.getSelectionModel().getSelectedItem() != null) {
	        Person selectedPerson = tvData.getSelectionModel().getSelectedItem();
	        
	        int id = selectedPerson.getId();
	        
	        tvObservableListBoat.clear();
			MysqlConnect pool = new MysqlConnect();
			Connection conn = pool.getConnection();
			
			Statement state = null;
		    ResultSet result;
			
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShowBoatDialog.fxml"));
			
		    Parent parent = fxmlLoader.load();
		    
		    ShowBoatDialogController dialogController = fxmlLoader.<ShowBoatDialogController> getController();
				
	        state = conn.createStatement();
	        result = state.executeQuery("select * from boat where owner="+id+"");
	        while(result.next()){
	            String Name = result.getString("name");
	            int Length = result.getInt("length");
	            int IdBoat = result.getInt("idboat");
	            Boat boat = new Boat(IdBoat, Name, Length);
	            tvObservableListBoat.add(boat);
	        	}
	        
	        pool.releaseConnection(conn);
	        
	        
			dialogController.setAppMainObservableList(tvObservableListBoat);
		    
		    Scene scene = new Scene(parent, 500, 500);
		    Stage stage = new Stage();
		    stage.setTitle("Show Boat");
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING,"Nothing selected",ButtonType.OK);
			alert.showAndWait();
		}	
  }
  
  /**
   * Sets the members' observable list.
   *
   * @param ol  the observable list member.
   *
   * @since       1.0
   */
  public void setAppMainObservableList(final ObservableList<Person> ol) {
  	this.tvObservableList = ol;
	}
  
  /** {@inheritDoc} **/
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {	   
  }
}
