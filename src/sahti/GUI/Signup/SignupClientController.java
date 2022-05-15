/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI.Signup;

import MailingAPI.Mailer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import sahti.entities.Client;
import sahti.services.Signup;

/**
 * FXML Controller class
 *
 * @author Rayen
 */
public class SignupClientController implements Initializable {

    @FXML
    private Button idbtnsignupC;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idEmail;
    @FXML
    private TextField idprenom;
    @FXML
    private TextField idaddress;
    @FXML
    private DatePicker idDate;
    @FXML
    private PasswordField idPassword;
    @FXML
    private PasswordField idConfirmPasswd;
    @FXML
    private Hyperlink idgotologin;
    @FXML
    private ImageView idimgview;
    @FXML
    private Button idupload;

    private String filepath;
    private File fileimg;

    Random r = new Random();
    static int nb_valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Uploadpic(ActionEvent event) {
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
            idimgview.setImage(image);
            //adding file to a variable 
            fileimg = file;
        }
    }

    public int randomInt() {
        int min = 50;
        int max = 100000000;

        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

        System.out.println(random_int);
        return random_int;
    }

    @FXML
    private void SignupC(ActionEvent event) {

        String nom = idnom.getText();
        String prenom = idprenom.getText();
        String addr = idaddress.getText();
        LocalDate date = idDate.getValue();
        String mail = idEmail.getText();
        String pwd = idPassword.getText();
        String confrimpwd = idConfirmPasswd.getText();
        Image imgp = idimgview.getImage();

        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(mail);
        boolean eq = pwd.equals(confrimpwd);
        try {
            if ((nom.isEmpty()) & (prenom.isEmpty()) & (addr.isEmpty()) & (date.toString().isEmpty()) & (mail.isEmpty()) & (pwd.isEmpty()) & (imgp.toString().isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fill your the form to continue !", ButtonType.CLOSE);
                alert.show();
            }
            else if (!mat.matches()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Email format wrong", ButtonType.CLOSE);
                alert.show();
            }
            else if (pwd.length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password must be more than 8 character", ButtonType.CLOSE);
                alert.show();
            }
            else if (eq == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Password mismatch", ButtonType.CLOSE);
                alert.show();
            }
            else {
                String file = fileimg.getName();
                Client c = new Client(nom, prenom, mail, pwd, addr, date.toString(), file);
                Signup s = new Signup();

                nb_valider = r.nextInt(10000);
                Mailer.mailingValider(mail, nb_valider);
                JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
                String txt_CodeConfirmation = jop.showInputDialog(null, "Please fil the textbox below with the verification code sent to your email !", "Email Verification", JOptionPane.QUESTION_MESSAGE);

                if (Integer.parseInt(txt_CodeConfirmation) == nb_valider) {

                    if (s.signupClient(c) == true) {

                        if (fileimg != null) {
                            try {
                                BufferedImage bufferedImage = ImageIO.read(fileimg);
                                //changer path 
                                File outputfile = new File("C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\sahti\\src\\sahti\\ProfileIMG\\Client\\" + file);
                                //save 
                                ImageIO.write(bufferedImage, "jpg", outputfile);
                            }
                            catch (IOException ex) {
                                ex.getMessage();
                            }
                        }
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Succes ");
                        alert.setHeaderText("Welcome home " + prenom + nom);
                        alert.showAndWait();

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error ");
                        alert.setHeaderText("Email already exist !");
                        alert.showAndWait();
                    }
                    //notification
                    Notifications notificationBuilder = Notifications.create()
                            .title("Notification").text("You're ready to login").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                            .position(Pos.BOTTOM_RIGHT)
                            .onAction((ActionEvent event1) -> {
                                System.out.println("You're ready to login ");
                            });
                    notificationBuilder.darkStyle();
                    notificationBuilder.show();
                    //redirect to login
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));
                    Parent root;
                    Stage stage;
                    try {
                        root = loader.load();
                        stage = (Stage) idgotologin.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Code incorrect", ButtonType.CLOSE);
                    alert.show();

                }
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error cannot signup ", ButtonType.CLOSE);
            alert.show();
        }

    }

    @FXML
    private void GotoLogin(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Login/Login.fxml"));

        Parent root;
        Stage stage;
        try {
            root = loader.load();
            stage = (Stage) idgotologin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
