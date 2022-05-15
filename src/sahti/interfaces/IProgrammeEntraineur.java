/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author abdou
 */
public interface IProgrammeEntraineur<T> {
    public void ajouterProgrammeEntraineur(T t);
    public void supprimerProgrammeEntraineur(T t);
    public void modifierProgrammeEntraineur(T t);
    public List<T> ProgrammeEntraineursList(); 
}
