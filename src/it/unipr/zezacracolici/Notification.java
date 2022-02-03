package it.unipr.zezacracolici;

/**
 * Notification is in charge of the notification.
 * Each notification has a type, id, bool read  and idperson.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Notification {

	private String type;
	private int idperson;
	private int read;
	private int idnotification;
	
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Notification() {
	}
	
	/** 
     * This constructor generates a Notification object.
     *
     * @param person the notification person 
     * @param type the notification type
     * @param read the notification read
     * 
     * @since 1.0
     */
	public Notification(int person, String type, int read) {
		this.idperson = person;
		this.type = type;
		this.read = read;
	}
	
	/** 
     * This constructor generates a Notification object.
     *
     * @param person the notification person 
     * @param type the notification type
     * @param read the notification read
     * @param id the notification id
     * 
     * @since 1.0
     */
	public Notification(int id, int person, String type, int read) {
		this.idnotification = id;
		this.idperson = person;
		this.type = type;
		this.read = read;
	}
	
	/** 
     * This method gets the Notification's type.
     *
     * @return String the Notification's type.
     * 
     * @since 1.0
     */
	public String getType() {
		return this.type;
	}
	
	/** 
     * This method gets the Notification's id.
     *
     * @return int the Notification's id.
     * 
     * @since 1.0
     */
	public int getIdnotification() {
		return this.idnotification;
	}
	
	/** 
     * This method gets the Notification's person.
     *
     * @return int the Notification's person.
     * 
     * @since 1.0
     */
	public int getPerson() {
		return this.idperson;
	}
	
	/** 
     * This method gets the Notification's read.
     *
     * @return int the Notification's read.
     * 
     * @since 1.0
     */
	public int getRead() {
		return this.read;
	}
}
