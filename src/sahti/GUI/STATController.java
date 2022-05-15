/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class STATController implements Initializable {

    @FXML
    private PieChart stat;
    @FXML
    private LineChart<String, Double> statcourbe;

    ObservableList<PieChart.Data> piechartdata;
    XYChart.Series<String, Double> linechartdata = new XYChart.Series();

    Connection cnx;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadDataPie();
        } catch (SQLException ex) {
            //Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stat.setData(piechartdata);
        try {
            loadDataLine();
        } catch (SQLException ex) {
            //  Logger.getLogger(StatRatingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statcourbe.getData().add(linechartdata);
    }

    public void loadDataPie() throws SQLException {
        int i = 0;
        int j = 0;
        int k = 0;
        piechartdata = FXCollections.observableArrayList();
        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from regime");
        rs = pst.executeQuery();

        while (rs.next()) {
            if (rs.getString("objectif").equals("Perdre du poids")) {
                i++;

            }
            if (rs.getString("objectif").equals("Gagner du poids")) {
                j++;

            }
            if (rs.getString("objectif").equals("Maintenir le poids")) {
                k++;

            }

        }
        piechartdata.add(new PieChart.Data("Perdre du poids", i));
        piechartdata.add(new PieChart.Data("Gagner du poids", j));
        piechartdata.add(new PieChart.Data("Maintenir le poids", k));
    }

    public void loadDataLine() throws SQLException {

        String dburl = "jdbc:mysql://localhost:3306/sahti";
        String dblogin = "root";
        String dbpwd = "";

        cnx = DriverManager.getConnection(dburl, dblogin, dbpwd);
        PreparedStatement pst = cnx.prepareStatement("SELECT * from regime");
        rs = pst.executeQuery();

        while (rs.next()) {
            String s;
            s = String.valueOf(rs.getInt("id_regime"));
            linechartdata.getData().add(new XYChart.Data<String, Double>(s, rs.getDouble("id_regime")));
//            name.add(rs.getString("nom_local"));
//            cap.add(rs.getInt("capacite"));             
        }
//        linechart.getData().add(linechartdata);
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
        
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Regime.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

}
