package ebay;

import java.sql.*;
/*import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/


public class dbscript {

/*	Connection conn;
	public Connection getConnection(){
		try {
			DriverManager.getConnection("jdbc:mysql://localhost/ebay?user=root&password=toor");
		    
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ebay?user=root&password=toor");
		    return conn;
	
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return conn;
		  
		}catch(Exception ex){
			ex.printStackTrace();
			return conn;
		}
		
	}*/
	
	private String url = "jdbc:mysql://localhost:3306/ebay";
	private String user = "root";
	private String password ="toor";
	
	
	public java.sql.Connection getConnection()
	{
	  
		java.sql.Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			java.sql.Connection myConn = DriverManager.getConnection(url,user,password);
			if(myConn !=null)
			{
				System.out.println("Connected to the database");
				conn = myConn;
			}
				
			
		}
		catch (Exception e)
		{
		  System.out.println("Cannot establish connection");	
		  e.printStackTrace();
		}
		
		return conn;
		
	}
	
	public ResultSet executeGetResults(Connection con, String query) throws SQLException {

		    Statement stmt = null;
		    ResultSet rs;
		    try {
		        stmt = con.createStatement();
		         rs = stmt.executeQuery(query);

		        return rs;
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		        return null;
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    }
		}
	
	public int executeUpdateResults(Connection con, String query) throws SQLException {

	    Statement stmt = null;
	    int rs;
	    try {
	        stmt = con.createStatement();
	         rs = stmt.executeUpdate(query);
	        
	        return rs;
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	        return 0;
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
}
