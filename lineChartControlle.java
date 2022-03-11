/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.view.adminPanel;

/**
 *
 * @author macbook
 */
import crud_1.*;
import Config.Datasource;
import controllerZ.AjouterActiviteController;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilsZ.DataSource;

public class lineChartControlle implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    Button count;

    @FXML
    private LineChart<?, ?> lineChart;

    ObservableList<LineChart.Data> data = FXCollections.observableArrayList();

    @FXML
    void stat(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stat2();
        connect = Datasource.getInstance().getCnx();

    }

//       private void stat()
//    {
//          
//           
//      try {
//           
//          String query = "SELECT COUNT(*) AS JANVI FROM abonnement WHERE Date_achat BETWEEN \"2022-02-01\" AND \"2022-05-01\";" ;
//       
//             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
//             rs = PreparedStatement.executeQuery();
//            
//                     
//            while (rs.next()){        
//                System.out.println(rs.getInt("JANVI") );
//            }     
//        } catch (SQLException ex) {
//            Logger.getLogger(AjouterActiviteController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      
//        lineChart.setTitle("Statistiques sur les types d'abonnements choisis par nos clients");
//        lineChart.setLegendSide(Side.LEFT);
//        
//    }
//     
    @FXML
    private void count(ActionEvent event) {
        int nb1 = 0;
        int nb2 = 0;
        int nb3 = 0;
        int nb4 = 0;
        int nb5 = 0;
        int nb6 = 0;
        int nb7 = 0;
        int nb8 = 0;
        int nb9 = 0;
        int nb10 = 0;
        int nb11 = 0;
        int nb12 = 0;

        connect = Datasource.getInstance().getCnx();

        String sql1 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-01-01\" AND \"2022-01-31\";";

        try {

            prepare = connect.prepareStatement(sql1);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb1 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb1);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }

        String sql2 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-02-01\" AND \"2022-02-28\";";

        try {

            prepare = connect.prepareStatement(sql2);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb2 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb2);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql3 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-03-01\" AND \"2022-03-31\";";

        try {

            prepare = connect.prepareStatement(sql3);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb3 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " +nb3);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql4 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-04-01\" AND \"2022-04-30\";";

        try {

            prepare = connect.prepareStatement(sql4);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb4 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb4);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql5 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-05-01\" AND \"2022-05-31\";";

        try {

            prepare = connect.prepareStatement(sql5);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb5 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb5);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql6 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-06-01\" AND \"2022-05-30\";";

        try {

            prepare = connect.prepareStatement(sql6);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb6 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb6);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql7 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-07-01\" AND \"2022-07-31\";";

        try {

            prepare = connect.prepareStatement(sql7);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb7 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb7);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql8 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-08-01\" AND \"2022-08-31\";";

        try {

            prepare = connect.prepareStatement(sql8);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb8 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb8);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql9 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-09-01\" AND \"2022-09-30\";";

        try {

            prepare = connect.prepareStatement(sql9);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb9 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb9);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql10 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-10-01\" AND \"2022-10-31\";";

        try {

            prepare = connect.prepareStatement(sql10);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb10 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb10);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql11 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-11-01\" AND \"2022-11-30\";";

        try {

            prepare = connect.prepareStatement(sql11);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb11 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb11);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
               String sql12 = "SELECT COUNT(*) FROM abonnement WHERE Date_achat BETWEEN \"2022-12-01\" AND \"2022-12-31\";";

        try {

            prepare = connect.prepareStatement(sql12);
            result = prepare.executeQuery();

            while (result.next()) {
                //What to put here
                nb12 = result.getInt(1);
                System.out.println("nombreeeeee d'abonnements: " + nb12);

            }

        } catch (SQLException ex) {
            System.out.println("errrreur" + ex);
        }
        

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Janvier", nb1));
        series.getData().add(new XYChart.Data("Fevrier", nb2));
        series.getData().add(new XYChart.Data("Mars", nb3));
        series.getData().add(new XYChart.Data("Avril", nb4));
        series.getData().add(new XYChart.Data("Mai", nb5));
        series.getData().add(new XYChart.Data("Juin", nb6));
        series.getData().add(new XYChart.Data("Juillet", nb7));
        series.getData().add(new XYChart.Data("Aout", nb8));
        series.getData().add(new XYChart.Data("Septembre", nb9));
        series.getData().add(new XYChart.Data("Octobre", nb10));
        series.getData().add(new XYChart.Data("Novembre", nb11));
        series.getData().add(new XYChart.Data("Decembre", nb12));
        lineChart.getData().addAll(series);

    }

    @FXML

    private void stat2() {

        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Janvier", 3));
        series.getData().add(new XYChart.Data("Fevrier", 12));
        series.getData().add(new XYChart.Data("Mars", 10));
        series.getData().add(new XYChart.Data("Avril", 15));
        series.getData().add(new XYChart.Data("Mai", 12));
        series.getData().add(new XYChart.Data("Juin", 8));
        series.getData().add(new XYChart.Data("Juillet", 5));
        series.getData().add(new XYChart.Data("Aout", 8));
        series.getData().add(new XYChart.Data("Septembre", 11));
        series.getData().add(new XYChart.Data("Octobre", 12));
        series.getData().add(new XYChart.Data("Novembre", 9));
        series.getData().add(new XYChart.Data("Decembre", 18));
        lineChart.getData().addAll(series);
//        lineChart.lookup( ".chart-plot-background").setStyle("-fx-background-color: transparent;");
//        series.getNode().setStyle("-fx-stroke: #FFD6DC");
    }

}
