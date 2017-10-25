package com.yaegar.qutana.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Location")
public class Location {
    private final String id;
    private final String area;

    public Location(String id, String area) {
        this.id = id;
        this.area = area;
    }

    @DynamoDBHashKey
    public String getId() {
        return id;
    }

    @DynamoDBRangeKey
    public String getArea() {
        return area;
    }
}
