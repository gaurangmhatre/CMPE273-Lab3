package ebay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class dbscript {

	Connection conn = null;
	String dbName = "ebay";
	public Connection getConnection(){
		try {
		    conn =
		       DriverManager.getConnection("jdbc:mysql://localhost:3306/ebay", "root", "toor");
		    
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
	
}
