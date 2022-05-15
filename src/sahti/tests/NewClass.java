package sahti.tests;
import javax.mail.*;
import java.util.*;
import javax.mail.Flags.Flag;
import java.io.IOException;
import javax.mail.internet.InternetAddress;

public class NewClass {

  private String myLogin;
  private String myPassword;
  private String myProtocole;
  private String myHost;
  private int myPort;
  private Session mySession;
  private Store myStore;
  private Folder myFolder;
  private Message[] myMailBox;

  public NewClass() {
  }

  /* ----------------- initialisation de la connection  ---------------*/
  public Store init(String login, String password, String protocole,
                      String host, int port)
  {
    myLogin = login;
    myPassword = password;
    myProtocole = protocole;
    myHost= host;
    myPort = port;

    Properties props = System.getProperties();

    // création d'une session
    mySession = Session.getDefaultInstance(props,null);
    mySession.setDebug(false);

    //création d'un objet d'enregistrement de message
    myStore = null;

    try
    {
      myStore = mySession.getStore(myProtocole);
      // DEBUG
      System.out.println("store ok !");
    }
    catch (NoSuchProviderException e)
    {
      // DEBUG
      System.out.println("store pas ok !");
      
    }

    // Connection
    try
    {
      System.out.println("début conncetion");
      myStore.connect(myHost, myPort, myLogin, myPassword);
      System.out.println("connexion ok !");
    }
    catch ( MessagingException e)
    {
       System.out.println("connexion pas ok !");
       
    }
    return myStore;
  }


  /* -----------------------fermeture de la session ---------*/
  /*-----------------------close ----------------------------*/
  public boolean close()
  {

    try
    {
      // fermeture du store
      myFolder.expunge();
      myStore.close();

    }
    catch (MessagingException e)
    {
      return false;
    }

    return true;
  }

  /*--------------------- ouverture d'un répertoire---------------*/
  /* ---------------------- openMailBox --------------------------*/
  public boolean openMailBox(String mailbox)
  {
    // ouverture du répertoire courant INBOX
    myFolder = null;
    try
    {
      myFolder = myStore.getDefaultFolder();

      if(myFolder == null)
      {
        System.out.println("Pas de boite aux lettres par défaut");
        return false;
      }

      // par défaut on récupère les messages dans INBOX
      myFolder = myFolder.getFolder(mailbox);
      if (myFolder == null)
      {
         System.out.println("pas de inbox");
      }

      try
      {
          myFolder.open(Folder.READ_WRITE);
          System.out.println("ficher " + myFolder.getName() + " ouvert");
      }
      catch (MessagingException e)
      {
        System.err.println("[process_folder]: cannot open folder " + myFolder +
                           ": " + e.getMessage());
      }

    }
    catch (MessagingException e)
    {

      return false;
    }

    try
    {
      myMailBox = new Message[myFolder.getMessageCount()];
      myMailBox = myFolder.getMessages();
    }
    catch( MessagingException e ){}

    return true;
  }

  //retourne le nombre de message contenu dans la boite
  public int nbMessage()
  {
    int totalMessages;
    try
    {
      totalMessages = myFolder.getMessageCount();
    }
    catch( MessagingException e )
    {
      return -1;
    }
     return totalMessages;
  }

  /* ----------------- retourne le nombre de message non lu -----------------*/
  public int nbMessageUnread()
  {
    int nbMessage;
    try
    {
      nbMessage = myFolder.getUnreadMessageCount();
    }
    catch (MessagingException e)
    {
      return -1;
    }
    return nbMessage;
  }

  /* ----------------- retourne le nombre de nouveau message  -----------------*/
  public int nbNewMessage()
  {
    int nbMessage;
    try
    {
      nbMessage = myFolder.getNewMessageCount();
    }
    catch (MessagingException e)
    {
      return -1;
    }
    return nbMessage;
  }

  /*--------------------- retourne le nombre de message non lu ---------------*/
  public int nbMessageRead()
  {
    return (nbMessage()-nbMessageUnread());
  }

  /*--------------------- retourne le nombre d'ancien message  ---------------*/
  public int nbOldMessage()
  {
    return (nbMessage()-nbNewMessage());
  }


  /*------------------------efface le message num ---------------------------*/
  /* ---------------------DeleteMessage--------------------------------------*/
  public boolean deleteMessage(int num)
  {
    try
    {
      myMailBox[num].setFlag(Flags.Flag.DELETED,true);
      return true;
    }
    catch (MessagingException e)
    {
      return false;
    }
  }


  /*------------------------retourner le sujet du message num ---------------------------*/
  /* ---------------------getSubject--------------------------------------*/

  public String getSubject(int num)
  {

    try
    {
      return myMailBox[num].getSubject();
    }
    catch(MessagingException e)
    {
       return null;
    }
  }



  /*-------------- retourner l'adresse du message num --------------------*/
  /* ---------------------GetAdresse--------------------------------------*/

  public String getAdresse(int num)
  {
    Address[] myAdresse;
    InternetAddress myAdr;

    try
    {
      myAdresse = myMailBox[num].getFrom();
      myAdr = new InternetAddress(myAdresse[0].toString());
      return myAdr.getPersonal();
    }
    catch(MessagingException e)
    {
       return null;
    }
  }

  /*------------------ retourner le sujet du message num -----------------*/
  /* ---------------------getSubject--------------------------------------*/

  public String getDate(int num)
  {
    GregorianCalendar myCalendar = new GregorianCalendar();
    String myDate = new String();
    String temp = new String();

    try
    {
      myCalendar.setTime(myMailBox[num].getReceivedDate());

      switch (myCalendar.get(myCalendar.DAY_OF_WEEK))
      {
        case Calendar.SUNDAY:    temp = "dimanche "; break;
        case Calendar.MONDAY:    temp = "lundi "; break;
        case Calendar.TUESDAY:   temp = "mardi "; break;
        case Calendar.WEDNESDAY: temp = "mercredi "; break;
        case Calendar.THURSDAY:  temp = "jeudi "; break;
        case Calendar.FRIDAY:    temp = "vendredi "; break;
        case Calendar.SATURDAY:  temp = "samedi "; break;
     }
    myDate = temp;

    Integer i = new Integer(myCalendar.get(myCalendar.DATE));
    myDate += i.toString();

    switch (myCalendar.get(myCalendar.MONTH))
      {
        case 0:   temp = " janvier"; break;
        case 1:   temp = " février"; break;
        case 2:   temp = " mars"; break;
        case 3:   temp = " avril"; break;
        case 4:   temp = " mai"; break;
        case 5:   temp = " juin"; break;
        case 6:   temp = " juillet"; break;
        case 7:   temp = " août"; break;
        case 8:   temp = " septembre"; break;
        case 9:   temp = " octobre"; break;
        case 10:  temp = " novembre"; break;
        case 11:  temp = " décembre"; break;
      }
    myDate += temp;

    return myDate;
    }
    catch(MessagingException e)
    {
       return null;
    }
  }

  /*------------------ retourner le text du message num -----------------*/
  /* ---------------------getText--------------------------------------*/


  public String getText(int num)
  {
    init("hellotest651@gmail.com", "123123123AQ", "imaps", "imap.gmail.com",993);
    Multipart myMulti;
    BodyPart myBody;

    try
    {

      if(myMailBox[num].isMimeType("text/plain"))
      {
        myMailBox[num].setFlag(Flags.Flag.SEEN,true);
        return (String)myMailBox[num].getContent();
      }
      else
      {
        myMulti = (Multipart)(myMailBox[num].getContent());
        myBody = myMulti.getBodyPart(0);
        myMailBox[num].setFlag(Flags.Flag.SEEN,true);
        return (String)myBody.getContent();
      }

    }
    catch(MessagingException e)
    {
       return null;
    }

    catch(IOException io)
    {
       return null;
    }
  }


  /*--------retourne si le messages est nouveau ou non-------*/
  /* ---------------------isNew--------------------------------------*/
  public boolean isNew(int num)
  {
    try
    {
      return myMailBox[num].isSet(Flag.RECENT);
    }
    catch(MessagingException e)
    {
       return false;
    }
  }

  /*--------retourne si le messages est lu ou non-------*/
  /* ---------------------isRead--------------------------------------*/
  public boolean isRead(int num)
  {
    try
    {
      return myMailBox[num].isSet(Flag.SEEN);
    }
    catch(MessagingException e)
    {
       return false;
    }
  }

}