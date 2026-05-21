package api.tests;
import api.base.ApiBase;
import api.endpoints.AuthApi;
import api.models.ProductResponse;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reports.ExtentManager;
import utils.ConfigReader;
import utils.LogUtils;

public class ProductApiTest extends ApiBase{
	private static final Logger log =
	        LogUtils.getLogger(ProductApiTest.class);
	    private static String authToken;

	    @BeforeClass 
	    public void setup() {
	        initSpec();
	        // Get auth token once for all product tests
	        authToken = AuthApi.getToken(
	            ConfigReader.get("api.email"),
	            ConfigReader.get("api.password"));
	        log.info("Auth token obtained for product tests");
	    }
	    @Test(description = "Get all products returns 200")
	    public void testGetAllProducts() {
	        Response response = RestAssured
	            .given().spec(requestSpec)
	            .when()
	            .get("/api/products")
	            .then()
	            .extract().response();

	        Assert.assertEquals(response.statusCode(), 200);
	        // Verify response has data array
	        Assert.assertNotNull(response.jsonPath().getList("data"));
	        int count = response.jsonPath().getList("data").size();
	        Assert.assertTrue(count > 0, "Products list not empty");
	        log.info("Products found: " + count);
	    }
	    @Test(description = "Get single product by ID")
	    public void testGetProductById() {
	        Response response = RestAssured
	            .given().spec(requestSpec)
	            .when()
	            .get("/api/products/1")
	            .then()
	            .extract().response();

	        Assert.assertEquals(response.statusCode(), 200);
	        Assert.assertEquals(
	            response.jsonPath().getInt("data.id"), 1);
	        String name = response.jsonPath().getString("data.name");
	        Assert.assertNotNull(name);
	        log.info("Product name: " + name);
	    }
	    @Test(description = "Non-existent product returns 404")
	    public void testProductNotFound() {
	        Response response = RestAssured
	            .given().spec(requestSpec)
	            .when()
	            .get("/api/products/9999")
	            .then()
	            .extract().response();

	        Assert.assertEquals(response.statusCode(), 404);
	    }
	    @DataProvider(name = "productIds")
	    public Object[][] productIds() {
	        return new Object[][]{{1},{2},{3},{4},{5},{6}};
	    }

	    @Test(dataProvider = "productIds",
	          description = "Validate all 6 SauceDemo products via API")
	    public void testEachProduct(int id) {
	        Response response = RestAssured
	            .given().spec(requestSpec)
	            .when().get("/api/products/" + id)
	            .then().extract().response();
	        Assert.assertEquals(response.statusCode(), 200);
	    }
	}




