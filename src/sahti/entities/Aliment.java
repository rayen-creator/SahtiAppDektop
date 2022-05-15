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
public class Aliment {
    private int id_aliment;
    private String nom;
    private String type;
    private String image;
    private int calories;
    private String description; 
    private int id_nutritionniste;

    public Aliment() {
    }

    public Aliment(int id_aliment, String nom, String type, String image, int calories, String description, int id_nutritionniste) {
        this.id_aliment = id_aliment;
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.calories = calories;
        this.description = description;
        this.id_nutritionniste = id_nutritionniste;
    }

    
 public Aliment(int id_aliment) {
        this.id_aliment=id_aliment;
      
    }
    public int getId_aliment() {
        return id_aliment;
    }

    public void setId_aliment(int id_aliment) {
        this.id_aliment = id_aliment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_nutritionniste() {
        return id_nutritionniste;
    }

    public void setId_nutritionniste(int id_nutritionniste) {
        this.id_nutritionniste = id_nutritionniste;
    }

    @Override
    public String toString() {
        return "Aliment{" + "id_aliment=" + id_aliment + ", nom=" + nom + ", type=" + type + ", image=" + image + ", calories=" + calories + ", description=" + description + ", id_nutritionniste=" + id_nutritionniste + '}';
    }

  
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}

