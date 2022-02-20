/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class Voyage_virtuel extends voyage {

    private int Idvv;
    private String Video;
    private String Image_v;
    private abonnement abonnement;

    private voyage voyage;

    public Voyage_virtuel(int Idvv, String Video, String Image_v, voyage voyage, String pays, String Image_pays) {
        super(pays, Image_pays);
        this.Idvv = Idvv;
        this.Video = Video;
        this.Image_v = Image_v;
        this.voyage = voyage;
    }

    public Voyage_virtuel(int Idvv, String Video, String Image_v, voyage voyage, int Idv, String pays, String Image_pays) {
        super(Idv, pays, Image_pays);
        this.Idvv = Idvv;
        this.Video = Video;
        this.Image_v = Image_v;
        this.voyage = voyage;
    }

    public Voyage_virtuel(int Idvv, String Video, String Image_v, voyage voyage) {
        this.Idvv = Idvv;
        this.Video = Video;
        this.Image_v = Image_v;
        this.voyage = voyage;
    }

    public Voyage_virtuel(int Idvv, String Video, String Image_v, voyage voyage, int Idv) {
        super(Idv);
        this.Idvv = Idvv;
        this.Video = Video;
        this.Image_v = Image_v;
        this.voyage = voyage;
    }
   
    public Voyage_virtuel() {
        this.Idvv=Idvv;
    }

    public Voyage_virtuel(int Idvv) {
        this.Idvv = Idvv;
    }

    public Voyage_virtuel(String Video, String Image_v, voyage voyage) {
        this.Video = Video;
        this.Image_v = Image_v;
        this.voyage = voyage;
    }

    public int getIdvv() {
        return Idvv;
    }

    public void setIdvv(int Idvv) {
        this.Idvv = Idvv;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    public String getImage_v() {
        return Image_v;
    }

    public void setImage_v(String Image_v) {
        this.Image_v = Image_v;
    }

    public voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(voyage voyage) {
        this.voyage = voyage;
    }

    public Voyage_virtuel(String Video, String Image_v, abonnement abonnement, voyage voyage) {
        this.Video = Video;
        this.Image_v = Image_v;
        this.abonnement = abonnement;
        this.voyage = voyage;
    }

    public abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public Voyage_virtuel(int Idvv, String Video, String Image_v, abonnement abonnement, voyage voyage) {
        this.Idvv = Idvv;
        this.Video = Video;
        this.Image_v = Image_v;
        this.abonnement = abonnement;
        this.voyage = voyage;
    }
    
    

}
