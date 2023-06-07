import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    private String jwtToken;

    public Authentication(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void Authenticate(){
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
            if (authorizationValue != null) {
                jwtToken = authorizationValue;
                System.out.println("Authorization successful!");
            } else {
                System.out.println("Authorization failed!");
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public String getJWTToken(){
        return jwtToken;
    }

    public void CreateOrder(){
        try{
            Order order = new Order("5c182b739603780081b09a55", Order.OrderType.sell,10,1,true);
            Gson gson = new Gson();
            String jsonRequest = gson.toJson(order);
            List<String> headers = new ArrayList<>();
            headers.add("Content-Type");
            headers.add("application/json");
            headers.add("Authorization");
            headers.add(jwtToken);
            headers.add("language");
            headers.add("en");
            headers.add("platform");
            headers.add("pc");
            headers.add("auth_type");
            headers.add("header");
            HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.warframe.market/v1/profile/orders"))
                .headers(headers.toArray(String[]::new))
                .POST(BodyPublishers.ofString(jsonRequest))
                .build(); 
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
            if(postResponse.statusCode() == 200){
                System.out.println("Order created!");
            }
            else{
                System.out.println("Error! Order not created! Status Code: " + postResponse.statusCode());
            }
        }
        catch(URISyntaxException ex){
            System.out.println(ex.getMessage());
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
