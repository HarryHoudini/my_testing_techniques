import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RequestMaker {
    private RequestSpecification request;

    public RequestMaker(final String baseUri, final Credential credential){
        this.request = RestAssured.given().baseUri(baseUri)
            .cookie(RestConstants.COOKIE_CONNECT_SID, getConnectSid(baseUri, credential))
            .contentType("application/json")
            .relaxedHTTPSValidation();
    }

    public Response makeRequest(final String json, final String apiURL){
        return this.request.body(json).post(apiURL);
    }

    private String createAuthJson(Credential credential){
        return String.format(RestConstants.AUTH_JSON_FORMAT, credential.getUsername(), credential.getPassword());
    }

    private String getConnectSid(final String baseUri, final Credential credential){
        RequestSpecification sidRequest = RestAssured.given()
            .baseUri(baseUri)
            .contentType("application/json")
            .body(createAuthJson(credential))
            .relaxedHTTPSValidation();
        return sidRequest
            .post(RestConstants.AUTH_REQUEST)
            .getCookie(RestConstants.COOKIE_CONNECT_SID);
    }
}
