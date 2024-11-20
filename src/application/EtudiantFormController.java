package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EtudiantFormController {
	 private static final String URL = "jdbc:mysql://localhost:3306/your_database";
	    private static final String USER = "username";
	    private static final String PASSWORD = "password";
    @FXML
    private Text fname2;

    @FXML
    private TextField nom;

    @FXML
    private Text lname2;

    @FXML
    private Text phone2;

    @FXML
    private TextField cne;

    @FXML
    private Text email2;

    @FXML
    private TextField filiere;

    @FXML
    private Button savebtn;

    @FXML
    private TextField prenom;

    @FXML
    void save(ActionEvent event) {
        String sql = "INSERT INTO etudiants (cne, nom, prenom,filiere) VALUES ( ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cne.getText());
            stmt.setString(2, nom.getText());
            stmt.setString(3, prenom.getText());
            stmt.setString(5, filiere.getText());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Succès", "Enseignant enregistré avec succès.");
            }

        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de l'enregistrement de l'enseignant : " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
