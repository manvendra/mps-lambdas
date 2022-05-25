package com.mps.api_gateway;

import java.util.List;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import static com.mps.Constants.*;
import static java.util.stream.Collectors.joining;

/**
 * @author manvendrasingh
 * @since 2022-May-22
 * <p>
 * A class used to log the
 * </p>
 **/
public class APIGatewayProxyRequestEventLogger {


    public static void logInput(APIGatewayProxyRequestEvent input, LambdaLogger lambdaLogger) {

        lambdaLogger.log("Received the request " + input);
        lambdaLogger.log("Request Body " + input.getBody());

        logHeaders(input, lambdaLogger);
        logMultiValueHeaders(input, lambdaLogger);
    }

    private static void logHeaders(APIGatewayProxyRequestEvent input, LambdaLogger lambdaLogger) {
        lambdaLogger.log("\n---------------------------");
        lambdaLogger.log("  Headers are as follows");
        lambdaLogger.log("---------------------------");
        input.getHeaders()
                .entrySet()
                .forEach(e -> logKeyValue(lambdaLogger, e.getKey(),e.getValue()));
    }

    private static void logMultiValueHeaders(APIGatewayProxyRequestEvent input, LambdaLogger lambdaLogger) {
        lambdaLogger.log("\n---------------------------");
        lambdaLogger.log("  multiValueHeaders are as follows");
        lambdaLogger.log("---------------------------");
        input.getMultiValueHeaders()
                .entrySet()
                .forEach(e -> logKeyValue(lambdaLogger, e.getKey(),getAsString(e.getValue())));
    }


    private static void logKeyValue(LambdaLogger lambdaLogger,String key, String value) {
        lambdaLogger.log("\t\t " + key + " : " + value + " ");
    }

    private static String getAsString(List<String> value) {
        if (value != null && value.size() != 0)
            return value.stream().collect(joining(COMMA));
        else return EMPTY_STRING;
    }

}
