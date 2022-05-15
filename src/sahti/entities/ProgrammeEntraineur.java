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
public class ProgrammeEntraineur {
    private int id;
    private String idExercice;
    private String nomPack;
    private String type;

    public ProgrammeEntraineur(int id, String idExercice, String nomPack, String type) {
        this.id = id;
        this.idExercice = idExercice;
        this.nomPack = nomPack;
        this.type = type;
    }

    public ProgrammeEntraineur() {
    }

    public ProgrammeEntraineur(int id) {
        this.id = id;
    }
    
    public ProgrammeEntraineur(String idExercice, String nomPack, String type) {
        this.idExercice = idExercice;
        this.nomPack = nomPack;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(String idExercice) {
        this.idExercice = idExercice;
    }

    public String getNomPack() {
        return nomPack;
    }

    public void setNomPack(String nomPack) {
        this.nomPack = nomPack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProgrammeEntraineur{" + "id=" + id + ", idExercice=" + idExercice + ", nomPack=" + nomPack + ", type=" + type + '}';
    }
    
    
}
