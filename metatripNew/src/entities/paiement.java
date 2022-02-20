
package entities;

import java.util.Date;

/**
 *
 * @author FLAM
 */
public class paiement {
    private int Ref_paiement;
    private Date Date_paiement;

    public paiement(int Ref_paiement, Date Date_paiement) {
        this.Ref_paiement = Ref_paiement;
        this.Date_paiement = Date_paiement;
    }

    public paiement() {
    }

    public int getRef_paiement() {
        return Ref_paiement;
    }

    public Date getDate_paiement() {
        return Date_paiement;
    }

    public void setRef_paiement(int Ref_paiement) {
        this.Ref_paiement = Ref_paiement;
    }

    public void setDate_paiement(Date Date_paiement) {
        this.Date_paiement = Date_paiement;
    }

    @Override
    public String toString() {
        return "paiement{" + "Ref_paiement=" + Ref_paiement + ", Date_paiement=" + Date_paiement + '}';
    }
    
    
}
