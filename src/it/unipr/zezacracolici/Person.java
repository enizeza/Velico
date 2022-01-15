package it.unipr.zezacracolici;

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
     * 
     * 
     * @since 1.0
     */
	public Person(String username, String password, String name, String surname, String fiscalcode, String address) {
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
}
