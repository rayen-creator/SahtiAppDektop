/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import sahti.entities.Categorie;
import sahti.services.CategorieCRUD;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private Button btnImg;
    @FXML
    private Button btnAjouterCat;
    @FXML
    private TextField txtTitre;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView VImage;

    private String filePath;
    @FXML
    private TableView<Categorie> tableCat;
    @FXML
    private TableColumn<Categorie, Integer> Idcat;
    @FXML
    private TableColumn<Categorie, String> idTitre;
    @FXML
    private TableColumn<Categorie, String> imgCat;
    @FXML
    private Button btnAfficherCat;
    @FXML
    private Button btnModifierCat;
    @FXML
    private Button btnSupprimerCat;
    @FXML
    private TextField txtrechercher;

    BufferedImage bufferedImage;
    private String path;

    private ObservableList<Categorie> dateListe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    protected void AjouterImgCat(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        filechooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        filechooser.setTitle("Open File Dialog");

        Stage satge = (Stage) anchorpane.getScene().getWindow();

        File file = filechooser.showOpenDialog(satge);

        if (file != null) {

            try {
                bufferedImage = ImageIO.read(file);
                //filePath = file.toURI().toURL().toString();
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                VImage.setImage(image);
                String name = file.getName();
                //changer path 
                path = "Image\\" + name;
//                File outputfile = new File(path);
//                //save 
//                ImageIO.write(bufferedImage,"png" , outputfile);

            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

//    @FXML
//    private void AjouterCategorie(ActionEvent event) throws IOException {
//        Categorie c = new Categorie();
//        CategorieCRUD pc = new CategorieCRUD();
//        if (txtTitre.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText(" vérifier vos informations ");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Succées");
//            alert.setHeaderText(null);
//            alert.setContentText("L'ajout d'un new produit a été effectué avec succées");
//            alert.showAndWait();
//
//            String titre = txtTitre.getText();
//            String image = path;
//            File outputfile = new File(path);
//            //save 
//            ImageIO.write(bufferedImage, "png", outputfile);
//            //Categorie c = new Categorie(txtTitre.getText(), path);
//            c.setTitre(titre);
//            c.setImg_cat(image);
//            pc.ajouterCategorie(c);
//        }
//    }
    @FXML
    private void AfficherCategorie(ActionEvent event
    ) {
        CategorieCRUD rc = new CategorieCRUD();
        Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));

        // Display row data
        ObservableList<Categorie> list = rc.afficheCategorie();
        tableCat.setItems(list);
    }

    @FXML
    private void SuppeimerCategorie(ActionEvent event) {
        CategorieCRUD se = new CategorieCRUD();
        Categorie r = tableCat.getSelectionModel().getSelectedItem();
        r.setId_cat(1);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER CET CATEGORIE ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            se.supprimerCategorie(r);
            ObservableList<Categorie> liste = se.afficheCategorie();
            Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
            idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));
            tableCat.setItems(liste);

            JOptionPane.showMessageDialog(null, " CATEGORIE SUPPRIME ");
        } else {
            JOptionPane.showMessageDialog(null, " CATEGORIE NON SUPPRIME ");
        }

        Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));

        // Display row data
        ObservableList<Categorie> list = se.afficheCategorie();
        tableCat.setItems(list);
    }

    private void rechercherCategorie(ActionEvent event
    ) {
        CategorieCRUD rs = new CategorieCRUD();

        ObservableList<Categorie> liste = rs.rechercheCategorie(txtrechercher.getText());
        Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));

        tableCat.setItems(liste);
    }

    @FXML
    private void ModifierCat(ActionEvent event) throws IOException {
        if (txtTitre.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'une new catégorie a été effectué avec succées");
            alert.showAndWait();

            CategorieCRUD sa = new CategorieCRUD();

            String titre = txtTitre.getText();
            String image = path;

            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);
            Categorie re = (Categorie) tableCat.getSelectionModel().getSelectedItem();
            re.setTitre(titre);
            re.setImg_cat(image);

            sa.modifierCategorie(re);
            ObservableList<Categorie> liste = sa.afficheCategorie();
            Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
            idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));
            tableCat.setItems(liste);
        }
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) throws IOException {
        Categorie c = new Categorie();
        CategorieCRUD pc = new CategorieCRUD();
        if (txtTitre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'une new catégorie a été effectué avec succées");
            alert.showAndWait();

            String titre = txtTitre.getText();
            String image = path;
            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);
            //Categorie c = new Categorie(txtTitre.getText(), path);
            c.setTitre(titre);
            c.setImg_cat(image);
            pc.ajouterCategorie(c);
            Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
            idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));

            // Display row data
            ObservableList<Categorie> list = pc.afficheCategorie();
            tableCat.setItems(list);
        }

    }

    @FXML
    private void RechercheAvancee(ActionEvent event) {

        CategorieCRUD service = new CategorieCRUD();
        Categorie r = new Categorie();

        Idcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        idTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        imgCat.setCellValueFactory(new PropertyValueFactory<>("img_cat"));

        ObservableList<Categorie> dateListe = service.afficheCategorie();
        tableCat.setItems(dateListe);

        FilteredList<Categorie> filteredData;
        filteredData = new FilteredList<>(dateListe, b -> true);
        txtrechercher.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Categorie -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Categorie.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });

        }));
        SortedList<Categorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCat.comparatorProperty());
        tableCat.setItems(sortedData);

    }
}
