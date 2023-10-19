package controlador;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/Vista_GestionDeVehiculos.fxml")); //Definimos la ruta del archivo fxml de la ventana principal.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestión de Vehículos");//Asignamos el titulo de la ventana principal.
        primaryStage.setResizable(false);//Evitamos que el usuario pueda modificar su tamaño.
        primaryStage.setScene(scene);//Cargamos la vista.
        primaryStage.show();//Mostramos la vista.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
