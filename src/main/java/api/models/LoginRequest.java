package api.models;
import com.fasterxml.jackson.annotation.JsonProperty;
public class LoginRequest {
	 @JsonProperty("username")
	    private String username;

	    @JsonProperty("password")
	    private String password;

	    // Builder pattern — fluent object creation
	    public LoginRequest username(String username) {
	        this.username = username;
	        return this;
	    }
	    public LoginRequest password(String pass) {
	        this.password = pass;
	        return this;
	    }

}
