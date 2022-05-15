/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BMICalculatorController implements Initializable {

    @FXML
    private Button buttonCalculate;
    @FXML
    private TextField weightinput;
    @FXML
    private TextField bmioutput;
    @FXML
    private TextField statusoutput;
    @FXML
    private TextField heightinput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void calculate(ActionEvent event) {
        try {
            Double weightValue = Double.parseDouble(weightinput.getText());
            Double heightValue = Double.parseDouble(heightinput.getText());
            Double bmiValue = weightValue/((heightValue/100)*(heightValue/100));
            weightinput.clear();
            heightinput.clear();
            setResult(bmiValue);

        } catch (Exception e) {

        }
    }

    void setResult(Double bmiValue) {

        bmioutput.setText(bmiValue.toString());
        if (bmiValue <= 18.5) {
            statusoutput.setText("UnderWeight");

        } else if (18.6 <= bmiValue && bmiValue <= 24.9) {
            statusoutput.setText("Normal Weight");

        } else if (25 <= bmiValue && bmiValue <= 29.9) {
            statusoutput.setText("OverWeight");
        } else {
            statusoutput.setText("Obesity");
        }
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
