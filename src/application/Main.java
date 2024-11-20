package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
//<<<<<<< HEAD
        	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Profile_.fxml"));
//=======
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Gestion_Etudiants.fxml"));
//>>>>>>> branch 'master' of https://github.com/HAMZA-EL-OUATTAB/MINI_PROJET_JAVA.git
            Parent root = loader.load();
//<<<<<<< HEAD
            Scene scene = new Scene(root, 900.0,600.0);
//=======
            //Scene scene = new Scene(root, 1000, 800);
//>>>>>>> branch 'master' of https://github.com/HAMZA-EL-OUATTAB/MINI_PROJET_JAVA.git

            scene.getStylesheets().add(getClass().getResource("/View/application.css").toExternalForm());
            primaryStage.setTitle("Gestionnaire de Notes"); // Ajout du titre
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();

            // Affichage d'une alerte en cas d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Impossible de charger la vue");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
