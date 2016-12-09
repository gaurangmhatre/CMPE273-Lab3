package ebay;

import javax.jws.WebService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

@WebService
public class userProfile {
	
	public ResultSet getUserAccountDetails(int userId) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getUserAccountDetailsQuery = "select UserId,FirstName,LastName,EmailId,Password,Address,CreditCardNumber,DateOfBirth,LastLoggedIn from user where UserId= "+ userId+";";
		
		ResultSet rs = db.executeGetResults(conn, getUserAccountDetailsQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	
	public ResultSet getAllProductsInCart(int userId) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getAllProductsInCartQuery =  "select uc.UserCartId, uc.ItemId, i.ItemName, i.ItemDescription, i.ItemTypeId ,i.Price from ebay.usercart uc join ebay.item i on uc.ItemId =  i.itemId where uc.UserId = '" + userId +"'";
		
		ResultSet rs = db.executeGetResults(conn, getAllProductsInCartQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	
	public int removeItemFromCart(int userId, int itemId) throws Exception
	{	
		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		//String getAllProductsInCartQuery =  "select uc.UserCartId, uc.ItemId, i.ItemName, i.ItemDescription, i.ItemTypeId ,i.Price from ebay.usercart uc join ebay.item i on uc.ItemId =  i.itemId where uc.UserId = '" + userId +"'";
		String removeItemFromCartQuery = "delete from usercart where UserId = "+userId+" and ItemId = "+itemId;
		int rs = db.executeUpdateResults(conn, removeItemFromCartQuery);
			
		return rs;
	}	
	
	public int buyItemsInCart(int userId, int creditCardNumber) throws Exception
	{	
		dbscript db = new dbscript();
		
		Connection conn = db.getConnection();		 
		
		/*This logic in java
		 * 
		 * var getAllCartItemsQuery = "Select UserCartId,UserId,ItemId,Qty from usercart where UserId ="+userId;
		console.log("Query:: " + getAllCartItemsQuery);
		logger.log('info','Query:: ' + getAllCartItemsQuery);
		mysql.fetchData(function(err,results) {
			if(err) {
				throw err;
				logger.log('error',err);

			}
			else {
				if(results.length > 0) {
					console.log("Got all the items for userId: "+ userId);
					logger.log('info','Query:: ' + getAllCartItemsQuery);
					for(result in results) {
							AddItemToSoldTable(results[result].ItemId,userId,creditCardNumber);
							updateItemQty(results[result].ItemId);
							removingItemFromCart(userId,results[result].ItemId);
						}						
						json_responses = results;
				}
				else{
						res.send(json_responses);
						console.log("No items in cart.");
						json_responses = {"statusCode" : 401};
				}
				res.send(json_responses);
			}	
			
		}, getAllCartItemsQuery);

		 * */
		
		//int rs = db.executeUpdateResults(conn, removeItemFromCartQuery);
			
		return 1;
	}	
	
	
	
	//History
	public ResultSet getAllUserDirectBuyingActivities(int userId) throws Exception
	{

		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getAllUserDirectBuyingActivitiesQuery =  "select u.Solddate, u.Qty, i.ItemName, i.ItemDescription,i.Price,seller.FirstName from sold as u left join item as i on u.ItemId=i.ItemId left join user as seller on i.SellerId=seller.UserId where u.BuyerId = "+userId;
		
		ResultSet rs = db.executeGetResults(conn, getAllUserDirectBuyingActivitiesQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	public ResultSet getAllAuctionProductHistory(int userId) throws Exception
	{

		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getAllAuctionProductHistoryQuery =  "select a.Paymentdate, i.ItemName, i.ItemDescription,i.Price, u.FirstName as SellerName from auctionWinners as a left join item as i on a.ItemId = i.ItemId left join user as u on i.SellerId = u.UserId where a.WinnerId = "+userId+";";
		
		ResultSet rs = db.executeGetResults(conn, getAllAuctionProductHistoryQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	public ResultSet getAllSoldProducts(int userId) throws Exception
	{

		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getAllSoldProductsQuery =   "select i.ItemName, i.ItemDescription,s.Qty,s.SoldDate,u.FirstName as Buyer,i.Price from item as i right join sold as s on i.ItemId=s.ItemId left join user u on s.BuyerId=u.UserId where i.SellerId = "+userId+";";
		
		ResultSet rs = db.executeGetResults(conn, getAllSoldProductsQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
	public ResultSet getAllUserBiddingActivity(int userId) throws Exception
	{

		dbscript db = new dbscript();
		Connection conn = db.getConnection();		 
		
		String getAllUserBiddingActivityQuery =  "select  i.ItemName, i.ItemDescription, i.Price, b.BidAmount,b.BidTime  from bidderList as b left join item as i  on b.ItemId=i.ItemId where BidderId = "+userId+" order by BidTime desc";
				
		ResultSet rs = db.executeGetResults(conn, getAllUserBiddingActivityQuery);
		
		if (rs.getInt("count")==0)
		{
			return rs;
		}else
		{		
			return rs;
		}
	}
		
	
	
}
