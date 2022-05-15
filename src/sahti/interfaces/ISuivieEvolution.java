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
public interface ISuivieEvolution<T> {
    public void ajouterSuivieEvolution(T t);
    public void supprimerSuivieEvolution(T t);
    public void modifierSuivieEvolution(T t);
    public List<T> SuivieEvolutionsList(); 
}
