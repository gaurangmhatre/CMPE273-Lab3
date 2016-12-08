var mysql = require('./mysql');
var winston = require('winston');
var ObjectId = require('mongodb').ObjectId;

var mongo = require('./mongo.js');
var mongoURL = "mongodb://localhost:27017/ebay";

var mq_client = require('../rpc/client');

var logger = new (winston.Logger)({
	transports: [
		new (winston.transports.Console)(),
		new (winston.transports.File)({ filename: 'ebayLog.log' })
	]
});

var EventLogger = new (winston.Logger)({
	transports: [
		new (winston.transports.Console)(),
		new (winston.transports.File)({ filename: 'ebayEventLog.log' })
	]
});

exports.getProductsPage = function(req,res){
		res.render('products',{validationMessage:'Empty Messgage'});
};

//changed
exports.getAllProducts = function(req,res){
	console.log("In getAllProducts.");

	console.log("userId: "+req.session.userid);

	var email = req.session.userid;
	//var msg_payload = {"email":email};
	if(email != undefined ) {

		if(email != undefined) {

			var option = {
				ignoredNamespaces : true
			};
			var url = baseURL+"/login?wsdl";
			var args = {};

			soap.createClient(url,option, function(err, client) {
				client.getAllProducts(args, function(err, result) {
					console.log("---Result: "+ result);
					// if(result.validateReturn === true){
					if(result==200){
						res.send({statusCode:200,"results":result.json_responses});
					}
					else{
						res.send({statusCode:401});
					}
				});
			});
			
		}
		else {
			//var json_responses = {"statusCode": 401};
			res.send({"statusCode": 401});
		}
	}
	else {
		var json_responses = {"statusCode": 401};
		res.send(json_responses);
	}
};

//changed
exports.getAllProductsForAuction = function(req,res){
	console.log("In getAllProductsForAuction.");

	console.log("userId: "+req.session.userid);

	var email = req.session.userid;
	//var msg_payload = {"email":email};
	if(email != undefined ) {
		var option = {
			ignoredNamespaces : true
		};
		var url = baseURL+"/login?wsdl";
		var args = {};
		soap.createClient(url,option, function(err, client) {
			client.getAllProductsForAuction(args, function(err, result) {
				console.log("---Result: "+ result);
				// if(result.validateReturn === true){
				if(result==200){
					res.send({statusCode:200,"results":result.json_responses});
				}
				else{
					res.send({statusCode:401});
				}
			});
		});
	}
	else {
		var json_responses = {"statusCode": 401};
		res.send(json_responses);
	}
};

//changed
exports.userAddToCart = function(req,res){
	console.log("In userAddToCart method.");

	var ItemId = req.param("ItemId");
	var Qty = 	 req.param("Qty");
	var UserId =  req.session.userid;
	var args = {"ItemId": ItemId,"Qty": Qty,"UserId":UserId};
	if(UserId != undefined) {

		var option = {
			ignoredNamespaces : true
		};
		var url = baseURL+"/login?wsdl";

		soap.createClient(url,option, function(err, client) {
			client.setAddToCart(args, function(err, result) {
				console.log("---Result: "+ result);
				// if(result.validateReturn === true){
				if(result==200){
					res.send({"statusCode":200});
				}
				else{
					res.send({"statusCode":401});
				}
			});
		});
	}
	else {
		//var json_responses = {"statusCode": 401};
		res.send({"statusCode": 401});
	}

};


exports.addBidOnProduct = function(req,res){
	/*get the product  done
	* get the userId done
	* update the max bidder id in product only
	* update the highest bid amount 
	* add bid to user profile
	* */
	
	console.log("In addBidOnProduct method.");
	
	var Item = req.param("Item");
	var BidAmount = req.param("BidAmount");
	var UserId =  req.session.userid;

	var msg_payload = {"Item":Item, "BidAmount":BidAmount ,"UserId": UserId};

	if(UserId != undefined ) {

		/*mq_client.make_request('addBidOnProduct_queue',msg_payload, function(err,results){
			console.log(results.json_responses.statusCode);
			if(err){
				throw err;
			}
			else
			{
				if(results.json_responses.statusCode == 200){
					console.log("Got user cart data  products.");
					res.send(results.json_responses);
				}
				else {
					console.log("No products in cart.");
					res.send({"statusCode" : 401});
				}
			}
		})*/

		var option = {
			ignoredNamespaces : true
		};
		var url = baseURL+"/login?wsdl";

		soap.createClient(url,option, function(err, client) {
			client.setAddToCart(args, function(err, result) {
				console.log("---Result: "+ result);
				// if(result.validateReturn === true){
				if(result==200){
					res.send({"statusCode":200});
				}
				else{
					res.send({"statusCode":401});
				}
			});
		});
	}
	else {
		var json_responses = {"statusCode": 401};
		res.send(json_responses);
	}
};

/*exports.getItemType = function(req,res){
	console.log("Inside getItemType Method.");
	
	var getItemTypeQuery = "SELECT ItemTypeId,ItemType FROM itemtype;";
	console.log("Query:: " + getItemTypeQuery);
	logger.log('info',"Query:: " + getItemTypeQuery);
	mysql.fetchData(function(err,results) {
		if(err) {
			throw err;
			logger.log('error',err);
		}
		else {
			if(results.length > 0) {
					console.log("Successful got All the ItemTypes.");
					logger.log('info',"Successful got All the ItemTypes.");

					json_responses = results;
			}
			else{
					res.send(json_responses);
					console.log("Invalid string.");
					logger.log('error', "zero itemsTypes retrived.");
					json_responses = {"statusCode" : 401};
			}
			res.send(json_responses);
		}	
		
	}, getItemTypeQuery);
	
};*/

exports.addProduct = function(req,res){
	console.log("Inside addProduct.");
	var json_responses="";

	var userId = req.session.userid;
	
	var ItemName = req.param("ItemName");
	var ItemDescription = req.param("ItemDescription");
	var ItemTypeId = req.param("ItemTypeId");
	var Price = req.param("Price");
	var Qty = req.param("Qty");
	var IsBidItem = req.param("IsBidItem");
	var Sold = 0;

	var CurrentDate = new Date();
	var AuctionEndDate = new Date();

	var msg_payload= {"ItemName":ItemName,"ItemDescription":ItemDescription,"Price":Price,"Qty":Qty,"DateAdded":CurrentDate,"SellerId":userId, "IsBidItem":IsBidItem, "Sold":Sold};

	AuctionEndDate.setDate(AuctionEndDate.getDate()+4);

	console.log(AuctionEndDate);

	if(userId != undefined ) {

			var option = {
			ignoredNamespaces : true
		};
			soap.createClient(url,option, function(err, client) {
				client.addProduct(msg_payload, function(err, result) {
					console.log("---Result: "+ result);
					// if(result.validateReturn === true){
					if(result==200){
						res.send({"statusCode":200});
					}
					else{
						res.send({"statusCode":401});
					}
				});
			});
	}
};

exports.labProducts = function(req,res){
	console.log("Inside logger.");
	EventLogger.log('info', req.session.userid +"userOver Product" +new Date());
};
