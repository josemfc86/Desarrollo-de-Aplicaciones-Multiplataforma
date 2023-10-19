/*
Mapea las tablas utilizando Hibernate con NetBeans y realiza un proyecto Java llamado HibernateOracle que obtenga lo siguiente:

Crea la base de datos.
Configura y crea la ORM Hibernate.
Realiza una inserción y un borrado sobre la tabla tutorias.
Obtener un listado sobre las tablas profesores y tutorias que visualice codProfe, nombre, apellido, departamento, diaSemana y horaTutoria.
Redactar un documento donde se explique el proceso seguido para la realización de la práctica. 
 */
package com.mycompany.fernandez_cedeno_jose_manuel_ad04_tarea;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author JMFC
 */
public class OperacionesIdiomas { //Creamos una clase para poder crear nuestro gestor de persistencia.

    private static EntityManager manager; // Creamos un ojeto EntityManager para poder realizar las operaciones crud.
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        //Creamos nuestro gestor de persistencia.
        emf = Persistence.createEntityManagerFactory("com.mycompany_fernandez_cedeno_jose_manuel_AD04_Tarea_jar_1.0-SNAPSHOTPU");
        manager = emf.createEntityManager();

        //Buscamos el profesor que vamos a insertar en la nueva tutoría.
        Profesores p = manager.find(Profesores.class, "P005");

        //Creamos la nueva tutoría.
        Tutorias t = new Tutorias("0022", "Nivel avanzado", "Viernes", hora(20, 0), p);

        //Iniciamos la transacción para insertar la nueva tutoría en la tabla tutorias.
        manager.getTransaction().begin();
        manager.persist(t); //Persistimos la tutoría
        manager.getTransaction().commit(); //Confirmamos la transacción.

        System.out.println("Nueva tutoría:");
        System.out.println(t.toString());
        System.out.println("");

        //Hacemos el borrado de una tutoría de la tabla tutorias.
        Tutorias t1 = manager.find(Tutorias.class, "0019"); //Buscamos la tutoría que queremos borrar.
        manager.getTransaction().begin();
        //Realizamos el borrado a través del comando "remove".
        manager.remove(t1);
        System.out.println("Tutoría borrada:");
        System.out.println(t1.toString());
        System.out.println("");
        manager.getTransaction().commit();

        //Obtenemos el listado sobre las tablas profesores y tutorias donde visualizamos codProfe, nombre, apellido, departamento, diaSemana y horaTutoria.
        List<Tutorias> profesoresTutorias = manager.createQuery("FROM Tutorias").getResultList(); //Creamos una lista de objetos Tutorias.
        //Recorremos la lista y a través de cada objeto "Tutorias" sacamos los datos del profesor, ya que cada objeto "Tutorias" tiene como atributo un objeto de tipo "Profesores".
        System.out.println("Listado de profesores y tutorías:");
        for (Tutorias r : profesoresTutorias) {
            System.out.println(r.getProfesor().getCodProfe() +" "+ r.getProfesor().getNombre() +" "+ r.getProfesor().getApellido() +" "+ r.getProfesor().getDepartamento() +" "+ r.getDiaSemana() +" "+ r.getHoraTutoria());
        }
        
        manager.close(); //Destruimos el gestor de entidad.
    }

    //Método para crear el parametro "Date" que se nos pide cuando creamos un nuevo objeto tutoría.
    public static Date hora(int hora, int minutos) {
        Calendar calendario = Calendar.getInstance();
        calendario.clear();
        calendario.set(0, 0, 0, hora, minutos, 0);
        return (Date) calendario.getTime();
    }
}
