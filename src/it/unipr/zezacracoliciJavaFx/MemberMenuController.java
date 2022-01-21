package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions and list
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;

import java.net.URL;
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
	private TableColumn<String, String> colId;
	@FXML
	private TableColumn<String, String> colType;
	
	private ObservableList<Notification> tvObservableList = FXCollections.observableArrayList();
	
	private ObservableList<Boat> appMainObservableList;
	
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
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
	 * Handles the open dialog event for removing a boat.
     *
     * @param e  the event.
	 * @throws IOException input output
     *
     * @since       1.0
     */
	@FXML
	public void onOpenDialogRemoveBoat(final ActionEvent e) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveBoatDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	    
	    SearchProductDialogController dialogController = fxmlLoader.<SearchProductDialogController> getController();
		dialogController.setAppMainObservableList(tvObservableList);
	    
	    Scene scene = new Scene(parent, 300, 200);
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	  
	/**
   	 * Handles the open dialog event for registration of a boat into a race.
   	 *
   	 * @param e  the event.
   	 * @throws IOException input output
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onOpenDialogRegistrationRaceBoat(final ActionEvent e) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegistrationBoatRaceDialog.fxml"));
		
	    Parent parent = fxmlLoader.load();
	
	    Scene scene = new Scene(parent, 300, 200);
	    Stage stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.showAndWait();
	}
	
	/**
   	 * Handles the open dialog event for payments.
   	 *
   	 * @param e  the event.
   	 * @throws IOException input output
   	 *
   	 * @since       1.0
   	 */
	@FXML
	public void onOpenDialogPayments(final ActionEvent e) throws IOException{
		/*if (tvData.getSelectionModel().getSelectedItem() != null) {
	        Product selectedProduct = tvData.getSelectionModel().getSelectedItem();
	        int id = selectedProduct.getId();
	        
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BuyProductDialog.fxml"));
	    	
		    Parent parent = fxmlLoader.load();
		    
		    BuyProductDialogController dialogController =
		            fxmlLoader.<BuyProductDialogController> getController();
		        dialogController.initData(id);
		
		    Scene scene = new Scene(parent, 300, 200);
		    Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING,"Nothing selected",ButtonType.OK);
			alert.showAndWait();
		}*/
	}

	/** {@inheritDoc} **/
    @Override
  	public void initialize(final URL location, final ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		//colNameProduct.setCellValueFactory(new PropertyValueFactory<>("name_product"));
		//colNameFactory.setCellValueFactory(new PropertyValueFactory<>("name_factory"));
		//colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
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
