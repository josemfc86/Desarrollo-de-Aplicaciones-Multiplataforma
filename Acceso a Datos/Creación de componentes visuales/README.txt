Se añade una tabla a la base de datos alumnos que represente las matrículas de los alumnos. Consta de los siguientes campos:
DNI: varchar(9).
NombreMódulo: varchar(60).
Curso: varchar(5), el curso se forma con los dos años que lo componen separados por un guión, por ejemplo 11-12.
Nota: double.
Recuerda rellenar la tabla con algunos datos para que puedas hacer pruebas.

Se crea un componente nuevo en el proyecto Alumno que para gestionar toda esta información.
Además del código necesario para gestionar las propiedades del componente y mantener la información de la base de datos en un vector interno, se incluyen los siguientes métodos:
seleccionarFila(i): recupera en las propiedades del componente el registro número i del vector.

RecargarDNI(): recarga la estructura interna del componente con las matrículas de un DNI en particular.

AddMatricula(): añade un registro nuevo a la base de datos con la información almacenada en las propiedades del componente.

Dado que el componente puede funcionar en dos modos diferentes (todos los alumnos o un alumno concreto) se genera un evento cada vez que se cambia de modo, es decir, cuando se carguen todas las matrículas se lanza un evento que lo señale y cuando se cargan las matrículas para un solo alumno también.

Se crea un proyecto de prueba del componente en el que se crea un listado de todas las matrículas que hay en el sistema, y luego un listado de las matrículas de un alumno concreto.

Cuando se carga la matricula del usuario en concreto se captura el evento generado al cambiar de modo.

Se agrega el código necesario para añadir una matrícula nueva a la base de datos.
