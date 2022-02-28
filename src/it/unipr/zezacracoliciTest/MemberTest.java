package it.unipr.zezacracoliciTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import it.unipr.zezacracolici.Boat;
import it.unipr.zezacracolici.Member;
import it.unipr.zezacracolici.Race;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * MemberTest is in charge of testing the methods of the class Member.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class MemberTest {
	
	private static final String NAME = "Eni";
	private static final String SURNAME = "Zeza";
	private static final String ADDRESS = "Via nonsisa";
	private static final String FISCALCODE = "dgdgfdgfd";
	private static final String USERNAME = "eni";
	private static final String PASSWORD = "pass";
	
    @Mock
    private Member m;

    @Before
    public void setUp() throws Exception {
    }	
	
    
    /**
     * Performs the test for the member class constructor.
     * 
     * @since 1.0
     */
	@Test
	public void testMemberStringStringStringStringStringString() {
		Member member = new Member(NAME, SURNAME, ADDRESS, FISCALCODE, USERNAME, PASSWORD);
	      assertTrue(member.getName().equals(NAME));
	      assertAll(() -> assertTrue(member.getSurname().equals(SURNAME)),
	        () -> assertTrue(member.getAddress().equals(ADDRESS)),
	        () -> assertTrue(member.getFiscalcode().equals(FISCALCODE)),
	        () -> assertTrue(member.getUsername().equals(USERNAME)),
	        () -> assertTrue(member.getPassword().equals(PASSWORD)) );
	}
	
	
	/**
     * Performs the test for the member addboat method.
     * 
     * @since 1.0
     */
	@Test
	public void testAddBoat() throws SQLException {
		Boat b = new Boat(1,"Barbara",234);
		m.addBoat(b,2);
	}
	
	
	/**
     * Performs the test for the member removeboat method.
     * 
     * @since 1.0
     */
	@Test
	public void testRemoveBoat() throws SQLException {
		Boat b = new Boat(1,"Barbara",234);
		m.addBoat(b,2);
		m.removeBoat(b.getId());
	}
	

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
}
