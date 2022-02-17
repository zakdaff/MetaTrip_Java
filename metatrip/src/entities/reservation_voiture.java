/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author medal
 */
public class reservation_voiture {
private int Idrvoit;
 private float prix_rent;
 private String Chauffeur;
 private String Trajet;
private user user;
private Voiture Voiture;

    public reservation_voiture() {
    }

    public reservation_voiture(int Idrvoit, float prix_rent, String Chauffeur, String Trajet, user user, Voiture Voiture) {
        this.Idrvoit = Idrvoit;
        this.prix_rent = prix_rent;
        this.Chauffeur = Chauffeur;
        this.Trajet = Trajet;
        this.user = user;
        this.Voiture = Voiture;
    }

    public reservation_voiture(float prix_rent, String Chauffeur, String Trajet, user user, Voiture Voiture) {
        this.prix_rent = prix_rent;
        this.Chauffeur = Chauffeur;
        this.Trajet = Trajet;
        this.user = user;
        this.Voiture = Voiture;
    }

    public int getIdrvoit() {
        return Idrvoit;
    }

    public float getPrix_rent() {
        return prix_rent;
    }

    public String getChauffeur() {
        return Chauffeur;
    }

    public String getTrajet() {
        return Trajet;
    }

    public user getUser() {
        return user;
    }

    public Voiture getVoiture() {
        return Voiture;
    }

    public void setIdrvoit(int Idrvoit) {
        this.Idrvoit = Idrvoit;
    }

    public void setPrix_rent(float prix_rent) {
        this.prix_rent = prix_rent;
    }

    public void setChauffeur(String Chauffeur) {
        this.Chauffeur = Chauffeur;
    }

    public void setTrajet(String Trajet) {
        this.Trajet = Trajet;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public void setVoiture(Voiture Voiture) {
        this.Voiture = Voiture;
    }

    public void setIdvoit(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "reservation_voiture{" + "Idrvoit=" + Idrvoit + ", prix_rent=" + prix_rent + ", Chauffeur=" + Chauffeur + ", Trajet=" + Trajet + ", user=" + user + ", Voiture=" + Voiture + '}';
    }


}
