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
public class reg_repas {
     public int id_regime;
   public int id_repas;

    public reg_repas(int id_regime, int id_repas) {
        this.id_regime = id_regime;
        this.id_repas = id_repas;
    }

    public reg_repas() {
    }

    public int getId_regime() {
        return id_regime;
    }

    public void setId_regime(int id_regime) {
        this.id_regime = id_regime;
    }

    public int getId_repas() {
        return id_repas;
    }

    public void setId_repas(int id_repas) {
        this.id_repas = id_repas;
    }

    @Override
    public String toString() {
        return "reg_repas{" + "id_regime=" + id_regime + ", id_repas=" + id_repas + '}';
    }
   
   
   
   
   
   
   
   
   
 
    
}
