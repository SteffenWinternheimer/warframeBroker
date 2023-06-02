import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Resource;

import com.google.gson.Gson;


public class Authentication {
    public static void main(String[] args) {
        try{
            String url = "https://api.warframe.market/v1/items/mesa_prime_chassis/orders?include=item";
    
            // Create a client resource
            ClientResource clientResource = new ClientResource(url);
    
            // Make the GET request and retrieve the representation
            Representation representation = clientResource.get(MediaType.APPLICATION_JSON);
    
            // Process the representation
            String response = representation.getText();
            Gson gson = new Gson();
            Data data = gson.fromJson(response, Data.class);

            Data.Payload.Order order = getLowestOrder(data.getPayload().getOrders());

            System.out.println(order.getUser().getIngame_name());
            // Release the client resource
            clientResource.release();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static Data.Payload.Order getLowestOrder(List<Data.Payload.Order> orders){
        return orders
        .stream()
        .filter(o -> o.getUser().getStatus().equals("ingame"))
        .filter(o -> o.getOrder_type().equals("sell"))
        .min(Comparator.comparing(Data.Payload.Order::getPlatinum))
        .get();
    }
}
