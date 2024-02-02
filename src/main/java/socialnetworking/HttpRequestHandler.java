package socialnetworking;

public interface HttpRequestHandler {
    String getUrlPartPath();
    Response get(String urlPathPart, String requestBody);

    Response post(String urlPathPart, String requestBody);
}
