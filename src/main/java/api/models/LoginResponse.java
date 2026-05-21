package api.models;
import com.fasterxml.jackson.annotation.JsonProperty;
public class LoginResponse {
	@JsonProperty("token")
    private String token;

    @JsonProperty("error")
    private String error;

    public String getToken() { return token; }
    public String getError() { return error; }
}


