/**
 * 
 */
package it.unipr.zezacracoliciTest;


import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import it.unipr.zezacracolici.Member;
import it.unipr.zezacracolici.Race;
import it.unipr.zezacracolici.Staff;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
	public void setUp() throws Exception {
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
	
	
	/**
     * Performs the test for the staff SendNotificationStorage method.
     * 
     * @since 1.0
     */
	@Test
	public void testSendNotificationStorage() throws SQLException {
		Member m = new Member(ID, USERNAME, PASSWORD);
		s.sendNotificationStorage(m.getId());
	}
	
	/**
     * Performs the test for the staff AddRace method.
     * 
     * @since 1.0
     */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddRace() throws SQLException {
		Date data = new Date(2022, 03, 23);
		Race race = new Race(23,"La Ruleta","Monaco",data);
		s.addRace(race);
	}
	
	/**
     * Performs the test for the staff RemoveRace method.
     * 
     * @since 1.0
     */
	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveRace() throws SQLException {
		Date data = new Date(2022, 03, 23);
		Race race = new Race(23,"La Ruleta","Monaco",data);
		s.addRace(race);
		s.removeRace(race.getIdrace());
	}
}
