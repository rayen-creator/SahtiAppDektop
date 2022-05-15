/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Akrimi
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("MainHOME.fxml"));

            Scene scene = new Scene(root);
            Screen screen = Screen.getPrimary();
            javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();

            //primaryStage.setMaximized(true);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Menu");
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
