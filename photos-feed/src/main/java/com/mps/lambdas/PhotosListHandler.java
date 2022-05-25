package com.mps.lambdas;

import com.mps.api_gateway.ApiGatewayResponse;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

/**
 * @author manvendrasingh
 * @since 2022-May-22
 * <p>
 * </p>
 **/
public class PhotosListHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponse> {
    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        return null;
    }
}
