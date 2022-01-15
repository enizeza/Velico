package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX and control Exceptions
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;

import it.unipr.zezacracolici.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The class {@code AddBoatDialogController} supports
 * the adding of the data of a new boat.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class AddBoatDialogController {
  @FXML
  private TextField addBoatName;
  @FXML
  private TextField addBoatLength;

  /**
   * Adds a new boat.
   *
   * @param event  the event that creates the new boat.
   * @throws IOException input output
   *
   * @since    1.0
   */
  @FXML
  public void addBoat(final ActionEvent event) throws IOException
  {
    String name = addBoatName.getText().trim();
    Integer length  = Integer.valueOf(addBoatLength.getText().trim());

    Boat boat = new Boat(name,length);

    Member mem = new Member();
    mem.addBoat(boat);
    closeStage(event);
  }
  
  private void closeStage(final ActionEvent event) {
	    Node  source = (Node) event.getSource();
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
  }
}
