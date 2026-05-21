package api.endpoints;
import api.base.ApiBase;
import api.models.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import utils.LogUtils;
public class AuthApi extends ApiBase {
	 private static final Logger log =
		        LogUtils.getLogger(AuthApi.class);

		    // POST /api/login → returns auth token
		    public static Response login(String username, String password) {
		        log.info("API Login: " + username);

		        LoginRequest request = new LoginRequest()
		            .username(username)
		            .password(password);

		        return RestAssured
		            .given().spec(requestSpec)
		            .body(request)
		            .when()
		            .post("/auth/login")
		            .then()
		            .extract().response();

}
		    
		 // Extract token from successful login response
		    public static String getToken(String email, String pass) {
		        Response response = login(email, pass);
		        LoginResponse loginResp =
		            response.as(LoginResponse.class);
		        String token = loginResp.getToken();
		        log.info("Token received: " + token);
		        return token;
		    }
		}
		    
