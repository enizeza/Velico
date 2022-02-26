package it.unipr.zezacracoliciJavaFx;

/**
 * Libraries SQL and for arrays
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
	
	/**
	 * Used to create the connection available 
	 * 
	 * @since 1.0
	 */
	public MysqlConnect() throws SQLException {
		for (int count = 0; count <MAX_CONNECTIONS; count++) {
			availableConnections.add(this.createConnection());
		}
	}

	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection(this.URL, this.USERID, this.PASSWORD);
	}


	/**
	 * Used by us to get connection from Pool 
	 * 
	 * @since 1.0
	 */
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


	/**
	 * Return connection back to the Pool 
	 * 
	 * @since 1.0
	 */
	public boolean releaseConnection(Connection con) {
		if (null != con) {
			usedConnections.remove(con);
			availableConnections.add(con);
			return true;
		}
		return false;
	}

	/** 
	 * Utility function to check the number of Available Connections 
	 * 
	 * @since 1.0
	 */
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






	

