/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceReclamation;
import entities.reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author yadii
 */
public class UpdateReclamationController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNumtel;
    @FXML
    private TextField txtSujet;
    @FXML
    private TextArea txtMessage;
    @FXML
    private Button btnUpdate;
    reclamation d =null; 
    private final Pattern nomPattern = Pattern.compile("[a-zA-Z]+\\s[a-zA-Z]+");
    private final Pattern emailPattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
    private final Pattern sujetPattern = Pattern.compile("[a-zA-Z0-9 ]+");
    private final Pattern messagePattern = Pattern.compile("[a-zA-Z0-9 ]+");
    private final Pattern numtelPattern = Pattern.compile("[259]\\d{7}");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            btnUpdate.setDisable(true);
        txtNom.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!nomPattern.matcher(newValue).matches()) {
                    txtNom.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                txtNom.setStyle("-fx-text-fill: green;");
            }
           handleKeyType(); 
        });

        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!emailPattern.matcher(newValue).matches()) {
                txtEmail.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                txtEmail.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });

        txtSujet.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!sujetPattern.matcher(newValue).matches()) {
                txtSujet.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                txtSujet.setStyle("-fx-text-fill: green;");
            }
           handleKeyType(); 
        });

        txtMessage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!messagePattern.matcher(newValue).matches()) {
                txtMessage.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                txtMessage.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();
        });
        
        txtNumtel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!numtelPattern.matcher(newValue).matches()) {
                txtNumtel.setStyle("-fx-border-color: red;-fx-text-fill: red;");
            } else {
                txtNumtel.setStyle("-fx-text-fill: green;");
            }
            handleKeyType();    
        });
    }
    public void setelementtoupdate(reclamation d){
        this.d=d;
    System.out.println("les champs a etes modifier :"+ d.toString());
    this.txtNom.setText(d.getNom());
    this.txtEmail.setText(d.getEmail());
    this.txtNumtel.setText(d.getNumtel());
    this.txtSujet.setText(d.getSujet());
    this.txtMessage.setText(d.getMessage());
    }
    @FXML
    private void updateReclamation(ActionEvent event) throws SQLException, IOException {
        String nom = txtNom.getText();
            String email = txtEmail.getText();
            String numtel = txtNumtel.getText();
              String sujet = txtSujet.getText(); 
              String message = txtMessage.getText();
            ServiceReclamation SM =new ServiceReclamation(); 
            this.d.setNom(nom);
            this.d.setEmail(email);
            this.d.setNumtel(numtel);
            this.d.setSujet(sujet);
            this.d.setMessage(message);     
           SM.modifier(d);
          // scene window update
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
         // Load the dashboard.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sidebar.fxml"));
            Parent root = loader.load();
            // Create a new scene with the dashboard.fxml file
            Scene scene = new Scene(root);
            // Set the scene for the current stage
            currentStage.setScene(scene);
    }

    @FXML
    private void handleKeyType() {
        boolean hasInvalidInput = !nomPattern.matcher(txtNom.getText()).matches()
                    || !emailPattern.matcher(txtEmail.getText()).matches()
                    || !sujetPattern.matcher(txtSujet.getText()).matches()
                    || !messagePattern.matcher(txtMessage.getText()).matches()
                    || !numtelPattern.matcher(txtNumtel.getText()).matches();
                btnUpdate.setDisable(hasInvalidInput);
    }
}