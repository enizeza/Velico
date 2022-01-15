package it.unipr.zezacracolici;

import java.util.Date;

/**
 * Payment is in charge of the payments.
 * Each payment has a date, price and type.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */
public class Payment {

	private Date date;
	private float price;
	private String type;
	
	/**
	 * Empty constructor for the object
	 * 
	 * @since 1.0
	 */
	public Payment() {
	}
	
	/** 
     * This constructor generates an Payment object.
     *
     * @param date the current date 
     * @param price the price
     * @param type the payment type
     * 
     * @since 1.0
     */
	public Payment(Date date, float price, String type) {
		this.date = new Date();
		//java.sql.Date sqldate = new java.sql.Date(date.getTime());
		this.price = price;
		this.type = type;
	}

}
