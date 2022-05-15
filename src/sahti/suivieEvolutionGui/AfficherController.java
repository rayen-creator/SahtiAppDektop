/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.suivieEvolutionGui;





import entities.EncapsulationSuivieEvolution;
import entities.SuivieEvolution;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import sahti.utils.MyConnection;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author abdou
 */
public class AfficherController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField in_search;
    @FXML
    private TableView<SuivieEvolution> table;
    @FXML
    private TableColumn<SuivieEvolution, String> cl_id;
    @FXML
    private TableColumn<SuivieEvolution, String> cl_user_id;
    @FXML
    private TableColumn<SuivieEvolution, String> cl_poids;
    @FXML
    private TableColumn<SuivieEvolution, String> cl_date_programme;
    @FXML
    private TableColumn<SuivieEvolution, String> cl_date_evolution;
    ObservableList<SuivieEvolution> obl = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String titre= "suivi evolution";
        String message =" bienvenue à l'interface suivi evolution";
        TrayNotification tray= new TrayNotification();
        AnimationType type= AnimationType.SLIDE;
        
        tray.setAnimationType(type);
        tray.setTitle(titre);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
        // TODO
        search_user();
        table.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                SuivieEvolution maSuivieEvolution = table.getSelectionModel().getSelectedItem();
                EncapsulationSuivieEvolution encapsulationSuivieEvolution = new EncapsulationSuivieEvolution(maSuivieEvolution.getId(), maSuivieEvolution.getIdUser(), maSuivieEvolution.getPoids(),maSuivieEvolution.getDateDebutProgramme(),maSuivieEvolution.getDateEvolution());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierSupprimer.fxml"));
                try {
                    Parent root = loader.load();
                    in_search.getScene().setRoot(root);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../test/acceuil.fxml"));
            try {
                Parent root = loader.load();
                btn_ajouter.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter.fxml"));
        try {
            Parent root = loader.load();
            in_search.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void search_user() {
        cl_user_id.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        cl_poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
        cl_date_evolution.setCellValueFactory(new PropertyValueFactory<>("dateEvolution"));
        cl_date_programme.setCellValueFactory(new PropertyValueFactory<>("dateDebutProgramme"));
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String requete = "SELECT * FROM suivieevolution ";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                SuivieEvolution se = new SuivieEvolution(rs.getInt("id"), rs.getInt("idUser"), rs.getInt("poids"), rs.getDate("dateDebutProgramme"),rs.getDate("dateEvolution"));
                obl.add(se);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        table.setItems(obl);
        FilteredList<SuivieEvolution> filteredData = new FilteredList<>(obl, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(SuivieEvolution -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(SuivieEvolution.getIdUser()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (String.valueOf(SuivieEvolution.getDateDebutProgramme()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else if (SuivieEvolution.getDateEvolution().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(SuivieEvolution.getPoids()).indexOf(lowerCaseFilter) != -1) {
                    return true;// Filter matches email
                } else {
                    return false; // Does not match.
                }

            });
        });

        table.setItems(filteredData);
    }

    
    
}
