/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nayrouz
 * 
 
 */
public class Voiture {
   private int  Idvoit ;
   private String Matricule ; 
   private int Puissance_fiscalle ;
   private String Image_v;
   private  String Modele ;
  
   
    public Voiture (){
   }

    public Voiture(int Idvoit) {
        this.Idvoit = Idvoit;
    }

    public Voiture(String Matricule, int Puissance_fiscalle, String Image_v, String Modele) {
        this.Matricule = Matricule;
        this.Puissance_fiscalle = Puissance_fiscalle;
        this.Image_v = Image_v;
        this.Modele = Modele;
    }
   
  
  
    public Voiture(int Idvoit, String Matricule, int Puissance_fiscalle, String Image_v, String Modele) {
        this.Idvoit = Idvoit;
        this.Matricule = Matricule;
        this.Puissance_fiscalle = Puissance_fiscalle;
        this.Image_v = Image_v;
        this.Modele = Modele;
    }

    public int getIdvoit() {
        return Idvoit;
    }

    public String getMatricule() {
        return Matricule;
    }

    public int getPuissance_fiscalle() {
        return Puissance_fiscalle;
    }

    public String getImage_v() {
        return Image_v;
    }

  

    public String getModele() {
        return Modele;
    }

    public void setIdvoit(int Idvoit) {
        this.Idvoit = Idvoit;
    }

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    public void setPuissance_fiscalle(int Puissance_fiscalle) {
        this.Puissance_fiscalle = Puissance_fiscalle;
    }

    public void setImage_v(String Image_v) {
        this.Image_v = Image_v;
    }

    public void setModele(String Modele) {
        this.Modele = Modele;
    }

    @Override
    public String toString() {
        return "Voiture{" + "Idvoit=" + Idvoit + ", Matricule=" + Matricule + ", Puissance_fiscalle=" + Puissance_fiscalle + ", Image_v=" + Image_v + ", Modele=" + Modele + '}';
    }
   
   
}

