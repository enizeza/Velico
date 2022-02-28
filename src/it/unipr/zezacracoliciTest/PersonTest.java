package it.unipr.zezacracoliciTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.Test;

import it.unipr.zezacracolici.Member;
import it.unipr.zezacracolici.Person;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * PersonTest is in charge of testing the methods of the class Person.
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

    @Before
    public void setUp() throws Exception {
    }	

    
    /**
     * Performs the test for the person class constructor.
     * 
     * @since 1.0
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
     * @since 1.0
     */
    @Test
	public void testRegistration() throws Exception{
    	Member m = new Member("Eni","Zeza","Vai nonsisa","dgdgfdgfd","eni","pass");
		p.registration(m,"Member");
	}
}
