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
import it.unipr.zezacracoliciJavaFx.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Person is in charge of saving a person object with its properties.
 * Each person has an username, password, name, surname, fiscalcode and address.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Person {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String fiscalcode;
	private String address;
	
	
	/**
     * Empty constructor for the object
     * 
     * @since 1.0
     */
	public Person() {	
		this.username = "";
		this.password = "";
		this.name = "";
		this.surname = "";
		this.fiscalcode = "";
		this.address = "";
	}
	
	/** 
     * This constructor generates a Person object.
     *
     * @param username the person username 
     * @param password the person password
     * @param name the person name
     * @param surname the person surname
     * @param fiscalcode the person fiscalcode
     * @param address the person address
     * @param role the person role
     * 
     * 
     * @since 1.0
     */
	public Person(String name, String surname, String address, String fiscalcode, String username, String password) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.fiscalcode = fiscalcode;
		this.address = address;
	}
	
	/** 
     * This method gets the Person's username.
     *
     * @return String the Person's username.
     * 
     * @since 1.0
     */
	public String getUsername() {
		return this.username;
	}
	
	/** 
     * This method gets the Person's password.
     *
     * @return String the Person's password.
     * 
     * @since 1.0
     */
	public String getPassword() {
		return this.password;
	}
	
	/** 
     * This method gets the Person's name.
     *
     * @return String the Person's name.
     * 
     * @since 1.0
     */
	public String getName() {
		return this.name;
	}
	
	/** 
     * This method gets the Person's surname.
     *
     * @return String the Person's surname.
     * 
     * @since 1.0
     */
	public String getSurname() {
		return this.surname;
	}
	
	/** 
     * This method gets the Person's fiscalcode.
     *
     * @return String the Person's fiscalcode.
     * 
     * @since 1.0
     */
	public String getFiscalcode() {
		return this.fiscalcode;
	}
	
	/** 
     * This method gets the Person's address.
     *
     * @return String the Person's address.
     * 
     * @since 1.0
     */
	public String getAddress() {
		return this.address;
	}
	
	/**
     * Registration into the database of a person
     * 
     * @param role the role of the person 
	 * @throws SQLException query error
     * 
     * @since 1.0
     */
	public void registration(Member member,String role) throws SQLException {
		PreparedStatement pstate;		
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
		try{
            pstate = conn.prepareStatement("insert into person(name, surname, address, fiscalcode, username, password, role)"+
                                            "values(?,?,?,?,?,?,?)");
            pstate.setString(1, member.getName());
            pstate.setString(2, member.getSurname());
            pstate.setString(3, member.getAddress());
            pstate.setString(4, member.getFiscalcode());
            pstate.setString(5, member.getUsername());
            pstate.setString(6, member.getPassword());
            pstate.setString(7, role);
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
