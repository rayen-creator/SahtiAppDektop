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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AffAlimentController implements Initializable {

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
      Connection cnx;
    Aliment al =new Aliment();
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private ImageView photo_view;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficherAliment();
    }    
  public void afficherAliment(){
     AlimentCRUD sr = new AlimentCRUD();
       ObservableList<Aliment> list  = sr.getAlimentList();
        alim.setCellValueFactory(new PropertyValueFactory<Aliment,Integer>("id_aliment"));
        n.setCellValueFactory(new PropertyValueFactory<Aliment,String>("nom"));
        ty.setCellValueFactory(new PropertyValueFactory<Aliment,String>("type"));
        ph.setCellValueFactory(new PropertyValueFactory<Aliment,String>("image"));
        ca.setCellValueFactory(new PropertyValueFactory<Aliment,Integer>("calories"));
        des.setCellValueFactory(new PropertyValueFactory<Aliment,String>("description"));
       
        
        tablev.setItems(list);   
        
        refrech();
        
    }
  private void refrech(){
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
  }
     @FXML
    private void HandleMouseAction1(MouseEvent event) {
        Aliment a = tablev.getSelectionModel().getSelectedItem();
        File file = new File(a.getImage());
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_view.setImage(image);
            } 
        catch (IOException ex) {
          
            }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAliment.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
 @FXML
    private void ListeRepas(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FrontRepas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}