package br.com.iwe.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "trips")
public class Trips {

	@DynamoDBHashKey(attributeName = "country")
	private String country;
	@DynamoDBRangeKey(attributeName = "city")
	private String city;

	@DynamoDBIndexRangeKey(attributeName = "date")
	private String date;

	@DynamoDBAttribute(attributeName = "reason")
	private String reason;
	
//	@DynamoDBAttribute(attributeName = "description")
//	private String description;

//	@DynamoDBIndexRangeKey(attributeName = "consumed", localSecondaryIndexName = "consumedIndex")
//	private String consumed;

	public Trips(String country, String date, String city, String reason) {
		super();
		this.country = country;
		this.date = date;
		this.city = city;
		this.reason = reason;
		//this.description = description;
//		this.consumed = consumed;
	}

	public Trips() {
		super();
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

//	public String getDescription() {
//		return description;
//	}

//	public void setDescription(String description) {
//		this.description = description;
//	}

//	public String getConsumed() {
//		return consumed;
//	}

//	public void setConsumed(String consumed) {
//		this.consumed = consumed;
//	}

}