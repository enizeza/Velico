/*package it.unipr.zezacracoliciTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;*/


package it.unipr.zezacracoliciTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;


import it.unipr.zezacracolici.Member;
import it.unipr.zezacracolici.Person;
import it.unipr.zezacracoliciJavaFx.MysqlConnect;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {
	
	@Mock
    private MysqlConnect ds;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;
    
    @Mock
    private Person p;

    @Before
    public void setUp() throws Exception {
        assertNotNull(ds);
    }	
	
    public static final String NAME = "Eni";
    public static final String SURNAME = "Zeza";
    public static final String ADDRESS = "Via nonsisa";
    public static final String FISCALCODE = "dgdgfdgfd";
    public static final String USERNAME = "eni";
    public static final String PASSWORD = "pass";

    /**
     * Performs the test for the person class constructor.
     *
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
    
    @Test
	public void testRegistration() throws Exception{
    	Member m = new Member("Eni","Zeza","Vai nonsisa","dgdgfdgfd","eni","pass");
		p.registration(m,"Member");
	}
}
