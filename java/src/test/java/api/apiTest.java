package api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class apiTest {

    private String season = "current";
    private String race = "last";
    private String driverID = "max_verstappen";

    @Test
    public void getLastResultsTest() {
        given()
                .when().get("http://ergast.com/api/f1/current/last/results")
                .then().statusCode(200);
    }

    @Test
    public void getMaxVerstappenResultsTest() {
        given().pathParam("season", season).
                pathParam("driverID", driverID).
                when().get("http://ergast.com/api/f1/{season}/drivers/{driverID}/results.json").
                then().assertThat().body("MRData.RaceTable.driverId", containsString(driverID));
    }

    // Stopt een variabele in de URL, die kun je ook variabel maken in je testklasse.
    @Test
    public void pathParam() {
        given().pathParam("season", season).
                pathParam("race", race).
                when().get("http://ergast.com/api/f1/{season}/{race}/results").
                then().statusCode(200);
    }

    // PathParam vervangen door variabelen in get()-methode aan te roepen
    @Test
    public void morePathParamTest() {
        given().
                when().get("http://ergast.com/api/f1/{season}/{race}/results", season, race).
                then().statusCode(200);
    }
}
