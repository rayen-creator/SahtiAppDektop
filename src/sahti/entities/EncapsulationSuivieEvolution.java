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
public class EncapsulationSuivieEvolution {
    private static int id;
    private static int idUser;
    private static int poids;
    private static Date dateDebutProgramme;
    private static Date dateEvolution;

    public EncapsulationSuivieEvolution() {
    }

    public EncapsulationSuivieEvolution(int id, int idUser, int poids, Date dateDebutProgramme, Date dateEvolution) {
        this.id = id;
        this.idUser = idUser;
        this.poids = poids;
        this.dateDebutProgramme = dateDebutProgramme;
        this.dateEvolution = dateEvolution;
    }
    
    public EncapsulationSuivieEvolution(int idUser, int poids, Date dateDebutProgramme, Date dateEvolution) {
        this.idUser = idUser;
        this.poids = poids;
        this.dateDebutProgramme = dateDebutProgramme;
        this.dateEvolution = dateEvolution;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapsulationSuivieEvolution.id = id;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        EncapsulationSuivieEvolution.idUser = idUser;
    }

    public static int getPoids() {
        return poids;
    }

    public static void setPoids(int poids) {
        EncapsulationSuivieEvolution.poids = poids;
    }

    public static Date getDateDebutProgramme() {
        return dateDebutProgramme;
    }

    public static void setDateDebutProgramme(Date dateDebutProgramme) {
        EncapsulationSuivieEvolution.dateDebutProgramme = dateDebutProgramme;
    }

    public static Date getDateEvolution() {
        return dateEvolution;
    }

    public static void setDateEvolution(Date dateEvolution) {
        EncapsulationSuivieEvolution.dateEvolution = dateEvolution;
    }
    
    @Override
    public String toString() {
        return "SuivieEvolution{" + "id=" + id + ", idUser=" + idUser + ", poids=" + poids + ", dateDebutProgramme=" + dateDebutProgramme + ", dateEvolution=" + dateEvolution + '}';
    }    
    
}
