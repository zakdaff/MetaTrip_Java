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
public class localisationvoyage {
    	private int Idl;
         private float latitude;	
        private float longitude;
        
        private int     Idv	;
        private voyage voyage;

    public localisationvoyage() {
    }

    public localisationvoyage(int Idl, float latitude, float longitude, voyage voyage) {
        this.Idl = Idl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.voyage = voyage;
    }

    public localisationvoyage(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public localisationvoyage(float latitude, float longitude, voyage voyage) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.voyage = voyage;
    }

    public int getIdl() {
        return Idl;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getIdv() {
        return Idv;
    }

    public voyage getVoyage() {
        return voyage;
    }

    public void setIdl(int Idl) {
        this.Idl = Idl;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setIdv(int Idv) {
        this.Idv = Idv;
    }

    public void setVoyage(voyage voyage) {
        this.voyage = voyage;
    }

    @Override
    public String toString() {
        return "localisationvoyage{" + "latitude=" + latitude + ", longitude=" + longitude + '}';
    }
        
        
        
    
}
