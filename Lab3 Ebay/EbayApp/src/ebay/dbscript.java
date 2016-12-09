package ebay;

import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbscript {

	//Connection conn = null;
	String dbName = "ebay";
	Connection conn;
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
	
	public static void main(String[] args) {
		dbscript db = new dbscript();
		db.getConnection();
	}
}
