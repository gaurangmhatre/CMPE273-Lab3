package ebay;

import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbscript {

	//Connection conn = null;
	String dbName = "ebay";
	public Connection getConnection(){
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");
		    /*conn =
		       DriverManager.getConnection("jdbc:mysql://localhost:3306/ebay", "root", "toor");*/
		    
		    //conn =
		    	//       DriverManager.getConnection("jdbc:mysql://localhost/ebay?user=root&password=toor");
		    
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ebay?user=root&password=toor");
		    return conn;
	
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return null;
		}
	}
	
	public ResultSet executeGetResults(Connection con, String query) throws SQLException {

		    Statement stmt = null;
		    ResultSet rs;
		    try {
		        stmt = con.createStatement();
		         rs = stmt.executeQuery(query);
		        /*while (rs.next()) {
		            String coffeeName = rs.getString("COF_NAME");
		            int supplierID = rs.getInt("SUP_ID");
		            float price = rs.getFloat("PRICE");
		            int sales = rs.getInt("SALES");
		            int total = rs.getInt("TOTAL");
		            System.out.println(coffeeName + "\t" + supplierID +
		                               "\t" + price + "\t" + sales +
		                               "\t" + total);
		        }*/
		        return rs;
		    } catch (SQLException e ) {
		    	e.printStackTrace();
		        return null;
		    } finally {
		        if (stmt != null) { stmt.close(); }
		    }
		}
	
	
	
	public ResultSet executeUpdateResults(Connection con, String query) throws SQLException {

	    Statement stmt = null;
	    ResultSet rs;
	    try {
	        stmt = con.createStatement();
	         rs = stmt.executeUpdate(query);
	        /*while (rs.next()) {
	            String coffeeName = rs.getString("COF_NAME");
	            int supplierID = rs.getInt("SUP_ID");
	            float price = rs.getFloat("PRICE");
	            int sales = rs.getInt("SALES");
	            int total = rs.getInt("TOTAL");
	            System.out.println(coffeeName + "\t" + supplierID +
	                               "\t" + price + "\t" + sales +
	                               "\t" + total);
	        }*/
	        return rs;
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	        return null;
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public static void main(String[] args) {
		dbscript db = dbscript();
		db.getConnection();
	}
}
