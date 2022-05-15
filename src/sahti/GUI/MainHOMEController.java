/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import sahti.GUI.AdminPannel.AdminPannelController;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import sahti.services.ListUser;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class MainHOMEController implements Initializable {

    @FXML
    private Button idLogbtn;
    @FXML
    private Button idsignupc;
    @FXML
    private Button idsign;
    @FXML
    private Button idsignupcoach;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void MainLogin(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPannel/AdminPannel.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login/Login.fxml"));
        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idLogbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

//test
    }

    @FXML
    private void btnsignupclient(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup/SignupClient.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idLogbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void btnsignupNut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup/SignupNutri.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idLogbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void btnsignupcoach(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup/SignCoach.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idLogbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
