/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.abonnement;

import entities.abonnement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface Iabonnement {

    void ajouter(abonnement a) throws SQLException;

    void modifier(int id, abonnement a) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<abonnement> afficher() throws SQLException;
}
