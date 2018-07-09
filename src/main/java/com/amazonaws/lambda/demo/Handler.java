package com.amazonaws.lambda.demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);
        context.getLogger().log("Input: " + input);
        
        String rawResponseBody = "<html><head>Dynamic Webpage</head><body><h1>Hello, the current time is " + new Date() + "</body></html>";
        
		Response responseBody = new Response(rawResponseBody);
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Powered-By", "AWS Lambda & Serverless");
		headers.put("Content-Type", "text/html");
		//headers.put("Content-Type", "application/json");
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
	//			.setObjectBody(responseBody)
				.setRawBody(rawResponseBody)
				.setHeaders(headers)
				.build();
	}
	
}
