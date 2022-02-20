/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author medal
 */
public class hotel {
    private int idh;
    private String Nom_hotel;
    private int Nb_etoiles;
    private String Adresse ;

    public hotel() {
    }

    public hotel(String Nom_hotel, int Nb_etoiles, String Adresse) {
        this.Nom_hotel = Nom_hotel;
        this.Nb_etoiles = Nb_etoiles;
        this.Adresse = Adresse;
    }

    public hotel(int idh, String Nom_hotel, int Nb_etoiles, String Adresse) {
        this.idh = idh;
        this.Nom_hotel = Nom_hotel;
        this.Nb_etoiles = Nb_etoiles;
        this.Adresse=Adresse;
    }

    public hotel(int idh) {
        this.idh = idh;
    }

    public int getIdh() {
        return idh;
    }

    public String getNom_hotel() {
        return Nom_hotel;
    }

    public int getNb_etoiles() {
        return Nb_etoiles;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public void setNom_hotel(String Nom_hotel) {
        this.Nom_hotel = Nom_hotel;
    }

    public void setNb_etoiles(int Nb_etoiles) {
        this.Nb_etoiles = Nb_etoiles;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }
    
   
}
