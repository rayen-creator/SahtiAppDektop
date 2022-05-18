/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI.ProgrammeEntraineurGui;



import entities.EncapsulationProgrammeEntraineur;
import entities.ProgrammeEntraineur;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.swing.JOptionPane;
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
    private TableView<ProgrammeEntraineur> table;
   
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TextField in_search;
    @FXML
    private ComboBox<String> triBox;
    @FXML
    private Button btn_csv;
    
    ObservableList<ProgrammeEntraineur> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<ProgrammeEntraineur, String> cl_exercice_id;
    @FXML
    private TableColumn<ProgrammeEntraineur, String> cl_nom_pack;
    @FXML
    private TableColumn<ProgrammeEntraineur, String> cl_type;
    @FXML
    private TableColumn<ProgrammeEntraineur, String> cl_id;

       /**
     * Initializes the controller class.
     */
    

    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String titre= "programme entraineur";
        String message =" bienvenue programme entraineur";
        TrayNotification tray= new TrayNotification();
        AnimationType type= AnimationType.SLIDE;
        
        tray.setAnimationType(type);
        tray.setTitle(titre);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(3000));
        triBox.getItems().addAll(
                "exercice",
                "nom pack",
                "type"
        );
        search_user();
        table.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                ProgrammeEntraineur monProgrammeEntraineur = table.getSelectionModel().getSelectedItem();
                EncapsulationProgrammeEntraineur encapsulationProgrammeEntraineur = new EncapsulationProgrammeEntraineur(monProgrammeEntraineur.getId(), monProgrammeEntraineur.getIdExercice(), monProgrammeEntraineur.getNomPack(), monProgrammeEntraineur.getType());
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
        cl_exercice_id.setCellValueFactory(new PropertyValueFactory<>("idExercice"));
        cl_nom_pack.setCellValueFactory(new PropertyValueFactory<>("nomPack"));
        cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            String requete = "SELECT * FROM programmeentraineur ";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ProgrammeEntraineur pe = new ProgrammeEntraineur(rs.getInt("id"), rs.getString("idExercice"), rs.getString("nomPack"), rs.getString("type"));
                obl.add(pe);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        table.setItems(obl);
        FilteredList<ProgrammeEntraineur> filteredData = new FilteredList<>(obl, b -> true);
        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ProgrammeEntraineur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if ((ProgrammeEntraineur.getIdExercice().toLowerCase()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (ProgrammeEntraineur.getNomPack().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (ProgrammeEntraineur.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else {
                    return false; 
                }

            });
        });

        table.setItems(filteredData);
    }



    @FXML
    private void trier(ActionEvent event) {
        Comparator<ProgrammeEntraineur> comparator;
       if (triBox.getValue() == "exercice") {
       
       
           comparator = Comparator.comparing(ProgrammeEntraineur::getIdExercice);
//
        } else if (triBox.getValue() == "nom pack") {
            comparator = Comparator.comparing(ProgrammeEntraineur::getNomPack);
        
        } else {
            comparator = Comparator.comparing(ProgrammeEntraineur::getType);

        }

        FXCollections.sort(obl, comparator);
        table.setItems(obl);

    }

    @FXML
    private void csv(ActionEvent event) {
        saveHeader("ProgrammeEntraineur.csv");
        try {
            String requete = "SELECT * FROM ProgrammeEntraineur ";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                saveRecord(rs.getInt("id"), rs.getString("idExercice"), rs.getString("nomPack"), rs.getString("type"), "ProgrammeEntraineur.csv");
            }
            JOptionPane.showMessageDialog(null, "Record saved");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveHeader(String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("ID,IDExercice,nomPack,type");
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

    private void saveRecord(int ID, String idExercice, String nomPack, String type, String filepath) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(ID + "," + idExercice + "," + nomPack + "," + type );
            pw.flush();
            pw.close();

        } catch (Exception E) {
            JOptionPane.showMessageDialog(null, "Record not saved");
        }
    }

}





