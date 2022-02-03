package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries JavaFX and control Exceptions
 * 
 * @version     1.0
 * @since       1.0
 */

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import it.unipr.zezacracolici.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The class {@code AddRaceDialogController} supports
 * the adding of the data of a new race.
 * 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 *
 * @version  1.0
 * @since    1.0
 */

public class AddRaceDialogController {
  @FXML
  private TextField addRaceName;
  @FXML
  private TextField addRacePlace;
  @FXML
  private DatePicker addRaceDate;
 

  /**
   * Adds a new race.
   *
   * @param event  the event that creates the new boat.
   * @throws IOException input output
   * @throws SQLException query error
   *
   * @since    1.0
   */
  @FXML
  public void addRace(final ActionEvent event) throws IOException, SQLException
  {
    String name = addRaceName.getText().trim();
    String place  = addRacePlace.getText().trim();
    LocalDate localdate = addRaceDate.getValue(); 
    
    Date date = Date.valueOf(localdate);
    
    Race race = new Race(name, place, date);
    
    Staff staff = new Staff();
    staff.addRace(race);
    
    closeStage(event);
  }
  
  private void closeStage(final ActionEvent event) {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
}
