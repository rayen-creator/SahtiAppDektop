/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

/**
 *
 * @author Akrimi
 */
public class Panier {
    private int id;
    private int qte;
    private double prixUnit;

    public Panier() {
    }

    public Panier(int id, int idProd, int qte, double prixUnit) {
        this.id = id;
        this.qte = qte;
        this.prixUnit = prixUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(double prixUnit) {
        this.prixUnit = prixUnit;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", qte=" + qte + ", prixUnit=" + prixUnit + '}';
    }    
            
}
