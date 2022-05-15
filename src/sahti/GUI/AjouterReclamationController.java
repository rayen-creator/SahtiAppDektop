/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sahti.entities.Reclamation;
import sahti.services.ReclamationCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField txtTitre;
    @FXML
    private TextArea txtMessage;
    @FXML
    private ComboBox<String> cmbType;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnAnnuler;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnPiece;
    public String filename="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> data = FXCollections.observableArrayList(); 
        data.addAll("Client", "Entraineur","Nutritionniste", "Systeme");
        cmbType.itemsProperty().setValue(data);
    }    

    @FXML
    private void addReclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champ obligatoire");
        // Header Text: null
        alert.setHeaderText(null);
        
        String titre = txtTitre.getText();
        String message = txtMessage.getText();
        String type = cmbType.getValue();
        if (titre.isEmpty()) {
            alert.setContentText("Le champ titre ne doit pas être vide!");
            alert.showAndWait();        
        }else
            if (message.isEmpty()){
                alert.setContentText("Le champ message ne doit pas être vide!");
                alert.showAndWait();
            }else
                if(type == null){
                    alert.setContentText("Le champ type ne doit pas être vide!");
                    alert.showAndWait();
                }
        Reclamation r = new Reclamation(titre, "Client: \n"+message, type, filename);
        ReclamationCRUD rc = new ReclamationCRUD();
        
         
        
                                
        
        try {
            rc.ajouterReclamation(r);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamations.fxml"));
        try {
            Stage primaryStage = new Stage();
            Parent root;

            root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Liste des réclamations");
            primaryStage.setScene(scene);
            primaryStage.show();

            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) {
        byte[] reclamation_image = null;
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {                                    
            BufferedImage bufferedImage = ImageIO.read(file);
            
            filename = file.getName();
            String path = "C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\applicationSahti-Finalmain\\Image\\" + filename;
             File outputfile = new File(path);
                //save
            ImageIO.write(bufferedImage,"png" , outputfile);
            
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgView.setImage(image);
            imgView.setFitWidth(200);
            imgView.setFitHeight(200);
            imgView.scaleXProperty();
            imgView.scaleYProperty();
            imgView.setSmooth(true);
            imgView.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            reclamation_image = bos.toByteArray();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
}
