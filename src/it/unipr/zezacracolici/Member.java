package it.unipr.zezacracolici;

/**
 * Libraries SQL
 * 
 * @version     1.0
 * @since       1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Member is a subclass of person. It has some privileges more than Person.
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
     * This constructor generates an Member object.
     *
     * @param username the person username 
     * @param password the person password
     * @param name the person name
     * @param surname the person surname
     * @param fiscalcode the person fiscalcode
     * @param address the person address
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
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
	public void addBoat(Boat boat) {
		 
	}
	
	/**
     * Pay organization sum
     * 
     * @since 1.0
     */
	public void payOrganizationSum() {
		
	}
	
	/**
     * Pay boat storage sum
     * 
     * @param boat the boat to storage 
     * 
     * 
     * @since 1.0
     */
	public void payBoatStorageSum(Boat boat) {
		
	}
	
	/**
     * Enroll a boat to a race
     * 
     * @param boat the boat to enroll to the race
     * 
     * 
     * @since 1.0
     */
	public void enrollBoatRace(Boat boat, Race race) {
		
	}
}