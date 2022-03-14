package it.unipr.zezacracolici;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.unipr.zezacracoliciJavaFx.MysqlConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * The class {@code Race} is a subclass of person. It has some privileges more than Person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Staff extends Person{
	
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Staff() {
	}
	
	/**
     * Send a notification of expired organization sum
     * 
     * @param iduser id of the member
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void sendNotificationOrganization(int iduser) throws SQLException {
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
		PreparedStatement pstate;
	    ResultSet result;
		try{
			state = conn.createStatement();
			result = state.executeQuery("SELECT * FROM organization_sum as os WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) < os.date && person="+iduser+"");
			pool.releaseConnection(conn);
			
			boolean val = result.next();
            if(!val){    
        		try{
                    pstate = conn.prepareStatement("insert into notification(person, sum_type, message_read)"+
                                                    "values(?,?,?)");
                    pstate.setString(1, Integer.toString(iduser));
                    pstate.setString(2, "Organization");
                    pstate.setString(3, Integer.toString(0));
                    pstate.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION,"Row inserted correctly",ButtonType.OK);
        			alert.showAndWait();
                    }
                catch(SQLException e){
                    Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
        			alert.showAndWait();
                }	
        		pool.releaseConnection(conn);
            }
          }
      catch(SQLException e){
            Alert alert = new Alert(AlertType.WARNING,"Query error!!!",ButtonType.OK);
		    alert.showAndWait();
          }
      catch(NullPointerException e){
            Alert alert = new Alert(AlertType.INFORMATION,"La quota non è scaduta ancora!!",ButtonType.OK);
		    alert.showAndWait();
          }
	}
	
	/**
     * Send a notification of expired storage sum boat
     * 
     * @param iduser id of the member
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void sendNotificationStorage(int iduser) throws SQLException {
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		Statement state = null;
		PreparedStatement pstate;
	    ResultSet result;
		try{
			state = conn.createStatement();
			result = state.executeQuery("SELECT * FROM boat WHERE boat.owner = "+iduser+" && boat.idboat NOT IN (SELECT boat_storage_sum.boat FROM boat_storage_sum)");
			
			boolean val = result.next();
            if(val){    
        		try{
                    pstate = conn.prepareStatement("insert into notification(person, sum_type, message_read)"+
                                                    "values(?,?,?)");
                    pstate.setString(1, Integer.toString(iduser));
                    pstate.setString(2, "Storage");
                    pstate.setString(3, Integer.toString(0));
                    pstate.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION,"Row inserted correctly",ButtonType.OK);
        			alert.showAndWait();
                    }
                catch(SQLException e){
                    Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
        			alert.showAndWait();
                }	
        		pool.releaseConnection(conn);
            }
            
            result = state.executeQuery("SELECT *\r\n"
            		+ "FROM boat as b join boat_storage_sum as bs on b.idboat = bs.boat\r\n"
            		+ "WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) > bs.date && b.owner = "+iduser+" && b.idboat != all (SELECT b1.idboat FROM boat as b1 join boat_storage_sum as bs1 on b1.idboat = bs1.boat\r\n"
            		+ "                                                                                        WHERE DATE_SUB(CURRENT_DATE(), INTERVAL 1 YEAR) < bs1.date && b1.owner = "+iduser+")\r\n"
            		+ "group by(b.idboat)");
            
            boolean val2 = result.next();
            if(val2){    
        		try{
                    pstate = conn.prepareStatement("insert into notification(person, sum_type, message_read)"+
                                                    "values(?,?,?)");
                    pstate.setString(1, Integer.toString(iduser));
                    pstate.setString(2, "Storage");
                    pstate.setString(3, Integer.toString(0));
                    pstate.executeUpdate();

                    Alert alert = new Alert(AlertType.INFORMATION,"Row inserted correctly",ButtonType.OK);
        			alert.showAndWait();
                    }
                catch(SQLException e){
                    Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
        			alert.showAndWait();
                }	
        		pool.releaseConnection(conn);
            }
            
            pool.releaseConnection(conn);
          }
      catch(SQLException e){
            Alert alert = new Alert(AlertType.WARNING,"Query error!!!",ButtonType.OK);
		    alert.showAndWait();
          }
      catch(NullPointerException e){
            Alert alert = new Alert(AlertType.INFORMATION,"La quota non è scaduta ancora!!",ButtonType.OK);
		    alert.showAndWait();
          }
	}
	
	/**
     * Add a new race
     * 
     * @param race race to add
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void addRace(Race race) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		try{
            pstate = conn.prepareStatement("insert into race(name, place, date)"+
                                            "values(?,?,?)");
            pstate.setString(1, race.getName());
            pstate.setString(2, race.getPlace());
            pstate.setDate(3, (Date) race.getDate());
            pstate.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION,"Row inserted correctly",ButtonType.OK);
			alert.showAndWait();
            }
        catch(SQLException e){
            Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
			alert.showAndWait();
        }	
		pool.releaseConnection(conn);
	}
	
	/**
     * Remove a race
     * 
     * @param idrace id of the race to remove
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void removeRace(int idrace) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		try{
            pstate = conn.prepareStatement("delete from race where idrace = ?");
            pstate.setString(1, Integer.toString(idrace));
            
            pstate.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION,"Row deleted correctly",ButtonType.OK);
			alert.showAndWait();
            }
        catch(SQLException e){
        	Alert alert = new Alert(AlertType.WARNING,"Error!!",ButtonType.OK);
			alert.showAndWait();
            }
		pool.releaseConnection(conn);
	}

}
