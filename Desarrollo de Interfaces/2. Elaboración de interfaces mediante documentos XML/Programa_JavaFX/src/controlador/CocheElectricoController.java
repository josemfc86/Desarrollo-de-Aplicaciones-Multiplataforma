
package controlador;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.plaf.RootPaneUI;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class CocheElectricoController implements Initializable {

    @FXML
    private TextField TF_Nombre;
    @FXML
    private TextField TF_Apellidos;
    @FXML
    private TextField TF_ID;
    @FXML
    private TextField TF_Edad;
    @FXML
    private RadioButton RB_Cables;
    @FXML
    private ComboBox<String> CB_Tipo;
    @FXML
    private Label L_Edad;
    @FXML
    private Label L_Cables;
    @FXML
    private TextField TF_Telefono;
    @FXML
    private TextField TF_Kms;
    @FXML
    private DatePicker DP_Recogida;
    @FXML
    private DatePicker DP_Devolucion;
    @FXML
    private CheckBox CB_Cadenas;
    @FXML
    private CheckBox CB_SillaB;
    @FXML
    private CheckBox CB_Seguro;
    @FXML
    private CheckBox CB_CancelacionG;
    @FXML
    private CheckBox CB_NoPrecisa;
    @FXML
    private Button B_Reservar;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CB_Tipo.getItems().addAll("COMPACTO", "BERLINA", "SUV", "DEPORTIVO", "TESLA");
        L_Edad.setVisible(false);
        TF_Edad.setVisible(false);
        RB_Cables.setVisible(false);
        L_Cables.setVisible(false);
     }    

    @FXML
    private void Reservar(ActionEvent event) {
        String nombre, apellidos, telefono, tipo;
        int validacion = 0;

        nombre = TF_Nombre.getText().trim();
        apellidos = TF_Apellidos.getText().trim();
        telefono = TF_ID.getText().trim();
        
        // El siguiente codigo marca en rojo los campos obligatorios cuando estos no han sido rellenados.
        if(nombre.equals("")){
            TF_Nombre.setBackground(Background.fill(Color.RED));
            validacion++;
        }

        if(apellidos.equals("")){
            TF_Apellidos.setBackground(Background.fill(Color.RED));
            validacion++;
        }

        if(telefono.equals("")){
            TF_ID.setBackground(Background.fill(Color.RED));
            validacion++;
        }
        
        //Codigo para indicarle al usuario que ha rellenado los campos obligatorios satisfactoriamente.
        if (validacion == 0) {
            Limpiar();
            TF_Nombre.setBackground(Background.fill(Color.GREEN));
            TF_Apellidos.setBackground(Background.fill(Color.GREEN));
            TF_ID.setBackground(Background.fill(Color.GREEN));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Reservado");
            alert.setContentText("El vehículo se ha reservado con éxito.");
            alert.showAndWait();
           
            TF_Nombre.setBackground(Background.fill(Color.WHITE));
            TF_Apellidos.setBackground(Background.fill(Color.WHITE));
            TF_ID.setBackground(Background.fill(Color.WHITE));
          
            
        }
        // Mediante la siguiente ventan de alerta le indicamos al usuario que debe rellenar los campos obligatorios.
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes rellenar los campos indicados.");
            alert.showAndWait();  
        }
    }
    
    //Creamos el metodo que limpie los campos cuando hacemos la reserva.
     public void Limpiar(){
        TF_Nombre.setText("");
        TF_Apellidos.setText("");
        TF_ID.setText("");
        CB_Tipo.setValue("");
        TF_Telefono.setText("");
        TF_Kms.setText("");
        DP_Recogida.setValue(null);
        DP_Devolucion.setValue(null);    
    }
     
     //Metodo que activa y desactiva los componentes extras del modelo Tesla.
    @FXML
    private void Escoger(ActionEvent event) {
        if (CB_Tipo.getValue().equals("TESLA")){
            L_Edad.setVisible(true);
            TF_Edad.setVisible(true);
            RB_Cables.setVisible(true);
            L_Cables.setVisible(true);
        }
        else{
            L_Edad.setVisible(false);
            TF_Edad.setVisible(false);
            RB_Cables.setVisible(false);
            L_Cables.setVisible(false);
        }
    }
    
}
