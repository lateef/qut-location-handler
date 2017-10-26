import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.yaegar.qutana.dao.LocationDAO;
import com.yaegar.qutana.dao.LocationDAOImpl;
import com.yaegar.qutana.model.Location;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class LocationStep {
    private AmazonDynamoDB amazonDynamoDB;
    private DynamoDBMapper dynamoDBMapper;
    private String id = UUID.randomUUID().toString();
    private Location location;
    private String area = "Glasgow";

    @Before
    public void setUp() {
        System.getProperties().setProperty("sqlite4java.library.path", "build/libs");
        amazonDynamoDB = DynamoDBEmbedded.create().amazonDynamoDB();
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest req = dynamoDBMapper.generateCreateTableRequest(Location.class);
        req.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(req);
    }

    @Given("^i create a location with name of area Glasgow$")
    public void iCreateALocationWithNameOfAreaGlasgow() throws Throwable {
        location = new Location();
        location.setId(id);
        location.setArea(area);
    }

    @When("^i save the location$")
    public void iSaveTheLocation() throws Throwable {
        LocationDAO locationDAO = new LocationDAOImpl(dynamoDBMapper);
        locationDAO.save(location);
    }

    @Then("^i should have location Glasgow in the database$")
    public void iShouldHaveLocationGlasgowInTheDatabase() throws Throwable {
        Location actualLocation = dynamoDBMapper.load(Location.class, id, area);
        assertEquals(id, actualLocation.getId());
    }
}
