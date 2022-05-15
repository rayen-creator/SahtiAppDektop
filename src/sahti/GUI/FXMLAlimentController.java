/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import sahti.entities.Aliment;
import sahti.services.AlimentCRUD;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLAlimentController implements Initializable {

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
    private ComboBox<String> tftype;
    ObservableList<String> list = FXCollections.observableArrayList("Fruits", "Légumes");
    String comb;
    @FXML
    private TextField tfaliment;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfcal;
    @FXML
    private TextArea photo_p;
    @FXML
    private Button browse;
    @FXML
    private Button gsupp;
    @FXML
    private TextField chercherAliment;
    @FXML
    private ImageView photo_view;
    @FXML
    private ComboBox<Integer> gComboBox;
    Connection cnx;
    Aliment al = new Aliment();
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private TextArea tfdesc;
    @FXML
    private Button btnA;
    @FXML
    private Button btnmodifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        afficherAliment();

        //combobox
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_aliment FROM aliment");
            while (rs.next()) {
                //get string from db,whichever way 
                options.add(new Aliment(rs.getInt("id_aliment")).getId_aliment());
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        gComboBox.setItems(null);
        gComboBox.setItems(options);
        tftype.setItems(list);
    }

    @FXML
    private void HandleMouseAction(MouseEvent event) {
        Aliment a = tablev.getSelectionModel().getSelectedItem();
        String idaliment = Integer.toString(a.getId_aliment());
        tfaliment.setText(idaliment);
        tfnom.setText(a.getNom());
        tftype.setValue(a.getType());
        String calories = Integer.toString(a.getCalories());
        tfcal.setText(calories);
        tfdesc.setText(a.getDescription());
        File file = new File(a.getImage());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo_view.setImage(image);
        } catch (IOException ex) {

        }
    }

    @FXML
    private void SelectType(ActionEvent event) {

    }

    @FXML
    private void btnbrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo_view.setImage(image);
            photo_p.setText(file.getAbsolutePath());
        } catch (IOException ex) {

        }
    }

    @FXML
    private void supprimerAliment(ActionEvent event) {

//        Notifications notifictaionBuilder = Notifications.create()
//                .title("Aliment supprimée")
//                .text("Votre Aliment a été supprimée avec succes")
//                .graphic(null)
//                .position(Pos.CENTER)
//               
//                .onAction((ActionEvent event1) -> {
//                    System.out.println("Clicked on notifictaion");
//                });
//        notifictaionBuilder.darkStyle();
//        notifictaionBuilder.show();
        AlimentCRUD sa = new AlimentCRUD();
        Aliment r = new Aliment();
        int id = 0;
        id = Integer.parseInt(tfaliment.getText());
        r.setId_aliment(id);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" Voulez Vous Supprimer L'Aliment ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sa.SupprimerAliment(r);

            JOptionPane.showMessageDialog(null, " Aliment supprimé ");
        } else {
            JOptionPane.showMessageDialog(null, " Aliment non supprimé ");
        }

        tfaliment.setText("");
        tftype.setValue(null);
        tfnom.setText("");
        tfcal.setText("");
        tfdesc.setText("");

        afficherAliment();
        refrech();
    }

    @FXML
    void search() {
        Aliment l = new Aliment();
        alim.setCellValueFactory(new PropertyValueFactory<Aliment, Integer>("id_membre"));
        n.setCellValueFactory(new PropertyValueFactory<Aliment, String>("nom"));
        ty.setCellValueFactory(new PropertyValueFactory<Aliment, String>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<Aliment, String>("photo"));
        ca.setCellValueFactory(new PropertyValueFactory<Aliment, Integer>("calories"));
        des.setCellValueFactory(new PropertyValueFactory<Aliment, String>("description"));

        ObservableList<Aliment> dataList;
        AlimentCRUD al = new AlimentCRUD();

        dataList = al.getEventsList();

        tablev.setItems(dataList);

        FilteredList<Aliment> filteredData = new FilteredList<>(dataList, b -> true);

        chercherAliment.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Aliment person) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<Aliment> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablev.comparatorProperty());
        tablev.setItems(sortedData);
    }

//    private void menu(ActionEvent event) throws IOException {
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("MenuGestion.fxml"));/* Exception */
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    private void refrech() {
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_aliment FROM aliment");
            while (rs.next()) {
                //get string from db,whichever way 
                options.add(new Aliment(rs.getInt("id_aliment")).getId_aliment());
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        gComboBox.setItems(null);
        gComboBox.setItems(options);
        tftype.setItems(list);

    }

    @FXML
    private void AjouterAliment(ActionEvent event) {

        Notifications notifictaionBuilder = Notifications.create()
                .title("Aliment Ajoutées")
                .text("Votre Aliment a été ajouté avec succes")
                .position(Pos.TOP_CENTER)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Clicked on notifictaion");
                });
        notifictaionBuilder.darkStyle();
        notifictaionBuilder.show();

        if (verifUserChamps()) {

            AlimentCRUD sa = new AlimentCRUD();
            Aliment a = new Aliment();

            a.setId_aliment(Integer.parseInt(tfaliment.getText()));
            a.setCalories(Integer.parseInt(tfcal.getText()));
            a.setType(tftype.getValue());
            a.setDescription(tfdesc.getText());
            a.setNom(tfnom.getText());
            a.setImage(photo_p.getText().replace('\\', '/'));

            sa.AjouterAliment(a);

            afficherAliment();
            refrech();
            JOptionPane.showMessageDialog(null, "Aliment ajouté");
        }

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

        refrech();

    }

    @FXML
    private void ModifierAliment(ActionEvent event) {

        Notifications notificationBuilder = Notifications.create()
                .title("Modification de Votre Aliment")
                .text("Votre Aliment a été modifiée avec succes")
                //.graphic(null)
                .position(Pos.TOP_CENTER)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Clicked on notifictaion");
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        AlimentCRUD sa = new AlimentCRUD();
        Aliment re = new Aliment();

        int id = 0;
        id = Integer.parseInt(tfaliment.getText());
        re.setNom(tfnom.getText());
        re.setId_aliment(id);
        int calories;
        calories = Integer.parseInt(tfcal.getText());
        re.setCalories(calories);
        re.setImage(photo_p.getText());
        re.setType(tftype.getValue());
        re.setDescription(tfdesc.getText());

        sa.ModifierAliment(re);
        afficherAliment();
        refrech();
    }

    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        tfaliment.setStyle(styledefault);
        tfnom.setStyle(styledefault);
        tftype.setStyle(styledefault);
        tfdesc.setStyle(styledefault);
        tfcal.setStyle(styledefault);

        if (tfaliment.getText().equals("")) {
            tfaliment.setStyle(style);
            verif = 1;
        }

        if (tfnom.getText().equals("")) {
            tfnom.setStyle(style);
            verif = 1;
        }

        if (tfcal.getText().equals("")) {
            tfcal.setStyle(style);
            verif = 1;
        }
        if (tfdesc.getText().equals("")) {
            tfdesc.setStyle(style);
            verif = 1;
        }

        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verfier votre champs!");
        return false;
    }

    @FXML
    private void Aliment(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontRepas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void Regime(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Regime.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }
}
