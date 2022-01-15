package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX, control Exceptions, reading files
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * The class {@code RegistrationController} supports
 * the registration of a person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 *
 *
 * @version     1.0
 * @since       1.0
 */
public class RegistrationController implements Initializable {
	@FXML
    private TextField registrationName;
	@FXML
    private TextField registrationSurname;
	@FXML
    private TextField registrationAddress;
	@FXML
    private TextField registrationFiscalcode;
	@FXML
    private TextField registrationUsername;
	@FXML
    private TextField registrationPassword;
	
	MysqlConnect con = new MysqlConnect();
	
	
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
     * Registration giving credentials.
     *
     * @param event the event of registration of a person.
     * @throws IOException input output
     *
     * @since       1.0
     */
	public void userRegistration(ActionEvent event) throws IOException{
		//conn.mysqlConnect();
		//conn.insertData("s6s","milon","dgh","ghjgh");
	}
	
	private static void insertData(String word, String meaning, String synonyms, String antonyms){
	    /*PreparedStatement pstate;
	    con.mysqlConnect();
        try{
            //using PreparedStatement
            pstate = con.prepareStatement("insert into person(name, surname, address, fiscalcode, usename, password)"+
                                            "values(?,?,?,?)");
            pstate.setString(1, word);
            pstate.setString(2, meaning);
            pstate.setString(3, synonyms);
            pstate.setString(4, antonyms);
            int value = pstate.executeUpdate();

            //using Statement
            //state = con.createStatement();
            //int value = state.executeUpdate("insert into dictionary(word, meaning, synonyms, antonyms)"+
            //                      "values('"+word+"', '"+meaning+"', '"+synonyms+"', '"+antonyms+"')");

            System.out.println("Query OK, 1 row insertedted.");
            }
        catch(SQLException e){
            System.err.println("Query error.");
            }*/
	}
}
