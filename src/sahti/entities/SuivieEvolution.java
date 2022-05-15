/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author abdou
 */
public class SuivieEvolution {
    private int id;
    private int idUser;
    private int poids;
    private Date dateDebutProgramme;
    private Date dateEvolution;

    public SuivieEvolution(int id, int idUser, int poids, Date dateDebutProgramme, Date dateEvolution) {
        this.id = id;
        this.idUser = idUser;
        this.poids = poids;
        this.dateDebutProgramme = dateDebutProgramme;
        this.dateEvolution = dateEvolution;
    }

    public SuivieEvolution() {
    }

    public SuivieEvolution(int id) {
        this.id = id;
    }

    public SuivieEvolution(int idUser, int poids, Date dateDebutProgramme, Date dateEvolution) {
        this.idUser = idUser;
        this.poids = poids;
        this.dateDebutProgramme = dateDebutProgramme;
        this.dateEvolution = dateEvolution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public Date getDateDebutProgramme() {
        return dateDebutProgramme;
    }

    public void setDateDebutProgramme(Date dateDebutProgramme) {
        this.dateDebutProgramme = dateDebutProgramme;
    }

    public Date getDateEvolution() {
        return dateEvolution;
    }

    public void setDateEvolution(Date dateEvolution) {
        this.dateEvolution = dateEvolution;
    }

    @Override
    public String toString() {
        return "SuivieEvolution{" + "id=" + id + ", idUser=" + idUser + ", poids=" + poids + ", dateDebutProgramme=" + dateDebutProgramme + ", dateEvolution=" + dateEvolution + '}';
    }
    
    
}
