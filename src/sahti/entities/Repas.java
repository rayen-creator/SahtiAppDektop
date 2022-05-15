/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

/**
 *
 * @author user
 */
public class Repas {
    
    private int id_repas;
    private String nom_rep;
    private int nb_cal;
    private int quantite;
    private int id_nutritionniste;

    public Repas() {
    }

    public Repas(int id_repas, String nom_rep, int nb_cal, int quantite, int id_nutritionniste) {
        this.id_repas = id_repas;
        this.nom_rep = nom_rep;
        this.nb_cal = nb_cal;
        this.quantite = quantite;
        this.id_nutritionniste = id_nutritionniste;
    }
    
    

    public Repas (int id_repas) {
        this.id_repas=id_repas;
      
    }

    public int getId_repas() {
        return id_repas;
    }

    public void setId_repas(int id_repas) {
        this.id_repas = id_repas;
    }

    public String getNom_rep() {
        return nom_rep;
    }

    public void setNom_rep(String nom_rep) {
        this.nom_rep = nom_rep;
    }

    public int getNb_cal() {
        return nb_cal;
    }

    public void setNb_cal(int nb_cal) {
        this.nb_cal = nb_cal;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Repas{" + "id_repas=" + id_repas + ", nom_rep=" + nom_rep + ", nb_cal=" + nb_cal + ", quantite=" + quantite + ", id_nutritionniste=" + id_nutritionniste + '}';
    }

    public int getId_nutritionniste() {
        return id_nutritionniste;
    }

    public void setId_nutritionniste(int id_nutritionniste) {
        this.id_nutritionniste = id_nutritionniste;
    }

    
    
    
    

}
