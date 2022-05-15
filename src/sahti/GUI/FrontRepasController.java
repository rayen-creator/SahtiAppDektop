/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import sahti.entities.Repas;
import sahti.services.RepasCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontRepasController implements Initializable {

    @FXML
    private TableView<Repas> tab_rep;
    @FXML
    private TableColumn<Repas, Integer> id_rep;
    @FXML
    private TableColumn<Repas, String> nom_rep;
    @FXML
    private TableColumn<Repas, Integer> nbre_cal;
    @FXML
    private TableColumn<Repas, Integer> quant;
    @FXML
    private Button btn_alim;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherRepas();
    }

    public void afficherRepas() {
        RepasCRUD sr = new RepasCRUD();
        ObservableList<Repas> list = sr.getRepasList();
        id_rep.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("id_repas"));
        nom_rep.setCellValueFactory(new PropertyValueFactory<Repas, String>("nom_rep"));
        nbre_cal.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("nb_cal"));
        quant.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("quantite"));
        tab_rep.setItems(list);

    }

    private void refrech() {
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);

            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_repas FROM repas");
            while (rs.next()) {

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

    }

    @FXML
    private void AlimAction(ActionEvent event) throws IOException {


  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAliment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void HandleMouseAction(MouseEvent event) {
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Repas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

}
