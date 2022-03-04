/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import entities.reservation_hotel;
import java.sql.Date;
import java.time.LocalDate;
import services.hotel.HotelCRUD;
import services.reservation_hotel.Reserrvation_Hotel_Service;

/**
 *
 * @author Mortadha
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date d=Date.valueOf(LocalDate.now());
        Reserrvation_Hotel_Service rs=new Reserrvation_Hotel_Service();
        reservation_hotel r=new reservation_hotel(5, 5, 60, 41, 22, d, d);
        //rs.ajouter(r);
        System.out.println(rs.afficher());
        HotelCRUD h=new HotelCRUD();
        System.out.println(h.getAllIdh());
    }
    
}
