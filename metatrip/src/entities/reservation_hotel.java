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
public class reservation_hotel {
    private int Idrh;
   private String Type_room;	
        private int        Nb_nuitees	;
            private int    Nb_personnes	;
             private float       Prix	;

       private user user;
       private hotel hotel;
       

    public reservation_hotel(int Idrh, String Type_room, int Nb_nuitees, int Nb_personnes, float Prix, user user, hotel hotel) {
        this.Idrh = Idrh;
        this.Type_room = Type_room;
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.hotel = hotel;
    }

    public reservation_hotel() {
    }

 

    public reservation_hotel(String Type_room, int Nb_nuitees, int Nb_personnes, float Prix, user user, hotel hotel) {
        this.Type_room = Type_room;
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.hotel = hotel;
    }

    
      public reservation_hotel(String Type_room, int Nb_nuitees, int Nb_personnes, float Prix, hotel hotel, user user) {
        this.Type_room = Type_room;
        this.Nb_nuitees = Nb_nuitees;
        this.Nb_personnes = Nb_personnes;
        this.Prix = Prix;
        this.user = user;
        this.hotel = hotel;
    }
    
    @Override
    public String toString() {
        return "reservation_hotel{" + "Idrh=" + Idrh + ", Type_room=" + Type_room + ", Nb_nuitees=" + Nb_nuitees + ", Nb_personnes=" + Nb_personnes + ", Prix=" + Prix + ", IDuser=" + user.getIdu() + ", IDhotel=" + hotel.getIdh() + '}';
    }

    public void setIdrh(int Idrh) {
        this.Idrh = Idrh;
    }

    public void setType_room(String Type_room) {
        this.Type_room = Type_room;
    }

    public void setNb_nuitees(int Nb_nuitees) {
        this.Nb_nuitees = Nb_nuitees;
    }

    public void setNb_personnes(int Nb_personnes) {
        this.Nb_personnes = Nb_personnes;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public void setHotel(hotel hotel) {
        this.hotel = hotel;
    }

    public int getIdrh() {
        return Idrh;
    }

    public String getType_room() {
        return Type_room;
    }

    public int getNb_nuitees() {
        return Nb_nuitees;
    }

    public int getNb_personnes() {
        return Nb_personnes;
    }

    public float getPrix() {
        return Prix;
    }

    public user getUser() {
        return user;
    }

    public hotel getHotel() {
        return hotel;
    }

    public void getHotel(hotel h1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


             
}
