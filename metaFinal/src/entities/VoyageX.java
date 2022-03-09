/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author medal
 */
public class VoyageX {
    
    private int idv;
  private String Pays;
   private String Image_pays;
   private Float Prix_billet;
    private String Airline;
     private int  Nb_nuitees;   

    public VoyageX() {
    }

    public VoyageX(String Pays, String Image_pays, Float Prix_billet, String Airline, int Nb_nuitees) {
        this.Pays = Pays;
        this.Image_pays = Image_pays;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
    }

    public String getPays() {
        return Pays;
    }

    public String getImage_pays() {
        return Image_pays;
    }

    public Float getPrix_billet() {
        return Prix_billet;
    }

    public String getAirline() {
        return Airline;
    }

    public int getNb_nuitees() {
        return Nb_nuitees;
    }

    @Override
    public String toString() {
        return "VoyageX{" + "idv=" + idv + ", Pays=" + Pays + ", Image_pays=" + Image_pays + ", Prix_billet=" + Prix_billet + ", Airline=" + Airline + ", Nb_nuitees=" + Nb_nuitees + '}';
    }

 
    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public void setImage_pays(String Image_pays) {
        this.Image_pays = Image_pays;
    }

    public void setPrix_billet(Float Prix_billet) {
        this.Prix_billet = Prix_billet;
    }

    public void setAirline(String Airline) {
        this.Airline = Airline;
    }

    public void setNb_nuitees(int Nb_nuitees) {
        this.Nb_nuitees = Nb_nuitees;
    }

    public int getIdv() {
        return idv;
    }

    public void setIdv(int idv) {
        this.idv = idv;
    }
   
     
}