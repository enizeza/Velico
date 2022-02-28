package it.unipr.zezacracoliciTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unipr.zezacracolici.Notification;

/**
 * NotificationTest is in charge of testing the methods of the class Notification.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

public class NotificationTest {

	private static final int ID = 1;
	private static final int IDPERSON = 9;
	private static final String TYPE = "Storage";
	private static final int READ = 0;

	@Test
	final void testNotificationIntStringInt() {
		Notification notification = new Notification(IDPERSON, TYPE, READ);
		  assertTrue(notification.getPerson() == IDPERSON);
		  assertAll(() -> assertTrue(notification.getType().equals(TYPE)),
		    () -> assertTrue(notification.getRead() == READ) );
	}

	@Test
	final void testNotificationIntIntStringInt() {
		Notification notification = new Notification(ID, IDPERSON, TYPE, READ);
		  assertTrue(notification.getIdnotification() == ID);
		  assertAll(() -> assertTrue(notification.getType().equals(TYPE)),
		    () -> assertTrue(notification.getRead() == READ),
		    () -> assertTrue(notification.getPerson() == IDPERSON));
	}

}
