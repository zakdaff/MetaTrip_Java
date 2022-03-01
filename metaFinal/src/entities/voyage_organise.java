
package entities;

/**
 *
 * @author FLAM
 */
public class voyage_organise extends voyage{
    private int Idvo;
    private float Prix_billet;
    private String Airline;
    private int Nb_nuitees;
    
    private EtatDispo etatVoyage;
    private voyage voyage;
    private int Idv;
   
  

    public voyage_organise(int Idvo,float pb,String air,int nb,String pays, String Image_pays) {
        
        this.Idvo=Idvo;
        this.Prix_billet=pb;
        this.Airline=air;
        this.Nb_nuitees=nb;
      
       
    }
    
        public voyage_organise(float pb,String air,int nb) {
        
     
        this.Prix_billet=pb;
        this.Airline=air;
        this.Nb_nuitees=nb;
      
       
    }


    public voyage_organise(float Prix_billet, String Airline, int Nb_nuitees, voyage voyage) {
        
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.voyage = voyage;
    }
    

    
    
    
    public voyage_organise( int Idvo, float Prix_billet, String Airline, int Nb_nuitees, voyage voyage) {
        //super(Idv, pays,Image_pays);
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.voyage = voyage;
    }

 
  

    public String getPays() {
        return this.getVoyage().getPays();
    }

    public String getImage_pays() {
        return this.getVoyage().getImage_pays();
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, EtatDispo etatVoyage, voyage voyage) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
    }

    public EtatDispo getEtatVoyage() {
        return etatVoyage;
    }

    public void setEtatVoyage(EtatDispo etatVoyage) {
        this.etatVoyage = etatVoyage;
    }

    public voyage_organise(float Prix_billet, String Airline, int Nb_nuitees, EtatDispo etatVoyage, voyage voyage) {
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.voyage = voyage;
    }

    public voyage_organise(int Idvo, float Prix_billet, String Airline, int Nb_nuitees, EtatDispo etatVoyage,int Idv) {
        this.Idvo = Idvo;
        this.Prix_billet = Prix_billet;
        this.Airline = Airline;
        this.Nb_nuitees = Nb_nuitees;
        this.etatVoyage = etatVoyage;
        this.Idv=Idv;
    }

  


    

    
    

    public voyage_organise() {     this.Idvo=Idvo;
    }

    public int getIdvo() {
        return Idvo;
    }
    
     public int getIdv() {
        return Idv;
    }
       public void setIdv(int Idvo) {
        this.Idv = Idvo;
    }

    public float getPrix_billet() {
        return Prix_billet;
    }

    public String getAirline() {
        return Airline;
    }

    public voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(voyage voyage) {
        this.voyage = voyage;
    }

    public int getNb_nuitees() {
        return Nb_nuitees;
    }

    public void setIdvo(int Idvo) {
        this.Idvo = Idvo;
    }

    public void setPrix_billet(float Prix_billet) {
        this.Prix_billet = Prix_billet;
    }

    public void setAirline(String Airline) {
        this.Airline = Airline;
    }

    public void setNb_nuitees(int Nb_nuitees) {
        this.Nb_nuitees = Nb_nuitees;
    }

    @Override
    public String toString() {
        return "voyage_organise{" + "Idvo=" + Idvo + ", Prix_billet=" + Prix_billet + ", Airline=" + Airline + ", Nb_nuitees=" + Nb_nuitees + ", etatVoyage=" + etatVoyage + ", voyage=" + voyage + ", Idv=" + Idv + '}';
    }

  
    
    
}
