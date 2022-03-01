
package services;

import java.util.List;

/**
 *
 * @author FLAM
 */
public interface IService<T> {
    void ajouter(T entity);
    void modifier(int id,T entity);
    void supprimer(int id);
    List<T> afficher();


}
