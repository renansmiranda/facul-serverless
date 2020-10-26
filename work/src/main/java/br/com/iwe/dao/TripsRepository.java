package br.com.iwe.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.iwe.model.Trip;

public class TripsRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public Trip save(final Trip study) {
		mapper.save(study);
		return study;
	}

	public List<Trip> findBycity(final String topic, final String isConsumed) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(topic));
		eav.put(":val2", new AttributeValue().withS(isConsumed));

		final Map<String, String> expression = new HashMap<>();

		// consumed is a reserver word in DynamoDB
		expression.put("#consumed", "consumed");

		final DynamoDBQueryExpression<Trip> queryExpression = new DynamoDBQueryExpression<Trip>()
				.withIndexName("consumedIndex").withConsistentRead(false)
				.withKeyConditionExpression("topic = :val1 and #consumed=:val2").withExpressionAttributeValues(eav)
				.withExpressionAttributeNames(expression);

		final List<Trip> studies = mapper.query(Trip.class, queryExpression);

		return studies;
	}
}