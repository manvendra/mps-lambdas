package com.mps.models;

import lombok.Builder;
import lombok.Data;

/**
 * @author manvendrasingh
 * @since 2022-May-24
 * <p>
 * </p>
 **/
@Data
@Builder
public class DeviceInfo {

    private String ipAddress;
    private String platform;
    private DeviceType deviceType;
    private boolean isMobileBrowser;

}
