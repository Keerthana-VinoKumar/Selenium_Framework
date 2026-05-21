package api.base;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.apache.logging.log4j.Logger;
import utils.*;

public class ApiBase {

	  private static final Logger log =
		        LogUtils.getLogger(ApiBase.class);

		    protected static RequestSpecification requestSpec;
		    protected static ResponseSpecification responseSpec;

		    // Called once — sets up base URL, headers, logging
		    protected static void initSpec() {
		        requestSpec = new RequestSpecBuilder()
		            .setBaseUri(ConfigReader.get("api.base.url"))
		            .setContentType(ContentType.JSON)
		            .addHeader("Accept", "application/json")
		            // logs every request/response to console + log file
		            .addFilter(new RequestLoggingFilter())
		            .addFilter(new ResponseLoggingFilter())
		            .build();

		        responseSpec = new ResponseSpecBuilder()
		            .expectContentType(ContentType.JSON)
		            .build();
		        log.info("API Base URL: " + ConfigReader.get("api.base.url"));
		    }

		    // Attach auth token to all subsequent requests
		    protected static RequestSpecification withToken(String token) {
		        return RestAssured.given()
		            .spec(requestSpec)
		            .header("Authorization", "Bearer " + token);
		    }
		        
}
