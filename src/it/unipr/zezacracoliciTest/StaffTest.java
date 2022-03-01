/**
 * 
 */
package it.unipr.zezacracoliciTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.unipr.zezacracolici.Boat;
import it.unipr.zezacracolici.Member;
import it.unipr.zezacracolici.Race;
import it.unipr.zezacracolici.Staff;

/**
 * StaffTest is in charge of testing the methods of the class Staff.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class StaffTest {
	
	private static final int ID = 1;
	private static final String USERNAME = "eni";
	private static final String PASSWORD = "pass";
	
    @Mock
    private Staff s;
	
	@Before
	void setUp() throws Exception {
	}
	
	/**
     * Performs the test for the staff SendNotificationOrganization method.
     * 
     * @since 1.0
     */
	@Test
	public void testSendNotificationOrganization() throws SQLException {
		Member m = new Member(ID, USERNAME, PASSWORD);
		s.sendNotificationOrganization(m.getId());
	}
	
	
	/*
	@Test
	public final void testPayOrganizationSum() throws SQLException {
		m.payOrganizationSum(1, 1, 234);
	}

	@Test
	public final void testPayBoatStorageSum() throws SQLException {
		Boat b = new Boat(1,"Barbara",234);
		m.payBoatStorageSum(b.getId(), 2, 256);
	}

	@SuppressWarnings("deprecation")
	@Test
	public final void testEnrollBoatRace() throws SQLException {
		Date data = new Date(2022, 03, 23);
		Race race = new Race(23,"La Ruleta","Monaco",data);
		Boat b = new Boat(1,"Barbara",234);
		m.enrollBoatRace(b.getId(), race.getIdrace(), 1, 234);
	}

	
	@Test
	final void testSendNotificationOrganization() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSendNotificationStorage() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testAddRace() {
		fail("Not yet implemented"); // TODO
	}


	@Test
	final void testRemoveRace() {
		fail("Not yet implemented"); // TODO
	}*/

}
