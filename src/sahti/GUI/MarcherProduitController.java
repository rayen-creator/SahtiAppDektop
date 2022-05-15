/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import sahti.entities.Categorie;
import sahti.entities.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sahti.entities.Commande;
import sahti.services.CategorieCRUD;
import sahti.services.CommandeCRUD;
import sahti.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MarcherProduitController implements Initializable {

    @FXML
    private Label idnamelabel;
    @FXML
    private Label idpricelabel;
    @FXML
    private ImageView imgV;
    @FXML
    private GridPane grid;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox chosenprodPanier;

    private Image image;
    private MyListener myListener;
    @FXML
    private Button btnmoin;
    @FXML
    private Label lblqte;
    @FXML
    private Button btnplus;
    @FXML
    private Label idquantite;

    private int test;
    private int idprod;
    private int qtefinal;

    @FXML
    private Button btnReche;
    @FXML
    private Button btnAffich;

    private List<String> cat = new ArrayList<>();

    private AnchorPane anchorpane;

    private AnchorPane anchorpane1;
    @FXML
    private ComboBox<String> boxCat;
    @FXML
    private Button btnaddcard;
    @FXML
    private ComboBox<String> sort_cb;

    private List<Produit> listprod = new ArrayList();

    ProduitCRUD Gr = new ProduitCRUD();
    List<Produit> l = Gr.afficheProduit();

    ObservableList<String> sortitems = FXCollections.observableArrayList("Prix", "Nom");
    @FXML
    private Button btndetail;
    @FXML
    private Button btnCommander;

    private ObservableList<Produit> getData() {
        ProduitCRUD cp = new ProduitCRUD();
        ObservableList<Produit> myProds = cp.afficheProduit();
        return myProds;
    }

    private int setChosenProd(Produit produit) {
        try {
            idquantite.setText(String.valueOf(produit.getQuantite()));
            idnamelabel.setText(produit.getNom());
            idpricelabel.setText(String.valueOf(produit.getPrix()));
            Image image = new Image(new FileInputStream(produit.getImage()));
            imgV.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarcherProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produit.getQuantite();
    }

    private int ProdCars(Produit produit) {
        return produit.getId_prod();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieCRUD c = new CategorieCRUD();
        cat = c.AllCatNames();
        boxCat.getItems().addAll(cat);

        sort_cb.getItems().removeAll(sort_cb.getItems());
        sort_cb.setItems(sortitems);
        sort_cb.getSelectionModel();

        ObservableList<Produit> Produits = getData();
        if (Produits.size() > 0) {
            setChosenProd(Produits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produit produit) {
                    lblqte.setText("1");
                    btnmoin.setVisible(true);
                    btnplus.setVisible(true);
                    setChosenProd(produit);
                    test = setChosenProd(produit);
                    idprod = ProdCars(produit);
                }
            };
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < Produits.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
                anchorpane = fxmlLoader.load();
//               
                ProduitController produitController = fxmlLoader.getController();
                produitController.setData(Produits.get(i), myListener);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row); //(child,column,row)
//                set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void decrementQte(ActionEvent event) {

        int quantite = Integer.valueOf(idquantite.getText());
        String qtes = lblqte.getText();

        int qtefinal = Integer.parseInt(qtes);

        if (qtefinal > 1) {
            btnplus.setVisible(true);
            qtefinal--;//selon le nombre dipo dans le stock;

        } else {
            btnmoin.setVisible(false);

        }

        lblqte.setText(String.valueOf(qtefinal));

    }

    @FXML
    private void incrementQte(ActionEvent event) {

        //int quantite = Integer.valueOf(idquantite.getText());
        String qtes = lblqte.getText();

        int qte = Integer.parseInt(qtes);

        if (qte < this.test) {
            qte++;//selon le nombre dipo dans le stock;

            btnmoin.setVisible(true);
        }
        if (qte == this.test) {
            btnplus.setVisible(false);
        }

        lblqte.setText(String.valueOf(qte));

    }

    @FXML
    private void RechercheProduit(ActionEvent event) {
        grid.getChildren().clear();

        if (boxCat.getValue() != null && sort_cb.getValue() == null) {
            List<Produit> liste = new ArrayList();
            for (Produit p : l) {
                ProduitCRUD cp = new ProduitCRUD();
                Categorie c = cp.findcategorie(p);
                if (c.getTitre().equals(boxCat.getValue())) {
                    liste.add(p);
                }
            }

            int column = 0;
            int row = 1;

            try {
                for (int i = 0; i < liste.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ProduitController produitController = fxmlLoader.getController();
                    produitController.setData(liste.get(i), myListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorpane, column++, row); //(child,column,row)
//                set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorpane, new Insets(1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            boxCat.setValue(null);
        }

        if (boxCat.getValue() != null && sort_cb.getValue() != null) {
            List<Produit> liste = new ArrayList();
            TreeSet<Produit> ts = null;
            for (Produit p : l) {
                ProduitCRUD cp = new ProduitCRUD();
                Categorie c = cp.findcategorie(p);
                if (c.getTitre().equals(boxCat.getValue())) {
                    liste.add(p);
                }
            }

            if (sort_cb.getValue().equals("Nom")) {
                ts = liste.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getNom().compareTo(b.getNom()))));
            } else {
                ts = liste.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> (int) a.getPrix() - (int) b.getPrix())));
            }

            int column = 0;
            int row = 1;
            List<Produit> list = new ArrayList<Produit>(ts);
            try {
                for (int i = 0; i < list.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ProduitController produitController = fxmlLoader.getController();
                    produitController.setData(list.get(i), myListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorpane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorpane, new Insets(1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            sort_cb.setValue(null);
            boxCat.setValue(null);
        }

        if (boxCat.getValue() == null && sort_cb.getValue() != null) {

            TreeSet<Produit> ts = null;

            if (sort_cb.getValue().equals("Nom")) {
                ts = l.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> a.getNom().compareTo(b.getNom()))));
            } else {
                ts = l.stream().collect(Collectors.toCollection(() -> new TreeSet<>((a, b) -> (int) a.getPrix() - (int) b.getPrix())));
            }

            int column = 0;
            int row = 1;
            List<Produit> list = new ArrayList<Produit>(ts);
            try {
                for (int i = 0; i < list.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
                    AnchorPane anchorpane = fxmlLoader.load();

                    ProduitController produitController = fxmlLoader.getController();
                    produitController.setData(list.get(i), myListener);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorpane, column++, row); //(child,column,row)
                    // set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorpane, new Insets(1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sort_cb.setValue(null);
        }

//        grid.getChildren().clear();
//        ProduitCRUD rs = new ProduitCRUD();
//        CategorieCRUD cc = new CategorieCRUD();
//        String catg = boxCat.getValue();
//        int id_cat = cc.rechercheParCat(catg);
//
//        ObservableList<Produit> liste = rs.rechercheParCat((id_cat));
//
//        int column = 0;
//        int row = 1;
//
//        try {
//            for (int i = 0; i < liste.size(); i++) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
//                AnchorPane anchorpane = fxmlLoader.load();
////               
//                ProduitController produitController = fxmlLoader.getController();
//                produitController.setData(liste.get(i), myListener);
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//                grid.add(anchorpane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//                GridPane.setMargin(anchorpane, new Insets(10));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//    }
    }

    @FXML
    private void AfficherProduits(ActionEvent event
    ) {
        grid.getChildren().clear();
        ProduitCRUD rc = new ProduitCRUD();
        ObservableList<Produit> liste = rc.trier();
        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < liste.size(); i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
//               
                ProduitController produitController = fxmlLoader.getController();
                produitController.setData(liste.get(i), myListener);
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorpane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
Produit p;
double somme=0;
List<String> prd = new ArrayList<>();
List<String> qantit = new ArrayList<>();
    @FXML
    private void AjouterAuPanier(ActionEvent event) {
        ProduitCRUD cp = new ProduitCRUD();
        int cardprodid = this.idprod;
        int qte = this.test;
        p = cp.FindProd(cardprodid);
        listprod.add(p);
        prd.add(String.valueOf(p.getId_prod()));
        qantit.add(String.valueOf(p.getQuantite()));
        somme+=p.getPrix();
        Notifications notificationBuilder = Notifications.create()
                .title("New Ajout au panier !")
                .text("le produit a été ajoutée")
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Notification !");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    }

    @FXML
    private void GoToPanier(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestPanier.fxml"));
//        try {
//            Stage primaryStage = new Stage();
//
//            Parent root = loader.load();
//            TestPanierController mc = loader.getController();
//            mc.setList(this.listprod);
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("Panier");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
        grid.getChildren().clear();
        try {
            int column = 0;
            int row = 1;
            for (int i = 0; i <= listprod.size() - 1; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DetailProduitPanier.fxml"));
                anchorpane1 = fxmlLoader.load();

                DetailProduitPanierController pcc = fxmlLoader.getController();
                pcc.setData(listprod.get(i));
                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane1, column, row++); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane1, new Insets(1));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void AfficherDetails(ActionEvent event) {
        ProduitCRUD cp = new ProduitCRUD();
        int cardprodid = this.idprod;
        int qte = Integer.parseInt(lblqte.getText());
        Produit p = cp.FindProd(cardprodid);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailProd.fxml"));
        try {
            Stage primaryStage = new Stage();

            Parent root = loader.load();
            DetailProdController mc = loader.getController();
            mc.setName(p.getNom());
            mc.setQte(qte);
            mc.setPrix(p.getPrix());
            mc.setDesc(p.getDescProd());
            mc.setImg(p.getImage());
            mc.setQteProd(p.getQuantite());
            Scene scene = new Scene(root);

            primaryStage.setTitle("detail");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void passerCommande(ActionEvent event) {
        for (int i = 0; i<listprod.size(); i++){
            listprod.get(i);
            System.out.println(listprod.get(i));
        }
        System.out.println(somme);
            
        
        //qteCmd.add(String.valueOf(qte));
        
        CommandeCRUD cr = new CommandeCRUD();
        Commande c = new Commande("commentaire");
        cr.passerCommande(somme, prd,qantit,c);
        prd.clear();
        qantit.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Paiement.fxml"));
        
        
            try {
                Stage primaryStage = new Stage();
                Parent root = loader.load();                              
                PaiementController pc = loader.getController();
        pc.setPrix(String.valueOf(somme));
                Scene scene = new Scene(root);
                primaryStage.setTitle("Payer Commande");
                primaryStage.setMaximized(true);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

//    @FXML
//    private void RechercheAvance(ActionEvent event) {
//        grid.getChildren().clear();
//        ProduitCRUD service = new ProduitCRUD();
//        ObservableList<Produit> dateListe = service.afficheProduit();
//
//        FilteredList<Produit> filteredData;
//        filteredData = new FilteredList<>(dateListe, b -> true);
//        txtrecher.textProperty().addListener(((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(Produit -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;
//
//                } else {
//                    return false;
//                }
//
//            });
//
//        }));
//        int column = 0;
//        int row = 1;
//
//        try {
//            for (int i = 0; i < filteredData.size(); i++) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("produit.fxml"));
//                AnchorPane anchorpane = fxmlLoader.load();
////               
//                ProduitController produitController = fxmlLoader.getController();
//                produitController.setData(filteredData.get(i), myListener);
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//                grid.add(anchorpane, column++, row); //(child,column,row)
//                //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);
//                GridPane.setMargin(anchorpane, new Insets(10));
//            }
//
////            SortedList<Produit> sortedData = new SortedList<>(filteredData);
////            sortedData.comparatorProperty().bind(tablePro.comparatorProperty());
////            tablePro.setItems(sortedData);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
