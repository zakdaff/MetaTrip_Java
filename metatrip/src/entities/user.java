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
public class user {
    private int Idu;
    private double Cin;
    private String Nom;
        private String Prenom;
        
    private double Tel;
      private String Email;
      private String Password;
      private String Image;
   private int role ;

   
    public user(int Idu, double Cin, String Nom, String Prenom, double Tel, String Email, String Password, String Image) {
        this.Idu = Idu;
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
    }

    public user(double Cin, String Nom, String Prenom, double Tel, String Email, String Password, String Image) {
        this.Cin = Cin;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Password = Password;
        this.Image = Image;
    }

    public user() {
        
    }
 public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }
 

    public void setIdu(int Idu) {
        this.Idu = Idu;
    }

    public void setCin(double Cin) {
        this.Cin = Cin;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setTel(double Tel) {
        this.Tel = Tel;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getIdu() {
        return Idu;
    }

    public double getCin() {
        return Cin;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public double getTel() {
        return Tel;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getImage() {
        return Image;
    }
       @Override
    public String toString() {
        return "user{" + "Idu=" + Idu + ", Cin=" + Cin + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", Password=" + Password + ", Image=" + Image + '}';
    }


    
}
