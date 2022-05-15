/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI.UserProfile;

import sahti.GUI.Login.LoginController;
import sahti.GUI.Signup.SignupClientController;
import sahti.entities.Client;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sahti.services.AccountModif;
import sahti.services.Login;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class UserProfileController implements Initializable {

    @FXML
    private Button idupdateusername;
    @FXML
    private Button idupdatepwd;
    private Button idlogout;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idprenom;
    @FXML
    private PasswordField idnewpwd;
    @FXML
    private PasswordField idconfirmpwd;
    @FXML
    private PasswordField idoldpwd;

    private Label idusernamelabel;
    @FXML
    private TextArea idbio;

    private boolean userclient = false;
    private boolean usercoach = false;
    private boolean usernutr = false;
    private boolean admin = false;
    private ImageView idpicprofil;
    @FXML
    private Button idupload;
    @FXML
    private ImageView iduploadpic;
    private File fileimg;
    @FXML
    private Text idlabelpic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AccountModif a = new AccountModif();

        Login l = new Login();

        if (l.loginClient(Login.emailUser, Login.pwduser) == true) {
            idbio.setVisible(false);
            userclient = true;
        }
        else if (l.loginEntraineur(Login.emailUser, Login.pwduser)) {
            idbio.setVisible(true);
            usercoach = true;
        }
        else if (l.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            idbio.setVisible(true);
            usernutr = true;
        }
        else if (l.loginAdmin(Login.emailUser, Login.pwduser)) { 
            idbio.setVisible(false);  
            admin = true;
        }
    }

    @FXML
    private void uploadbtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        ImageView myImageView = new ImageView();

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            myImageView.setImage(image);
            iduploadpic.setImage(image);
            //adding file to a variable 
            fileimg = file;
        }
    }

    @FXML
    private void updateusername(ActionEvent event) {

        String newnom = idnom.getText();
        String newprenom = idprenom.getText();
        String bio = idbio.getText();

        if (!newnom.isEmpty() & !newprenom.isEmpty()) {
            AccountModif a = new AccountModif();
            Login l = new Login();
            if (userclient == true) {
                a.ClientModifUserName(newnom, newprenom, fileimg.getName(), Login.emailUser);

                //*****storing img once again*****
                if (fileimg != null) {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(fileimg);
                        //changer path 
                        File outputfile = new File("C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Client\\" + fileimg.getName());
                        //save 
                        ImageIO.write(bufferedImage, "jpg", outputfile);
                    }
                    catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                //************
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Your information  updated successfully !");
                alert.showAndWait();

                //refreshing update
//                String username = a.searchclientbyemail(Login.emailUser);
//                idusernamelabel.setText(username);
//                idusernamelabel.setMaxWidth(Double.MAX_VALUE);
//                idusernamelabel.setAlignment(Pos.CENTER_RIGHT);
//
//                Image image = new Image("file:" + "C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Client\\" + a.searchImgClient(Login.emailUser));
//                idpicprofil.setImage(image);
                //*****************
                idnom.setText("");
                idprenom.setText("");
                iduploadpic.setImage(null);

            }
            else if (usercoach == true) {
                a.EntraineurModifUserName(newnom, newprenom, bio, fileimg.getName(), Login.emailUser);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Your information  updated successfully !");
                alert.showAndWait();
                //*****storing img once again*****
                if (fileimg != null) {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(fileimg);
                        //changer path 
                        File outputfile = new File("C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Coach\\" + fileimg.getName());
                        //save 
                        ImageIO.write(bufferedImage, "jpg", outputfile);
                    }
                    catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                //******refrshing updates***
                String username = a.searchcoachbyemail(Login.emailUser);
                idusernamelabel.setText(username);

                idusernamelabel.setMaxWidth(Double.MAX_VALUE);
                idusernamelabel.setAlignment(Pos.CENTER_RIGHT);

                ImageView myImageView = new ImageView();
                Image image = new Image("file:" + "C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Coach\\" + a.searchImgCoach(Login.emailUser));
                idpicprofil.setImage(image);
                //************
                idnom.setText("");
                idprenom.setText("");
                idbio.setText("");
                iduploadpic.setImage(null);
            }
            else if (usernutr == true) {
                a.NutritionisteModifUserName(newnom, newprenom, bio, fileimg.getName(), Login.emailUser);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Your information  updated successfully !");
                alert.showAndWait();
                //*****storing img once again*****
                if (fileimg != null) {
                    try {
                        BufferedImage bufferedImage = ImageIO.read(fileimg);
                        //changer path 
                        File outputfile = new File("C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Nutri\\" + fileimg.getName());
                        //save 
                        ImageIO.write(bufferedImage, "jpg", outputfile);
                    }
                    catch (IOException ex) {
                        ex.getMessage();
                    }
                }
                //************
                
                //*************
//                String username = a.searchnutritionistebyemail(Login.emailUser);
//                idusernamelabel.setText(username);
//
//                idusernamelabel.setMaxWidth(Double.MAX_VALUE);
//                idusernamelabel.setAlignment(Pos.CENTER_RIGHT);
//
//                Image image = new Image("file:" + "C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Nutri\\" + a.searchImgNutri(Login.emailUser));
//                idpicprofil.setImage(image);
                //**************
                idnom.setText("");
                idprenom.setText("");
                idbio.setText("");
                iduploadpic.setImage(null);

            }
            else if (admin == true) {
                a.AdminModifUserName(newnom, newprenom, Login.emailUser);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succes ");
                alert.setHeaderText("Your information  updated successfully !");
                alert.showAndWait();

                idnom.setText("");
                idprenom.setText("");
                idbio.setText("");
                iduploadpic.setImage(null);

            }

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Somthing ain't right please try again  ");
            alert.showAndWait();
        }

    }

    @FXML
    private void updatepwd(ActionEvent event) {
        String oldpwd = idoldpwd.getText();
        String newpwd = idnewpwd.getText();
        String confirmpwd = idconfirmpwd.getText();
        AccountModif a = new AccountModif();
        boolean eq = newpwd.equals(confirmpwd);

        if (oldpwd.isEmpty() & newpwd.isEmpty() & confirmpwd.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Fill your form to continue ! ");
            alert.showAndWait();
        }
        else if (newpwd.length() < 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Password Must be more than 8 character  ");
            alert.showAndWait();
        }
        else if (eq == false) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Confirm password must match the new password  ");
            alert.showAndWait();
        }
        else {
            if (userclient == true) {
                a.ClientModifPwd(newpwd, oldpwd, Login.emailUser);
                idoldpwd.setText("");
                idnewpwd.setText("");
                idconfirmpwd.setText("");
            }
            else if (usercoach == true) {
                a.CoachModifPwd(newpwd, oldpwd, Login.emailUser);
                idoldpwd.setText("");
                idnewpwd.setText("");
                idconfirmpwd.setText("");
            }
            else if (usernutr == true) {
                a.NutriModifPwd(newpwd, oldpwd, Login.emailUser);
                idoldpwd.setText("");
                idnewpwd.setText("");
                idconfirmpwd.setText("");
            }
            else if (admin == true) {
                a.AdminModifPwd(newpwd, oldpwd, Login.emailUser);
                idoldpwd.setText("");
                idnewpwd.setText("");
                idconfirmpwd.setText("");
            }
        }

    }

    private void Logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));

        Parent root;
        Stage stage;
        Parent root1;
        Stage stage1;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logging out ");
        alert.setHeaderText("You have logged out see you next time ");
        alert.showAndWait();

        try {
            root = loader.load();
            stage = (Stage) idlogout.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
