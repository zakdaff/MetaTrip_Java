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
public class chambre {
       private int Idc;
    private String numc	;
    private String type 	;
    private String etat;
    private int idh ; 

    public chambre(int Idc, String numc, String type, String etat, int idh) {
        this.Idc = Idc;
        this.numc = numc;
        this.type = type;
        this.etat = etat;
        this.idh = idh;
    }

    public chambre(String numc, String type, String etat, int idh) {
        this.numc = numc;
        this.type = type;
        this.etat = etat;
        this.idh = idh;
    }

    public chambre() {
    }

    public int getIdc() {
        return Idc;
    }

    public String getNumc() {
        return numc;
    }

    public String getType() {
        return type;
    }

    public String getEtat() {
        return etat;
    }

    public int getIdh() {
        return idh;
    }

    @Override
    public String toString() {
        return "chambre{" + "Idc=" + Idc + ", numc=" + numc + ", type=" + type + ", etat=" + etat + ", idh=" + idh + '}';
    }

    public void setIdc(int Idc) {
        this.Idc = Idc;
    }

    public void setNumc(String numc) {
        this.numc = numc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }
    
    

}
