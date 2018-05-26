package columnStore;

import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class adminDatabase extends JFrame{
	Connection conn;
	public adminDatabase()  {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //it is to load driver
		 	String url = "jdbc:mysql://localhost:3306/DBname"; //database name
		    String uname=yourInfo.username;
		    String pass=yourInfo.pass;
		    
		    conn = DriverManager.getConnection(url,uname,pass); 
		   
		    
	  		}
	  		catch(Exception e)
	  		{
	  			System.out.println(e);
	  		}
	}
  	public void enterName(String databaseName)
  	{
  		try {
  		String query="insert into namelist(dbname) values (\""+databaseName+"\")";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.execute();
		System.out.println("the name of the database insert it namelist!");
  		}
  		catch(Exception e)
  		{
  			System.out.println(e);
  		}
  	}
  	public void enterNamedQuery(String queryName,String exactQuery,String dbname)
  	{
  		//JOptionPane.showMessageDialog(null,queryName );
		//JOptionPane.showMessageDialog(null,exactQuery );
		//OptionPane.showMessageDialog(null,getDatabaseName.database );
  		try {
  		String query="insert into query(queryname,query,dbname) values (\""+queryName+"\",\""+exactQuery+"\",\""+dbname+"\")";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
		preparedStatement.execute();
		System.out.println("the name of the database insert it namelist!");
		JOptionPane.showMessageDialog(null,"Your Query Successfully Saved!" );
  		}
  		catch(Exception e)
  		{
  			JOptionPane.showMessageDialog(null,e );
  			
  		}
  	}
  	public String[] getQueryName(String name)
  	{
  		String[] answer=new String[100];
  		int i=0;
  		try {
  		String query="select queryname from query where dbname=\""+name+"\"";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next())
		 {
			answer[i++]=rs.getString(1);
			//System.out.println(answer[i-1]);
			// System.out.println(rs.getString(1));
		 }
		System.out.println("all query name fetched..!");
		
  		}
  		catch(Exception e)
  		{
  			System.out.println(e);
  		}
  		return answer;
  	}
  	public String getExactQuery(String name)
  	{
  		String answer=null;
  		int i=0;
  		try {
  		String query="select query from query where queryname=\""+name+"\"";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next())
		 {
			answer=rs.getString(1);
			//System.out.println(answer[i-1]);
			// System.out.println(rs.getString(1));
		 }
		System.out.println("all query name fetched..!");
		
  		}
  		catch(Exception e)
  		{
  			System.out.println(e);
  		}
  		return answer;
  	}
  	
  	public String[] getDBName()
  	{
  		String[] answer=new String[100];
  		int i=0;
  		try {
  		String query="select dbname from namelist";
	    PreparedStatement preparedStatement = conn.prepareStatement(query);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next())
		 {
			answer[i++]=rs.getString(1);
			//System.out.println(answer[i-1]);
			// System.out.println(rs.getString(1));
		 }
		System.out.println("all database name fetched..!");
		
  		}
  		catch(Exception e)
  		{
  			System.out.println(e);
  		}
  		return answer;
  	}
  	
 /* 	public static void main(String args[])
  	{
  		adminDatabase databaseName=new adminDatabase();
  		databaseName.enterNamedQuery("check", "select * from employee", "hanshika");
  		
  	}*/
 
}
