package com.yaegar.qutana.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yaegar.qutana.model.Location;

public class LocationDAOImpl implements LocationDAO {
 private final DynamoDBMapper dynamoDBMapper;

    public LocationDAOImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void save(Location location) {
        dynamoDBMapper.save(location);
    }
}