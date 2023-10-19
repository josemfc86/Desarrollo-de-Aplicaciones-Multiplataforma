/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import controlador.CocheElectricoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class GestionDeVehiculosController implements Initializable {

    @FXML
    private MenuItem MI_CocheE;
    @FXML
    private MenuItem MI_FurgonetaE;
    @FXML
    private MenuItem MI_BicicletaE;
    @FXML
    private MenuItem MI_PatineteE;
    @FXML
    private Button B_CocheE;
    @FXML
    private Button B_Salir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //Metodo que llama al dialogo donde se reserva el coche electrico.
    @FXML
    private void ReservarCoche(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(CocheElectricoController.class.getResource("/vista/Vista_CocheElectrico.fxml")); //Definimos la ruta del archivo fxml del dialogo donde se realiza la reserva del coche electrico.
        Stage stage = new Stage();
        stage.setScene(new Scene(root)); //Cargamos la vista.
        stage.setTitle("Reserva de coche eléctrico"); //Asignamos el titulo de la ventana.
        stage.setResizable(false); //Evitamos que el usuario pueda modificar su tamaño.
        stage.initModality(Modality.APPLICATION_MODAL); //Hace que la ventana sea modal.
        stage.showAndWait(); //Muestra la ventana y espera.
    }
    
    //Metodo que se llama cuando se presiona el boton "Salir".
    @FXML
    private void Salir(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
    
}
