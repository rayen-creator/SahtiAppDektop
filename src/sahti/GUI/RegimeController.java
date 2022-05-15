/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import MailingAPI.Mailer;
import sahti.entities.Regime;
import sahti.entities.Repas;
import sahti.entities.reg_repas;
import sahti.services.RegimeCRUD;
import sahti.services.RepasCRUD;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

import sahti.services.AccountModif;
import sahti.services.Login;
import sahti.services.MailJava;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegimeController implements Initializable {

    private Label label;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfregime;
    @FXML
    private TextField tfduree;
    @FXML
    private TextField tfcalorie;
    @FXML
    private TextField tfrepas;
    @FXML
    private TextField tfspec;
    @FXML
    private Button bntajouter;
    @FXML
    private RadioButton perd;
    @FXML
    private RadioButton gagner;
    @FXML
    private RadioButton maint;
    String radioButtonLabel;

    @FXML
    private Button btnupload;
    @FXML
    private TextArea photo_p;
    @FXML
    private TableView<Regime> tbv;
    @FXML
    private TableColumn<Regime, Integer> reg;
    @FXML
    private TableColumn<Regime, String> obj;
    @FXML
    private TableColumn<Regime, String> dd;
    @FXML
    private TableColumn<Regime, Integer> d;
    @FXML
    private TableColumn<Regime, Integer> c;
    @FXML
    private TableColumn<Regime, Integer> nr;
    @FXML
    private TableColumn<Regime, Integer> spec;
    @FXML
    private Button gsupp;
    @FXML
    private Button btnmodifier;

    @FXML
    private TableView<Repas> tab_rep;
    @FXML
    private TableColumn<Repas, Integer> id_rep;
    @FXML
    private TableColumn<Repas, String> nom_rep;
    @FXML
    private TableColumn<Repas, Integer> nbre_cal;
    @FXML
    private TableColumn<Repas, Integer> quant;
    @FXML
    private Button btn_Aff;
    private int id_repas;
    private int id_regime;
    @FXML
    private ComboBox<Integer> gComboBox;
    Connection cnx;
    ObservableList options = FXCollections.observableArrayList();
    @FXML
    private Button BtnStat;
    @FXML
    private ImageView photo_view;
    @FXML
    private TableColumn<Regime, String> im;
    @FXML
    private Button export;
    @FXML
    private ComboBox<String> boxEmail;

    private List<String> emails = new ArrayList<>();
    @FXML
    private VBox idvboxleft;
    @FXML
    private VBox idvboxright;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MailJava m = new MailJava();
        emails = m.ListEmail();
        boxEmail.getItems().addAll(emails);
        //TODO
        afficherRegime();
        afficherRepas();
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
            options = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_regime FROM regime");
            while (rs.next()) {
                //get string from db,whichever way 
                options.add(new Regime(rs.getInt("id_regime")).getId_regime());
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        gComboBox.setItems(options);

        //hide menu 
        Login l = new Login();
        if (l.loginClient(Login.emailUser, Login.pwduser)) {
            idvboxleft.setVisible(false);
            idvboxright.setVisible(false);
            bntajouter.setVisible(false);
            btnmodifier.setVisible(false);
            gsupp.setVisible(false);
        }

    }

    public void afficherRegime() {
        RegimeCRUD sr = new RegimeCRUD();
        ObservableList<Regime> list = sr.getRegimesList();
        reg.setCellValueFactory(new PropertyValueFactory<Regime, Integer>("id_regime"));
        obj.setCellValueFactory(new PropertyValueFactory<Regime, String>("objectif"));
        dd.setCellValueFactory(new PropertyValueFactory<Regime, String>("date_debut"));
        d.setCellValueFactory(new PropertyValueFactory<Regime, Integer>("duree"));
        c.setCellValueFactory(new PropertyValueFactory<Regime, Integer>("max_calories"));
        nr.setCellValueFactory(new PropertyValueFactory<Regime, Integer>("nb_repas"));
        spec.setCellValueFactory(new PropertyValueFactory<Regime, Integer>("id_specialiste"));
        im.setCellValueFactory(new PropertyValueFactory<Regime, String>("image"));
        tbv.setItems(list);

    }

    public void afficherRepas() {
        RepasCRUD sr = new RepasCRUD();
        ObservableList<Repas> list = sr.getRepasList();
        id_rep.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("id_repas"));
        nom_rep.setCellValueFactory(new PropertyValueFactory<Repas, String>("nom_rep"));
        nbre_cal.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("nb_cal"));
        quant.setCellValueFactory(new PropertyValueFactory<Repas, Integer>("quantite"));
        tab_rep.setItems(list);

    }

    @FXML
    private void AjouterRegime(ActionEvent event) throws AddressException, MessagingException {
        String tilte;
        String message;
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        if (verifUserChamps()) {
            (new Alert(Alert.AlertType.CONFIRMATION, "Regime Added Successfully", new ButtonType[]{ButtonType.OK})).show();
            tilte = "Added Successful";
            message = "Customer Is Added";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            RegimeCRUD sr = new RegimeCRUD();
            Regime r = new Regime();
            AccountModif am = new AccountModif();
            int idclient = am.searchIDCoach(boxEmail.getValue());
            r.setId_client(idclient);
            r.setObjectif(radioButtonLabel);
            r.setDate_debut(tfdate.getEditor().getText());
            r.setId_regime(Integer.parseInt(tfregime.getText()));
            r.setDuree(Integer.parseInt(tfduree.getText()));
            r.setMax_calories(Integer.parseInt(tfcalorie.getText()));
            r.setNb_repas(Integer.parseInt(tfrepas.getText()));
            r.setId_specialiste(Integer.parseInt(tfspec.getText()));
            r.setImage(photo_p.getText().replace('\\', '/'));

            sr.AjouterRegime(r);
            afficherRegime();
            refrech();
            JOptionPane.showMessageDialog(null, "Regime ajouté");
            final String username = "noreplysahti@gmail.com";
            final String password = "rwtlwppcxycwhwfa";

            Properties props = new Properties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(username, password);
                }
            });
//            try {
//                Statement stmp = cnx.createStatement();
//                String query = "SELECT m.email from nutritionniste m,regime r where r.id_specialiste= m.id  and r.id_specialiste=?";
//                //String req2 = "SELECT `email` from client  where id='" + r.getId_specialiste() + "'";
//                ResultSet resultat = stmp.executeQuery(query);
//                while (resultat.next()) {
//                    String to = resultat.getString("email");
//
//                    try {
//                        Message msg = new MimeMessage(session);
//                        msg.setFrom(new InternetAddress("sahti"));
//                        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//                        msg.setSubject("Email De Confirmation");
//                        msg.setText("\n" + "*******Bienvenue  votre regime a été activé avec succes merci pour votre confiance *******");
//                        Transport.send(msg);
//                        JOptionPane.showMessageDialog(null, "Email sent successfully!");
//                    } catch (MessagingException ex) {
//                        JOptionPane.showMessageDialog(null, "Oops something went wrong" + "\n" + ex.getMessage());
//                    }
//                }
//
//            } catch (SQLException ex) {
//                  //Logger.getLogger(Specialiste.class.getName()).log(Level.SEVERE, null, ex);
//            }
            MailJava m = new MailJava();

            List<String> emails = m.ListEmail();
            for (int i = 0; i < emails.size(); i++) {
                MailJava.send("noreplysahti@gmail.com", "rwtlwppcxycwhwfa", emails.get(i), "Notification regime ", "votre regime a été affecté avec succes !");

            }

//            try {
//                String query = "SELECT m.email from nutritionniste m,regime r where r.id_specialiste= m.id  and r.id_specialiste=?";
//
//                PreparedStatement pst = cnx.prepareStatement(query);
//                pst.setInt(1, r.getId_specialiste());
//                ResultSet resultat = pst.executeQuery();
//                if (resultat.next()) {
//                    String mail = resultat.getString("email");
//                    if (!mail.isEmpty()) {
//                        Mailer.send("noreplysahti@gmail.com", "rwtlwppcxycwhwfa", mail, "tiitre", "contenue");
//                    } else {
//                        System.out.println("ERROR");
//                    }
//                } else {
//                    System.out.println("error");
//                }

        }
    }


    @FXML
    private void supprimerRegime(ActionEvent event) {

        RegimeCRUD se = new RegimeCRUD();
        Regime r = new Regime();
        int id = 0;
        id = Integer.parseInt(tfregime.getText());
        r.setId_regime(id);

        if (event.getSource() == gsupp) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ALERT suppression");
            alert.setHeaderText(null);
            alert.setContentText(" VOULEZ VOUS SUPPRIMER LE REGIME?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                se.SupprimerRegime(r);

                JOptionPane.showMessageDialog(null, " Regime supprimer ");
            } else {
                JOptionPane.showMessageDialog(null, " Regime non Supprimer ");
            }

            tfregime.setText("");
            gagner.setSelected(false);
            perd.setSelected(false);
            maint.setSelected(false);
            tfdate.setUserData("");
            tfduree.setText("");
            tfrepas.setText("");
            tfcalorie.setText("");
            tfspec.setText("");
            afficherRegime();
            refrech();

        }
    }

    @FXML
    private void ModifierRegime(ActionEvent event) {

        Notifications notificationBuilder = Notifications.create()
                .title("Modification de Votre Régime")
                .text("Votre regime a été modifié avec succes")
                .graphic(null)
                .position(Pos.CENTER)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Clicked on notifictaion");
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        RegimeCRUD sr = new RegimeCRUD();
        Regime re = new Regime();

        int id = 0;
        id = Integer.parseInt(tfregime.getText());
        re.setDate_debut(tfdate.getEditor().getText());
        re.setId_regime(id);
        int duree;
        duree = Integer.parseInt(tfduree.getText());
        re.setDuree(duree);
        int idspec;
        idspec = Integer.parseInt(tfspec.getText());
        re.setId_specialiste(idspec);
        re.setMax_calories(Integer.parseInt(tfcalorie.getText()));
        re.setNb_repas(Integer.parseInt(tfrepas.getText()));
        re.setObjectif(radioButtonLabel);

        sr.ModifierRegime(re);
        afficherRegime();
        refrech();

    }

    @FXML
    private void UPLOADiMAGE(ActionEvent event) {
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

    private void refrech() {
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";
        try {
            cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
            options = FXCollections.observableArrayList();

            ResultSet rs = cnx.createStatement().executeQuery("SELECT id_regime FROM regime");
            while (rs.next()) {

                options.add(new Regime(rs.getInt("id_regime")).getId_regime());
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        perd.setSelected(false);
        gagner.setSelected(false);
        maint.setSelected(false);
        gComboBox.setItems(null);
        gComboBox.setItems(options);
    }

    @FXML
    private void bouton_Aff(ActionEvent event) {
        System.out.println("");
        reg_repas re = new reg_repas();
        RepasCRUD sr = new RepasCRUD();
        re.setId_regime(id_regime);
        re.setId_repas(id_repas);

        System.out.println(re);
        sr.ajouterREGIME(re);
        JOptionPane.showMessageDialog(null, "Repas ajoutée !");

    }

    @FXML
    private void StatAction(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("STAT.fxml"));/*
         * Exception
         */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Repas.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

    @FXML
    private void PerdrePoids(ActionEvent event) {
        radioButtonLabel = perd.getText();

    }

    @FXML
    private void GagnerPoids(ActionEvent event) {
        radioButtonLabel = gagner.getText();
    }

    @FXML
    private void MaintenirPoids(ActionEvent event) {
        radioButtonLabel = maint.getText();

    }

    public boolean verifUserChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";
        String styledefault = "-fx-border-color: green;";

        perd.setStyle(styledefault);
        gagner.setStyle(styledefault);
        maint.setStyle(styledefault);
        tfregime.setStyle(styledefault);
        tfdate.setStyle(styledefault);
        tfduree.setStyle(styledefault);
        tfrepas.setStyle(styledefault);
        tfcalorie.setStyle(styledefault);
        tfspec.setStyle(styledefault);

        if (perd.equals("")) {
            perd.setStyle(style);
            verif = 1;
        }
        if (gagner.equals("")) {
            gagner.setStyle(style);
            verif = 1;
        }
        if (maint.equals("")) {
            maint.setStyle(style);
            verif = 1;
        }
        if (tfregime.getText().equals("")) {
            tfregime.setStyle(style);
            verif = 1;
        }

        if (tfduree.getText().equals("")) {
            tfduree.setStyle(style);
            verif = 1;
        }

        if (tfspec.getText().equals("")) {
            tfspec.setStyle(style);
            verif = 1;
        }
        if (tfrepas.getText().equals("")) {
            tfrepas.setStyle(style);
            verif = 1;
        }
        if (tfcalorie.getText().equals("")) {
            tfcalorie.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Verfier votre champs!");
        return false;
    }

    @FXML
    private void HandleMouseAction(MouseEvent event) {

        Regime r = tbv.getSelectionModel().getSelectedItem();
        id_regime = r.getId_regime();
        String idregime = Integer.toString(r.getId_regime());
        tfregime.setText(idregime);
        tfdate.getEditor().setText(r.getDate_debut());
        String duree = Integer.toString(r.getDuree());
        tfduree.setText(duree);
        String calories = Integer.toString(r.getMax_calories());
        tfcalorie.setText(calories);
        String repas = Integer.toString(r.getNb_repas());
        tfrepas.setText(repas);
        String idspec = Integer.toString(r.getId_specialiste());
        tfspec.setText(idspec);
        File file = new File(r.getImage());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            photo_view.setImage(image);
        } catch (IOException ex) {

        }
        if (null == r.getObjectif()) {
            perd.setSelected(false);
            gagner.setSelected(false);
            maint.setSelected(false);
        } else {
            switch (r.getObjectif()) {
                case "Perdre du poids":
                    perd.setSelected(true);
                    break;
                case "Gagner du poids":
                    gagner.setSelected(true);
                    break;
                case "Maintenir le poids":
                    maint.setSelected(true);
                    break;
                default:
                    perd.setSelected(false);
                    gagner.setSelected(false);
                    maint.setSelected(false);
                    break;
            }
        }

    }

    @FXML
    private void HandleMouseAction2(MouseEvent event) {
        Repas r = tab_rep.getSelectionModel().getSelectedItem();
        id_repas = r.getId_repas();
    }

    private void retour(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MenuGestion.fxml"));/*
         * Exception
         */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
        PrinterAttributes attr = printer.getPrinterAttributes();
        PrinterJob job = PrinterJob.createPrinterJob();
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);

        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(pageLayout, node);
            if (success) {
                job.endJob();

            }
        }
        node.getTransforms().remove(scale);

    }

    @FXML
    private void printevent(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(tbv);
    }

    void pdf() {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tbv;
            job.printPage(root);
            job.endJob();

        }
    }

    @FXML
    private void IMC(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BMICalculator.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }
}
