/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import sahti.entities.Reclamation;
import sahti.entities.Role;
import sahti.utils.MyConnection;

/**
 *
 * @author Akrimi
 */
public class ReclamationCRUD {

    Connection cnx;
    Role role = new Role();
    public int priorite = 3;
    Reclamation reclamation = new Reclamation();
    java.util.Date date_util = new java.util.Date();
    java.sql.Date date_sql = new java.sql.Date(date_util.getTime());

    Login login = new Login();
    AccountModif am = new AccountModif();

    String query;
    //Client
    Client client = new Client();
    Nutritioniste nutritionniste = new Nutritioniste();
    Entraineur entraineur = new Entraineur();

    public ReclamationCRUD() {
        cnx = MyConnection.getInstance().getConnexion();
    }

    ///Important analyser par mots et affecter la prioriter (Systeme et client)
    public int analyserReclamation(Reclamation r) {

        String message = "";
        List<String> haute = Arrays.asList("systeme", "critique", "erreur", "spam", "arnaque", "erreurs", "bug", "crash", "commande", "commandes", "annuler", "paiement");
        List<String> moyenne = Arrays.asList("coach", "nutritioniste", "achat", "évolution");
        haute.replaceAll(String::toUpperCase);
        moyenne.replaceAll(String::toUpperCase);

        String[] splittedMessage;
        try {
            String query = "select * from reclamations where id=" + r.getId();

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Reclamation e = new Reclamation();
                e.setMessage(res.getString("message"));

                message = e.getMessage();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        splittedMessage = message.split("[' '\n]");
        //System.out.println(splittedMessage);
        for (String s : splittedMessage) {
            s = s.replaceAll("\n\r", "");
            s = s.replaceAll("Client:", "");
            s = s.replaceAll("\n", " ");
            s = s.replaceAll("Administrateur:", "");

        }
        //System.out.println(splittedMessage);
        //splittedMessage = 
        for (String sm : splittedMessage) {

            for (String p : haute) {
                if (sm.toUpperCase().equals(p)) {
                    priorite = 1;//high
                    return priorite;
                }
            }
            for (String p1 : moyenne) {
                if (sm.toUpperCase().equals(p1)) {
                    priorite = 2;//medium
                    return priorite;
                }
            }
        }
        return priorite;
    }

    //Rechercher réclamation
    public ObservableList<Reclamation> rechercheNumReclamation(Reclamation r, boolean priorite) {
        String query;
        PreparedStatement pst;
        ResultSet rs;
        priorite = false;
        List<Reclamation> listReclamation = new ArrayList<>();
        System.out.println(r.getNumReclamation());

        int i = 0;
        try {
            int size = 0;
            pst = cnx.prepareStatement("Select count(*) from reclamations");
            rs = pst.executeQuery();
            while (rs.next()) {
                size = rs.getInt(1);
            }
            int ids[] = new int[size];
            String messages[] = new String[size];
            pst = cnx.prepareStatement("Select * from reclamations");
            rs = pst.executeQuery();

            Reclamation e = new Reclamation();

            while (rs.next()) {
                e.setId(rs.getInt(1));
                //e.setNumReclamation(rs.getString("numReclamation"));
                e.setMessage(rs.getString("message"));
                //e.setType(rs.getString("type"));
                //e.setTitre(rs.getString("titre"));
                //listReclamation.add(e);
                ids[i] = rs.getInt(1);
                messages[i] = rs.getString("message");
                i++;

            }
            for (int j = 0; j < ids.length; j++) {
                System.out.println(ids[j]);
            }
            for (int k = 0; k < messages.length; k++) {
                System.out.println(messages[k]);
            }

            int f = 0;
            int l = size;
            int mid = (f + l) / 2;
            System.out.println(messages[3].indexOf("titre", l));
            //while(f <= l){
            /*if (messages[mid] < ){
                    f = mid + 1;   
                  }else if(messages[mid] == val){
                    System.out.println("L'élément se trouve à l'index: " + mid);
                    break;
                  }else{
                     l = mid - 1;
                  }
                  mid = (f + l)/2;
               }
                if (f > l){
                  System.out.println("L'élément n'existe pas!");
                    }*/
            //}

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Reclamation> observablelist = FXCollections.observableArrayList(listReclamation);
        return observablelist;
    }

    public void rechercherCatReclamation(int idCategorie) {
    }

    //par ordre décroissant (ou detection de priorité)
    public ObservableList<Reclamation> afficherReclamation() {
        List<Reclamation> listReclamation = new ArrayList<>();
        //String query;
        int id = 0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM reclamations";
        } else if (login.loginClient(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM reclamations where idClient=" + id;
        } else if (login.loginEntraineur(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM reclamations where idEntraineur=" + id;
        } else if (login.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM reclamations where idNutritionniste=" + id;
        } else {
            return null;
        }

        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Reclamation e = new Reclamation();
                e.setId(res.getInt(1));
                e.setNumReclamation(res.getString("numReclamation"));
                e.setTitre(res.getString("titre"));
                e.setMessage(res.getString("message"));
                e.setImage(res.getString("image"));
                e.setType(res.getString("Type"));
                e.setCloturer(res.getBoolean("cloturer"));
                e.setDateReclamation(res.getDate("dateReclamation"));
                if (analyserReclamation(e) == 1) {
                    listReclamation.add(0, e);
                } else if (analyserReclamation(e) == 2 && listReclamation.size() > 2) {
                    listReclamation.add((listReclamation.size() / 2) + 1, e);
                } else if (!listReclamation.isEmpty()) {
                    listReclamation.add(listReclamation.size() - 1, e);
                } else {
                    listReclamation.add(e);
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ObservableList<Reclamation> observablelist = FXCollections.observableArrayList(listReclamation);
        return observablelist;
    }

    public void ajouterReclamation(Reclamation r) throws SQLException {
        int id = getConnectedUser();
        String user = getUser();
        System.out.println(user);
        String query = "";

        //récupérer numReclamation if existe
        int count = 0;
        String numReclamation = "22-1";

        String query1 = "Select count(*) as total from Reclamations";
        Statement st = cnx.createStatement();
        ResultSet res1 = st.executeQuery(query1);
        while (res1.next()) {
            count = res1.getInt(1);
        }
        if (count > 0) {
            numReclamation = "22-" + ++count;
        }

        if (!"".equals(user)) {
            switch (user) {
                case "Nutritionniste":
                    query = "insert into Reclamations(idNutritionniste, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
                    break;
                case "Entraineur":
                    query = "insert into Reclamations(idEntraineur, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
                    break;
                case "Client":
                    query = "insert into Reclamations(idClient, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
                    break;
                default:
                    break;
            }
        }

        PreparedStatement pst = cnx.prepareStatement(query);

        pst.setInt(1, id);
        pst.setString(2, numReclamation);
        pst.setString(3, r.getTitre());
        pst.setString(4, r.getMessage());
        pst.setString(5, r.getType());
        pst.setString(6, r.getImage());
        pst.setDate(7, date_sql);

        pst.executeUpdate();
        System.out.println("Réclamation est ajoutée");
    }

    public void modifierReclamation(Reclamation r) {
        int id = 0;
        String user = getUser();
        System.out.println(user);
        String query = "";
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        query = "update reclamations SET titre = ? , message=? , type=? where numReclamation=?";

        user = getUser();
        System.out.println(user);

//        if (!"".equals(user)) {
//            switch (user) {
//                case "Nutritionniste":
//                    query = "insert into Reclamations(idNutritionniste, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
//                    break;
//                case "Entraineur":
//                    query = "insert into Reclamations(idEntraineur, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
//                    break;
//                case "Client":
//                    query = "insert into Reclamations(idClient, numReclamation, titre, message, type,image,dateReclamation) values(?,?,?,?,?,?,?)";
//                    break;
//                default:
//                    break;
//            }
//        }
//        String message = "";
//        if ("Admin".equals(user)){
//            message += r.getMessage();
//            message = "\nAdministrateur:\n";
//            
//        }
//        else{
//            message = "\nClient\n";
//            message += r.getMessage();
//        }
        try {
            PreparedStatement pst = cnx.prepareStatement("");
            pst.setString(1, r.getTitre());
            pst.setString(2, r.getMessage());
            pst.setString(3, r.getType());
            pst.setString(4, r.getNumReclamation());
            if (!user.equals("")) {
                pst.setInt(5, id);
            }
            pst.executeUpdate();
            System.out.println("Réclamation modifé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerReclamation(Reclamation r) {
        int id = 0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        query = "Delete from reclamations where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Réclamation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean validerResolution(Reclamation r) {
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            try {
                PreparedStatement pst;
                pst = cnx.prepareStatement("Update reclamations set etat=true where id=?");
                pst.setInt(1, r.getId());
                pst.executeUpdate();
                System.out.println("Etat reclamation changée");
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }

        return false;

    }//si true = traittement réclamation en cours sinon pas encore traiter 

    public void envoyerCommentaire(String numReclamation, String comment, String message) {//A vérifier
        //tous les user peuvent envoyer un commentaire
        //select numReclamation
        //idClient, Nutritioniste ou coach
        //cloturer doit êtra false
        //update d'une réclamation et ajouter ;(points virgule pour la séparation)
        int cloturer;
        PreparedStatement pst;
        String msg = "Client:\n";
        String user = getUser();
        System.out.println(user);
        if ("Admin".equals(user)) {
            msg += message;
            msg += "\nAdministrateur:\n";
            msg += comment;

        } else {
            msg += message;
            msg += "\nClient:\n";
            msg += comment;
        }

        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select cloturer from reclamations where numReclamation=" + numReclamation);
            Reclamation r = new Reclamation();
            while (rs.next()) {
                r.setCloturer(rs.getBoolean(1));
            }
            if (!r.isCloturer()) {
                pst = cnx.prepareStatement("Update reclamations set message=? where numReclamation=?");//insert commentaire (Ajouter à l'ancien commentaires)
                pst.setString(1, msg);
                pst.setString(2, numReclamation);
                pst.executeUpdate();
                System.out.println("Message modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    } //ex: question au client ou nutri ou entraineur

    public List<Reclamation> consulterReclamation(int idReclamation) {
        List<Reclamation> listRec = new ArrayList<>();
        String query = "";

        try {
            query = "select * from reclamations where id=" + idReclamation;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Reclamation e = new Reclamation();
                e.setId(rs.getInt(1));
                e.setTitre(rs.getString("titre"));
                e.setMessage(rs.getString("message"));
                // e.setDateReclamation(res.getString("DateReclamation"));
                //e.setType("Type"); //A corriger (type enuméré)

                listRec.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listRec;
    }

    public List<Reclamation> consulterReclamationCloturer() {
        List<Reclamation> listRecClot = new ArrayList<>();
        String query = "";
        int id = 0;
        String user = "";
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            query = "select * from reclamations where cloturer=true";
        } else if (login.loginClient(Login.emailUser, Login.pwduser)) {
            query = "select * from reclamations where cloturer=true and idClient=?";
        } else if (login.loginEntraineur(Login.emailUser, Login.pwduser)) {
            query = "select * from reclamations where cloturer=true and idEntraineur=?";
        } else if (login.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            query = "select * from reclamations where cloturer=true and idNutritionniste=?";
        }
        user = getUser();
        try {
            //query = "select * from reclamations where cloturer=true";

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Reclamation e = new Reclamation();
                e.setId(res.getInt(1));
                e.setTitre(res.getString("titre"));
                e.setMessage(res.getString("message"));
                e.setCloturer(res.getBoolean("cloturer"));
                if (user.equals("Nutritionniste")) {
                    e.setIdUser(res.getInt("idNutritionniste"));
                } else if (user.equals("Entraineur")) {
                    e.setIdUser(res.getInt("idEntraineur"));
                } else if (user.equals("Client")) {
                    e.setIdUser(res.getInt("idClient"));
                }

                listRecClot.add(e);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listRecClot;
    }

    //Si résolution validé changer la bouton validation résolution par cloturer
    public void cloturerReclamation(Reclamation r) {
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            try {
                //if(validerResolution(idReclamation) == true){
                PreparedStatement pst = cnx.prepareStatement("update reclamations SET cloturer = ? , dateCloture=? where numReclamation=?");
                pst.setBoolean(1, true);
                pst.setDate(2, date_sql);
                pst.setString(3, r.getNumReclamation());
                pst.executeUpdate();
                System.out.println("Réclamation cloturé");
                //}
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public void notifierClient(int idReclamation, int idClient) {
    }//notifier par email dés la résolution de réclamation
////////////////////////////////////////////////////////////////////Role////////////////////////////////////////

    public void renvoyerReclamation(int idReclamation) {
        //réclamation doit être cloturer
        //
        try {
            String cloturer;
            PreparedStatement pst = cnx.prepareStatement("Select cloturer from reclamations where id=" + idReclamation);
            pst.setString(1, "cloturer");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                cloturer = rs.getString("cloturer");
            }
            /*if (cloturer!=1){
                
            }*/ //qu'est ce qu'il doit être modifié
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//Réclamation cloturer renvoyer (donner l'acccée au au bouton renvoyer)

    public void suivreReclamation(int idReclamation, int idClient) {
    }

    public boolean reclamationExist(String titre) {
        //réclamation doit être cloturer
        //
        boolean result = false;
        int message = 0;
        try {

            PreparedStatement pst = cnx.prepareStatement("Select count(*) as c from reclamations where titre=?");
            pst.setString(1, titre);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                message = rs.getInt("c");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        if (message > 0) {
            result = true;
        }
        return result;
    }

    public String analyseMail(String body) {
        String type = "Autre";
        List<String> mailBody = new ArrayList<>();
        String[] words = body.split(" ");

        for (String word : words) {
            mailBody.add(word);
        }
        //System.out.println(word);
        if (mailBody.contains("SYSTEME\r\n") || mailBody.contains("SYSTEME")) {
            type = "Systeme";
        }
        if (mailBody.contains("CLIENT") || mailBody.contains("CLIENT\r\n")) {
            type = "Client";

        }
        if (mailBody.contains("ENTRAINEUR\r\n") || mailBody.contains("ENTRAINEUR")) {
            type = "Entraineur";

        }
        if (mailBody.contains("NUTRITIONNISTE\r\n") || mailBody.contains("NUTRITIONNISTE")) {
            type = "Nutritionniste";

        }
        return type;
    }

    ////////////////////////////////////////////////////////////////ajouter from email and search then add/////////////////
    public void getReclamationParEmail() throws SQLException {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "hellotest651@gmail.com", "123123123AQ");
            System.out.println(store);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
            System.out.println("ficher " + inbox.getName() + " ouvert");
            Message messages[];
            messages = inbox.getMessages();

            Multipart myMulti;
            BodyPart myBody;
            int totalMessage = inbox.getMessageCount() - 1;
            myMulti = (Multipart) (messages[totalMessage].getContent());
            myBody = myMulti.getBodyPart(0);
            messages[totalMessage].setFlag(Flags.Flag.SEEN, true);
            inbox.getMessageCount();
            reclamation.setTitre(messages[totalMessage].getSubject());//subject
            reclamation.setMessage("Client:\n" + myBody.getContent().toString());//body
            reclamation.setType(analyseMail(myBody.getContent().toString().toUpperCase()));//analyse   
            System.out.println(messages[totalMessage].getSubject());
            if (!reclamationExist(messages[totalMessage].getSubject())) {
                ajouterReclamation(reclamation);
            }

        } catch (MessagingException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getConnectedUser() throws SQLException {
        int id = 0;
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            id = 1;
        } else if (login.loginClient(Login.emailUser, Login.pwduser)) {
            id = am.searchIDClient(Login.emailUser);
        } else if (login.loginEntraineur(Login.emailUser, Login.pwduser)) {

            id = am.searchIDCoach(Login.emailUser);
        } else if (login.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            id = am.searchIDNutri(Login.emailUser);
        }
        return id;
    }

    public String getUser() {
        String user = "";
        if (login.loginAdmin(Login.emailUser, Login.pwduser)) {
            user = "Admin";
        } else if (login.loginClient(Login.emailUser, Login.pwduser)) {
            user = "Client";
        } else if (login.loginEntraineur(Login.emailUser, Login.pwduser)) {
            user = "Entraineur";
        } else if (login.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            user = "Nutritionniste";
        }
        return user;
    }

}
