/*
Debes añadir una tabla a la base de datos alumnos que represente las matrículas de los alumnos. Consta de los siguientes campos:
DNI: varchar(9).
NombreMódulo: varchar(60).
Curso: varchar(5), el curso se forma con los dos años que lo componen separados por un guión, por ejemplo 11-12.
Nota: double.
Recuerda rellenar la tabla con algunos datos para que puedas hacer pruebas.

Crea un componente nuevo en el proyecto Alumno que para gestionar toda esta información. Además del código necesario para gestionar las propiedades del componente y mantener la información de la base de datos en un vector interno, es preciso que incluyas los siguientes métodos:
seleccionarFila(i): recupera en las propiedades del componente el registro número i del vector.
RecargarDNI(): recarga la estructura interna del componente con las matrículas de un DNI en particular.
AddMatricula(): añade un registro nuevo a la base de datos con la información almacenada en las propiedades del componente.
Dado que el componente puede funcionar en dos modos diferentes (todos los alumnos o un alumno concreto) se generará un evento cada vez que se cambie de modo, es decir, cuando se carguen todas las matrículas se lanzará un evento que lo señale y cuando se carguen las matrículas para un solo alumno también.
Tendrás que crear un proyecto de prueba del componente en el que hagas un listado de todas las matrículas que hay en el sistema, y luego hagas un listado de las matrículas de un alumno concreto.
Cuando cargues la matricula del usuario concreto deberás capturar el evento generado al cambiar de modo.
Añade el código necesario para añadir una matrícula nueva a la base de datos.
 */
package Alumno;

import java.beans.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Manuel Fernández
 */
//Eliminamos los elementos que no hacen falta al crear la clase JavaBeanComponents
public class MatriculasBean implements Serializable {

    private PropertyChangeSupport propertySupport;

    public MatriculasBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            //Para cargar las filas
            this.recargarFilas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //con el boton derecho le damos a ADDProperty, y añadimos los campos de la BD
    //o escribimos las variables y añadimos sus getter y setters 
    protected String DNI;

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Set the value of DNI
     *
     * @param DNI new value of DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    protected String NombreModulo;

    /**
     * Get the value of NombreModulo
     *
     * @return the value of NombreModulo
     */
    public String getNombreModulo() {
        return NombreModulo;
    }

    /**
     * Set the value of NombreModulo
     *
     * @param NombreModulo new value of NombreModulo
     */
    public void setNombreModulo(String NombreModulo) {
        this.NombreModulo = NombreModulo;
    }

    protected String Curso;

    /**
     * Get the value of Curso
     *
     * @return the value of Curso
     */
    public String getCurso() {
        return Curso;
    }

    /**
     * Set the value of Curso
     *
     * @param Curso new value of Curso
     */
    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    protected double Nota;

    /**
     * Get the value of Nota
     *
     * @return the value of Nota
     */
    public double getNota() {
        return Nota;
    }

    /**
     * Set the value of Nota
     *
     * @param Nota new value of Nota
     */
    public void setNota(double Nota) {
        this.Nota = Nota;
    }

    /* Definimos los métodos y atributos privados del componente que usaremos
     * para darle funcionalidad. 
     */
    /**
     * **************************************************
     * Clase auxiliar que usaremos para crear un vector privado de matriculas.
     */
    public class Matricula {

        String DNI;
        String NombreModulo;
        String Curso;
        double Nota;

        public Matricula() {
        }

        public Matricula(String nDNI, String nNombreModulo, String nCurso, double nNota) {
            this.DNI = nDNI;
            this.NombreModulo = nNombreModulo;
            this.Curso = nCurso;
            this.Nota = nNota;
        }

    }

    /**
     * ****************************************************
     * Usaremos un vector auxiliar para cargar la información de la tabla de
     * forma que tengamos acceso a los datos sin necesidad de estar conectados
     * constantemente
     */
    private Vector<Matricula> listadoMatriculas = new Vector();

    public Vector<Matricula> getListadoMatriculas() {
        return listadoMatriculas;
    }

 
    /**
     * ***********************************************************************
     * Actualiza el contenido de la tabla en el vector de matriculas. Las
     * propiedades contienen el valor del primer elementos de la tabla. Dentro
     * del método recargarFilas hacemos una consulta y rellenamos el Vector
     * listadoMatrícula con datos.
     */
    public void recargarFilas() throws ClassNotFoundException {
        listadoMatriculas.removeAllElements();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "");
            Statement s = con.createStatement();
            //Importante mirar bien el nombre de la tabla que se va a ejecutar
            ResultSet rs = s.executeQuery("select * from matriculas");
            while (rs.next()) {
                Matricula a = new Matricula(rs.getString("DNI"),
                        rs.getString("NombreModulo"),
                        rs.getString("Curso"),
                        rs.getDouble("Nota"));

                listadoMatriculas.add(a);
            }
            //instanciamos la clase matricula
            Matricula a = new Matricula();
            a = (Matricula) listadoMatriculas.elementAt(0);
            this.DNI = a.DNI;
            this.NombreModulo = a.NombreModulo;
            this.Curso = a.Curso;
            this.Nota = a.Nota;
            rs.close();//cierro conexion BD
            con.close();//cierro conexion

        } catch (SQLException e) {

            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * ******************************************************
     *
     * @param i numero de la fila a cargar en las propiedades del componente
     *
     * Metodo para ver los datos de cada fila del vector
     */
    public void seleccionarFila(int i) {
        //no puede ser <= las matriculas no salen
        if (i < listadoMatriculas.size()) {
            Matricula a = new Matricula();//instanciamos la clase matricula
            a = (Matricula) listadoMatriculas.elementAt(i);//array con el vector
            this.DNI = a.DNI;
            this.NombreModulo = a.NombreModulo;
            this.Curso = a.Curso;
            this.Nota = a.Nota;

        } else {

            this.DNI = "";
            this.NombreModulo = "";
            this.Curso = "";
            this.Nota = Nota;
        }
    }

    /**
     * Este método recibe el dni que queremos encontrar en nuestro vector y
     * busca las matrícula que estan relacionadas a este DNI, en el caso de que
     * no encuentre ninguna mandará un mensaje notificándolo que no existe
     * ninguna matrícula con ese DNI.
     */
    public void recargarDNI(String dni) throws ClassNotFoundException {

        Matricula a = new Matricula();
        Vector<Matricula> matriculasDNI = new Vector();
        if (dni.equals("")) {
            for (int i = 0; i < this.listadoMatriculas.size(); i++) {
                a = listadoMatriculas.elementAt(i);
                Matricula b = new Matricula();
                this.DNI = b.DNI;
                this.NombreModulo = b.NombreModulo;
                this.Curso = b.Curso;
                this.Nota = b.Nota;
                matriculasDNI.add(b);
            }
            listadoMatriculas = matriculasDNI; //Rellena el listadoMatriculas las matriculas de todos los alumnos.
            receptor.capturarMuestraTodosAlumnos(new BDEvent(this));//Se capta el evento de consulta a la base de datos para mostrar las matriculas de todos los alumnos.
            System.out.println("Total de matriculas registradas: " + matriculasDNI.size());
             
        } else {
            for (int i = 0; i < this.listadoMatriculas.size(); i++) {

                a = listadoMatriculas.elementAt(i);
                if (a.DNI.equals(dni)) {
                    Matricula b = new Matricula();
                    this.DNI = b.DNI;
                    this.NombreModulo = b.NombreModulo;
                    this.Curso = b.Curso;
                    this.Nota = b.Nota;
                    matriculasDNI.add(b);

                }
            }
            listadoMatriculas = matriculasDNI; //Rellena listadoMatriculas solo con las matriculas que perteneces al DNI pasado como parámetro.

            if (matriculasDNI.size() > 0) {
                receptor.capturarMuestraAlumno(new BDEvent(this)); //Se capta el evento de consulta a la base de datos para mostrar las matriculas de un alumno en especifico.
                System.out.println("Cantidad de matriculas que posee este DNI: " + matriculasDNI.size());   
            } else {
                receptor.capturarMuestraAlumno(new BDEvent(this)); //Se capta el evento de consulta a la base de datos para mostrar las matriculas de un alumno en especifico.
                System.out.println("No se han encontrado matriculas para este DNI");
            }
        }

    }

    /**
     * *******************************************************************
     * Codigo para generar eventos cuando se modifica el estado de la BD o se
     * realiza una consulta a esta.
     */
    private BDListener receptor;

    public class BDEvent extends java.util.EventObject {

        // constructor
        public BDEvent(Object source) {
            super(source);
        }
    }

    //Define la interfaz para el nuevo tipo de evento
    public interface BDListener extends EventListener {

        public void capturarBDModificada(BDEvent ev);

        public void capturarMuestraAlumno(BDEvent ev);

        public void capturarMuestraTodosAlumnos(BDEvent ev);
    }

    public void addBDModificadaListener(BDListener receptor) {
        this.receptor = receptor;
    }

    public void removeBDModificadaListener(BDListener receptor) {
        this.receptor = null;
    }

    /**
     * ************************************************************************
     * Método que añade una matricula a la base de datos, añade un registro a la
     * base de datos formado a partir de los valores de las propiedades del
     * componente.
     *
     * Se presupone que se han usado los métodos set para configurar
     * adecuadamente las propiedades con los datos del nuevo registro.
     */
    public void addMatricula() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/alumnos", "root", "");
            PreparedStatement s = con.prepareStatement("insert into matriculas values (?,?,?,?)");

            s.setString(1, DNI);
            s.setString(2, NombreModulo);
            s.setString(3, Curso);
            s.setDouble(4, Nota);

            s.executeUpdate();
            recargarFilas();
            receptor.capturarBDModificada(new BDEvent(this)); //Se capta el evento de que la base de datos ha sido modificada.
        } catch (SQLException ex) {
            Logger.getLogger(MatriculasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *****************************************************
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
