/*package it.unipr.zezacracoliciJavaFx;

//import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnect{
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String connection = "jdbc:mysql://localhost:3308/velico"; //'milon' is your database name
    private static String user = "root";                  //'root' is username
    private static String password = "123456";        //'pass' is password


    private static Connection con = null;
    private static Statement state = null;
    private static ResultSet result;
    private static PreparedStatement pstate;

    /*public static void main(String args[]) throws Exception{
        mysqlConnect();
        insertData("s6s","milon","dgh","ghjgh");
        deleteData("sd");
        countRow("dictionary");
        updateData("ss", "suru");
        showData("surayea");
        closeConnection();
        }


    public void mysqlConnect(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(connection, user, password);
            System.out.println("Successfully connected to database.");
            }
        catch(ClassNotFoundException e){
            System.err.println("Couldn't load driver.");
            }
        catch(SQLException e){
            System.err.println("Couldn't connect to database.");
            }
        }


    public static void closeConnection(){
        try{
            if(!con.isClosed()){
                con.close();
                System.out.println("Database closed successfully.");
                }
            }
        catch(NullPointerException e){
            System.err.println("Couldn't load driver.");
            }
        catch(SQLException e){
            System.err.println("Couldn't close database.");
            }
        }

    public static void insertData(String word, String meaning, String synonyms, String antonyms){
        try{
            //using PreparedStatement
            pstate = con.prepareStatement("insert into dictionary(word, meaning, synonyms, antonyms)"+
                                            "values(?,?,?,?)");
            pstate.setString(1, word);
            pstate.setString(2, meaning);
            pstate.setString(3, synonyms);
            pstate.setString(4, antonyms);
            int value = pstate.executeUpdate();

            //using Statement
            //state = con.createStatement();
            //int value = state.executeUpdate("insert into dictionary(word, meaning, synonyms, antonyms)"+
            //                      "values('"+word+"', '"+meaning+"', '"+synonyms+"', '"+antonyms+"')");

            System.out.println("Query OK, 1 row insertedted.");
            }
        catch(SQLException e){
            System.err.println("Query error.");
            }
        }

    public static void deleteData(String word){
        try{
            //using PreparedStatement
            pstate = con.prepareStatement("delete from dictionary where word = ?");
            pstate.setString(1,"word");
            int value = pstate.executeUpdate();

            //using Statement
            //state = con.createStatement();
            //int value = state.executeUpdate("delete from dictionary where word='"+word+"'");

            System.out.println("Query OK, 1 row deleted.");
            }
        catch(SQLException e){
            System.err.println("Query error.");
            }
        }

    public static void countRow(String table){
        try{
            result = state.executeQuery("SELECT COUNT(*) FROM "+table);
            result.next();
            int rowcount = result.getInt(1);
            System.out.println("Number of rows: "+rowcount);
            }
        catch(SQLException e){
            System.err.println("Query error.");
            }
        }

    public static void showData(String word){
        try{
            state = con.createStatement();
            result = state.executeQuery("select * from dictionary where word='"+word+"'");
            while(result.next()){
                String word1 = result.getString("word");
                String mean = result.getString("meaning");
                String syno = result.getString("synonyms");
                String anto = result.getString("antonyms");
                System.out.println("Word: "+word1+" Meaning: "+mean+" Synonyms: "+syno+" Antonyms: "+anto);
                }
            }
        catch(SQLException e){
            System.err.println("Query error.");
            }
        catch(NullPointerException e){
            System.err.println("Element not found.");
            }
        }

    public static void updateData(String word, String meaning){
        try{
            //using Statement
            //state = con.createStatement();
            //int value = state.executeUpdate("update dictionary set meaning='"+meaning+"' where word='"+word+"'");

            //using PreparedStatement
            pstate = con.prepareStatement("update dictionary set meaning= ? whrere word = ?");
            pstate.setString(1, meaning);
            pstate.setString(2, word);
            pstate.executeUpdate();

            System.out.println("Query OK, 1 row updated.");
            }
        catch(SQLException e){
            System.err.println("Query error."+e.getMessage());
            }
        }

 }*/

/*package it.unipr.zezacracoliciJavaFx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MysqlConnect {
	private String databaseUrl;
	private String userName;
	private String password;
	private int maxPoolSize = 10;
	private int connNum = 0;

	private static final String SQL_VERIFYCONN = "select 1";

	Stack<Connection> freePool = new Stack<>();
	Set<Connection> occupiedPool = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param databaseUrl
	 *            The connection url
	 * @param userName
	 *            user name
	 * @param password
	 *            password
	 * @param maxSize
	 *            max size of the connection pool
	 
	public MysqlConnect(String databaseUrl, String userName,
			String password, int maxSize) {
		this.databaseUrl = databaseUrl;
		this.userName = userName;
		this.password = password;
		this.maxPoolSize = maxSize;
	}

	/**
	 * Get an available connection
	 * 
	 * @return An available connection
	 * @throws SQLException
	 *             Fail to get an available connection
	 
	public synchronized Connection getConnection() throws SQLException {
		Connection conn = null;

		if (isFull()) {
			throw new SQLException("The connection pool is full.");
		}

		conn = getConnectionFromPool();

		// If there is no free connection, create a new one.
		if (conn == null) {
			conn = createNewConnectionForPool();
		}

		// For Azure Database for MySQL, if there is no action on one connection for some
		// time, the connection is lost. By this, make sure the connection is
		// active. Otherwise reconnect it.
		conn = makeAvailable(conn);
		return conn;
	}

	/**
	 * Return a connection to the pool
	 * 
	 * @param conn
	 *            The connection
	 * @throws SQLException
	 *             When the connection is returned already or it isn't gotten
	 *             from the pool.
	 
	public synchronized void returnConnection(Connection conn)
			throws SQLException {
		if (conn == null) {
			throw new NullPointerException();
		}
		if (!occupiedPool.remove(conn)) {
			throw new SQLException(
					"The connection is returned already or it isn't for this pool");
		}
		freePool.push(conn);
	}

	/**
	 * Verify if the connection is full.
	 * 
	 * @return if the connection is full
	 
	private synchronized boolean isFull() {
		return ((freePool.size() == 0) && (connNum >= maxPoolSize));
	}

	/**
	 * Create a connection for the pool
	 * 
	 * @return the new created connection
	 * @throws SQLException
	 *             When fail to create a new connection.
	 
	private Connection createNewConnectionForPool() throws SQLException {
		Connection conn = createNewConnection();
		connNum++;
		occupiedPool.add(conn);
		return conn;
	}

	/**
	 * Crate a new connection
	 * 
	 * @return the new created connection
	 * @throws SQLException
	 *             When fail to create a new connection.
	 
	private Connection createNewConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(databaseUrl, userName, password);
		return conn;
	}

	/**
	 * Get a connection from the pool. If there is no free connection, return
	 * null
	 * 
	 * @return the connection.
	 
	private Connection getConnectionFromPool() {
		Connection conn = null;
		if (freePool.size() > 0) {
			conn = freePool.pop();
			occupiedPool.add(conn);
		}
		return conn;
	}

	/**
	 * Make sure the connection is available now. Otherwise, reconnect it.
	 * 
	 * @param conn
	 *            The connection for verification.
	 * @return the available connection.
	 * @throws SQLException
	 *             Fail to get an available connection
	 
	private Connection makeAvailable(Connection conn) throws SQLException {
		if (isConnectionAvailable(conn)) {
			return conn;
		}

		// If the connection is't available, reconnect it.
		occupiedPool.remove(conn);
		connNum--;
		conn.close();

		conn = createNewConnection();
		occupiedPool.add(conn);
		connNum++;
		return conn;
	}

	/**
	 * By running a sql to verify if the connection is available
	 * 
	 * @param conn
	 *            The connection for verification
	 * @return if the connection is available for now.
	 
	private boolean isConnectionAvailable(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeQuery(SQL_VERIFYCONN);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Just an Example
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Connection conn2 = null;
		Connection conn3 = null;
		MysqlConnect pool = new MysqlConnect(
				"jdbc:mysql://localhost:3308/velico",
				"root", "123456", 2);
		try {
			conn = pool.getConnection();
			conn2 = pool.getConnection();
			try (Statement statement = conn.createStatement())
			{
				ResultSet res = statement.executeQuery("show tables");
				System.out.println("There are below tables:");
				while (res.next()) {
					String tblName = res.getString(1);
					System.out.println(tblName);
				}
			}
			conn.close();
			conn3 = pool.getConnection();		
		}
		 finally {
			if (conn != null) {
				pool.returnConnection(conn);
			}
		}
	}

}*/

package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries SQL
 * 
 * @version     1.0
 * @since       1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MysqlConnect of connection pool with 5 Available Connections 
 * 
 * @author   Eni Zeza 308966
 * @author   Leonardo Cracolici 306798
 * 
 * @version  1.0
 * @since    1.0
 */

public class MysqlConnect {

	private List<Connection>availableConnections = new ArrayList<Connection>();
	private List<Connection>usedConnections = new ArrayList<Connection>();
	private final int MAX_CONNECTIONS = 5;

	private String URL = "jdbc:mysql://localhost:3308/velico";
	private String USERID = "root";
	private String PASSWORD = "123456";
	
	//MysqlConnect pool = new MysqlConnect("jdbc:mysql://localhost:3308/velico","root", "123456");

	/* Initialize all 5 Connections and put them in the Pool 
	public MysqlConnect(String Url, String UserId, String password) throws SQLException {
		this.URL = Url;
		this.USERID = UserId;
		this.PASSWORD = password;

		for (int count = 0; count <MAX_CONNECTIONS; count++) {
			availableConnections.add(this.createConnection());
		}
	}*/
	
	public MysqlConnect() throws SQLException {
		for (int count = 0; count <MAX_CONNECTIONS; count++) {
			availableConnections.add(this.createConnection());
		}
	}

	/** Private function, 
	used by the Pool to create new connection internally **/

	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection(this.URL, this.USERID, this.PASSWORD);
	}


	/** Public function, used by us to get connection from Pool **/
	public Connection getConnection() {
		if (availableConnections.size() == 0) {
			System.out.println("All connections are Used !!");
			return null;
		} else {
			Connection con = 
			availableConnections.remove(availableConnections.size() - 1);
			usedConnections.add(con);
			return con;
		}
	}


	/** Public function, to return connection back to the Pool **/
	public boolean releaseConnection(Connection con) {
		if (null != con) {
			usedConnections.remove(con);
			availableConnections.add(con);
			return true;
		}
		return false;
	}

	/** Utility function to check the number of Available Connections **/
	public int getFreeConnectionCount() {
		return availableConnections.size();
	}
	
	/*public static void main(String[] args) throws SQLException {	
		
		Connection con1 = pool.getConnection();
		Connection con2 = pool.getConnection();
		System.out.println(pool.getFreeConnectionCount());
		Connection con3 = pool.getConnection();
		Connection con4 = pool.getConnection();
		Connection con5 = pool.getConnection();
		Connection con6 = pool.getConnection();
		System.out.println(pool.getFreeConnectionCount());
		pool.releaseConnection(con1);
		pool.releaseConnection(con2);
		pool.releaseConnection(con4);
		System.out.println(pool.getFreeConnectionCount());
	}*/
}






	

