/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerRetrieveParams;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaiementController implements Initializable {
        
   @FXML
    private TextField carte;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;

    public void setPrix(String prix) {
       this.prix.setText(prix);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // prix.setDisable(true);
        // TODO
//                this.prix.setText(String.valueOf(CommandeFrontController.prix));
        valider.setOnAction((ActionEvent event) -> {

            try {

                if (controleDeSaisi()) {
                    if (carte.getText().isEmpty()) {
                        carte.setText("");
                    }
                    if (month.getText().isEmpty()) {
                        month.setText("");
                    }
                    if (year.getText().isEmpty()) {
                        year.setText("");
                    }
                    if (cvc.getText().isEmpty()) {
                        cvc.setText("");
                    }

                }

                
                Stripe.apiKey = "sk_test_51JiSXuCXmeN1Ut9rueknuut1UN35T364E70ThhUt5cNakzvScQM5fTZ1ELMmP1Qn6Z9mBhKSs5bUKZj5sbDIZdIY00EO8BEGmr";
                System.out.println("Chargement paiement...");
                CustomerRetrieveParams params = CustomerRetrieveParams.builder()
                        .addExpand("sources").build();
                Customer stripeCustomer = Customer.retrieve("cus_LFwrOKYEVIKep9", params, null);
                System.out.println("Paiement effectué");

                Map<String, Object> cardParam = new HashMap<String, Object>();
                cardParam.put("number", carte.getText());
                cardParam.put("exp_month", month.getText());
                cardParam.put("exp_year", year.getText());
                cardParam.put("cvc", cvc.getText());

                Map<String, Object> tokenParams = new HashMap<String, Object>();
                tokenParams.put("card", cardParam);

                Token token = Token.create(tokenParams);

                stripeCustomer.getSources();

                Map<String, Object> chargeParams = new HashMap<String, Object>();
                chargeParams.put("amount", String.valueOf(Integer.parseInt(prix.getText())*100));
                chargeParams.put("currency", "usd");
                chargeParams.put("customer", stripeCustomer.getId());

                Charge.create(chargeParams);
                System.out.print("transaction réussite");

                showAlert(Alert.AlertType.CONFIRMATION, "Données Valide", "Success", "Payment avec succes!");

            } catch (StripeException ex) {
            }

        });

        Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
                s.close();

            }

        });

    }

    private boolean controleDeSaisi() {

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

            if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }

        }
        return true;

    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

}
