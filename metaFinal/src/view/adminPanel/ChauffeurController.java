

import Config.Datasource;
import entities.Chauffeur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author ASUS I7
 */
public class ChauffeurController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Tel;
    @FXML
    private TextField Description;
    @FXML
    private TableView<Chauffeur> table;
    @FXML
    private TableColumn<Chauffeur, Integer> id1;
    @FXML
    private TableColumn<Chauffeur, String> Nom1;
    @FXML
    private TableColumn<Chauffeur, String> Prenom1;
    @FXML
    private TableColumn<Chauffeur, String> Tel1;
    @FXML
    private TableColumn<Chauffeur,String> Description1;
    @FXML
    private TableColumn<Chauffeur, String> photo1;
    @FXML
    private TableColumn<Chauffeur, String> Etat;
    @FXML
    private ComboBox<?> etat;

    /**
     * Initializes the controller class.
     */
   private Connection conn = null;
    java.sql.Statement ste = null;
    PreparedStatement pste = null;
    PreparedStatement pste2 = null;
    ResultSet rs = null;
    
    private String[] comboGender = {"Dispo", "Indispo"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //showchauffeur();
             showData();
         comboBox();
   
    }    
      @FXML
    public void comboBox(){
        
        List<String> list = new ArrayList<>();
            List<Integer> list1 = new ArrayList<>();
        for(String data: comboGender){
            
            list.add(data);
            
        }
      ObservableList dataList = FXCollections.observableArrayList(list);
        
        etat.setItems(dataList);}
        
     public ObservableList<Chauffeur> getChauffeurList() {
        ObservableList<Chauffeur> ChauffeurList = FXCollections.observableArrayList();
        conn = Datasource.getInstance().getCnx();
        String req = "SELECT idch,nom,prenom,photo,tel,description,etatDispo FROM `chauffeur`";


        try {
            pste = conn.prepareStatement(req);

            rs = pste.executeQuery();

            while (rs.next()) {
                Chauffeur c = new Chauffeur();



                c.setidch(rs.getInt(1));
                c.setnom(rs.getString(2));
                 c.setprenom(rs.getString(3));
                     c.setphoto(rs.getString(4));
                     c.settel(rs.getString(5));
                      c.setdescription(rs.getString(6));
                       c.setetatDispo(rs.getString(7));

                ChauffeurList.add(c);
          
            }

        } catch (Exception ex) {
            System.out.println("erreur theni: " + ex.getMessage());
        }
        return ChauffeurList;
    }
  
        @FXML
    public void showData(){
       
        ObservableList<Chauffeur> showList = getChauffeurList();
  
        id1.setCellValueFactory(new PropertyValueFactory<Chauffeur, Integer>("idch"));
        Nom1.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("nom"));
        Prenom1.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("prenom"));
        photo1.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("photo"));
        Tel1.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("tel"));
              
                        Description1.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("description"));
                            Etat.setCellValueFactory(new PropertyValueFactory<Chauffeur, String>("etatDispo"));
                          
        table.setItems(showList);
          System.out.print("++++++++"+table.getId().toString());
    }
    
}