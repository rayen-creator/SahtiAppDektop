/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sahti.entities.Livraison;
import sahti.services.CommandeCRUD;
import sahti.services.LivraisonCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherLivraisonController implements Initializable {

    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRechercher;
    @FXML
    private TableColumn<Livraison, String> colId;
    @FXML
    private TableColumn<Livraison, String> colClient;
    @FXML
    private TableColumn<Livraison, String> colEtatLiv;
    @FXML
    private TableColumn<Livraison, String> colDateLiv;
    @FXML
    private TableView<Livraison> table;
    @FXML
    private TableColumn<?, ?> colNumCmd;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      LivraisonCRUD lv = new LivraisonCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colEtatLiv.setCellValueFactory(new PropertyValueFactory<>("etatLivraison"));
        colDateLiv.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
           // Display row data
        ObservableList<Livraison> list = lv.afficherListeLivraison();
        table.setItems(list);
    }
//    public void getListLivraison(){
//        
//    }
    @FXML
    private void getSelectedLivraison(MouseEvent event) {
        CommandeCRUD c = new CommandeCRUD();
        LivraisonCRUD lcn = new LivraisonCRUD();
        if (event.getClickCount() == 2) {
            Livraison l = table.getSelectionModel().getSelectedItem();
            //int idCommande = lcn.getIdCommande(l.getId());
            //System.out.print(idCommande);
            List<String> rs  = lcn.getLivraison(l.getId());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailLivraison.fxml"));

            try {
                Parent root = loader.load();

                DetailLivraisonController lc = loader.getController();
                lc.setlblNumCmd(rs.get(1));
                lc.setlblEtatLivraison(rs.get(0));
                lc.setlblDaetCmd(rs.get(2));
                
                

                txtRecherche.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
