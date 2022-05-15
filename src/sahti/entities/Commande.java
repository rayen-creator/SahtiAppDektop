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
public class Commande{
    private int id ;   
    private String numCmd;
    private double montantCmd;
    private String modePay;
    private String commentaireCmd;
    private String etat;
    private Date dateCommande;
    private Date dateModif;
    
    public Commande() {
    }
    
    public Commande(String numCmd, double montantCmd, String modePay, String commentaireCmd, Date dateCommande, Date dateModif) {
        this.numCmd = numCmd;
        this.montantCmd = montantCmd;
        this.modePay = modePay;
        this.commentaireCmd = commentaireCmd;
        this.dateCommande = dateCommande;
        this.dateModif = dateModif;
    }

    public Commande(int id, Date dateModif, String etat) {
        
        
        this.id = id;
        this.etat = etat;
        this.dateModif = dateModif;
    }

    public Commande(int id,double montantCmd, String commentaireCmd, Date dateCommande) {
        this.id = id;
        this.montantCmd = montantCmd;
        this.commentaireCmd = commentaireCmd;
        this.dateCommande = dateCommande;
    }        

    public Commande(int id) {
        this.id = id;
    }

    public Commande(String commentaireCmd/*, Date dateCommande*/) {
        this.commentaireCmd = commentaireCmd;
        //this.dateCommande = dateCommande;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }     
 
    public String getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(String numCmd) {
        this.numCmd = numCmd;
    }

    public double getMontantCmd() {
        return montantCmd;
    }

    public void setMontantCmd(double montantCmd) {
        this.montantCmd = montantCmd;
    }

    public String getModePay() {
        return modePay;
    }

    public void setModePay(String modePay) {
        this.modePay = modePay;
    }

    public String getCommentaireCmd() {
        return commentaireCmd;
    }

    public void setCommentaireCmd(String commentaireCmd) {
        this.commentaireCmd = commentaireCmd;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateModif() {
        return dateModif;
    }

    public void setDateModif(Date dateModif) {
        this.dateModif = dateModif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }   

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", numCmd=" + numCmd + ", montantCmd=" + montantCmd + ", modePay=" + modePay + ", commentaireCmd=" + commentaireCmd + ", etat=" + etat + ", dateCommande=" + dateCommande + ", dateModif=" + dateModif + '}';
    }
    
   
    
}
