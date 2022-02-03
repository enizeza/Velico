package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions, reading files, SQL
 * 
 * @version     1.0
 * @since       1.0
 */

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The class {@code PaySelectionCardTransferDialogController} supports
 * the selection of the payment method.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 *
 *
 * @version     1.0
 * @since       1.0
 */
public class PaySelectionCardTransferDialogController implements Initializable {
	private int price;
	private int idboat;
	private int idrace;
	private int iduser;
	private String type;
	
	/** {@inheritDoc} **/
	@Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
	 
	private void closeStage(ActionEvent event) {
		Node  source = (Node) event.getSource();
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	/**
     * Payment with card.
     *
     * @param event the event of paying with card.
	 * @throws IOException Input output
     *
     * @since       1.0
     */
	public void payCard(ActionEvent event) throws  IOException{	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CardPayment.fxml"));
		
	    Parent parent= fxmlLoader.load();
	    
	    CardPaymentDialogController dialogController = fxmlLoader.<CardPaymentDialogController> getController();
	    dialogController.initData(price,type);
	    
	    switch (type) {
			case "Enroll":
				dialogController.initDataEnroll(idboat,idrace);
				break;
			case "Organization":
				dialogController.initDataOrganization(iduser);
				break;
			case "Storage":
				dialogController.initDataStorage(idboat);
				break;
		}
		
		closeStage(event);
		Scene sceneLogin = new Scene(parent, 400, 300);
	    Stage stageLogin = new Stage();
	    stageLogin.setTitle("Card Payment");
	    stageLogin.initModality(Modality.APPLICATION_MODAL);
	    stageLogin.setScene(sceneLogin);
	    stageLogin.show();
	}
	
	/**
     * Payment with transfer.
     *
     * @param event the event of paying with transfer.
	 * @throws IOException Input output
     *
     * @since       1.0
     */
	public void payTransfer(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferPayment.fxml"));
		
	    Parent parent= fxmlLoader.load();
	    
	    TransferPaymentDialogController dialogController = fxmlLoader.<TransferPaymentDialogController> getController();
	    dialogController.initData(price,type);
	    
	    switch (type) {
			case "Enroll":
				dialogController.initDataEnroll(idboat,idrace);
				break;
			case "Organization":
				dialogController.initDataOrganization(iduser);
				break;
			case "Storage":
				dialogController.initDataStorage(idboat);
				break;
		}
		
		closeStage(event);
		Scene sceneLogin = new Scene(parent, 500, 400);
	    Stage stageLogin = new Stage();
	    stageLogin.setTitle("Transfer Payment");
	    stageLogin.initModality(Modality.APPLICATION_MODAL);
	    stageLogin.setScene(sceneLogin);
	    stageLogin.show();
	}
	
	/**
     * Initialize the data.
     *
     * @param p the price
     * @param type the type of payment
     *
     * @since  1.0
     */
	public void initData(final Integer p, final String type) {
		this.price = p;
		this.type = type;
	}
	
	/**
     * Initialize the data for enrollment.
     *
     * @param idBoat the id of the boat
     * @param idRace the id of the race
     *
     * @since  1.0
     */
	public void initDataEnroll(final Integer idBoat, final Integer idRace) {
		this.idboat = idBoat;
		this.idrace = idRace;
	}
	
	/**
     * Initialize the data for Boat storage sum.
     *
     * @param idBoat the id of the boat
     *
     * @since  1.0
     */
	public void initDataStorage(final Integer idBoat) {
		this.idboat = idBoat;
	}
	
	/**
     * Initialize the data for User organization sum.
     *
     * @param idUser the id of the user
     *
     * @since  1.0
     */
	public void initDataOrganization(final Integer idUser) {
		this.iduser = idUser;
	}
}
