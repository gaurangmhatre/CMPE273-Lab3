package ebay;


import javax.jws.WebService;

import java.sql.*;

@WebService
public class login {
	
	public int checkSignUp(String emailid) throws Exception
	{
		
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		/*String query = "SELECT * as count  FROM ebay.user where EmailId ='"+emailid+"';"; 
		ResultSet rs = db.executeGetResults(conn, query);
		*/
		/*PreparedStatement stm = c.prepareStatement("UPDATE user_table SET name=? WHERE id=?");
		stm.setString(1, "the name");
		stm.setInt(2, 345);
		stm.executeUpdate();*/
		
		//String query = "SELECT * as count  FROM ebay.user where EmailId ='"+emailid+"';"; 
		PreparedStatement stm = conn.prepareStatement("SELECT * as count  FROM ebay.user where EmailId =?");
		stm.setString(1, "emailid");
		
		ResultSet rs = stm.executeQuery();
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
	}
	/*
	public int afterSignUp(String firstname,String lastname,String email,String hash,String location,String creditCardNumber,String dateOfBirth) throws Exception
	{
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		 
		String query = "INSERT INTO user (FirstName, LastName, EmailId, Password, Address, CreditCardNumber,DateOfBirth) VALUES ('" + firstname + "','" + lastname + "','" + email + "','" + hash + "','" + location + "','" + creditCardNumber + "','"+dateOfBirth+"')";
		
		int rs = db.executeUpdateResults(conn, query);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}	
		return 200;
	}
	
	public int addLastLogin(String userId) throws Exception
	{
		
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		String query = "UPDATE user	SET LastLoggedIn = NOW() WHERE UserId = "+userId+";"; 
		int rs = db.executeUpdateResults(conn, query);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
		return 200;
	}
	
*/	
}
