/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.user;
import java.sql.Date;

/**
 *
 * @author medal
 */
public class reservation_hotel {

    private int Idrh;
    private int Nb_nuitees;
    private int Nb_personnes;
    private float Prix;

    private user user;
    private Chambre chambre ;
    private int Idu;
    private int Idh;
    private Date Date_depart;
    private Date Date_arrivee;

    public reservation_hotel(int Idrh, int Nb_nuitees, int Nb_personnes, float Prix, user user, Chambre chambre ) {
        this.Idrh = Idrh;
        
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
       // this.Prix = Prix;
        this.Prix = Prix*Nb_personnes*Nb_nuitees;
        this.user = user;
       this.chambre=chambre ;
    }
    

    public reservation_hotel() {
    }

    public reservation_hotel(int Idrh, int Nb_nuitees, int Nb_personnes, float Prix, user user,Chambre chambre, int Idu, int Idh, Date Date_depart, Date Date_arrivee) {
        this.Idrh = Idrh;
       
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.chambre = chambre;
        this.Idu = Idu;
        this.Idh = Idh;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
    }

    public reservation_hotel( int Nb_nuitees, int Nb_personnes, float Prix, int Idu, int Idh, Date Date_depart, Date Date_arrivee) {
       
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
    
        this.Idu = Idu;
        this.Idh = Idh;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
    }

    public reservation_hotel(int Idrh, int Nb_nuitees, int Nb_personnes, float Prix, user user, Chambre chambre, Date Date_depart, Date Date_arrivee) {
        this.Idrh = Idrh;
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.chambre = chambre;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
    }

    public reservation_hotel( int Nb_nuitees, int Nb_personnes, float Prix, user user, Chambre chambre , Date Date_depart, Date Date_arrivee) {
        
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.chambre= chambre;
        this.Date_depart = Date_depart;
        this.Date_arrivee = Date_arrivee;
    }

    
    public reservation_hotel(int Nb_nuitees, int Nb_personnes, float Prix, user user,Chambre chambre) {
        
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.chambre = chambre;
    }

    public reservation_hotel( int Nb_nuitees, int Nb_personnes, float Prix, Chambre chambre, user user) {
        
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.chambre = chambre;
    }

    
    

   
    

   

   
   

    

    @Override
    public String toString() {
        return "reservation_hotel{" + "Idrh=" + Idrh + ", Nb_nuitees=" + Nb_nuitees + ", Nb_personnes=" + Nb_personnes + ", Prix=" + Prix + ", user=" + user + ", chambre=" + chambre + ", Idu=" + Idu + ", Idh=" + Idh + ", Date_depart=" + Date_depart + ", Date_arrivee=" + Date_arrivee + '}';
    }

    public int getIdrh() {
        return Idrh;
    }

    public void setIdrh(int Idrh) {
        this.Idrh = Idrh;
    }

    public int getNb_nuitees() {
        return Nb_nuitees;
    }

    public void setNb_nuitees(int Nb_nuitees) {
        this.Nb_nuitees = Nb_nuitees;
    }

    public int getNb_personnes() {
        return Nb_personnes;
    }

    public void setNb_personnes(int Nb_personnes) {
        this.Nb_personnes = Nb_personnes;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public int getIdu() {
        return Idu;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public int getIdh() {
        return Idh;
    }

    public void setIdh(int Idh) {
        this.Idh = Idh;
    }

    public Date getDate_depart() {
        return Date_depart;
    }

    public void setDate_depart(Date Date_depart) {
        this.Date_depart = Date_depart;
    }

    public Date getDate_arrivee() {
        return Date_arrivee;
    }

    public void setDate_arrivee(Date Date_arrivee) {
        this.Date_arrivee = Date_arrivee;
    }

    
}
