/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import java.awt.print.PrinterJob;
import java.io.File;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.printing.PDFPageable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import sahti.entities.Produit;
import sahti.services.CategorieCRUD;
import sahti.services.ProduitCRUD;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private Button btnAjouterPro;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrix;
    @FXML
    private ChoiceBox<String> boxCat;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Button btnImage;
    @FXML
    private ImageView VImage;
    @FXML
    private AnchorPane anchorpane;

    private List<String> cat = new ArrayList<>();
    //private String filePath;
    private String path;
    private String img;
    BufferedImage bufferedImage;
    @FXML
    private TextField txtrecher;
    @FXML
    private TableView<Produit> tablePro;
    @FXML
    private Button btnafficher;
    @FXML
    private TableColumn<Produit, String> tbnom;
    @FXML
    private TableColumn<Produit, Double> tbprix;
    @FXML
    private TableColumn<Produit, String> tbimg;
    @FXML
    private TableColumn<Produit, String> tbdesc;
    @FXML
    private TableColumn<Produit, Integer> tbidcat;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnred;
    @FXML
    private TableColumn<Produit, Integer> tbquan;
    @FXML
    private TextField txtQuantite;
    @FXML
    private TableColumn<Produit, Integer> tbidpro;
    @FXML
    private Button btnImp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieCRUD c = new CategorieCRUD();
        cat = c.AllCatNames();
        boxCat.getItems().addAll(cat);

    }

    @FXML
    private void initialize(MouseEvent event) {

    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sahti", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    @FXML
    private void AjouterProduit(ActionEvent event) throws IOException {
        CategorieCRUD cp = new CategorieCRUD();
        ProduitCRUD pc = new ProduitCRUD();
        if (txtNom.getText().trim().isEmpty() || txtPrix.getText().trim().isEmpty()
                || boxCat.getValue().trim().isEmpty() || txtDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'un new produit a été effectué avec succées");
            alert.showAndWait();

            String nom = txtNom.getText();
            double prix = Double.parseDouble(txtPrix.getText());
            String catg = boxCat.getValue();
            int idcat = cp.rechercheParCat(catg);
            String desc = txtDesc.getText();
            int quantite = Integer.parseInt(txtQuantite.getText());
            //String image = filePath;
            String image = path;
            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);
            
            Produit p = new Produit(nom, prix, img, quantite, desc, idcat);

            pc.ajouterProduit(p);
            tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
            tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
            tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
            tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
            // Display row data
            ObservableList<Produit> list = pc.afficheProduit();
            tablePro.setItems(list);
        }
    }

    @FXML
    protected void HandleButtonAction(ActionEvent event
    ) {

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
                img= name;
//                File outputfile = new File(path);
//                //save 
//                ImageIO.write(bufferedImage,"png" , outputfile);

            } catch (IOException ex) {
                Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void RechercherProduit(ActionEvent event
    ) {
        ProduitCRUD rs = new ProduitCRUD();

        ObservableList<Produit> liste = rs.rechercheParNom((txtrecher.getText()));

        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
        tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_pro"));
        tablePro.setItems(liste);
    }

    @FXML
    private void AfficherProduit(ActionEvent event
    ) {
        ProduitCRUD rc = new ProduitCRUD();

        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
        tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        // Display row data
        ObservableList<Produit> list = rc.afficheProduit();
        tablePro.setItems(list);
    }

//    public void SelectProduit(MouseEvent event) {
//        Produit M = tablePro.getSelectionModel().getSelectedItem();
//        tbid.setText(String.valueOf(M.getId_prod()));
//        tbnom.setText(M.getNom());
//        tbprix.setText(M.getNom());
//        tbimg.setText(M.getNom());
//        tbdesc.setText(M.getNom());
//        tbidcat.setText(String.valueOf(M.getId_cat()));
//    }
//    @FXML
//    private void ModifierfProduit(ActionEvent event
//    ) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @FXML
    private void ModifierfProduit(ActionEvent event) throws IOException {
        if (txtNom.getText().trim().isEmpty() || txtPrix.getText().trim().isEmpty()
                || boxCat.getValue().trim().isEmpty() || txtDesc.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText(" vérifier vos informations ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'un new produit a été effectué avec succées");
            alert.showAndWait();
            ProduitCRUD sa = new ProduitCRUD();
            CategorieCRUD cp = new CategorieCRUD();
            String nom = txtNom.getText();
            double prix = Double.valueOf(txtPrix.getText());
            String desc = txtDesc.getText();
            String catg = boxCat.getValue();
            int idcat = cp.rechercheParCat(catg);
            int quantite = Integer.parseInt(txtQuantite.getText());
            String image = path;

            File outputfile = new File(path);
            //save 
            ImageIO.write(bufferedImage, "png", outputfile);

            Produit re = (Produit) tablePro.getSelectionModel().getSelectedItem();
            re.setNom(nom);
            re.setPrix(prix);
            re.setImage(image);
            re.setDescProd(desc);
            re.setId_cat(idcat);
            sa.modifierProduit(re);
            tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
            tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
            tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
            tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
            // Display row data
            ObservableList<Produit> list = sa.afficheProduit();
            tablePro.setItems(list);

        }
    }

    @FXML
    private void SupprimerProduit(ActionEvent event
    ) {
        ProduitCRUD se = new ProduitCRUD();
        Produit r = tablePro.getSelectionModel().getSelectedItem();
        r.setId_prod(1);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ALERT suppression");
        alert.setHeaderText(null);
        alert.setContentText(" VOULEZ VOUS SUPPRIMER LE PRODUIT?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            se.supprimerProduit(r);

            JOptionPane.showMessageDialog(null, " PRODUIT SUPPRIME ");
        } else {
            JOptionPane.showMessageDialog(null, " PRODUIT NON SUPPRIME ");
        }

        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
        tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));
        // Display row data
        ObservableList<Produit> list = se.afficheProduit();
        tablePro.setItems(list);
    }

    @FXML
    private void ReducePrix(ActionEvent event) {
        ProduitCRUD sa = new ProduitCRUD();
        Produit M = tablePro.getSelectionModel().getSelectedItem();

        tbnom.setText(M.getNom());
        tbprix.setText(String.valueOf(M.getPrix()));
        tbimg.setText(M.getImage());
        tbdesc.setText(M.getDescProd());
        tbidcat.setText(String.valueOf(M.getId_cat()));
        tbidcat.setText(String.valueOf(M.getId_prod()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPromotion.fxml"));
        try {
            Stage primaryStage = new Stage();

            Parent root = loader.load();
            AjouterPromotionController mc = loader.getController();
            mc.setIdProd(M.getId_prod());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Ajouter Promotion");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void ImprimerListeProduit(ActionEvent event) throws SQLException, FileNotFoundException, PrintException, IOException, DocumentException, PrinterException {

        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            PdfWriter.getInstance(doc, new FileOutputStream("liste.pdf"));
            doc.open();

            // Image img = Image.getInstance("C:\\Users\\HP\\Documents\\NetBeansProjects\\Sahti\\Evenement.pdf");
            //img.scaleAbsoluteHeight(60);
            //img.scaleAbsoluteWidth(100);
            //img.setAlignment(Image.ALIGN_RIGHT);
            //doc.open();
            //doc.add(img);
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("liste des Produit  ", font);
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(6);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Image", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Quantite", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Description ", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Id categorie", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            Connection conn = getConnection();
            String query = "SELECT * FROM produit";

            Statement st;
            ResultSet rs;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            // PreparedStatement pst = cnx.prepareStatement(requete);
            // ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("nom"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("prix"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("image"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("quantite"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("descProd"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("id_cat"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
            }

            doc.add(tabpdf);
            JOptionPane.showMessageDialog(null, "Success !!");
            doc.close();
            Desktop.getDesktop().open(new File("Liste.pdf"));
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
//        PDDocument document = PDDocument.load(new File("C:\\Users\\HP\\Documents\\NetBeansProjects\\Sahti\\Liste.pdf"));
//
//        PrintService myPrintService = findPrintService("My Windows printer Name");
//
//        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPageable(new PDFPageable(document));
//        job.setPrintService(myPrintService);
//        job.print();
//
//    }
//
//    private static PrintService findPrintService(String printerName) {
//        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//        for (PrintService printService : printServices) {
//            if (printService.getName().trim().equals(printerName)) {
//                return printService;
//            }
//        }
//        return null;
    }

    @FXML
    private void RechercheAvance(ActionEvent event) {
        ProduitCRUD service = new ProduitCRUD();
        Produit r = new Produit();

        tbnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tbprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        tbquan.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tbdesc.setCellValueFactory(new PropertyValueFactory<>("descProd"));
        tbidcat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        tbidpro.setCellValueFactory(new PropertyValueFactory<>("id_prod"));

        ObservableList<Produit> dateListe = service.afficheProduit();
        tablePro.setItems(dateListe);

        FilteredList<Produit> filteredData;
        filteredData = new FilteredList<>(dateListe, b -> true);
        txtrecher.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;

                } else {
                    return false;
                }

            });

        }));
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablePro.comparatorProperty());
        tablePro.setItems(sortedData);

    }
    }

