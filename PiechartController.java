/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.view.adminPanel;

//import Zahra.guiZ.*;
import controllerZ.AjouterActiviteController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import Config.Datasource;



/**
 * FXML Controller class
 *
 * @author Zakou
 */
public class PiechartController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    
     @FXML
    private LineChart <?, ?>  lineChart;
    
    
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx=Datasource.getInstance().getCnx();
        stat();
    }    
     private void stat()
    {
          
           
      try {
           
          String query = "SELECT COUNT(*),Type FROM abonnement GROUP BY Type" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("Type"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("Statistiques sur les types d'abonnements choisis par nos clients");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
     

    
}
