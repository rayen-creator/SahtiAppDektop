/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI.Login;

import sahti.entities.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sahti.services.AdminPannel;
import sahti.services.Login;
import sahti.services.Signup;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class LoginController implements Initializable {

    @FXML
    private Button idreset;
    @FXML
    private Button idloginbtn;
    @FXML
    private TextField idemail;
    @FXML
    private PasswordField idpwd;
    @FXML
    private Hyperlink gotomain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void LetReset(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPasswd.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idreset.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void letLogin(ActionEvent event) throws IOException {
        String mail = idemail.getText();
        String pwd = idpwd.getText();
        if ((!mail.isEmpty()) & (!pwd.isEmpty())) {
            AdminPannel ap = new AdminPannel();
            Login l = new Login();
//            | (l.loginAdmin(mail, pwd))
            if (l.loginEntraineur(mail, pwd)) {
                if (ap.returnBlockedCoach(mail) == true) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information ");
                    alert.setHeaderText("You have been blocked by the Administration !");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Login succes!");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuCoachNutri.fxml"));

                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idloginbtn.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else if (l.loginClient(mail, pwd)) {
                if (ap.returnBlockedClient(mail) == true) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information ");
                    alert.setHeaderText("You have been blocked by the Administration !");
                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Login succes!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuClient.fxml"));

                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idloginbtn.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else if (l.loginNutritioniste(mail, pwd)) {

                if (ap.returnBlockedNutri(mail) == true) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Information ");
                    alert.setHeaderText("You have been blocked by the Administration !");
                    alert.showAndWait();
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Login succes!");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuCoachNutri.fxml"));

                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idloginbtn.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else if (l.loginAdmin(mail, pwd) == true) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Login succes!");
                alert.showAndWait();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../menu.fxml"));

                Parent root;
                Stage stage;
                try {
                    root = loader.load();
                    stage = (Stage) idloginbtn.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText("Login failed!");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Fill your the form to continue !");
            alert.showAndWait();
        }

    }

    @FXML
    private void letgotomain(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainHOME.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) gotomain.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
