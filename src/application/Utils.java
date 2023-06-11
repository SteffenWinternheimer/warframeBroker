package application;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import application.UserOrders.Order;
import application.UserOrders.UserOrdersResponse;
import javafx.scene.image.Image;

public class Utils {
	public static UserOrdersResponse userOrdersResponse;

    static Data.Payload.Order getLowestOrder(List<Data.Payload.Order> orders){
        return orders
        .stream()
        .filter(o -> o.getUser().getStatus().equals("ingame"))
        .filter(o -> o.getOrder_type().equals("sell"))
        .min(Comparator.comparing(Data.Payload.Order::getPlatinum))
        .get();
    }
    
    public Root getLowestPriceOfItem(String itemURL) {
    	
//        try {
//            String url = "https://api.warframe.market/v1/items/" + itemURL + "/orders?include=item";
//
//            HttpRequest getRequest = HttpRequest.newBuilder()
//                    .uri(new URI(url))
//                    .GET()
//                    .build();
//            HttpClient httpClient = HttpClient.newHttpClient();
//            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
//
//            String jsonResponse = getResponse.body();
//            System.out.println(jsonResponse);
//
//            Gson gson = new Gson();
//            OrderPayload orderPayload = gson.fromJson(jsonResponse, OrderPayload.class);
//            return orderPayload;
//        } catch (URISyntaxException | IOException | InterruptedException ex) {
//            System.out.println(ex.getMessage());
//        }
        return null;
    }
    
    
	public void setUserOrders(String user_name) throws URISyntaxException, IOException, InterruptedException {	
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.warframe.market/v1/profile/"+user_name+"/orders?include=profile"))
                .GET()
                .build();  
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
        Gson gson = new Gson();
        userOrdersResponse = gson.fromJson(postResponse.body(), UserOrdersResponse.class); 
	}
	
	public static Image getItemImage(String itemURL) {
		Image image = new Image("https://warframe.market/static/assets/"+itemURL);
		return image;
	}
}
