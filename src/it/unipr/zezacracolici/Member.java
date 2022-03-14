package it.unipr.zezacracolici;

/**
 * Libraries SQL
 * 
 * @version     1.0
 * @since       1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.unipr.zezacracoliciJavaFx.MysqlConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * The class {@code Member} is a subclass of person. It has some privileges more than Person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Member extends Person {

	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Member() {
	}
	
	/** 
     * This constructor generates a Member object.
     *
     * @param username the member username 
     * @param password the member password
     * @param id the member id
     * 
     * @since 1.0
     */
	public Member(int id, String username, String password) {
		super(id, username, password);
	}
	
	/** 
     * This constructor generates a Member object.
     *
     * @param username the member username 
     * @param password the member password
     * @param name the member name
     * @param surname the member surname
     * @param fiscalcode the member fiscalcode
     * @param address the member address
     * 
     * @since 1.0
     */
	public Member(String name, String surname, String address, String fiscalcode, String username, String password) {
		super(name, surname, address, fiscalcode, username, password);
	}
		
	/**
     * Add boat ownership
     * 
     * @param boat boat of the member to add
     * @param idowner id of the member
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void addBoat(Boat boat, int idowner) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		try{
            pstate = conn.prepareStatement("insert into boat(name, length, owner)"+
                                            "values(?,?,?)");
            pstate.setString(1, boat.getName());
            pstate.setString(2, Integer.toString(boat.getLength()));
            pstate.setString(3, Integer.toString(idowner));
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
     * Remove boat ownership
     * 
     * @param idboat id of the boat to remove
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void removeBoat(int idboat) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		
		try{
            pstate = conn.prepareStatement("delete from boat where idboat = ?");
            pstate.setString(1, Integer.toString(idboat));
            
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
	
	/**
     * Pay organization sum
     * 
     * @param iduser the user id 
     * @param idPayment the payment id
     * @param price the storage price 
     * 
     * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void payOrganizationSum(int iduser, int idPayment, int price) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		try{
            pstate = conn.prepareStatement("insert into organization_sum(person, payment, date, price)"+
                                            "values(?,?,?,?)");
            
            pstate.setString(1, Integer.toString(iduser));
            pstate.setString(2, Integer.toString(idPayment));
            pstate.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pstate.setString(4, Integer.toString(price));
            
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
     * Pay boat storage sum
     * 
     * @param idboat the boat id 
     * @param idPayment the payment id
     * @param price the storage price 
     * 
     * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void payBoatStorageSum(int idboat, int idPayment, int price) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		try{
            pstate = conn.prepareStatement("insert into boat_storage_sum(boat, payment, date, price)"+
                                            "values(?,?,?,?)");
            
            pstate.setString(1, Integer.toString(idboat));
            pstate.setString(2, Integer.toString(idPayment));
            pstate.setDate(3, sqlDate);
            pstate.setString(4, Integer.toString(price));
            
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
     * Enroll a boat to a race
     * 
     * @param idboat the boat id
     * @param idrace the race id
     * @param idPayment the payment id
     * @param price the race price 
     * 
	 * @throws SQLException query errors
     * 
     * @since 1.0
     */
	public void enrollBoatRace(int idboat, int idrace, int idPayment,int price) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		try{
            pstate = conn.prepareStatement("insert into participant(boat, race, payment, price)"+
                                            "values(?,?,?,?)");
            
            pstate.setString(1, Integer.toString(idboat));
            pstate.setString(2, Integer.toString(idrace));
            pstate.setString(3, Integer.toString(idPayment));
            pstate.setString(4, Integer.toString(price));
            
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