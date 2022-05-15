/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author abdou
 */
public class EncapsulationProgrammeEntraineur {
     private static int id;
    private static String idExercice;
    private static String nomPack;
    private static String type;

    public EncapsulationProgrammeEntraineur() {
    }
    
    public EncapsulationProgrammeEntraineur(int id, String idExercice, String nomPack, String type) {
        this.id = id;
        this.idExercice = idExercice;
        this.nomPack = nomPack;
        this.type = type;
    }
    
    public EncapsulationProgrammeEntraineur(String idExercice, String nomPack, String type) {
        this.idExercice = idExercice;
        this.nomPack = nomPack;
        this.type = type;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        EncapsulationProgrammeEntraineur.id = id;
    }

    public static String getIdExercice() {
        return idExercice;
    }

    public static void setIdExercice(String idExercice) {
        EncapsulationProgrammeEntraineur.idExercice = idExercice;
    }

    public static String getNomPack() {
        return nomPack;
    }

    public static void setNomPack(String nomPack) {
        EncapsulationProgrammeEntraineur.nomPack = nomPack;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        EncapsulationProgrammeEntraineur.type = type;
    }

     @Override
    public String toString() {
        return "EncapsulationProgrammeEntraineur{" + "id=" + id + ", idExercice=" + idExercice + ", nomPack=" + nomPack + ", type=" + type + '}';
    }
    
    
    
    
}
