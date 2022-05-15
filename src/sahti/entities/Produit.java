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
public class Produit extends Categorie {

    private int id_prod;
    private String nom;
    private Double prix; 
    private String image;
    private int quantite;
    private String descProd;
    
    public Produit() {
    }
    

    public Produit(String nom, Double prix, String image, int quantite, String descProd, int id_cat) {
        super(id_cat);
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.descProd = descProd;
    }

    public Produit(int id_prod, String nom, Double prix, String image, int quantite, String descProd, int id_cat) {
        super(id_cat);
        this.id_prod = id_prod;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
        this.quantite = quantite;
        this.descProd = descProd;
    }
    

    public Produit(Double prix) {
        this.prix = prix;
    }
    public Produit(int id_prod) {
        super();
        this.id_prod = id_prod;
    }

    
    
    
    

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescProd() {
        return descProd;
    }

    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", nom=" + nom + ", prix=" + prix + ", image=" + image + ", quantite=" + quantite + ", descProd=" + descProd + ", Id_cat="+ getId_cat() + '}';
    }
    
    
    

    
  
   
   

}
