/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import sahti.entities.Aliment;
import sahti.entities.Repas;
import sahti.entities.ali_repas;
import sahti.services.AlimentCRUD;
import sahti.services.RepasCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RepasController implements Initializable {

    @FXML
    private TableView<Repas> tab_rep;
    @FXML
    private TableColumn<Repas, Integer> id_repa;
    @FXML
    private TableColumn<Repas, String> nom_rep;
    @FXML
    private TableColumn<Repas, Integer> nbre_cal;
    @FXML
    private TableColumn<Repas, Integer> quant;
    @FXML
    private TableView<Aliment> tablev;
    @FXML
    private TableColumn<Aliment, Integer> alim;
    @FXML
    private TableColumn<Aliment, String> n;
    @FXML
    private TableColumn<Aliment, String> ty;
    @FXML
    private TableColumn<Aliment, String> ph;
    @FXML
    private TableColumn<Aliment, Integer> ca;
    @FXML
    private TableColumn<Aliment, String> des;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_sup;
    @FXML
    private Button btn_modif;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private Button btn_affecter;
    private int id_repas;
    private int id_aliment;
    @FXML
    private TextField trrepas;
    @FXML
    private TextField l_nbr;
    @FXML
    private TextField l_nom;
    @FXML
    private TextField l_quantite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherRepas();
        afficherAliment();
    }

    public void afficherRepas() {
        RepasCRUD sr = new RepasCRUD();
        ObservableList<Repas> list = sr.getRepasList();
        id_repa.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("id_repas"));
        nom_rep.setCellValueFactory(new PropertyValueFactory<Repas, String>("nom_rep"));
        nbre_cal.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("nb_cal"));
        quant.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("quantite"));
        tab_rep.setItems(list);

    }

    public void afficherAliment() {
        AlimentCRUD sr = new AlimentCRUD();
        ObservableList<Aliment> list = sr.getAlimentList();
        alim.setCellValueFactory(new PropertyValueFactory<Aliment, Integer>("id_aliment"));
        n.setCellValueFactory(new PropertyValueFactory<Aliment, String>("nom"));
        ty.setCellValueFactory(new PropertyValueFactory<Aliment, String>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<Aliment, String>("image"));
        ca.setCellValueFactory(new PropertyValueFactory<Aliment, Integer>("calories"));
        des.setCellValueFactory(new PropertyValueFactory<Aliment, String>("description"));
        tablev.setItems(list);

    }

    private void menu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuGestion.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("");
        stage.show();

    }
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("MenuGestion.fxml"));/* Exception */
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

    @FXML
    private void HandleMouseAction(MouseEvent event) {
        Repas a = tab_rep.getSelectionModel().getSelectedItem();
        l_nom.setText(a.getNom_rep());

        String idr = Integer.toString(a.getId_repas());
        trrepas.setText(idr);
        String calories = Integer.toString(a.getNb_cal());
        l_nbr.setText(calories);
        String quantite = Integer.toString(a.getQuantite());
        l_quantite.setText(quantite);
        id_repas = a.getId_repas();

    }

    @FXML
    private void HandleMouseAction1(MouseEvent event) {
        Aliment a = tablev.getSelectionModel().getSelectedItem();
        id_aliment = a.getId_aliment();

    }

    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";
        String styledefault = "-fx-border-color: green;";

        trrepas.setStyle(styledefault);
        l_nom.setStyle(styledefault);
        l_nbr.setStyle(styledefault);
        l_quantite.setStyle(styledefault);
//        tfdate.setStyle(styledefault);
//        tfduree.setStyle(styledefault);
//        tfrepas.setStyle(styledefault);
//        tfcalorie.setStyle(styledefault);
//        tfspec.setStyle(styledefault);

        if (trrepas.equals("")) {
            trrepas.setStyle(style);
            verif = 1;
        }
        if (l_nom.equals("")) {
            l_nom.setStyle(style);
            verif = 1;
        }
        if (l_nbr.equals("")) {
            l_nbr.setStyle(style);
            verif = 1;
        }
        if (l_quantite.getText().equals("")) {
            l_quantite.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verfier votre champs!");

        return false;
    }

    @FXML
    private void ajouterRepas(ActionEvent event) {
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        if (verifUserChamps()) {
            (new Alert(Alert.AlertType.CONFIRMATION, "Repas Ajouté", new ButtonType[]{ButtonType.OK})).show();
            tilte = "Reussit";
            message = "Repas ajouté";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            RepasCRUD sr = new RepasCRUD();
            Repas a = new Repas();
            a.setNb_cal(Integer.parseInt(l_nbr.getText()));
            a.setQuantite(Integer.parseInt(l_quantite.getText()));
            a.setNom_rep(l_nom.getText());
            sr.AjouterRepas(a);
            afficherRepas();
            refrech();

        }
    }

    @FXML
    private void supprimerRepas(ActionEvent event) {

        RepasCRUD se = new RepasCRUD();
        Repas r = new Repas();
        int id = 0;
        id = Integer.parseInt(trrepas.getText());
        r.setId_repas(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER LE REPAS ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            se.SupprimerRepas(r);

            JOptionPane.showMessageDialog(null, " Repas supprimer ");
        } else {
            JOptionPane.showMessageDialog(null, " Repas non Supprimer");
        }

        l_nom.setText("");
        l_nbr.setText("");
        l_quantite.setText("");

        afficherRepas();
        refrech();

    }

    @FXML
    private void ModifierRepas(ActionEvent event
    ) {

        Notifications notificationBuilder = Notifications.create()
                .title("Modification de Votre Repas")
                .text("Votre repas a été modifié avec succes")
                .graphic(null)
                .position(Pos.CENTER)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Clicked on notifictaion");
                });
        RepasCRUD sa = new RepasCRUD();
        Repas re = new Repas();
        int id = 0;
        re.setId_repas(id_repas);
        re.setNom_rep(l_nom.getText());
        int calories;
        calories = Integer.parseInt(l_nbr.getText());
        re.setNb_cal(calories);
        int quant = Integer.parseInt(l_quantite.getText());
        re.setQuantite(quant);
        sa.ModifierRepas(re);
        afficherRepas();
        refrech();
    }

    private void refrech() {
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
            options = FXCollections.observableArrayList();

            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_repas FROM repas");
            while (rs.next()) {

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }

    @FXML
    private void AffecterAliment(ActionEvent event) {

        System.out.println("");
        ali_repas l = new ali_repas();
        AlimentCRUD sa = new AlimentCRUD();
        l.setId_aliment(id_aliment);
        l.setId_repas(id_repas);

        System.out.println(l);
        sa.ajouterREPAS(l);
        JOptionPane.showMessageDialog(null, "Repas ajoutée !");
    }

    @FXML
    private void liste(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAliment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

}
