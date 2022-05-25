package com.mps.lambdas;

import com.mps.api_gateway.ApiGatewayResponse;
import com.mps.dynamodb.*;
import org.apache.http.HttpStatus;

import java.util.*;

import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import static com.mps.MpsUtils.*;
import static com.mps.api_gateway.APIGatewayProxyRequestEventLogger.logInput;
import static com.mps.Constants.*;

public class ContactMeHandler implements RequestHandler<APIGatewayProxyRequestEvent, ApiGatewayResponse> {


    private static final String RESPONSE_BODY_CONTACT_ME = "Thanks for contacting !";

    private KaleidoscopeDataRepo kaleidoscopeDataRepo;
    private LambdaLogger logger;


    @Override
    public ApiGatewayResponse handleRequest(APIGatewayProxyRequestEvent apiGatewayRequestEvent, Context context) {

        logger = context.getLogger();
        logInput(apiGatewayRequestEvent,logger);

        Feedback feedback = new FeedbackMapper(logger)
                .extractFeedbackModel(apiGatewayRequestEvent);

        KaleidoscopeData kaleidoscopeData = getTableData(feedback);
        logger.log("going to save this kaleidoscopeData  " + kaleidoscopeData);

        if (kaleidoscopeData != null) {
            kaleidoscopeDataRepo = new KaleidoscopeDataRepoImpl();
            kaleidoscopeDataRepo.save(kaleidoscopeData);
        }
        return createResponse();
    }

    private ApiGatewayResponse createResponse() {
        return ApiGatewayResponse
                .builder()
                .setStatusCode(HttpStatus.SC_OK)
                .setObjectBody(new Response(RESPONSE_BODY_CONTACT_ME, null))
                .setHeaders(Collections.singletonMap(HEADER_X_POWERED_BY, SERVICE_DESCRIPTION))
                .build();
    }


    private KaleidoscopeData getTableData(Feedback feedback) {
        if (feedback == null ||
                feedback.getEmail() == null ||
                feedback.getMessage() == null) {
            return null;
        }
        return KaleidoscopeData
                .builder()
                .partitionKey(feedback.getEmail())
                .sortKey(getCurrentUtcTime())
                .content(feedback.getMessage())
                .name(feedback.getName())
                .ipAddress(feedback.getDeviceInfo().getIpAddress())
                .deviceType(feedback.getDeviceInfo().getDeviceType().name())
                .build();
    }

}
