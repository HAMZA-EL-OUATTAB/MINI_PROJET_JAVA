package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GestionetudiantController {

	  private static final String URL = "jdbc:mysql://localhost:3306/your_database";
	    private static final String USER = "username";
	    private static final String PASSWORD = "password";
	    @FXML
	    private TableView<Etudiant> etudiantTable;
    @FXML
    private TableColumn<Etudiant,String> CneColumn;

    @FXML
    private TableColumn<Etudiant , String> NomColumn;

    @FXML
    private TableColumn<Etudiant, String> PrenomColumn;

    @FXML
    private TableColumn<Etudiant,String> FiliereColumn;

    @FXML
    private Button Ajouter;

    @FXML
    private Button modifierButton;

    @FXML
    private Button affichernotesButton;

    @FXML
    private Button supprimerButton;
    
    private ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
  
    @FXML
    public void initialize() {
        CneColumn.setCellValueFactory(new PropertyValueFactory<>("cne"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        FiliereColumn.setCellValueFactory(new PropertyValueFactory<>("filiere"));

        loadDataFromDatabase();
    }
    private void loadDataFromDatabase() {
        String sql = "SELECT cne, nom, prenom, filiere FROM etudiants";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                etudiantList.add(new Etudiant(
                    rs.getString("cne"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("filiere")
                ));
            }
            
            etudiantTable.setItems(etudiantList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherNotes(ActionEvent event) {

    }

    @FXML
    void ajouter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Interface/EtudiantForm.fxml"));
            
            Scene scene = new Scene(root, 472, 542);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Ajouter un étudiant");
            stage.setScene(scene);
            
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    

    @FXML
    void modifier(ActionEvent event) {
    	  try {
              Parent root = FXMLLoader.load(getClass().getResource("/Interface/EtudiantForm.fxml"));
              
              Scene scene = new Scene(root, 472, 542);
              scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

              Stage stage = new Stage();
              stage.setTitle("Ajouter un étudiant");
              stage.setScene(scene);
              
              stage.show();
          } catch (Exception e) {
              e.printStackTrace();
          }
    	
    }

    @FXML
    void supprimer(ActionEvent event) {

    }

}
