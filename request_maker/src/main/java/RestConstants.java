public class RestConstants {
    private RestConstants(){
        throw new UnsupportedOperationException("Illegal access to private constructor!");
    }

    // REST API constants
    public final static String AUTH_REQUEST = "/api/signin";
    public final static String CREDENTIAL_RECORD_REQUEST = "/api/credentialrecord";

    public final static String AUTH_JSON_FORMAT = "{\"username\":\"%s\",\"password\":\"%s\"}";
    public final static String COOKIE_CONNECT_SID = "connect.sid";

    // Status codes
    public final static int OK_STATUS_CODE = 200;
}
