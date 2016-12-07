package ebay;


import javax.jws.WebService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@WebService
public class login {
	public int checkSignUp(String emailid) throws Exception
	{
		
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		String query = "SELECT count(*) as count  FROM ebay.user where EmailId = "+emailid+";"; 
		ResultSet rs = db.executeGetResults(conn, query);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
	}
}
