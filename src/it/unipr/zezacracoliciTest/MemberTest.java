package it.unipr.zezacracoliciTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import it.unipr.zezacracolici.Member;
import it.unipr.zezacracoliciJavaFx.MysqlConnect;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * The class {@code MemberTest} is in charge of testing the methods of the class Member.
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
	
	private static final String NAMEBOAT = "BoatTest";
	private static final int LENGTHBOAT = 123;
	private static final int OWNER = 1;
	
	private static final int PRICE = 345;
	private static final int PAYMENT = 1;
	private static final String DATE = "2022-03-14";
	
	private static final int BOATESTID = 1;
	
	private static final int RACEID = 1;
	    
    @Mock
    private Member m;
    
	@InjectMocks private MysqlConnect dbConnection;
    @Mock private Connection mockConnection;
    @Mock private Statement mockStatement;
   
    /**
     * SetUp for the test.
     * 
     * @version  1.0
     * @since    1.0
     */
    @SuppressWarnings("deprecation")
	@Before
    public void setUp() {
      MockitoAnnotations.initMocks(this);
    }
    
	   
    /**
     * Performs the test for the member class constructor.
     * 
     * @version  1.0
     * @since    1.0
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
     * @throws Exception error
     * 
     * @version  1.0 
     * @since    1.0
     * 
     */
	@Test
	public void testAddBoat() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("insert into boat(name, length, owner) values('"+NAMEBOAT+"','"+LENGTHBOAT+"','"+OWNER+"')");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
		//dbConnection.executeQuery("delete from boat where name = '"+NAMEBOAT+"' and owner = '"+OWNER+"' and length = '"+LENGTHBOAT+"'");
	}
	
	
	/**
     * Performs the test for the member removeboat method.
     * 
	 * @throws Exception error
	 * 
	 * @version  1.0
     * @since    1.0
     */
	@Test
	public void testRemoveBoat() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("delete from boat where name = '"+NAMEBOAT+"' and owner = '"+OWNER+"' and length = '"+LENGTHBOAT+"'");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	}
	
	/**
     * Performs the test for the member payorganizationsum method.
     * 
     * @throws Exception error
     * 
     * @version  1.0
     * @since    1.0
     */
	@Test
	public final void testPayOrganizationSum() throws Exception {	
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("insert into organization_sum(person, payment, date, price) values('"+OWNER+"','"+PAYMENT+"','"+DATE+"','"+PRICE+"')");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
		dbConnection.executeQuery("delete from organization_sum where person = '"+OWNER+"' and payment = '"+PAYMENT+"' and date = '"+DATE+"' and price = '"+PRICE+"'");
	}
	
	/**
     * Performs the test for the member payboatstoragesum method.
     * 
     * @throws Exception error
     * 
     * @version  1.0
     * @since    1.0
     */
	@Test
	public final void testPayBoatStorageSum() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("insert into boat_storage_sum(boat, payment, date, price) values('"+BOATESTID+"','"+PAYMENT+"','"+DATE+"','"+PRICE+"')");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
		dbConnection.executeQuery("delete from boat_storage_sum where boat = '"+BOATESTID+"' and payment = '"+PAYMENT+"' and date = '"+DATE+"' and price = '"+PRICE+"'");
	}

	/**
     * Performs the test for the member enrollboatrace method.
     * 
     * @throws Exception error
     *
     * @version  1.0
     * @since    1.0
     */
	@Test
	public final void testEnrollBoatRace() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("insert into participant(boat, race, payment, price) values('"+BOATESTID+"','"+RACEID+"','"+PAYMENT+"','"+PRICE+"')");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
		dbConnection.executeQuery("delete from participant where boat = '"+BOATESTID+"' and payment = '"+PAYMENT+"' and race = '"+RACEID+"' and price = '"+PRICE+"'");
	}
}
