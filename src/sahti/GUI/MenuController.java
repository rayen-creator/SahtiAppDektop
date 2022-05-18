/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sahti.services.AccountModif;
import sahti.services.Login;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane idanchorpane;
    private Parent fxml;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button logout;
    @FXML
    private Label idusername;
    @FXML
    private Button EditProfile;
    private TitledPane idmagsin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AccountModif a = new AccountModif();

        Login l = new Login();

        if (l.loginAdmin(Login.emailUser, Login.pwduser)) {
            String username = a.searchadminbyemail(Login.emailUser);
            idusername.setText(username);
            idusername.setMaxWidth(Double.MAX_VALUE);
            idusername.setAlignment(Pos.CENTER_LEFT);
            //idmagsin.setExpanded(false);
            
        }
//     
//        else if (l.loginEntraineur(Login.emailUser, Login.pwduser)) {
//            String username = a.searchcoachbyemail(Login.emailUser);
//            idusername.setText(username);
//
//            idusername.setMaxWidth(Double.MAX_VALUE);
//            idusername.setAlignment(Pos.CENTER_LEFT);
//
//            ImageView myImageView = new ImageView();
//            Image image = new Image("file:" + "src\\sahti\\img\\" + a.searchImgCoach(Login.emailUser));
//            idpicprofil.setImage(image);
//
//        }
//        else if (l.loginNutritioniste(Login.emailUser, Login.pwduser)) {
//            String username = a.searchnutritionistebyemail(Login.emailUser);
//            idusername.setText(username);
//
//            idusername.setMaxWidth(Double.MAX_VALUE);
//            idusername.setAlignment(Pos.CENTER_LEFT);
//
//            ImageView myImageView = new ImageView();
//            Image image = new Image("file:" + "C:\\3A16\\S2\\projet Java\\Desktop App\\Code\\SahtiApp\\src\\ProfileIMG\\Nutri\\" + a.searchImgNutri(Login.emailUser));
//            idpicprofil.setImage(image);
//
//        }
    }

    private void btnlogins(ActionEvent event) throws IOException {

    }

    @FXML
    private void idbtn(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login/Login.fxml"));

        Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AdminPannel/AdminPannel.fxml"));
        idanchorpane.getChildren().setAll(node);

    }

    private void idloginslogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainHOME.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
//        stage.setAlwaysOnTop(true);
        //

        //
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(new Scene(root1));
        stage.show();
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
    private void gererProd(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void gererCat(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    private void gererPromo(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AjouterPromotion.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void addReclamation(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterReclamation(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherReclamations.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void consulterAvis(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherAvis.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void listCmd(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherCommandes.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

    @FXML
    private void listLvr(ActionEvent event) throws IOException {
         Node node;
        node = (Node) FXMLLoader.load(getClass().getResource("AfficherLivraison.fxml"));
        idanchorpane.getChildren().setAll(node);
    }

}
