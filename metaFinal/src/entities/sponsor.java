/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
/**
 *
 * @author Z4RGA
 */
public class sponsor {
    
    private int idS;
    private String nomsponsor ; 
    private String Tel; 
    private String email ; 
    private Date date_sp ; 
    private float prix_sp ; 
    private String image;
    
    private evenement evenement ; 
    
    
    public sponsor() {
    }

    public sponsor(int idS, String nomsponsor, String Tel, String email, Date date_sp, float prix_sp, String image, evenement evenement) {
        this.idS = idS;
        this.nomsponsor = nomsponsor;
        this.Tel = Tel;
        this.email = email;
        this.date_sp = date_sp;
        this.prix_sp = prix_sp;
        this.image = image;
        this.evenement = evenement;
    }

    public sponsor(String nomsponsor, String Tel, String email, Date date_sp, float prix_sp, String image, evenement evenement) {
        this.nomsponsor = nomsponsor;
        this.Tel = Tel;
        this.email = email;
        this.date_sp = date_sp;
        this.prix_sp = prix_sp;
        this.image = image;
        this.evenement = evenement;
    }

    public int getIdS() {
        return idS;
    }

    public String getNomsponsor() {
        return nomsponsor;
    }

    public String getTel() {
        return Tel;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_sp() {
        return date_sp;
    }

    public float getPrix_sp() {
        return prix_sp;
    }

    public String getImage() {
        return image;
    }

    public evenement getEvenement() {
        return evenement;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    public void setNomsponsor(String nomsponsor) {
        this.nomsponsor = nomsponsor;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_sp(Date date_sp) {
        this.date_sp = date_sp;
    }

    public void setPrix_sp(float prix_sp) {
        this.prix_sp = prix_sp;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "sponsor{" + "idS=" + idS + ", nomsponsor=" + nomsponsor + ", Tel=" + Tel + ", email=" + email + ", date_sp=" + date_sp + ", prix_sp=" + prix_sp + ", image=" + image + ", evenement=" + evenement + '}';
    }

    
  
    
}
