/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

import java.sql.Date;

/**
 *
 * @author Akrimi
 */
public class Facture extends Commande{
    private int id;
    private double prixTotal;
    private Date DateFacturation;

    public Facture() {
        super();
    }

    public Facture(int id, double prixTotal, Date DateFacturation, int idCommande, double montantCmd, String commentaireCmd, Date dateCommande) {
        super(idCommande, montantCmd, commentaireCmd, dateCommande);
        this.id = id;
        this.prixTotal = prixTotal;
        this.DateFacturation = DateFacturation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDateFacturation() {
        return DateFacturation;
    }

    public void setDateFacturation(Date DateFacturation) {
        this.DateFacturation = DateFacturation;
    }

    @Override
    public String toString() {
        return super.toString()+"Facture{" + "id=" + id + ", prixTotal=" + prixTotal + ", DateFacturation=" + DateFacturation + '}';
    }
}