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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import sahti.entities.Produit;
import sahti.entities.Promotion;
import sahti.services.ProduitCRUD;
import sahti.services.PromotionCRUD;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterPromotionController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField txtPorc;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Button btnImg;
    @FXML
    private ImageView imgV;

    @FXML
    private Button btnAjouPro;
    @FXML
    private TextField txtRech;
    @FXML
    private Button btnAffi;
    @FXML
    private Button btnMod;
    @FXML
    private TextField txtTitre;

    private String path;
    BufferedImage bufferedImage;
    @FXML
    private TextField txtidpro;
    @FXML
    private TableView<Promotion> tablePro;
    @FXML
    private TableColumn<Promotion, String> titre;
    @FXML
    private TableColumn<Promotion, Double> Proc;
    @FXML
    private TableColumn<Promotion, Double> oldprix;
    @FXML
    private TableColumn<Promotion, String> img;
    @FXML
    private TableColumn<Promotion, String> desc;
    @FXML
    private TableColumn<Promotion, Integer> idpro;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterImg(ActionEvent event) {
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
                imgV.setImage(image);
                String name = file.getName();
                //changer path 
                path = "C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\Image\\" + name;
//                File outputfile = new File(path);
//                //save 
//                ImageIO.write(bufferedImage,"png" , outputfile);

            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void RechercherPromotion(ActionEvent event) {
        PromotionCRUD rs = new PromotionCRUD();
        ObservableList<Promotion> liste = rs.recherchePromotion(txtRech.getText());

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
        oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
        img.setCellValueFactory(new PropertyValueFactory<>("image"));
        desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
        idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));

        tablePro.setItems(liste);
    }

    @FXML
    private void AjouterPeromotion(ActionEvent event) throws IOException {
        if (txtTitre.getText().trim().isEmpty() || txtPorc.getText().trim().isEmpty()
                || txtDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'un new event a été effectué avec succées");
            alert.showAndWait();

            int idprod = Integer.valueOf(txtidpro.getText());
            String titre = txtTitre.getText();
            double proc = Double.valueOf(txtPorc.getText());
            String Desc = txtDesc.getText();
            //String image = filePath;
            String image = path;
            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);

            ProduitCRUD cp = new ProduitCRUD();
            Produit re = cp.FindProd(idprod);
            double newprix = cp.Reduction(proc, re);
            cp.modifierPrixProduit(re, newprix);
            double oldprix = re.getPrix();

            Promotion p = new Promotion(titre, proc, oldprix, image, Desc, idprod);
            PromotionCRUD pc = new PromotionCRUD();
            pc.ajouterPromotion(p);
        }
    }

    @FXML

    private void AfficherPromotion(ActionEvent event) {

        PromotionCRUD rc = new PromotionCRUD();

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
        oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
        img.setCellValueFactory(new PropertyValueFactory<>("image"));
        desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
        idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        // Display row data
        ObservableList<Promotion> list = rc.affichePromotion();
        tablePro.setItems(list);
    }

    @FXML
    private void ModifierPromotion(ActionEvent event) throws IOException {

        if (txtTitre.getText().trim().isEmpty() || txtPorc.getText().trim().isEmpty()
                || txtDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'un new event a été effectué avec succées");
            alert.showAndWait();
            PromotionCRUD sa = new PromotionCRUD();

            String title = txtTitre.getText();
            double proc = Double.valueOf(txtPorc.getText());
            String Desc = txtDesc.getText();
            String image = path;

            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);
            Promotion re = (Promotion) tablePro.getSelectionModel().getSelectedItem();
            re.setTitre(title);
            re.setPorcentage(proc);
            re.setImage(image);
            re.setDescProm(Desc);
            sa.modifierPromotion(re);
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
            oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
            img.setCellValueFactory(new PropertyValueFactory<>("image"));
            desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
            idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
            // Display row data
            ObservableList<Promotion> list = sa.affichePromotion();
            tablePro.setItems(list);

        }
    }

//    private void SupprimerPromotion(ActionEvent event
//    ) {
//        Produit re = new Produit();
//        PromotionCRUD se = new PromotionCRUD();
//        ProduitCRUD cp = new ProduitCRUD();
//        Promotion p = tablePro.getSelectionModel().getSelectedItem();
//        p.setId_prom(1);
//        p.setTitre(p.getTitre());
//        p.setId_prod(p.getId_prod());
//        p.setAncienPrix(p.getAncienPrix());
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("ALERT suppression");
//        alert.setHeaderText(null);
//        alert.setContentText(" VOULEZ VOUS SUPPRIMER CET PROMOTION?");
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
//
//            re = cp.FindProd(p.getId_prod());
//            cp.modifierPrixProduit(re, p.getAncienPrix());
//            se.supprimerPromotion(p);
//            ObservableList<Promotion> liste = se.affichePromotion();
//
//            titre.setCellFactory(new PropertyValueFactory<>("titre"));
//            Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
//            oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
//            img.setCellValueFactory(new PropertyValueFactory<>("image"));
//            desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
//            idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
//            tablePro.setItems(liste);
//
//            JOptionPane.showMessageDialog(null, " PRODUIT SUPPRIME ");
//        } else {
//            JOptionPane.showMessageDialog(null, " PRODUIT NON SUPPRIME ");
//        }
//        se.affichePromotion();
//    }
    public void setIdProd(int idp) {
        this.txtidpro.setText(String.valueOf(idp));
    }

    @FXML
    private void AnnulerPromotion(ActionEvent event) {
        Produit re = new Produit();
        PromotionCRUD se = new PromotionCRUD();
        ProduitCRUD cp = new ProduitCRUD();
        Promotion p = tablePro.getSelectionModel().getSelectedItem();
        p.setId_prom(1);
        p.setTitre(p.getTitre());
        p.setId_prod(p.getId_prod());
        p.setAncienPrix(p.getAncienPrix());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER CET PROMOTION?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            re = cp.FindProd(p.getId_prod());
            cp.modifierPrixProduit(re, p.getAncienPrix());
            se.supprimerPromotion(p);
            titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
            oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
            img.setCellValueFactory(new PropertyValueFactory<>("image"));
            desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
            idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
            // Display row data
            ObservableList<Promotion> list = se.affichePromotion();
            tablePro.setItems(list);

            JOptionPane.showMessageDialog(null, " PRODUIT SUPPRIME ");
        } else {
            JOptionPane.showMessageDialog(null, " PRODUIT NON SUPPRIME ");
        }

    }

    @FXML
    private void sendmail(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("EnvoyerMail.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RechercheAvance(ActionEvent event) {
        PromotionCRUD service = new PromotionCRUD();
        Promotion r = new Promotion();

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Proc.setCellValueFactory(new PropertyValueFactory<>("porcentage"));
        oldprix.setCellValueFactory(new PropertyValueFactory<>("ancienPrix"));
        img.setCellValueFactory(new PropertyValueFactory<>("image"));
        desc.setCellValueFactory(new PropertyValueFactory<>("descProm"));
        idpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));

        ObservableList<Promotion> dateListe = service.affichePromotion();
        tablePro.setItems(dateListe);

        FilteredList<Promotion> filteredData;
        filteredData = new FilteredList<>(dateListe, b -> true);
        txtRech.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Promotion -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Promotion.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });

        }));
        SortedList<Promotion> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablePro.comparatorProperty());
        tablePro.setItems(sortedData);

    }
}
