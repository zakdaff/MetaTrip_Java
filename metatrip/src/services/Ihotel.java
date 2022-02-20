/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.hotel;
import entities.user;
import entities.voyage;
import java.sql.SQLException;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author medal
 */
public interface Ihotel  {
    void ajouter(user user) throws SQLException;;
    void modifier(int id,user user) throws SQLException;;
    void supprimer(int id) throws SQLException;;
    List<user> afficher() throws SQLException;
}
