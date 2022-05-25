package com.mps.models;

import lombok.*;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 **/
@Data
@Builder
public class LocationInfo {

    private String city;
    private String regionName;
    private String zip;
    private String country;
    private String continent;

    private String region;
    private String countryCode;
    private String continentCode;

    private double lat;
    private double lon;

    private String timezone;
    private int offset;
}
