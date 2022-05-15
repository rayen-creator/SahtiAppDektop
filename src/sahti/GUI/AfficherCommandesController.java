/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sahti.entities.Commande;
import sahti.services.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherCommandesController implements Initializable {

    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRechercher;
    @FXML
    private TableColumn<Commande, String> colId;
    @FXML
    private TableColumn<Commande, String> colClient;
    @FXML
    private TableColumn<Commande, String> colNumCmd;
    @FXML
    private TableColumn<Commande, String> colMntCmd;
    @FXML
    private TableColumn<Commande, String> colDateCmd;
    @FXML
    private TableColumn<Commande, String> colEtatCmd;
    @FXML
    private TableView<Commande> table;
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    private Label lblClient;
    @FXML
    private Label lblNumcommande;
    @FXML
    private Text lblCommentCmd;
    @FXML
    private Label lblEtatCmd;
    @FXML
    private Label lblMntCmd;
    @FXML
    private Label lblDateCmd;
    @FXML
    private Label lblDT;
    @FXML
    private Button btnRefresh;
    @FXML
    private Label aClient;
    @FXML
    private Label anumCmd;
    @FXML
    private Label amnt;
    @FXML
    private Label aComment;
    @FXML
    private Label aDate;
    @FXML
    private Label aEtat;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        getListCmd();
        lblDT.setVisible(false);
        aClient.setVisible(false);
        anumCmd.setVisible(false);
        amnt.setVisible(false);
        aComment.setVisible(false);
        aDate.setVisible(false);
        aEtat.setVisible(false);
        //lblDT.setVisible(false);
    }

    public void getListCmd() {
        CommandeCRUD cmd = new CommandeCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colNumCmd.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
        colMntCmd.setCellValueFactory(new PropertyValueFactory<>("montantCmd"));
        colEtatCmd.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colDateCmd.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        
        // Display row data
        ObservableList<Commande> list = cmd.consulterCommandes();
        table.setItems(list);
    }

    @FXML
    private void rechercherCommande(ActionEvent event) {
        try {
            Optional<ButtonType> obligatoire;
            String recherche = txtRecherche.getText();
            if (recherche.isEmpty()) {
                confirmation.setTitle("Champ obligatoire");
                // Header Text: null
                confirmation.setHeaderText(null);
                confirmation.setContentText("Le champ recherche ne doit pas être vide!");
                obligatoire = confirmation.showAndWait();
                if(obligatoire.get() == ButtonType.OK)
                    return ;
                else
                    return;
                
            }
            
            CommandeCRUD cc = new CommandeCRUD();
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
            colNumCmd.setCellValueFactory(new PropertyValueFactory<>("numCmd"));
            colMntCmd.setCellValueFactory(new PropertyValueFactory<>("montantCmd"));
            colEtatCmd.setCellValueFactory(new PropertyValueFactory<>("etat"));
            colDateCmd.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
            
            ObservableList<Commande> list =  cc.rechercherCommande(recherche);
            table.setItems(list);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }        
    }

    @FXML
    private void getDetailCmd(MouseEvent event) {
        CommandeCRUD c = new CommandeCRUD();
        Commande l = table.getSelectionModel().getSelectedItem();

        lblNumcommande.setText(l.getNumCmd());
        lblCommentCmd.setText(l.getCommentaireCmd());
        lblEtatCmd.setText(l.getEtat());
        lblMntCmd.setText(String.valueOf(l.getMontantCmd()));
        lblClient.setText("Client");
        lblDateCmd.setText(l.getDateCommande().toString());
        lblDT.setVisible(true);
        aClient.setVisible(true);
        anumCmd.setVisible(true);
        amnt.setVisible(true);
        aComment.setVisible(true);
        aDate.setVisible(true);
        aEtat.setVisible(true);
        
        if (event.getClickCount() == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommande.fxml"));
            try {
                Stage primaryStage = new Stage();

                Parent root = loader.load();
                ModifierCommandeController mc = loader.getController();
                mc.setId(l.getId());
                Scene scene = new Scene(root);

                primaryStage.setTitle("Modifier Commande");
                primaryStage.setScene(scene);
                primaryStage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void refrechTable(ActionEvent event) {
        getListCmd();
    }
}
