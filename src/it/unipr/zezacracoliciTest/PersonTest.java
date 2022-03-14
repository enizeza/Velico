package it.unipr.zezacracoliciTest;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

import it.unipr.zezacracolici.Person;
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
 * The class {@code PersonTest} is in charge of testing the methods of the class Person.
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
	
	private static final String NAME = "Eni";
	private static final String SURNAME = "Zeza";
	private static final String ADDRESS = "Via nonsisa";
	private static final String FISCALCODE = "dgdgfdgfd";
	private static final String USERNAME = "eni";
	private static final String PASSWORD = "pass";
	
	@Mock
    private Person p;
    
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
     * Performs the test for the person class constructor.
     * 
     * @version  1.0
     * @since    1.0
     */
    @Test
    public void standardConstructorTest()
    {
      Person person = new Person(NAME, SURNAME, ADDRESS, FISCALCODE, USERNAME, PASSWORD);
      assertTrue(person.getName().equals(NAME));
      assertAll(() -> assertTrue(person.getSurname().equals(SURNAME)),
        () -> assertTrue(person.getAddress().equals(ADDRESS)),
        () -> assertTrue(person.getFiscalcode().equals(FISCALCODE)),
        () -> assertTrue(person.getUsername().equals(USERNAME)),
        () -> assertTrue(person.getPassword().equals(PASSWORD)) );
    }
    
    
    
    /**
     * Performs the test for the person registration method.
     * 
     * @throws Exception error
     * 
     * @version  1.0
     * @since    1.0
     */  
    @Test
    public void testRegistration() throws Exception {
      Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
      Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
      int value = dbConnection.executeQuery("insert into person(name, surname, address, fiscalcode, username, password, role) values('"+NAME+"','"+SURNAME+"','"+ADDRESS+"','"+FISCALCODE+"','"+USERNAME+"','"+PASSWORD+"','Member')");
      Assert.assertEquals(value, 1);
      Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
      dbConnection.executeQuery("delete from person where username = '"+USERNAME+"' and  password = '"+PASSWORD+"'");
    }
}
