package entities;

/**
 *
 * @author medal
 */
public class Chauffeur {
       private int  idch ;
   private String nom ;
   private String prenom ;
   private String photo;
   private String  tel ;
   private String description;
   private  String etatDispo ;

    public Chauffeur(){}
    public Chauffeur(int idch, String nom, String prenom, String photo, String tel, String description, String etatDispo) {
        this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }
    public Chauffeur(String nom, String prenom, String photo, String tel, String description, String etatDispo) {
       // this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
        this.etatDispo = etatDispo;
    }

    public Chauffeur(int idch, String nom, String prenom, String photo, String tel, String description) {
        this.idch = idch;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.tel = tel;
        this.description = description;
    }

    public Chauffeur(int idch) {
        this.idch = idch;
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

    public String gettel() {
        return tel;
    }

    public void settel(String tel) {
        this.tel = tel;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getetatDispo() {
        return etatDispo;
    }

    public void setetatDispo(String etatDispo) {
        this.etatDispo = etatDispo;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "idch=" + idch + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", tel=" + tel + ", description=" + description + ", etatDispo=" + etatDispo + '}';
    }

    
 
   
}