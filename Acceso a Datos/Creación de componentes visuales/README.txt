Se a�ade una tabla a la base de datos alumnos que represente las matr�culas de los alumnos. Consta de los siguientes campos:
DNI: varchar(9).
NombreM�dulo: varchar(60).
Curso: varchar(5), el curso se forma con los dos a�os que lo componen separados por un gui�n, por ejemplo 11-12.
Nota: double.
Recuerda rellenar la tabla con algunos datos para que puedas hacer pruebas.

Se crea un componente nuevo en el proyecto Alumno que para gestionar toda esta informaci�n.
Adem�s del c�digo necesario para gestionar las propiedades del componente y mantener la informaci�n de la base de datos en un vector interno, se incluyen los siguientes m�todos:
seleccionarFila(i): recupera en las propiedades del componente el registro n�mero i del vector.

RecargarDNI(): recarga la estructura interna del componente con las matr�culas de un DNI en particular.

AddMatricula(): a�ade un registro nuevo a la base de datos con la informaci�n almacenada en las propiedades del componente.

Dado que el componente puede funcionar en dos modos diferentes (todos los alumnos o un alumno concreto) se genera un evento cada vez que se cambia de modo, es decir, cuando se carguen todas las matr�culas se lanza un evento que lo se�ale y cuando se cargan las matr�culas para un solo alumno tambi�n.

Se crea un proyecto de prueba del componente en el que se crea un listado de todas las matr�culas que hay en el sistema, y luego un listado de las matr�culas de un alumno concreto.

Cuando se carga la matricula del usuario en concreto se captura el evento generado al cambiar de modo.

Se agrega el c�digo necesario para a�adir una matr�cula nueva a la base de datos.
