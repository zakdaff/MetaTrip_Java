package entities;

import java.sql.Date;

/**
 *
 * @author ASUS I7
 */
public class abonnement {

    private int Ida;
    private String Type;
    private int Prix_a;
    private Date Date_achat;
    private Date Date_expiration;
    private String Etat;
    private int Ref_paiment;

    public abonnement() {
    }

    public abonnement(String Type, int Prix_a, Date Date_achat, Date Date_expiration, String Etat, int Ref_paiment) {
        this.Type = Type;
        this.Prix_a = Prix_a;
        this.Date_achat = Date_achat;
        this.Date_expiration = Date_expiration;
        this.Etat = Etat;
        this.Ref_paiment = Ref_paiment;
    }

    public abonnement(int Ida, String Type, int Prix_a, Date Date_achat, Date Date_expiration, String Etat, int Ref_paiment) {
        this.Ida = Ida;
        this.Type = Type;
        this.Prix_a = Prix_a;
        this.Date_achat = Date_achat;
        this.Date_expiration = Date_expiration;
        this.Etat = Etat;
        this.Ref_paiment = Ref_paiment;
        
    
} 

    public int getIda() {
        return Ida;
    }

    public void setIda(int Ida) {
        this.Ida = Ida;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getPrix_a() {
        return Prix_a;
    }

    public void setPrix_a(int Prix_a) {
        this.Prix_a = Prix_a;
    }

    public Date getDate_achat() {
        return Date_achat;
    }

    public void setDate_achat(Date Date_achat) {
        this.Date_achat = Date_achat;
    }

    public Date getDate_expiration() {
        return Date_expiration;
    }

    public void setDate_expiration(Date Date_expiration) {
        this.Date_expiration = Date_expiration;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getRef_paiment() {
        return Ref_paiment;
    }

    public void setRef_paiment(int Ref_paiment) {
        this.Ref_paiment = Ref_paiment;
    }














}
