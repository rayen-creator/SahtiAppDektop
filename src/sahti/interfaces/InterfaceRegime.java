/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.interfaces;


import sahti.entities.Regime;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceRegime {
    public void  AjouterRegime(Regime r);
    public List<Regime>AfficherRegime()throws SQLException;
    public void SupprimerRegime(Regime r);
    public void ModifierRegime(Regime r);
    
}
