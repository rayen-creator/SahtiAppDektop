/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sahti.entities.Reclamation;
import sahti.services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private TextField txtNumReclamation;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtType;
    @FXML
    private TextArea txtaMessage;
    @FXML
    private Button txtAnnuler;
    @FXML
    private Button txtModifier;
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    Alert info = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtNumReclamation.setEditable(false);
    }

    @FXML
    private void updateReclamation(ActionEvent event) {
        Optional<ButtonType> modification;
        Optional<ButtonType> obligatoire;

        confirmation.setTitle("Modifier reclamation");
        // Header Text: null
        confirmation.setHeaderText(null);
        confirmation.setContentText("Voulez vous vraiment modifier la reclamation!");
        modification = confirmation.showAndWait();
        String numReclamation = txtNumReclamation.getText();
        String titre = txtTitre.getText();
        String type = txtType.getText();
        String message = txtaMessage.getText();
        if (titre.isEmpty()) {
            confirmation.setTitle("Champ obligatoire");
            // Header Text: null
            confirmation.setHeaderText(null);
            confirmation.setContentText("Le champ titre ne doit pas être vide!");
            obligatoire = confirmation.showAndWait();
        } else if (type.isEmpty()) {
            confirmation.setTitle("Champ obligatoire");
            // Header Text: null
            confirmation.setHeaderText(null);
            confirmation.setContentText("Le champ type ne doit pas être vide!");
            obligatoire = confirmation.showAndWait();
        } else if (message.isEmpty()) {
            confirmation.setTitle("Champ obligatoire");
            // Header Text: null
            confirmation.setHeaderText(null);
            confirmation.setContentText("Le champ message ne doit pas être vide!");
            obligatoire = confirmation.showAndWait();
        }

        if (modification.get() == ButtonType.OK) {
            Reclamation r = new Reclamation(numReclamation, titre, message, type);
            ReclamationCRUD rc = new ReclamationCRUD();
            rc.modifierReclamation(r);

            info.setTitle("Modififcation effectuée");
            // Header Text: null
            info.setHeaderText(null);
            info.setContentText("Modifié avec succés!");
            info.showAndWait();

           
        }

    }

    public void settxtNumReclamation(String message) {
        this.txtNumReclamation.setText(message);
    }

    public void settxtTitre(String message) {
        this.txtTitre.setText(message);
    }

    public void settxtType(String message) {
        this.txtType.setText(message);
    }

    public void settxtMessage(String message) {
        this.txtaMessage.setText(message);
    }

}
