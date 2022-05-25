package com.mps.lambdas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mps.models.DeviceInfo;
import com.mps.models.DeviceType;
import lombok.AllArgsConstructor;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.util.StringUtils;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.mps.Constants.*;

/**
 * @author manvendrasingh
 * @since 2022-May-24
 * <p>
 * </p>
 **/
@AllArgsConstructor
public class FeedbackMapper {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

    private LambdaLogger logger; // every request get this from lambda context


    public Feedback extractFeedbackModel(APIGatewayProxyRequestEvent input) {
        try {

            Feedback feedback = OBJECT_MAPPER.readValue(input.getBody(), Feedback.class);
            feedback.setDeviceInfo(deviceInfoFromHeader(input.getHeaders()));

            logger.log("getFeedback() returning " + feedback);
            return feedback;

        } catch (Exception e) {
            logger.log("getFeedback() exception in unmarshalling the body so returning null ");
            e.printStackTrace();
            return null;
        }
    }

    private DeviceInfo deviceInfoFromHeader(Map<String, String> headers) {
        return DeviceInfo.builder()
                .deviceType(deviceTypeFromHeader(headers))
                .ipAddress(ipAddressFromHeader(headers))
                .isMobileBrowser("?1".equals(headers.get(HEADER_FOR_MOBILE_BROWSER)))
                .platform(headers.get(HEADER_FOR_PLATFORM))
                .build();
    }

    private DeviceType deviceTypeFromHeader(Map<String, String> headers) {
        if(TRUE.equalsIgnoreCase(headers.get(HEADER_CLOUD_FRONT_IS_DESKTOP_VIEWER))){
            return DeviceType.DESKTOP;
        }else if(TRUE.equalsIgnoreCase(headers.get(HEADER_CLOUD_FRONT_IS_MOBILE_VIEWER))){
            return DeviceType.MOBILE;
        }else if(TRUE.equalsIgnoreCase(headers.get(HEADER_CLOUD_FRONT_IS_SMART_TV_VIEWER))){
            return DeviceType.SMART_TV;
        }else if(TRUE.equalsIgnoreCase(headers.get(HEADER_CLOUD_FRONT_IS_TABLET_VIEWER))){
            return DeviceType.TABLET;
        }else{
            return null;
        }
    }

    private String ipAddressFromHeader(Map<String, String> headers) {

        String xForwardFor = headers.get(HEADER_X_FORWARDED_FOR);
        if (!StringUtils.isNullOrEmpty(xForwardFor)) {
            String[] split = xForwardFor.split(StringUtils.COMMA_SEPARATOR);
            if (split.length != 0) {
                return split[0];
            }
        }
        return null;
    }
}
