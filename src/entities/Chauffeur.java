/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS I7
 */
public class Chauffeur {
       private int  idch ;
   private String nom ; 
   private String prenom ;
   private String photo;
   private int  tel ;
   private String description;
   private  etat etatDispo ;

    public Chauffeur(String nom, String prenom, String photo, int tel, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
    }
	


    public Chauffeur(){}
    public Chauffeur(int idch, String nom, String prenom, String photo, int tel, String description, etat etatDispo) {
        this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }
    public Chauffeur(String nom, String prenom, String photo, int tel, String description, etat etatDispo) {
       // this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }

    public Chauffeur(int idch, String nom, String prenom, String photo, int tel, String description) {
        this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
    }

    public Chauffeur(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public int getidch() {
        return idch;
    }

    public void setidch(int idch) {
        this.idch = idch;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

    public String getprenom() {
        return prenom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public String getphoto() {
        return photo;
    }

    public void setphoto(String photo) {
        this.photo = photo;
    }

    public int gettel() {
        return tel;
    }

    public void settel(int tel) {
        this.tel = tel;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Enum getetatDispo() {
        return etatDispo.DISPO;
    }

    public void setetatDispo(etat etatDispo) {
        this.etatDispo = etatDispo;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "idch=" + idch + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", tel=" + tel + ", description=" + description + ", Disponibilit\u00e9=" + etatDispo + '}';
    }
  
   
}
