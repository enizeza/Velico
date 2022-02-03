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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import it.unipr.zezacracolici.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class {@code TransferPaymentDialogController} supports
 * the payment with transfer.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class TransferPaymentDialogController implements Initializable {
	@FXML
    private Text priceToPay;
	@FXML
    private Text iban;
	@FXML
    private Text beneficiario;
	@FXML
    private Text causale;
	
	private String type;
	private int idboat;
	private int idrace;
	private int price;
	private int iduser;
	
	
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
     * The payment.
     *
     * @param event the event of payment.
	 * @throws SQLException query error
	 * @throws IOException Input output
     *
     * @since       1.0
     */
	public void pay(ActionEvent event) throws SQLException, IOException{	
		String credit = "Transfer";
		MysqlConnect pool = new MysqlConnect();
		try (
				Connection conn = pool.getConnection();
				PreparedStatement pstate = conn.prepareStatement("insert into payment(payment_type)"+"values(?)",
		                                      Statement.RETURN_GENERATED_KEYS);
		    ) {
				pstate.setString(1, credit);

		        int affectedRows = pstate.executeUpdate();

		        if (affectedRows == 0) {
		            Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
					alert.showAndWait();
		        }
		        pool.releaseConnection(conn);
		        
		        try (ResultSet generatedKeys = pstate.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                int idpayment = (int) generatedKeys.getLong(1);
		                
		        		Member member = new Member();
		        		switch (type) {
		        			case "Enroll":
		        				member.enrollBoatRace(idboat, idrace, idpayment, price);
		        				break;
		        			case "Organization":
		        				member.payOrganizationSum(iduser, idpayment, price);
		        				break;
		        			case "Storage":
		        				member.payBoatStorageSum(idboat, idpayment, price);
		        				break;
		        		}
		            }
		            else {
		                Alert alert = new Alert(AlertType.WARNING,"No ID obtained",ButtonType.OK);
		    			alert.showAndWait();
		            }
		        } 
		    }	
		
		closeStage(event);
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
		priceToPay.setText(p.toString());
		iban.setText("IT43W36000032000CA005867472");
		beneficiario.setText("Velico Spa");
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
		causale.setText("Enroll boat to a race");
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
		causale.setText("Storage boat");
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
		causale.setText("Organization fee");
		this.iduser = idUser;
	}
}
