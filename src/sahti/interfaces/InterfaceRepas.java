/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.interfaces;

import sahti.entities.Repas;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public interface InterfaceRepas {
    public void AjouterRepas (Repas r);
    public List<Repas>AfficherRepas()throws SQLException;
    public void SupprimerRepas(Repas r);
    public void ModifierRepas(Repas r);
}
