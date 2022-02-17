
package entities;

import java.sql.Date;

/**
 *
 * @author FLAM
 */
public class reservation_voyage {
    private int Idrv;
    private Date Date_depart;
    private Date Date_arrivee;
    private EtatPayment etat;
     private int Idu;
    private int Idv;
    private int Ref_paiement;
    private user user;
    private paiement paiement;
    private voyage voyage;

    public reservation_voyage(int Idrv, Date Date_depart, Date Date_arrivee, EtatPayment etat, int Idu, int Idv, int Ref_paiement) {
        this.Idrv = Idrv;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
        this.etat = etat;
        this.Idu = Idu;
        this.Idv = Idv;
        this.Ref_paiement = Ref_paiement;
    }
    
     
    
 
    
       public reservation_voyage( Date Date_depart, Date Date_arrivee, EtatPayment etat,user u,voyage voyage) {
       
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
        this.etat = etat;
        this.user=u;
        this.voyage=voyage;
    }
       
           public reservation_voyage( int idr,Date Date_depart, Date Date_arrivee,user u,voyage voyage) {
       this.Idrv=idr;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
        this.etat = this.etat.NonPaye;
        this.user=u;
        this.voyage=voyage;
    }

    public reservation_voyage(int Idrv, Date Date_depart, Date Date_arrivee, int Ref_paiement, user user, voyage voyage) {
        this.Idrv = Idrv;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
        this.Ref_paiement = Ref_paiement;
        this.user = user;
        this.voyage = voyage;
    }
           
    

    public reservation_voyage() {
        this.etat=EtatPayment.NonPaye;    }

    public int getIdrv() {
        return Idrv;
    }

    public Date getDate_depart() {
        return Date_depart;
    }

    public Date getDate_arrivee() {
        return Date_arrivee;
    }

    public EtatPayment getEtat() {
        return etat;
    }

    public int getIdu() {
        return Idu;
    }

    public int getIdv() {
        return Idv;
    }

    public int getRef_paiement() {
        return Ref_paiement;
    }

    public user getUser() {
        return user;
    }

    public paiement getPaiement() {
        return paiement;
    }

    public voyage getVoyage() {
        return voyage;
    }

    public void setIdrv(int Idrv) {
        this.Idrv = Idrv;
    }

    public void setDate_depart(Date Date_depart) {
        this.Date_depart = Date_depart;
    }

    public void setDate_arrivee(Date Date_arrivee) {
        this.Date_arrivee = Date_arrivee;
    }

    public void setEtat(EtatPayment etat) {
        this.etat = etat;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public void setIdv(int Idv) {
        this.Idv = Idv;
    }

    public void setRef_paiement(int Ref_paiement) {
        this.Ref_paiement = Ref_paiement;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public void setPaiement(paiement paiement) {
        this.paiement = paiement;
    }

    public void setVoyage(voyage voyage) {
        this.voyage = voyage;
    }

    @Override
    public String toString() {
        return "reservation_voyage{" + "Idrv=" + Idrv + ", Date_depart=" + Date_depart + ", Date_arrivee=" + Date_arrivee + ", etat=" + etat + ", Idu=" + Idu + ", Idv=" + Idv + ", Ref_paiement=" + Ref_paiement + ", user=" + user + ", paiement=" + paiement + ", voyage=" + voyage + '}';
    }
     
    
    
    
    
}
