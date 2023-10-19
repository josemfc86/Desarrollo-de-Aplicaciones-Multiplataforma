Realizaci�n de un programa en java que simula un buz�n de correo (recurso compartido), de forma que se pueda enviar o leer un mensaje. El buz�n s�lo puede almacenar un mensaje, de manera que para poder escribir se debe encontrar vac�o y para poder leer debe estar lleno. Se crean varios hilos lectores y escritores que manejen dicho buz�n de forma sincronizada.

Clases necesarias:

LectorHilo: codificamos las instrucciones que deben ejecutar los hilos encargados de leer los mensajes del recurso compartido buz�n.

EscritorHilo: donde codificaremos las instrucciones que deben ejecutar los hilos encargados de escribir mensajes en el recurso compartido buz�n.

BuzonCorreo: donde encapsularemos el recurso compartido buz�n y haremos visibles los m�todos disponibles por los lectores y escritores para poder acceder de forma sincronizada al buz�n. La clase buz�nCorreo as� concebida posee la estructura de un monitor.

En el programa principal:

Se instancia un objeto BuzonCorreo y varios LectorHilo y EscritorHilo, respectivamente. Se prueba con dos de cada. Se arrancan los hilos y espera a que terminen.

Al final, el programa principal imprimir� la frase "FIN DE LA EJECUCI�N". 

