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
    private String image_hotel;

    public hotel() {
    }

    public hotel(String Nom_hotel, int Nb_etoiles, String Adresse) {
        this.Nom_hotel = Nom_hotel;
        this.Nb_etoiles = Nb_etoiles;
        this.Adresse = Adresse;
    }

    public hotel(String Nom_hotel, int Nb_etoiles, String Adresse, String image_hotel) {
        this.Nom_hotel = Nom_hotel;
        this.Nb_etoiles = Nb_etoiles;
        this.Adresse = Adresse;
        this.image_hotel = image_hotel;
    }

    public hotel(int idh, String Nom_hotel, int Nb_etoiles, String Adresse,String image_hotel) {
        this.idh = idh;
        this.Nom_hotel = Nom_hotel;
        this.Nb_etoiles = Nb_etoiles;
        this.Adresse=Adresse;
        this.image_hotel=image_hotel;
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

    public String getImage_hotel() {
        return image_hotel;
    }

    public void setImage_hotel(String image_hotel) {
        this.image_hotel = image_hotel;
    }

    @Override
    public String toString() {
        return "hotel{" + "idh=" + idh + ", Nom_hotel=" + Nom_hotel + ", Nb_etoiles=" + Nb_etoiles + ", Adresse=" + Adresse + ", image_hotel=" + image_hotel + '}';
    }

    
   
}
