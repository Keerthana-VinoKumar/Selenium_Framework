package api.tests;
import api.base.ApiBase;
import api.endpoints.AuthApi;
import io.restassured.response.Response;
import reports.ExtentManager;
import retry.RetryAnalyzer;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.*;
public class AuthApiTest extends ApiBase {
	private static final Logger log =
	        LogUtils.getLogger(AuthApiTest.class);

	    @BeforeClass
	    public void setup() {
	        initSpec(); // set base URL, headers
	        ExtentManager.getInstance();
	    }

	    @Test(description = "Valid credentials return token",retryAnalyzer = RetryAnalyzer.class)
	    public void testValidLogin() {
	        ExtentManager.createTest("API - Valid Login");
	        log.info("Testing valid API login");

	        Response response = AuthApi.login(
	            ConfigReader.get("api.username"),
	            ConfigReader.get("api.password"));
	     // Status code assertion
	        Assert.assertEquals(response.statusCode(), 201,
	            "Expected 200 OK");

	        // Token exists in response
	        String token = response.jsonPath().getString("token");
	        Assert.assertNotNull(token, "Token should not be null");
	        Assert.assertFalse(token.isEmpty(), "Token should not be empty");

	        log.info("Token received: " + token);
	        ExtentManager.getTest()
	            .pass("Login API returned token: " + token);
	    }
	    @Test(description = "Invalid credentials return 400")
	    public void testInvalidLogin() {
	        ExtentManager.createTest("API - Invalid Login");

	        Response response = AuthApi.login(
	            "wrong@email.com", "wrongpass");

	        Assert.assertEquals(response.statusCode(), 401);
	        String error = response.jsonPath().getString("error");
	        Assert.assertEquals(error, "user not found");
	        ExtentManager.getTest().pass("Invalid login correctly rejected");
}
	    @Test(description = "Missing password returns 400")
	    public void testMissingPassword() {
	        ExtentManager.createTest("API - Missing Password");

	        Response response = AuthApi.login(
	            "eve.holt@reqres.in", "");

	        Assert.assertEquals(response.statusCode(), 400);
	        ExtentManager.getTest().pass("Missing password rejected");
	    }
	    @Test(description = "Verify response time under 2 seconds")
	    public void testResponseTime() {
	        ExtentManager.createTest("API - Response Time");

	        Response response = AuthApi.login(
	            ConfigReader.get("api.email"),
	            ConfigReader.get("api.password"));

	        long responseTime = response.getTime();
	        Assert.assertTrue(responseTime < 2000,
	            "Response should be under 2 seconds, was: " + responseTime);
	        ExtentManager.getTest()
	            .pass("Response time: " + responseTime + "ms");
	    }

	    @AfterClass
	    public void tearDown() {
	        ExtentManager.flush();
	    }
	}   
