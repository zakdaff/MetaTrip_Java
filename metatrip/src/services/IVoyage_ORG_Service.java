/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.voyage;
import entities.voyage_organise;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author FLAM
 */
public interface IVoyage_ORG_Service {
    
      void ajouter(voyage_organise vo) throws SQLException;
    void modifier(int id,voyage_organise vo) throws SQLException;
    void supprimer(int id) throws SQLException;
    List<voyage_organise> afficher() throws SQLException;
}
