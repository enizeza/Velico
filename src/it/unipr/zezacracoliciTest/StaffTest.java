package it.unipr.zezacracoliciTest;


import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import it.unipr.zezacracolici.Staff;
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
 * The class {@code StaffTest} is in charge of testing the methods of the class Staff.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class StaffTest {
	
	private static final String DATE = "2023-03-14";
	private static final String NAME = "La rouleta";
	private static final String PLACE = "Barcelona";
	
	@Mock
    private Staff s;
    
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
     * Performs the test for the staff AddRace method.
     * 
     * @throws Exception error
     * 
     * @version  1.0
     * @since    1.0
     */
	@Test
	public void testAddRace() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("insert into race(name, place, date) values('"+NAME+"','"+PLACE+"','"+DATE+"')");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
		//dbConnection.executeQuery("delete from race where name = '"+NAME+"' and place = '"+PLACE+"' and date = '"+DATE+"'");
	}
	
	/**
     * Performs the test for the staff RemoveRace method.
     * 
     * @throws Exception error
     * 
     * @version  1.0
     * @since    1.0
     */
	@Test
	public void testRemoveRace() throws Exception {
		Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
		Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
		int value = dbConnection.executeQuery("delete from race where name = '"+NAME+"' and place = '"+PLACE+"' and date = '"+DATE+"'");
		Assert.assertEquals(value, 1);
		Mockito.verify(mockConnection.createStatement(), Mockito.times(1));		
	}
}
