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
public class Regime {

    private int id_regime;
    private String objectif;
    private String date_debut;
    private int duree;
    private int max_calories;
    private int nb_repas;
    private int id_specialiste;
    private int id_client;
    private String image;

    public Regime(int id_regime, String objectif, String date_debut, int duree, int max_calories, int nb_repas, int id_specialiste, int id_client, String image) {
        this.id_regime = id_regime;
        this.objectif = objectif;
        this.date_debut = date_debut;
        this.duree = duree;
        this.max_calories = max_calories;
        this.nb_repas = nb_repas;
        this.id_specialiste = id_specialiste;
        this.id_client = id_client;
        this.image = image;
    }

    public int getId_nutritionniste() {
        return id_client;
    }

    public void setId_nutritionniste(int id_client) {
        this.id_client = id_client;
    }

    public Regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Regime() {
    }

    public Regime(int id_regime, String objectif, String date_debut, int duree, int max_calories, int nb_repas, int id_specialiste) {
        this.id_regime = id_regime;
        this.objectif = objectif;
        this.date_debut = date_debut;
        this.duree = duree;
        this.max_calories = max_calories;
        this.nb_repas = nb_repas;
        this.id_specialiste = id_specialiste;
    }

    public Regime(String objectif, String date_debut) {
        this.objectif = objectif;
        this.date_debut = date_debut;
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getMax_calories() {
        return max_calories;
    }

    public void setMax_calories(int max_calories) {
        this.max_calories = max_calories;
    }

    public int getNb_repas() {
        return nb_repas;
    }

    public void setNb_repas(int nb_repas) {
        this.nb_repas = nb_repas;
    }

    public int getId_specialiste() {
        return id_specialiste;
    }

    public void setId_specialiste(int id_specialiste) {
        this.id_specialiste = id_specialiste;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Regime{" + "id_regime=" + id_regime + ", objectif=" + objectif + ", date_debut=" + date_debut + ", duree=" + duree + ", max_calories=" + max_calories + ", nb_repas=" + nb_repas + ", id_specialiste=" + id_specialiste + ", id_client=" + id_client + ", image=" + image + '}';
    }

    

   

}
