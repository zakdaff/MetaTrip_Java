/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nayrouz
 */
public class Chambre {
    private int idc ;
    private int numc;
    private String type;
    private  String etat_dispo  ;
    private hotel hotel ;
    private int Idh ;
    private  String image_chambre ;
 
    
    public Chambre (){
        
    }

    public Chambre(int idc, int numc, String type, String image_chambre) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.image_chambre = image_chambre;
    }
    

    public Chambre(int idc, int numc, String type, String etat_dispo, hotel hotel, int Idh , String image_chambre ) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.etat_dispo= etat_dispo;
        this.hotel = hotel;
        this.Idh = Idh;
        this.image_chambre=image_chambre;
    }

    public Chambre(int idc, int numc, String type, hotel hotel, String image_chambre) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.hotel = hotel;
        this.image_chambre = image_chambre;
    }
    

    public Chambre(int idc, int numc, String type, String etat_dispo, hotel hotel) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.etat_dispo=etat_dispo;
        this.hotel = hotel;
        
    }

    public Chambre(int numc, String type,String etat_dispo, hotel hotel) {
        this.numc = numc;
        this.type = type;
        this.etat_dispo=etat_dispo;
        this.hotel = hotel;
    }
    

    public Chambre(int numc, String type, String etat_dispo, hotel hotel, int Idh) {
        this.numc = numc;
        this.type = type;
        this.etat_dispo=etat_dispo;
        this.hotel = hotel;
        this.Idh = Idh;
    }

    public Chambre(int idc, int numc, String type, int Idh) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.Idh = Idh;
    }
    public Chambre(int idc, int numc, String type, int Idh, String image_chambre) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.Idh = Idh;
        this.image_chambre=image_chambre;
    }
    

    public Chambre(int idc, int numc, String type, hotel hotel) {
        this.idc = idc;
        this.numc = numc;
        this.type = type;
        this.hotel = hotel;
    }
    

    public Chambre(int idc) {
        this.idc = idc;
    }

    public int getIdc() {
        return idc;
    }

    public int getNumc() {
        return numc;
    }

    public String getType() {
        return type;
    }

    
    public hotel getHotel() {
        return hotel;
    }

    

    public int getIdh() {
        return Idh;
    }

   

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setNumc(int numc) {
        this.numc = numc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat_dispo() {
        return etat_dispo;
    }

    public void setEtat_dispo(String etat_dispo) {
        this.etat_dispo = etat_dispo;
    }

   

    public void setHotel(hotel hotel) {
        this.hotel = hotel;
    }

  
    public void setIdh(int Idh) {
        this.Idh = Idh;
    }

    public String getImage_chambre() {
        return image_chambre;
    }

    public void setImage_chambre(String image_chambre) {
        this.image_chambre = image_chambre;
    }

    @Override
    public String toString() {
        return "Chambre{" + "idc=" + idc + ", numc=" + numc + ", type=" + type + ", etat_dispo=" + etat_dispo + ", hotel=" + hotel + ", Idh=" + Idh + ", image_chambre=" + image_chambre + '}';
    }
    
    

   
   

    

   
    
    
    
}
