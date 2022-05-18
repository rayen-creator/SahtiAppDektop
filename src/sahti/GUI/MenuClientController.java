/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sahti.services.AccountModif;
import sahti.services.Login;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class MenuClientController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane idanchorpane;
    @FXML
    private Button logout;
    @FXML
    private Label idusername;
    @FXML
    private Button EditProfile;
    @FXML
    private ImageView idpicprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AccountModif a = new AccountModif();
        Login l = new Login();
        if (l.loginClient(Login.emailUser, Login.pwduser) == true) {
            String username = a.searchclientbyemail(Login.emailUser);
            idusername.setText(username);
            idusername.setFont(Font.font("Trebuchet MS", 18));

            idusername.setMaxWidth(Double.MAX_VALUE);
            idusername.setAlignment(Pos.CENTER_LEFT);

            ImageView myImageView = new ImageView();
            Image image = new Image("file:" + "src\\sahti\\ProfileIMG\\Client\\" + a.searchImgClient(Login.emailUser));
            idpicprofil.setImage(image);

        }
    }

    @FXML
    private void idlogout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login/Login.fxml"));

        Parent root;
        Stage stage;
        Parent root1;
        Stage stage1;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logging out ");
        alert.setHeaderText("You have logged out see you next time ");
        alert.showAndWait();

        try {
            root = loader.load();
            stage = (Stage) logout.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void EditProfile(ActionEvent event) throws IOException {
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("UserProfile/UserProfile.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void affRegime(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("Regime.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void affEx (ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AdminPannel/AdminPannel.fxml"));/////////////////////////affectation//////////////////////////////
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterNutri(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherCommande.fxml"));//////////////Afficher
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterCoach(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AdminPannel/AdminPannel.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterCmd(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("Affichercommandes.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void addReclamaiton(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterRecla(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherReclamations.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterMagasin(ActionEvent event) throws IOException {        
        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("MarcherProduit.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void suivreLvr(ActionEvent event) throws IOException {
          Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherLivraison.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

}
