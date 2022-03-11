/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Z4RGA
 */
public class showevents {
    
       private String chanteur;
    private String imgSrc ;
    private String adresse ;

    public void setChanteur(String chanteur) {
        this.chanteur = chanteur;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getChanteur() {
        return chanteur;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getAdresse() {
        return adresse;
    }
    
    
}
