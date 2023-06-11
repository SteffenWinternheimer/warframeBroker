package application;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;


public class Authentication {

    private String email;
    private String password;
    static String jwtToken;

    public Authentication(String email, String password){
        this.email = email;
        this.password = password;
    }

    public HttpResponse<String> Authenticate(){
        try{
            SignInBody signInBody =  new SignInBody(email, password, "header");
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(signInBody);
            List<String> headers = new ArrayList<>();
            headers.add("content-type");
            headers.add("application/json; utf-8");
            headers.add("accept");
            headers.add("application/json");
            headers.add("authorization");
            headers.add("jwt");
            headers.add("platform");
            headers.add("pc");
            headers.add("language");
            headers.add("en");
            HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.warframe.market/v1/auth/signin"))
                .headers(headers.toArray(String[]::new))
                .POST(BodyPublishers.ofString(jsonRequest))
                .build();   
                
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
            HttpHeaders responseHeader = postResponse.headers();
            String authorizationValue = responseHeader.firstValue("authorization").orElse(null);
            jwtToken = authorizationValue;
            return postResponse;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String getJWTToken(){
        return jwtToken;
    }
}
