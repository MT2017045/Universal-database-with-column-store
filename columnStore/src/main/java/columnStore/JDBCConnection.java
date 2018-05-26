package columnStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class JDBCConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	static final String USER = yourInfo.username;
	static final String PASS = yourInfo.pass;

	public void doTheJDBCStuffs(HashMap<String, String> hashMap,String databaseName) {
		Connection conn = null;
		//
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql_1 = "CREATE DATABASE "+databaseName; // our query to create the database in mySql
			stmt.executeUpdate(sql_1);
			System.out.println("Database created successfully...");
			String sql_2 = "use "+databaseName;
			stmt.executeUpdate(sql_2);
			System.out.println("Database selected...");

			Set set = hashMap.entrySet();

			Iterator iterator = set.iterator();

			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry<String, String>) iterator.next();
				String sql = "CREATE TABLE " + mapEntry.getKey() + "(id INTEGER not NULL AUTO_INCREMENT, "
						+ mapEntry.getKey() + " " + mapEntry.getValue() + "," + " PRIMARY KEY (id) )";
				stmt.executeUpdate(sql);
				System.out.println("Created table in given database...");

			}
			JOptionPane.showMessageDialog(null, "Uploaded");

		} catch (SQLException se) {
			// Handle errors for JDBC
			JOptionPane.showMessageDialog(null, se.getMessage());
			;
		} catch (Exception e) {
			// Handle errors for Class.forName
			JOptionPane.showMessageDialog(null, e.getMessage());
			;
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, se.getMessage());
				;
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end
}
