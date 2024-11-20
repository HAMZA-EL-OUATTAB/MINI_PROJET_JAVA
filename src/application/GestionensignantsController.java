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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionensignantsController {
	
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "username";
    private static final String PASSWORD = "password";

    @FXML
    private TableView<Ensignant> ensignantTable;
    @FXML
    private TableColumn<Ensignant,String> CneColumn;
    @FXML
    private TableColumn<Ensignant,String> NomColumn;
    @FXML
    private TableColumn<Ensignant,String> PrenomColumn;
    @FXML
    private TableColumn<Ensignant,String> MatiereColumn;
    @FXML
    private TableColumn<Ensignant,String> FiliereColumn;

    @FXML
    private Button ajouterButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;

    @FXML
    private TextField cneField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField matiereField;
    @FXML
    private TextField filiereField;
    private ObservableList<Ensignant> ensignantList = FXCollections.observableArrayList();

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    @FXML
    public void initialize() {
        CneColumn.setCellValueFactory(new PropertyValueFactory<>("cne"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        FiliereColumn.setCellValueFactory(new PropertyValueFactory<>("filiere"));
        MatiereColumn.setCellValueFactory(new PropertyValueFactory<>("matiere"));


        loadDataFromDatabase();
    }
    private void loadDataFromDatabase() {
        String sql = "SELECT cne, nom, prenom, filiere,matiere FROM ensignants";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
            	ensignantList.add(new Ensignant(
                    rs.getString("cne"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("matiere"),
                    rs.getString("filiere")
                ));
            }
            
            ensignantTable.setItems(ensignantList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void ajouter(ActionEvent event) {
        String sql = "INSERT INTO enseignants (cne, nom, prenom, matiere, filiere) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cneField.getText());
            stmt.setString(2, nomField.getText());
            stmt.setString(3, prenomField.getText());
            stmt.setString(4, matiereField.getText());
            stmt.setString(5, filiereField.getText());

            stmt.executeUpdate();
            showAlert("Succès", "Enseignant ajouté avec succès.");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de l'ajout de l'enseignant : " + e.getMessage());
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        String sql = "UPDATE enseignants SET nom = ?, prenom = ?, matiere = ?, filiere = ? WHERE cne = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomField.getText());
            stmt.setString(2, prenomField.getText());
            stmt.setString(3, matiereField.getText());
            stmt.setString(4, filiereField.getText());
            stmt.setString(5, cneField.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Succès", "Enseignant modifié avec succès.");
            } else {
                showAlert("Erreur", "Aucun enseignant trouvé avec ce CNE.");
            }
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de la modification de l'enseignant : " + e.getMessage());
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        String sql = "DELETE FROM enseignants WHERE cne = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cneField.getText());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Succès", "Enseignant supprimé avec succès.");
            } else {
                showAlert("Erreur", "Aucun enseignant trouvé avec ce CNE.");
            }
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de la suppression de l'enseignant : " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
