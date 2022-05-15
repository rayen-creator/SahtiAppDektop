/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.interfaces;

import sahti.entities.Aliment;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceAliment {
     public void AjouterAliment(Aliment a);
    public List<Aliment> AfficherAliment()throws SQLException;
    public void SupprimerAliment(Aliment a);
    public void ModifierAliment(Aliment a) ;
    
}
