package api.models;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ProductResponse {
	
	@JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;

    // getters...

}
