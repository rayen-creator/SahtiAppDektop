/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Akrimi
 */
public class Reclamation {
    private int id;
    private int idUser;
    private String numReclamation;
    private String titre;
    private String message;
    private String type;
    private String image;
    private boolean cloturer;    
    private Date dateReclamation;
    private Date dateClotureRec;
    
   
    
    //Ajouter capture ou image ((essayer de stocker les image dan dropbox ou google drive) 
    public Reclamation(){}

    public Reclamation(int id, int idUser, String numReclamation, String titre, String message, String type, String image, boolean cloturer, Date dateReclamation, Date dateClotureRec) {
        this.id = id;
        this.idUser = idUser;
        this.numReclamation = numReclamation;
        this.titre = titre;
        this.message = message;
        this.type = type;
        this.image = image;
        this.cloturer = cloturer;
        this.dateReclamation = dateReclamation;
        this.dateClotureRec = dateClotureRec;
    }

    public Reclamation(int id) {
        this.id = id;
    }

    public Reclamation(int idUser, String titre, String message, String type, String image) {
        this.idUser = idUser;
        this.titre = titre;
        this.message = message;
        this.type = type;
        this.image = image;
    }

    public Reclamation(String numReclamation, boolean cloturer) {
        this.numReclamation = numReclamation;
        this.cloturer = cloturer;
    }

    public Reclamation(String numReclamation, String titre, String message, String type) {
        this.numReclamation = numReclamation;
        this.titre = titre;
        this.message = message;
        this.type = type;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNumReclamation() {
        return numReclamation;
    }

    public void setNumReclamation(String numReclamation) {
        this.numReclamation = numReclamation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isCloturer() {
        return cloturer;
    }

    public void setCloturer(boolean cloturer) {
        this.cloturer = cloturer;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public Date getDateClotureRec() {
        return dateClotureRec;
    }

    public void setDateClotureRec(Date dateClotureRec) {
        this.dateClotureRec = dateClotureRec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idUser=" + idUser + ", numReclamation=" + numReclamation + ", titre=" + titre + ", message=" + message + ", type=" + type + ", image=" + image + ", cloturer=" + cloturer + ", dateReclamation=" + dateReclamation + ", dateClotureRec=" + dateClotureRec + '}';
    }

    
}
