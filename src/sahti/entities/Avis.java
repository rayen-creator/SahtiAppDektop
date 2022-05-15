/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Akrimi
 */
public class Avis {
    private int id;  
    private int idClient;
    private String commentaire;
    private int rating;
    private Date dateAvis;

    public Avis(String commentaire, int rating) {
        this.commentaire = commentaire;
        this.rating = rating;        
    }

    public Avis(int id, int idClient,String commentaire, int rating) {
        this.id = id;
        this.idClient = idClient;
        this.commentaire = commentaire;
        this.rating = rating;
    }

    public Avis() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateAvis() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Date dateAvis=(calendar.getTime());
        return dateAvis;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", idClient=" + idClient + ", commentaire=" + commentaire + ", rating=" + rating + ", dateAvis=" + dateAvis + '}';
    }

  

}