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
public class ali_repas {
   public int id_aliment;
   public int id_repas;

    public ali_repas(int id_aliment, int id_repas) {
        this.id_aliment = id_aliment;
        this.id_repas = id_repas;
    }

    public ali_repas() {
    }

    public int getId_aliment() {
        return id_aliment;
    }

    public void setId_aliment(int id_aliment) {
        this.id_aliment = id_aliment;
    }

    public int getId_repas() {
        return id_repas;
    }

    public void setId_repas(int id_repas) {
        this.id_repas = id_repas;
    }

    @Override
    public String toString() {
        return "ali_repas{" + "id_aliment=" + id_aliment + ", id_repas=" + id_repas + '}';
    }
  
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}
