package it.unipr.zezacracolici;

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
	public Member(String username, String password, String name, String surname, String fiscalcode, String address) {
		super(username, password, name, surname, fiscalcode, address);
	}
		
	/**
     * Add boat ownership
     * 
     * @param boat of the
     * 
     * @throws IOException input output
     * 
     * @since 1.0
     */
	public void addBoat(Boat boat) {
		 String driver = "com.mysql.cj.jdbc.Driver";
	    String connection = "jdbc:mysql://localhost:3308/velico"; //'milon' is your database name
	     String user = "root";                  //'root' is username
	     String password = "123456";        //'pass' is password


	     Connection con = null;
	      Statement state = null;
	      ResultSet result;
	      PreparedStatement pstate;
		
		try{
            Class.forName(driver);
            con = DriverManager.getConnection(connection, user, password);
            System.out.println("Successfully connected to database.");
            }
        catch(ClassNotFoundException e){
            System.err.println("Couldn't load driver.");
            }
        catch(SQLException e){
            System.err.println("Couldn't connect to database.");
            }
		
		try{
            //using PreparedStatement
            pstate = con.prepareStatement("insert into boat(name, length)"+
                                            "values(?,?)");
            pstate.setString(1, boat.getName());
            pstate.setString(2, String.valueOf(boat.getLength()));
            int value = pstate.executeUpdate();

            //using Statement
            //state = con.createStatement();
            //int value = state.executeUpdate("insert into dictionary(word, meaning, synonyms, antonyms)"+
            //                      "values('"+word+"', '"+meaning+"', '"+synonyms+"', '"+antonyms+"')");

            System.out.println("Query OK, 1 row insertedted.");
            }
        catch(SQLException d){
            System.err.println("Query error.");
            }
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
     * Pay boat storage sum
     * 
     * @param boat the boat to storage 
     * 
     * 
     * @since 1.0
     */
	public void enrollBoatRace(Boat boat, Race race) {
		
	}
	
	/**
     * Pay boat storage sum
     * 
     * @param boat the boat to storage 
     * 
     * 
     * @since 1.0
     */
	public void registation() {
		
	}
}