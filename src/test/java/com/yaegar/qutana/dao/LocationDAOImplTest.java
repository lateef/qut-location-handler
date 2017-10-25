package com.yaegar.qutana.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yaegar.qutana.model.Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LocationDAOImplTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private LocationDAO locationDAO;

    @Before
    public void setUp() {
        locationDAO = new LocationDAOImpl(dynamoDBMapper);
    }

    @Test
    public void shouldSaveLocation() {
        //arrange
        String id = UUID.randomUUID().toString();
        String area = "Glasgow";
        Location location = new Location(id, area);
        doNothing().when(dynamoDBMapper).save(location);

        //act
        locationDAO.save(location);

        //assert
        verify(dynamoDBMapper, times(1)).save(location);
    }
}
