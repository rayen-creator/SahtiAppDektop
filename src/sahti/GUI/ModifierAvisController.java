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
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;
import sahti.entities.Avis;
import sahti.services.AvisCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class ModifierAvisController implements Initializable {

    @FXML
    private Rating rtProduit;
    @FXML
    private ImageView imgProduit;
    @FXML
    private TextArea txtaComment;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnModifier;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void updateAvis(ActionEvent event) {
        Optional<ButtonType> obligatoire;
        Optional<ButtonType> modification;
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Modifier reclamation");
        // Header Text: null
        a.setHeaderText(null);
        a.setContentText("Voulez vous vraiment modifier la reclamation!");
        modification = a.showAndWait();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champ obligatoire");
        // Header Text: null
        alert.setHeaderText(null);

        String comment = txtaComment.getText();
        int rating = (int) rtProduit.getRating();
        if (rating == 0) {
            alert.setContentText("Le rating ne doit pas être vide!");
            obligatoire = alert.showAndWait();
        } else if (comment.isEmpty()) {
            alert.setContentText("Le champ commentaire ne doit pas être vide!");
            obligatoire = alert.showAndWait();
        }
        if (modification.get() == ButtonType.OK) {
            Avis av = new Avis("test", 5);
            AvisCRUD ac = new AvisCRUD();
            ac.modifierAvis(av);

//            info.setTitle("Modififcation effectuée");
//            // Header Text: null
//            info.setHeaderText(null);
//            info.setContentText("Modifié avec succés!");
//            info.showAndWait();

            //System.out.println(confirmation);
        }

    }

    public void settxtComment(String message) {
        this.settxtComment(message);
    }

    public void settxtRating(String message) {
        this.settxtRating(message);
    }

}
