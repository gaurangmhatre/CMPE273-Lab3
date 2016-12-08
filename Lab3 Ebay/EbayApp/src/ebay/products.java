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
	
	
	
	public int addBidOnProduct(int UserId, int ItemId, int BidAmount) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		 
		String addBidOnProductQuery = "INSERT INTO bidderlist(BidderId,ItemId,BidAmount,BidTime)VALUES(" + UserId + "," + ItemId + "," + BidAmount + ",NOW());";
		ResultSet rs = db.executeUpdateResults(conn, addBidOnProductQuery);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
	}

	public int addProduct(String ItemName, String ItemDescription, int SellerId, int Price, int Qty, int IsBidItem, int Sold ) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();
		String ItemTypeId = 1; 
		
		String insertNewProductQuery = "INSERT INTO item (ItemName,ItemDescription,ItemTypeId,SellerId,Price,Qty,DateAdded,AuctionEndDate,IsBidItem,Sold) VALUES ('"+ItemName+"','"+ItemDescription+"',"+ItemTypeId+","+SellerId+","+Price+","+Qty+",NOW(),date_add(NOW(),INTERVAL 4 DAY),"+IsBidItem+","+Sold+")";
		ResultSet rs = db.executeUpdateResults(conn, insertNewProductQuery);
		
		if (rs.getInt("count")==0)
		{
			return 401;
		}else
		{		
			return 200;
		}
	}
	
	
	
}
