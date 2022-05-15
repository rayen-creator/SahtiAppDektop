/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import sahti.GUI.PanierController;
import sahti.entities.Avis;
import sahti.entities.Client;
import sahti.entities.Commande;
import sahti.entities.Livraison;
import sahti.entities.Reclamation;
import sahti.services.AvisCRUD;
import sahti.services.CommandeCRUD;
import sahti.services.LivraisonCRUD;
import sahti.services.PanierCRUD;
import sahti.services.ReclamationCRUD;
import sahti.utils.MyConnection;
import sahti.services.Signup;

/**
 *
 * @author Akrimi
 */
public class MainClass {

    public static void main(String[] args) {
        MyConnection mc = new MyConnection();
        //DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        //Calendar calendar = Calendar.getInstance();
        //String dateRec=(format.format(calendar.getTime()));
//        ReclamationCRUD rc = new ReclamationCRUD();
        //Reclamation r = new Reclamation(1, "","Client", "titre", "message", false, dateRec, dateRec);
//        Reclamation r2 = new Reclamation("Systeme");
        //rc.ajouterReclamation(r);
        //System.out.println(rc.afficherReclamation());
        /*
         * System.out.println(rc.consulterReclamation(1));
         * rc.modifierReclamation(1,"Nouveau titre", "nouveau
         * message","Systeme"); System.out.println(rc.consulterReclamation(1));
         * rc.supprimerReclamation(1);
         * System.out.println(rc.consulterReclamation(1));
         * rc.validerResolution(2);
        System.out.println(rc.consulterReclamation(2));
         */
        //rc.cloturerReclamation(7);
        //System.out.println(rc.consulterReclamationCloturer());
        //Reclamation r1 = new Reclamation(2);
        //System.out.println(rc.analyserReclamation(r1));

        //System.out.println(rc.rechercheNumReclamation(r2,false));
//        Avis a = new Avis("test Avis", 5);
//        Avis a1 = new Avis();
//        AvisCRUD ac = new AvisCRUD();
        //ac.ajouterAvis(a);
        //System.out.println(ac.afficherAvis());
//        Livraison lv = new Livraison(1,"etat");
//        LivraisonCRUD lvc = new LivraisonCRUD();
//        System.out.println(lvc.afficherListeLivraison());
// créer un objet de la classe ArrayList
/*
         * List<String> array_L=new ArrayList<String>();
         *
         * // Ajouter des élément dans le ArrayList array_L.add("1");
         * array_L.add("2"); array_L.add("3"); List<String> array_Lq=new
         * ArrayList<String>();
         *
         * // Ajouter des élément dans le ArrayList array_Lq.add("8");
         * array_Lq.add("3"); array_Lq.add("10");
         *
         *
         * // Affichage à l'aide d'une boucle forEach for(String elem: array_L)
         * { System.out.println (elem); } CommandeCRUD cr = new CommandeCRUD();
         * Commande c = new Commande("commentaire");
    cr.passerCommande(100.00f,array_L,array_Lq,c);
         */
 /*
         * NewClass n = new NewClass(); Store s =
         * n.init("hellotest651@gmail.com", "123123123AQ", "imaps",
         * "imap.gmail.com",993);
System.out.println(s.);
         */
 /*
         * Properties props = System.getProperties();
         * props.setProperty("mail.store.protocol", "imaps"); try { Session
         * session = Session.getDefaultInstance(props, null); Store store =
         * session.getStore("imaps"); store.connect("imap.gmail.com",
         * "hellotest651@gmail.com", "123123123AQ"); System.out.println(store);
         * Folder inbox = store.getFolder("Inbox");
         * //inbox.open(Folder.READ_WRITE); inbox.open(Folder.READ_WRITE);
         * System.out.println("ficher " + inbox.getName() + " ouvert"); Message
         * messages[]; messages = inbox.getMessages();
         * messages[10].getSubject();
         * System.out.println(messages[10].getSubject()); Multipart myMulti;
         * BodyPart myBody; myMulti = (Multipart) (messages[10].getContent());
         * myBody = myMulti.getBodyPart(0);
         * messages[10].setFlag(Flags.Flag.SEEN, true);
         *
         * System.out.println(myBody.getContent()); } catch (MessagingException
         * | IOException ex) { System.out.println(ex.getMessage());
        }
         */
        //rc.getReclamationParEmail();
//           Produit produit1 = new Produit(1,"test1",100.0);
//           Produit produit2 = new Produit(2,"test2",200.0);
//           PanierCRUD p = new PanierCRUD();
////            p.ajouterPanier(produit1); 
////            p.ajouterPanier(produit2);
////Map<String,Object> hp = new HashMap<>();
////
////System.out.println(p.ajouterPanier(hp));
//            
//            System.out.println(rc.afficherReclamation());
//        Client c = new Client(11,"ahmed", "rayan", "jackrayan@gmail.com", "12345", "New York", "29 / 01 / 1990", "img" , true);
//       Client c = new Client();
//        Signup s = new Signup();
//        s.signupClient(c);

    }
}
