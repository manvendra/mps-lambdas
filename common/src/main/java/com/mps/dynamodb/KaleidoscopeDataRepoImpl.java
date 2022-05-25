package com.mps.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

/**
 * @author manvendrasingh
 * @since 2022-May-23
 * <p>
 * </p>
 **/
public class KaleidoscopeDataRepoImpl implements KaleidoscopeDataRepo{

    private final DynamoDBMapper mapper;

    public KaleidoscopeDataRepoImpl() {
         mapper = new DynamoDBMapper(getClient(),getMapperConfig());
    }

    public void save(KaleidoscopeData kaleidoscopeData){
        try {
            mapper.save(kaleidoscopeData);
        }catch (Throwable th){
            System.out.println("Exception in save th = " + th);
        }

    }

    public KaleidoscopeData load(KaleidoscopeData partialObject){
       return  mapper.load(partialObject);
    }



    /**
     *  I'm not specifying any credential/region   because
     *  the default implementation uses the default credentials -
     *         - on local from ~/.aws
     *         - on cloud from profile
     * @return
     */
    private AmazonDynamoDB getClient() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

    private DynamoDBMapperConfig getMapperConfig() {
        return DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withTableNameOverride(null)
                .build();
    }

}
