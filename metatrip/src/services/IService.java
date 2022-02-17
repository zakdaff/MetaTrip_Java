/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author remo
 */
public interface IService<T> {
    void ajouter(T entity);
    void modifier(int id,T entity);
    void supprimer(int id);
    List<T> afficher();

}
