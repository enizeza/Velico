package it.unipr.zezacracolici;

/**
 * Boat is in charge of saving a boat object with its properties.
 * Each boat has an name, id and length.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Boat {

	private String name;
	private int id;
	private int length;
	
	/**
     * Empty constructor for the object
     * 
     * @since 1.0
     */
	public Boat() {
	}
	
	/** 
     * This constructor generates a Boat object.
     *
     * @param name the boat name 
     * @param length the boat length
     * 
     * @since 1.0
     */
	public Boat(String name, int length) {
		this.name = name;
		this.length = length;
	}
	
	/** 
     * This constructor generates a Boat object.
     *
     * @param name the boat name 
     * @param length the boat length
     * 
     * @since 1.0
     */
	public Boat(int id, String name, int length) {
		this.id = id;
		this.name = name;
		this.length = length;
	}
	
	/** 
     * This method gets the Boat's name.
     *
     * @return String the Boat's name.
     * 
     * @since 1.0
     */
	public String getName() {
		return this.name;
	}
	
	/** 
     * This method gets the Boat's id.
     *
     * @return int the Boat's id.
     * 
     * @since 1.0
     */
	public int getId() {
		return this.id;
	}
	
	/** 
     * This method gets the Boat's length.
     *
     * @return int the Boat's length.
     * 
     * @since 1.0
     */
	public int getLength() {
		return this.length;
	}

}
