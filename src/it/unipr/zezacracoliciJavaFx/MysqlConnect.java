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
	
	/**
	 * Used to create the connection available
	 * 
	 * @throws SQLException query errors
	 * 
	 * @version     1.0
     * @since       1.0
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
	 * @return con the connection for the database
	 * 
	 * @version     1.0
     * @since       1.0
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
	 * @param con the connection for the database to release
	 * 
	 * @return boolean that shows if the release was done
	 * 
	 * @version     1.0
     * @since       1.0
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
	 * @return int the number of connections available
	 * 
	 * @version     1.0
     * @since       1.0
	 */
	public int getFreeConnectionCount() {
		return availableConnections.size();
	}
	
	/** 
	 * Utility function to execute a query 
	 * 
	 * @param query the query to execute
	 * 
	 * @throws ClassNotFoundException error class
	 * @throws SQLException query error
	 * 
	 * @return int the result of the execution of the query
	 * 
	 * @version     1.0
     * @since       1.0
	 */
	public int executeQuery(String query) throws ClassNotFoundException, SQLException {
		MysqlConnect pool = new MysqlConnect();
		Connection conn = pool.getConnection();
	    return conn.createStatement().executeUpdate(query);
	}
}






	

