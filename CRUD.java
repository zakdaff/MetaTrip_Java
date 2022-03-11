package src.view.adminPanel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CRUD extends Application {

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

         Parent root = FXMLLoader.load(getClass().getResource("VoyageVirtuel.fxml")); //Main.fxml
        //Parent root = FXMLLoader.load(getClass().getResource("Main.fxml")); //Main.fxml
        // Parent   root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Voyage virtuel");

        stage.setScene(scene);
        stage.show();

    }

}
