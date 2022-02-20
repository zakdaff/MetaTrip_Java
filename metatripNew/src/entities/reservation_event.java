/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.user;

/**
 *
 * @author Z4RGA
 */
public class reservation_event{
    
   private int Idrev ;
    private int Nb_pers;
    
    
    private evenement evenement ; 
    private user user ; 
    private int Ide;
    private int Idu;

    public reservation_event() {
    }

    public reservation_event(int Nb_pers, evenement event, user user) {
        this.Nb_pers = Nb_pers;
        this.evenement = event;
        this.user = user;
    }

    public reservation_event(int Idrev, int Nb_pers, int Ide, int Idu, evenement evenement, user user) {
        this.Idrev = Idrev;
        this.Nb_pers = Nb_pers;
          this.Ide = Ide;
        this.Idu = Idu;
        this.evenement = evenement;
        this.user = user;
      
    }

   
    public reservation_event(int Idrev, int Nb_pers, evenement event, user user) {
        this.Idrev = Idrev;
        this.Nb_pers = Nb_pers;
        this.evenement = event;
        this.user = user;
    }

    public reservation_event(int Idrev, int Nb_pers, int Ide, int Idu) {
        this.Idrev = Idrev;
        this.Nb_pers = Nb_pers;
        this.Ide = Ide;
        this.Idu = Idu;
    }

    public int getIde() {
        return Ide;
    }

    public void setIde(int Ide) {
        this.Ide = Ide;
    }

    public int getIdu() {
        return Idu;
    }

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }
    
    
    

    public reservation_event(int Nb_pers) {
        this.Nb_pers = Nb_pers;
    }

    
    public evenement getEvenement() {
        return evenement;
    }

    public user getUser() {
        return user;
    }

    
    
    
    public int getIdrev() {
        return Idrev;
    }

    public int getNb_pers() {
        return Nb_pers;
    }

    public void setIdrev(int Idrev) {
        this.Idrev = Idrev;
    }

    public void setNb_pers(int Nb_pers) {
        this.Nb_pers = Nb_pers;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    public void setUser(user user) {
        this.user = user;
    }

    
    
    
    @Override
    public String toString() {
        return "reservation_event{" + "Idrev=" + Idrev + ", Nb_pers=" + Nb_pers + '}';
    }

    
    
    
}
