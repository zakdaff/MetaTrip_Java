/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.voyage.voyage_virtuel;

import entities.Voyage_virtuel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface IVoyage_VRT_Service {
     void ajouter(Voyage_virtuel vv) throws SQLException;

    void modifier(int id, Voyage_virtuel vv ) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<Voyage_virtuel> afficher() throws SQLException;
    
}
