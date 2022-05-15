/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

/**
 *
 * @author HP
 */
public class Promotion extends Produit{

    private int id_prom;
    private String titre;
    private double porcentage; 
    private double ancienPrix;
    private String image;
    private String descProm;
    

    public Promotion() {
        
    }

    public Promotion(int id_prom, String titre, double porcentage, double ancienPrix, String image, String descProm, int id_prod) {
        super(id_prod);
        this.id_prom = id_prom;
        this.titre = titre;
        this.porcentage = porcentage;
        this.ancienPrix = ancienPrix;
        this.image = image;
        this.descProm = descProm;
    }

    public Promotion(String titre, double porcentage, double ancienPrix, String image, String descProm, int id_prod) {
        super(id_prod);
        this.titre = titre;
        this.porcentage = porcentage;
        this.ancienPrix = ancienPrix;
        this.image = image;
        this.descProm = descProm;
    }

    

    public Promotion(double porcentage) {
        this.porcentage = porcentage;
    }

    public int getId_prom() {
        return id_prom;
    }

    public void setId_prom(int id_prom) {
        this.id_prom = id_prom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPorcentage() {
        return porcentage;
    }

    public void setPorcentage(double porcentage) {
        this.porcentage = porcentage;
    }

    public double getAncienPrix() {
        return ancienPrix;
    }

    public void setAncienPrix(double ancienPrix) {
        this.ancienPrix = ancienPrix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescProm() {
        return descProm;
    }

    public void setDescProm(String descProm) {
        this.descProm = descProm;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id_prom=" + id_prom + ", titre=" + titre + ", porcentage=" + porcentage + ", ancienPrix=" + ancienPrix + ", image=" + image + ", descProm=" + descProm +", id_prod"+getId_prod()+ '}';
    }
    
    

    

}
