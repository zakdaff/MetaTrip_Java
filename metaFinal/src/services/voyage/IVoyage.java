
package services.voyage;


import entities.voyage;
import services.IService;

/**
 *
 * @author FLAM
 */
public interface IVoyage extends IService<voyage>{
    voyage ajout (voyage v);
        voyage afficherById(voyage v);
}
