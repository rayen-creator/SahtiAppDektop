/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI.AdminPannel;

import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import sahti.services.AccountModif;
import sahti.services.AdminPannel;
import sahti.services.ListUser;
import sahti.services.Login;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class AdminPannelController implements Initializable {

    @FXML
    private ImageView idimageprofile;
    private Label idnameprofile;

    private boolean userclient = false;
    private boolean usercoach = false;
    private boolean usernutr = false;
    @FXML
    private TableView<Client> idtabClient;
    @FXML
    private TableColumn<Client, String> idnom;
    @FXML
    private TableColumn<Client, String> idprenom;
    @FXML
    private TableColumn<Client, String> idaddr;
    @FXML
    private TableColumn<Client, String> iddate;
    @FXML
    private TableView<Entraineur> idCoach;
    @FXML
    private TableColumn<Entraineur, String> idnomCoach;
    @FXML
    private TableColumn<Entraineur, String> idprenomCoach;
    @FXML
    private TableColumn<Entraineur, String> idaddrCoach;
    @FXML
    private TableColumn<Entraineur, String> idcertifCoach;
    @FXML
    private TableColumn<Nutritioniste, String> idnomNut;
    @FXML
    private TableColumn<Nutritioniste, String> idpreNut;
    @FXML
    private TableColumn<Nutritioniste, String> idaddrNut;
    @FXML
    private TableColumn<Nutritioniste, String> idCertNut;
    @FXML
    private Button idsupprimeClient;
    @FXML
    private TableView<Nutritioniste> idtabnut;
    @FXML
    private Button idsupprimeCoach;
    @FXML
    private Button idsupprimeNut;
    @FXML
    private Text idtitle;
    @FXML
    private Separator idtraitclient;
    @FXML
    private Text idclientnom;
    @FXML
    private Button idchoisircoach;
    @FXML
    private Button idchoisrnutri;
    @FXML
    private Button idblock;
    @FXML
    private Button idblock1;
    @FXML
    private Button idblock2;
    @FXML
    private Button idunblock1;
    @FXML
    private Button idunblock2;
    @FXML
    private Button idunblock3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO

        AccountModif a = new AccountModif();
//
        Login l = new Login();
        ListUser ls = new ListUser();

        if (l.loginAdmin(Login.emailUser, Login.pwduser)) {
            idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            idaddr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            iddate.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));

            // Display row data
            ObservableList<Client> list = ls.GetClient();
            idtabClient.setItems(list);

            //**************coach***************
            idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

            // Display row data
            ObservableList<Entraineur> listE = ls.GetEntraineur();
            idCoach.setItems(listE);
            //***************nutri*************************
            idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

            // Display row data
            ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
            idtabnut.setItems(listN);

            idchoisircoach.setVisible(false);
            idchoisrnutri.setVisible(false);

        }

//        idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        idprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        idaddr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
//        iddate.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));
//
//        // Display row data
//        ObservableList<Client> list = ls.GetClient();
//        idtabClient.setItems(list);
        if (l.loginClient(Login.emailUser, Login.pwduser)) {

            //**************coach***************
            idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

            // Display row data
            ObservableList<Entraineur> listE = ls.GetEntraineur();
            idCoach.setItems(listE);
            //***************nutri*************************
            idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
            idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

            // Display row data
            ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
            idtabnut.setItems(listN);

            idsupprimeNut.setVisible(false);
            idsupprimeCoach.setVisible(false);
            idsupprimeClient.setVisible(false);
            idtabClient.setVisible(false);

            idtitle.setText("Liste des entraineurs et nutritionnistes");
            idclientnom.setVisible(false);
            idtraitclient.setVisible(false);
            //cacher les  btn bloquer
            idblock.setVisible(false);
            idblock1.setVisible(false);
            idblock2.setVisible(false);
            
            idunblock1.setVisible(false);
            idunblock2.setVisible(false);
            idunblock3.setVisible(false);
        }
    }

    @FXML
    private void idbtnsupClient(ActionEvent event
    ) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Client c = idtabClient.getSelectionModel().getSelectedItem();
        boolean pressed = false;
        pressed = idtabClient.isPressed();
        if (c != null) {

            c.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT suppression");
            alert.setHeaderText(null);
            alert.setContentText(" ARE YOU SURE YOU WANT TO DELETE THIS USER ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                am.DeleteClient(c);

                JOptionPane.showMessageDialog(null, " USER DELETED ! ");
                idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                iddate.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));

                // Display row data
                ObservableList<Client> list = ls.GetClient();
                idtabClient.setItems(list);
            }
            else {
                JOptionPane.showMessageDialog(null, " failed to delete user ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to delete ");
        }
    }

    @FXML
    private void idbtnsupCoach(ActionEvent event
    ) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Entraineur e = idCoach.getSelectionModel().getSelectedItem();

        if (e != null) {

            e.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to delete this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.DeleteEntraineur(e);

                JOptionPane.showMessageDialog(null, " Coach  successfully deleted ! ");
                idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Entraineur> listE = ls.GetEntraineur();
                idCoach.setItems(listE);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to delete coach ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to delete ");
        }
    }

    @FXML
    private void idbtnsupNut(ActionEvent event
    ) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Nutritioniste n = idtabnut.getSelectionModel().getSelectedItem();

        if (n != null) {

            n.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to delete this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.DeleteNutritioniste(n);

                JOptionPane.showMessageDialog(null, " Nutritionist successfully deleted ! ");

                idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
                idtabnut.setItems(listN);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to delete nutritionist ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to delete ");
        }
    }

//*******************************************************************
    @FXML
    private void btnblockclient(ActionEvent event) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Client c = idtabClient.getSelectionModel().getSelectedItem();
        boolean pressed = false;
        pressed = idtabClient.isPressed();
        if (c != null) {

            c.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT suppression");
            alert.setHeaderText(null);
            alert.setContentText(" ARE YOU SURE YOU WANT TO BLOCK THIS USER ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                am.blockClient(c);

                JOptionPane.showMessageDialog(null, " USER BLOCKED ! ");
                idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                iddate.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));

                // Display row data
                ObservableList<Client> list = ls.GetClient();
                idtabClient.setItems(list);
            }
            else {
                JOptionPane.showMessageDialog(null, " failed to blcok user ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to delete ");
        }
    }

    @FXML
    private void btnblockcoach(ActionEvent event) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Entraineur e = idCoach.getSelectionModel().getSelectedItem();

        if (e != null) {

            e.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to delete this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.blockCoach(e);

                JOptionPane.showMessageDialog(null, " Coach  successfully deleted ! ");
                idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Entraineur> listE = ls.GetEntraineur();
                idCoach.setItems(listE);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to block coach ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to block ");
        }
    }

    @FXML
    private void btnblocknutri(ActionEvent event) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Nutritioniste n = idtabnut.getSelectionModel().getSelectedItem();

        if (n != null) {

            n.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to block this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.blockNutri(n);

                JOptionPane.showMessageDialog(null, " Nutritionist successfully blocked ! ");

                idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
                idtabnut.setItems(listN);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to block nutritionist ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to block ");
        }
    }

//*******************************************************************
    @FXML
    private void btnchoisrnutri(ActionEvent event) {
          ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Nutritioniste n = idtabnut.getSelectionModel().getSelectedItem();

        if (n != null) {

            n.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure to continue ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.linkNutriClient(n);

                JOptionPane.showMessageDialog(null, "  You have selected your Nutritionist ! ");

                idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
                idtabnut.setItems(listN);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to select nutritionist ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose your Nutritionist ");
        }
    }

    @FXML
    private void btnchoisitcoach(ActionEvent event) {
          ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Entraineur e = idCoach.getSelectionModel().getSelectedItem();

        if (e != null) {

            e.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure to continue ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.linkCoachClient(e);

                JOptionPane.showMessageDialog(null, " You have selected your coach ! ");
                idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Entraineur> listE = ls.GetEntraineur();
                idCoach.setItems(listE);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to select coach ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose your coach");
        }
    }
//*********************************************************************
    @FXML
    private void btnunblockclient(ActionEvent event) {
        ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Client c = idtabClient.getSelectionModel().getSelectedItem();
        boolean pressed = false;
        pressed = idtabClient.isPressed();
        if (c != null) {

            c.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT suppression");
            alert.setHeaderText(null);
            alert.setContentText(" ARE YOU SURE YOU WANT TO UNBLOCK THIS USER ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                am.unblockClient(c);

                JOptionPane.showMessageDialog(null, " USER UNBLOCKED ! ");
                idnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                iddate.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));

                // Display row data
                ObservableList<Client> list = ls.GetClient();
                idtabClient.setItems(list);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to unblock user  ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to unblock ");
        }
    }

    @FXML
    private void btnunblockcoach(ActionEvent event) {
         ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Entraineur e = idCoach.getSelectionModel().getSelectedItem();

        if (e != null) {

            e.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to unblock this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.unblockCoach(e);

                JOptionPane.showMessageDialog(null, " Coach  successfully unblocked ! ");
                idnomCoach.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idprenomCoach.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrCoach.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idcertifCoach.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Entraineur> listE = ls.GetEntraineur();
                idCoach.setItems(listE);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to unblock coach ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to ublock ");
        }
    }

    @FXML
    private void btnunblocknutri(ActionEvent event) {
         ListUser ls = new ListUser();
        AdminPannel am = new AdminPannel();
        Nutritioniste n = idtabnut.getSelectionModel().getSelectedItem();

        if (n != null) {

            n.getId();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT ");
            alert.setHeaderText(null);
            alert.setContentText(" Are you sure you want to unblock this user ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                am.unblockNutri(n);

                JOptionPane.showMessageDialog(null, " Nutritionist successfully unblocked ! ");

                idnomNut.setCellValueFactory(new PropertyValueFactory<>("nom"));
                idpreNut.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                idaddrNut.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                idCertNut.setCellValueFactory(new PropertyValueFactory<>("certification"));

                // Display row data
                ObservableList<Nutritioniste> listN = ls.GetNutritioniste();
                idtabnut.setItems(listN);
            }
            else {
                JOptionPane.showMessageDialog(null, " Failed to unblock nutritionist ! ");
            }

        }
        else {
            JOptionPane.showMessageDialog(null, " Choose user to unblock ");
        }
    }

}
