/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sahti.services.MailJava;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EnvoyerMailController implements Initializable {

    @FXML
    private TextField txttitre;
    @FXML
    private Button btnmail;
    @FXML
    private TextArea txtmsg;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Sahti.GUI.EnvoyerMailController.initialize()");
    }

    @FXML
    private void SendMail(ActionEvent event) {

        MailJava m = new MailJava();

        List<String> emails = m.ListEmail();

        if (txttitre.getText().trim().isEmpty()
                || txtmsg.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'envoie des emails a été effectué avec succées");
            alert.showAndWait();

            String titre = txttitre.getText();
            String msg = txtmsg.getText();
            String message;
            for (int i = 0; i < emails.size(); i++) {
                MailJava.send("noreplysahti@gmail.com", "rwtlwppcxycwhwfa", emails.get(i), titre, msg);

            }
        }

    }
}


    

