

package pruebaMatriculaBean;
import Alumno.MatriculasBean;
import Alumno.MatriculasBean.BDEvent;
import Alumno.MatriculasBean.BDListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Manuel Fernández
 */
public class AccedeBD implements BDListener{

    MatriculasBean matricula;
    Vector <MatriculasBean.Matricula> listaMatriculas = new Vector();

    AccedeBD() {
        matricula = new MatriculasBean();

        matricula.addBDModificadaListener((BDListener) this);
    }
    
    /*Método que nos muestra el listado de matriculas de todos los alumnos.*/
    public void Matriculas() throws ClassNotFoundException {
        matricula.recargarDNI("");
        listaMatriculas = matricula.getListadoMatriculas();
        
        for (int i = 0; i < listaMatriculas.size(); i++) {
            matricula.seleccionarFila(i);
            System.out.println("Matricula: " + (i+1) + "\n\tDNI:"+matricula.getDNI());
            System.out.println("\tNombreModulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());

        }
    }
    /*Método que recibe como parámetro el DNI de un alumno en concreto y nos muestra el listado de matriculas de dicho alumno.*/
    public void Matriculas(String DNI) throws ClassNotFoundException {
        
        
        matricula.recargarDNI(DNI);
        listaMatriculas = matricula.getListadoMatriculas();
        
        for (int i = 0; i < listaMatriculas.size(); i++) {
            matricula.seleccionarFila(i);
            System.out.println("Matricula: " + (i+1) + "\n\tDNI:"+matricula.getDNI());
            System.out.println("\tNombreModulo: " + matricula.getNombreModulo());
            System.out.println("\tCurso: " + matricula.getCurso());
            System.out.println("\tNota: " + matricula.getNota());

        }
    }

    /*Método para añadir un nuevo registro.*/
    void anade()
    {
        matricula.setDNI("66859599A");
        matricula.setNombreModulo("DAM");
        matricula.setCurso("21-22");
        matricula.setNota(5);

        try {
            matricula.addMatricula();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void capturarBDModificada(BDEvent ev) {
       System.out.println("Se ha añadido un elemento a la base de datos");
    }

    @Override
    public void capturarMuestraAlumno(BDEvent ev) {
       System.out.println("Se muestran todas las matriculas del alumno");
    }

    @Override
    public void capturarMuestraTodosAlumnos(BDEvent ev) {
       System.out.println("Se muestran todas las matriculas existentes en la base de datos");
    }
}
