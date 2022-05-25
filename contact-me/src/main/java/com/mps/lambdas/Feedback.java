package com.mps.lambdas;

import com.mps.models.DeviceInfo;
import com.mps.models.LocationInfo;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 **/
@Data
public class Feedback {

    private String email;
    private OffsetDateTime createDateTime;

    private String message;
    private String name;

    private DeviceInfo deviceInfo;

    private LocationInfo locationInfo;
}
