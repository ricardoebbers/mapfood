package mapfood;

import com.mongodb.MongoClient;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.Assert.assertTrue;

@DataMongoTest
public class Stepdefs {

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
    }

    @Given("there are available restaurants")
    public void there_are_available_restaurants() {

        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @When("users want to list restaurants")
    public void users_want_to_list_restaurants() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the requested list is returned")
    public void the_requested_list_is_returned() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Given("restaurants have products")
    public void restaurants_have_products() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @When("users want to see products from restaurants")
    public void users_want_to_see_products_from_restaurants() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Given("users have chosen a list of products from a restaurant")
    public void users_have_chosen_a_list_of_products_from_a_restaurant() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @When("users request a order")
    public void users_request_a_order() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should be created")
    public void the_order_should_be_created() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should have all the products ordered")
    public void the_order_should_have_all_the_products_ordered() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should have the user details")
    public void the_order_should_have_the_user_details() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should have the restaurant details")
    public void the_order_should_have_the_restaurant_details() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should have the products detail")
    public void the_order_should_have_the_products_detail() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(true);
    }

    @Then("the order should have the expected deliver time")
    public void the_order_should_have_the_expected_deliver_time() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(false);
    }

}
