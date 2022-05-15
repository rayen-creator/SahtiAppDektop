/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.entities;

/**
 *
 * @author Rayen
 */
public class Role {

    private int id_role;
    private String name_role;

    public Role() {
    }
                                                                               
//         => admin
//                 client
//                 coach/nutrionsnite
    
    public Role(int id_role, String name_role) {
        this.id_role = id_role;
        this.name_role = name_role;
    }

    public int getId_role() {
        return id_role;
    }

    public String getName_role() {
        return name_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setName_role(String name_role) {
        this.name_role = name_role;
    }

}
