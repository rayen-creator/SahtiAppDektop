/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

import java.util.Date;

/**
 *
 * @author Akrimi
 */
public class Livraison{
    private int id;
    private int idCommande;
    public String etatLivraison;
    private Date DateLivraison;    

    public Livraison() {
    }

    public Livraison(int idCommande, String etatLivraison, Date DateLivraison) {
        this.idCommande = idCommande;
        this.etatLivraison = etatLivraison;
        this.DateLivraison = DateLivraison;
    }

    public Livraison(String etatLivraison) {
        this.etatLivraison = etatLivraison;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(String etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public Date getDateLivraison() {
        return DateLivraison;
    }

    public void setDateLivraison(Date DateLivraison) {
        this.DateLivraison = DateLivraison;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", idCommande=" + idCommande + ", etatLivraison=" + etatLivraison + ", DateLivraison=" + DateLivraison + '}';
    }

    
      
}
