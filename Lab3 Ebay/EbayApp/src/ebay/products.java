package ebay;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class products {

	public ResultSet getAllProducts() throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		 
		String getAllProductQuery = "select ItemId, ItemName,ItemDescription,ItemTypeId,SellerId,Price,Qty,DateAdded,IsBidItem, sold from item where IsBidItem=0 and Qty>0";
		ResultSet rs = db.executeGetResults(conn, getAllProductQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	
	public ResultSet getAllProductsForAuction() throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		 
		String getAllProductForAuctionQuery = "select i.ItemId, i.ItemName,i.ItemDescription,i.ItemTypeId,i.SellerId,i.Price,i.Qty,i.DateAdded,i.AuctionEndDate,i.IsBidItem,i.sold, max(b.BidAmount) as MaxBidAmount from item as i left join bidderList as b on i.ItemId = b.ItemId  where i.IsBidItem=1 and i.AuctionEndDate > NOW() group by i.ItemId, i.ItemName,i.ItemDescription,i.ItemTypeId,i.SellerId,i.Price,i.Qty,i.DateAdded,i.AuctionEndDate,i.IsBidItem, i.sold";
		ResultSet rs = db.executeGetResults(conn, getAllProductForAuctionQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	
	public int setAddToCart(String UserId, String ItemId, String Qty) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		 
		String userAddToCartQuery = "INSERT INTO usercart(`UserId`,`ItemId`,`Qty`)VALUES(" + UserId + "," + ItemId + "," + Qty + ");";
		ResultSet rs = db.executeUpdateResults(conn, userAddToCartQuery);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
	}
	
	
}