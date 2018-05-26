package columnStore;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.StringUtils;

public class fireQuery {
	
	private String query;
	private String qq;
	private int tableLength;
	//select from on or multiple table without any condition
	public void selectClause(ArrayList<String> table) 
	{
		
		String columns="";
		String tables="";
		//String query=null;
		//int size=0;
		this.tableLength=table.size();
		for(int i=0;i<table.size();i++)
		{
			//JOptionPane.showMessageDialog(null, table.get(i).toString());
			columns=columns+table.get(i).toString();
			tables=tables+table.get(i).toString();
			if(i!=(table.size()-1))
			{columns=columns+",";
			tables=tables+" NATURAL JOIN ";
			}
			
		}
		query="SELECT "+columns+" FROM "+tables;
		 //System.out.println(query);
		 qq = query;
		
		
	}
	
	public String getQq() {
		return qq;
	}

	

	public void whereClause(ArrayList<String> wherecols,ArrayList<String> operators, ArrayList<String> textvalues,ArrayList<String> conditions) 
	{
		
		//query=query+" WHERE "+whereCondition;
		query=query+" WHERE ";
		for(int i=1;i<wherecols.size();i++)
		{
			query=query+wherecols.get(i)+""+operators.get(i)+"\""+textvalues.get(i)+"\"";
			if(i<(wherecols.size()-1))
				query=query+" "+conditions.get(i)+" ";
		}
		
		System.out.println(query);
		//System.out.println(query);
		/* try {
		 RunningSQLStatement();
		 }
		 catch(Exception e)
		 {
			 JOptionPane.showMessageDialog(null, e);
		 }*/
		
	}
	
	public String getQuery() {
		return query;
	}
	public void RunningSQLStatement() throws Exception
	{
		sqlconn sqlconn=new sqlconn();
		Connection conn=sqlconn.createConn();
		 PreparedStatement preparedStatement = conn.prepareStatement(query);
		 ResultSet rs=preparedStatement.executeQuery();
		
		 
		 while(rs.next())
		 {
			 //JOptionPane.showMessageDialog(null, "hum loop k ander hai");
			 for(int i=1;i<=tableLength;i++)
				 System.out.print(rs.getString(i)+" ");
			 System.out.println("");
		 }
		 
	}
	
	
	public void Display(String[] table,ArrayList<Integer> e,Connection conn) throws Exception
	{
		List<String>[] a= new List[table.length];
		int size=0;
		for(int i=0;i<table.length;i++)
		{
			String query;
			 if(e.isEmpty())
			 query="SELECT "+table[i]+" FROM "+table[i];
			 else
			 {
				 String fetchedID="(";
				 for(int j=0;j<e.size();j++)
				 {
					 fetchedID=fetchedID+e.get(j)+",";
				 }
				 fetchedID=fetchedID.substring(0,fetchedID.length()-1);
				 fetchedID=fetchedID+")";
				 	
				 query="SELECT "+table[i]+" FROM "+table[i]+" WHERE id IN"+fetchedID;
			 }
			 PreparedStatement preparedStatement = conn.prepareStatement(query);
			 ResultSet rs=preparedStatement.executeQuery();
			 List<String> l=new ArrayList<String>();
			 
			 while(rs.next())
				 {
					 l.add(rs.getString(1));
					
				 }
			 size=l.size();
			 a[i]=l;
		}
		for(int i=0;i<table.length;i++)
		{
			 System.out.print(table[i]+" ");	
		}
		 System.out.println("....................................................");	
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<a.length;j++)
			{
				 System.out.print(a[j].get(i)+" ");	
			}
			System.out.println("");
			
		}
		
	}
	public void oneTableWithCondn(String table,Connection conn,String operation,String value) throws Exception
	{
			String op = operation;
			
			 try{
			        Integer.parseInt(value);
			        Float.parseFloat(value);
			    }
			 catch(NumberFormatException e){
			        //not int,not float
			    	String temp="\"";
			    	temp=temp+value;
			    	temp=temp+"\"";
			    	value=temp;
			    }
			
			
			 
			 String query="SELECT "+table+" FROM "+table+" WHERE "+table+op+value;
			 PreparedStatement preparedStatement = conn.prepareStatement(query);
			 ResultSet rs=preparedStatement.executeQuery();
			 while(rs.next())
			 {
				 
				 System.out.println(rs.getString(1));
			 }
		
	}
	
	public void selectAggregateFunction(String[] table,Connection conn) throws Exception
	{ 
		for(int i=0;i<table.length;i++)
		{
			 String query="SELECT SUM(salary) FROM "+table[i];
			 PreparedStatement preparedStatement = conn.prepareStatement(query);
			 ResultSet rs=preparedStatement.executeQuery();
			 rs.next();
				 System.out.println(rs.getString(1));
		}
	}

	
	
	
	public ArrayList<Integer> condition(String ConditionTable,String operation,String value,Connection conn) throws Exception
	{
		String op = operation;
		 try{
		        Integer.parseInt(value);
		        Float.parseFloat(value);
		    }
		 catch(NumberFormatException e){
		        //not int,not float
		    	String temp="\"";
		    	temp=temp+value;
		    	temp=temp+"\"";
		    	value=temp;
		    }
		
		
		
			 String query="SELECT id FROM "+ConditionTable+" WHERE "+ConditionTable+op+value;
			 PreparedStatement preparedStatement = conn.prepareStatement(query);
			 ResultSet rs=preparedStatement.executeQuery();
			 
			 ArrayList<Integer> a=new ArrayList<Integer>();
			 while(rs.next())
			 {
				 a.add(rs.getInt(1));
			 }
		
			System.out.println(a);
			 return a;
		
	}
	
	public ArrayList<Integer> conjunction(ArrayList<Integer>[] a,String c)
	{
		ArrayList<Integer> ans=a[0];
		if(c=="AND")
		{
			for(int i=1;i<a.length;i++)
			{
				ans.retainAll(a[i]);
			}
		}
		else
		{
			for(int i=1;i<a.length;i++)
			{
				ans.removeAll(a[i]);
				ans.addAll(a[i]);
			}
		}
		System.out.println(ans);
		return ans;
	}
	
	
	
/*	public void query() throws Exception
	{
		sqlconn sqlconn=new sqlconn();
		Connection conn=sqlconn.createConn();
		String table[]= {"Customer_Name","Sales","Profit"};
		String table2="Sales";
		String table3="Profit";
		

		System.out.println("id of all Sales value>500");
		
		ArrayList<Integer> a=condition(table2,">","500",conn);
		System.out.println("");
		
		System.out.println("id of all profit value>0");
		
		ArrayList<Integer> b=condition(table3,">","0",conn);
		System.out.println("");
		ArrayList<Integer>[] c= new ArrayList[2];
		c[0]=a;
		c[1]=b;
		ArrayList<Integer> h=new ArrayList<Integer>();
		System.out.println("");
		System.out.println("AND operation between extracted Index");
		h=conjunction(c,"AND");
		System.out.println("");
	
		System.out.println("Displaying customer_Name with sales value>500 AND profit>0");
		Display(table,h,conn);
	
	}*/
	
}