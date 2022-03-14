package it.unipr.zezacracolici;

import java.util.Date;

/**
 * The class {@code Race} is in charge of the race.
 * Each Race has an Id, name, place, date.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Race {
	
	private int idrace;
	private String name;
	private String place;
	private Date date;
		
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Race() {
	}
	
	/** 
     * This constructor generates a Race object.
     *
     * @param idrace the race id 
     * @param name the race name
     * @param place the race place
     * @param date the race date
     * 
     * @since 1.0
     */
	public Race(int idrace, String name, String place, Date date) {
		this.idrace = idrace;
		this.name = name;
		this.place = place;
		this.date = date;
	}
	
	/** 
     * This constructor generates a Race object.
     *
     * @param name the race name
     * @param place the race place
     * @param date the race date
     * 
     * @since 1.0
     */
	public Race(String name, String place, Date date) {
		this.name = name;
		this.place = place;
		this.date = date;
	}
	
	/** 
     * This method gets the Race id.
     *
     * @return int the Race id.
     * 
     * @since 1.0
     */
	public int getIdrace() {
		return this.idrace;
	}
	
	/** 
     * This method gets the Race name.
     *
     * @return String the Race name.
     * 
     * @since 1.0
     */
	public String getName() {
		return this.name;
	}
	
	/** 
     * This method gets the Race place.
     *
     * @return string the Race place.
     * 
     * @since 1.0
     */
	public String getPlace() {
		return this.place;
	}
	
	/** 
     * This method gets the Race date.
     *
     * @return Date the Race date.
     * 
     * @since 1.0
     */
	public Date getDate() {
		return this.date;
	}
}
