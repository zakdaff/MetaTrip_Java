/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.PartieClient;

/**
 *
 * @author medal
 */
import Config.Datasource;
import Config.Metatrip;
import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;
import entities.localisationvoyage;
import entities.user;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import static java.nio.file.Files.size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javax.swing.JButton;
import services.voyage.voyageService;
import view.adminPanel.UserListController;

public class Mapa extends MapView{
	
/**
 * The map object
 * 
 */
                       protected JFrame parentFrame;
  
	private static Map map;

/**
 * Editable circle options 
 */
	private CircleOptions settingsCircle;
/**
 * Editable LineOptions
 */
	private  PolylineOptions settingsLine;

	
	public CircleOptions getSettingsCircle() {
		return settingsCircle;
	}

	public void setSettingsCircle(CircleOptions settingsCircle) {
		this.settingsCircle = settingsCircle;
	}





	/**
	 * Generate a marker on the LatLongPoint
	 * @param pos of the wanted marker
	 * @return Marker
	 */
	public void generateMarker(LatLng pos)
	{        HashMap<String,LatLng > List = new HashMap< >();
        
        ArrayList<localisationvoyage> localisationvoyage = new ArrayList<localisationvoyage>();
                localisationvoyage= (ArrayList<localisationvoyage>) GetGPS();
                
                        ArrayList<LatLng> LatLng = new ArrayList<LatLng>();
                         ArrayList<Marker> Marker = new ArrayList<Marker>();
        for(int i = 0 ; i < localisationvoyage.size(); i++){
             LatLng TN = new LatLng(localisationvoyage.get(i).getLatitude(),localisationvoyage.get(i).getLongitude());
       
             LatLng.add(TN);
                
        }
         
	   for(int i = 0 ; i < LatLng.size(); i++){
            Marker  Marker1 = new Marker(map);
            Marker1.setPosition(LatLng.get(i));
/*
           
     InfoWindow window = new InfoWindow(map);
                                window.setContent("");
                                window.open(map,Marker1);*/
        }
			
	

                                
                                
       
	}
        		
                          


	/**
	 * Generate a simple nibe between two LatLong points
	 * @param start Start point of the line
	 * @param end End point of the line
	 * @param markers Do you wanna put a marker on the LatLong points
	 */
	public void generateSimplePath(LatLng start,LatLng end,Boolean markers)
	{
		LatLng[] path = {start,end};
		Polyline polyline = new Polyline(map);
		polyline.setPath(path);
		if(markers)
		{
			generateMarker(start);
			generateMarker(end);
		}
	}

	public Map darMap()
	{
		return map;
	}

	/**
	 * Generate a circle area on the map
	 * @param center LatLong of the center of the map
	 * @param radius on meters
	 */
	public void generateArea(LatLng center,Double radius)
	{
		Circle circle = new Circle(map);
		circle.setCenter(center);
		circle.setRadius(radius);
		circle.setVisible(true);
		circle.setOptions(settingsCircle);
	}
        public List<localisationvoyage> GetGPS(){
    
	ArrayList<localisationvoyage> localisationvoyage = new ArrayList<localisationvoyage>(); 	
                           Connection con = Datasource.getInstance().getCnx();
				String select="SELECT  `latitude`, `longitude` FROM `localisationvoyage` ;";
			
				 try {
            PreparedStatement st = con.prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                localisationvoyage u = new localisationvoyage();
                u.setLatitude(rs.getFloat(1));
                u.setLongitude(rs.getFloat(2));
           
                localisationvoyage.add(u);
            }}
         catch (SQLException ex) {
         System.out.println(ex.getMessage());
        }
        return localisationvoyage;}

	/**
	 * Generate a line on the Map on the selected breakpoints
	 * @param markers  do you wanna put a marker on each vertex 
	 * @param path Group of points of the Line
	 */
	public void GenerateLine(boolean markers,LatLng... path)
	{
		if(markers)
		{
			for(LatLng p:path)
			{
				generateMarker(p);
			}
		}
		Polyline polyline = new Polyline(map);
		polyline.setPath(path);
	}
      

	/**
	 * Create a new Map panel whit the param Name
	 * @param pString Name for the map
	 */
	public Mapa(String pString) {

		JFrame frame = new JFrame("Chicago-Data: "+pString);
                 frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		settingsCircle=new CircleOptions();
		settingsCircle.setFillColor("#FF0000");
		settingsCircle.setRadius(2000000);
		settingsCircle.setFillOpacity(0.35);

		settingsLine=new PolylineOptions();
		settingsLine.setGeodesic(true);
		settingsLine.setStrokeColor("#FF0000");
		settingsLine.setStrokeOpacity(1.0);
		settingsLine.setStrokeWeight(2.0);

		// Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady customizes the map object.
		setOnMapReadyHandler(new MapReadyHandler() {
			@Override
			public void onMapReady(MapStatus status) {
				// Check if the map is loaded correctly
				if (status == MapStatus.MAP_STATUS_OK) {
					// Getting the associated map object
					map = getMap();
					MapOptions mapOptions = new MapOptions();
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
					mapOptions.setMapTypeControlOptions(controlOptions);
					
					map.setOptions(mapOptions);
					map.setCenter(new LatLng(42.045527, -88.037659));
					map.setZoom(10);

				}
			}
		});
		System.out.print("Voici Notre Carte Geo :");
		try {
			for(int i=0;i<10;i++)
			{
				TimeUnit.SECONDS.sleep(1);
				System.out.print(".");
			}
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("|");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

                 frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}
public static void test(){
    
              Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("loading");
                alert.setHeaderText("loading");
                alert.setContentText("pouvez-vous attendre quelques secondes pour le chargement de map");
                alert.show();
                try{
               
    		 Mapa example = new Mapa("test");

		example.generateMarker(map.getCenter());
                 }catch(NullPointerException ex){
                                System.out.println("map closed");
                         
                 }
}
	



}