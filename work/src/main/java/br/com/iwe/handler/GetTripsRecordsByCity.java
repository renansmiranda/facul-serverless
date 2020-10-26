package br.com.iwe.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.iwe.dao.TripsRepository;
import br.com.iwe.model.HandlerRequest;
import br.com.iwe.model.HandlerResponse;
import br.com.iwe.model.Trip;

public class GetTripsRecordsByCity implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final TripsRepository repository = new TripsRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String country = request.getPathParameters().get("country");
		final String city = request.getQueryStringParameters().get("city");

		context.getLogger()
				.log("Searching for registered studies for " + country + " thas is Consumed equals " + city);
		final List<Trip> studies = this.repository.findBycity(country, city);

		if (studies == null || studies.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(studies).build();
	}
}