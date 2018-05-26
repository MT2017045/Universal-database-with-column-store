package columnStore;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconn {
public Connection createConn() throws Exception{

		Class.forName("com.mysql.jdbc.Driver"); //it is to load driver
	 	String url = "jdbc:mysql://localhost:3306/"+getDatabaseName.database; //database name
	   // String uname="root";
	    String uname=yourInfo.username;
	    String pass=yourInfo.pass;
	    
	    Connection conn = DriverManager.getConnection(url,uname,pass); 
	    return conn;

	}
}
