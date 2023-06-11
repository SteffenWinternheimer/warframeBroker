package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class ProfileSceneController implements Initializable{

    @FXML
    private Button btnCreateOrder;
    @FXML
    private Label lbUsername;
    @FXML
    private ListView<Pane> listView;
    private ObservableList<Pane> paneList;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeListView();
    }

    private void initializeListView() {
        paneList = FXCollections.observableArrayList();
        listView.setItems(paneList);
    }
    
    public void setUsernameText(String text) {
        lbUsername.setText(UserData.ingameName);
    }

    @FXML
    void btnCreateOrderClicked(ActionEvent event) {
        List<UserOrders.Order> sellOrders = Utils.userOrdersResponse.getPayload().getSellOrders();
        for (UserOrders.Order order : sellOrders) {
        	paneList.add(createPane(order));
		}
    }

    public Pane createPane(UserOrders.Order order) {
        Pane pane = new Pane();
        pane.setPrefHeight(100); // Festlegen der festen Höhe für jede Pane
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 10px;");

        // Label hinzufügen
        Label itemName = new Label(order.getItem().getEn().getItemName());
        itemName.setLayoutX(10);
        itemName.setLayoutY(80);
        pane.getChildren().add(itemName);

        // The image is downloaded from the supplied URL through http protocol
        Image itemImage = Utils.getItemImage(order.getItem().getIcon());
        ImageView imageView = new ImageView(itemImage);
        imageView.setLayoutX(10);
        imageView.setLayoutY(15);
        double scaleX = 0.15; // Skalierungsfaktor für die Breite
        double scaleY = 0.15; // Skalierungsfaktor für die Höhe
        imageView.getTransforms().add(new Scale(scaleX, scaleY));
        pane.getChildren().add(imageView);
        
        Label curentPriceLabel = new Label("Your Price");
        curentPriceLabel.setLayoutX(160);
        curentPriceLabel.setLayoutY(25);
        pane.getChildren().add(curentPriceLabel);
        
        Label itemPrice = new Label(String.valueOf(order.getPlatinum()));
        itemPrice.setLayoutX(180);
        itemPrice.setLayoutY(45);
        pane.getChildren().add(itemPrice);
        
        Utils utils = new Utils();
//        utils.getLowestPriceOfItem(order.getItem().getUrlName());
//        Label otherItemPrice = new Label(utils.GetLowestPriceOfItem(order.getItem().getUrlName()));

        return pane;
    }

}
