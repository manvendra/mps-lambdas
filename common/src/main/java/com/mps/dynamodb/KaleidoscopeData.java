package com.mps.dynamodb;

import lombok.*;

import java.time.OffsetDateTime;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 * <p>
 * I'm using one table for app pattern
 * so this same table will be used by all lambda's
 * </p>
 **/

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamoDBTable(tableName = "kaleidoscope-data")
public class KaleidoscopeData {

    @DynamoDBHashKey(attributeName = "pk")
    private String partitionKey;

    @DynamoDBRangeKey(attributeName = "sk")
    private String sortKey; // ISO 8601 time string

    @DynamoDBAttribute(attributeName = "content")
    private String content; //non queried JSON content

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "ipAddress")
    private String ipAddress;

    @DynamoDBAttribute(attributeName = "deviceType")
    private String deviceType;

    @DynamoDBAttribute(attributeName = "city")
    private String city;

    @DynamoDBAttribute(attributeName = "regionName")
    private String regionName;

    @DynamoDBAttribute(attributeName = "country")
    private String country;

    @DynamoDBAttribute(attributeName = "continent")
    private String continent;

}
