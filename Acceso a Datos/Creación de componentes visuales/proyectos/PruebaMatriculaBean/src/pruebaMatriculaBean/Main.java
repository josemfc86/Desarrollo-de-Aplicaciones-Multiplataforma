

package pruebaMatriculaBean;


/**
 *
 * @author José Manuel Fernández
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        AccedeBD gestion = new AccedeBD();
        
        gestion.Matriculas("12345678A"); //Solicito las matriculas de un alumno en particular.
        System.out.println(""); 
        gestion.Matriculas(); //Solicito las matriculas de todos los alumnos.
        System.out.println("");
        gestion.anade();
    }

}
