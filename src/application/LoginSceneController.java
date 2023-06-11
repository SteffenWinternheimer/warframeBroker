package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginSceneController {
	@FXML
	private PasswordField tfPassword;
	@FXML
	private Button btnSignUp;
	@FXML
	private Button btnLogin;
	@FXML
	private TextField tfEmail;
	@FXML
	private Label lbErrorMessage;

	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// Event Listener on Button[#btnSignUp].onAction
	@FXML
	public void btnSignUpClicked(ActionEvent event) {
		try {
			Desktop.getDesktop().browse(new URL("https://warframe.market/auth/register").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void btnLoginClicked(ActionEvent event) throws IOException, URISyntaxException, InterruptedException {
		Authentication authentication = new Authentication(tfEmail.getText(), tfPassword.getText());
		HttpResponse<String> postResponse  = authentication.Authenticate();
		Gson gson = new Gson();
		switch(postResponse.statusCode()) {
			case 200: 
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileScene.fxml"));
				root = loader.load();
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				JsonObject jsonObject = gson.fromJson(postResponse.body(), JsonObject.class);
				JsonObject payload = jsonObject.getAsJsonObject("payload");
				JsonObject user = payload.getAsJsonObject("user");
				UserData.ingameName = user.get("ingame_name").getAsString();
				ProfileSceneController controller = loader.getController();
				controller.setUsernameText(UserData.ingameName);
				Utils utils = new Utils();
				utils.setUserOrders(UserData.ingameName);
				break;
			case 400:
				lbErrorMessage.setText("E-Mail / Password incorrect!");
				break;
		}
	}
}
