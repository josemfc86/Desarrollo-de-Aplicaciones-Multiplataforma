package com.example.juegomemory;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ParejasAnimales extends AppCompatActivity {
    //variables para los componentes de la vista
    int intentos;
    int aciertos;
    int recordIntentos;
    ImageButton imb0, imb1, imb2, imb3, imb4, imb5, imb6, imb7, imb8, imb9, imb10, imb11;
    ImageButton[] tablero = new ImageButton[12];
    TextView textoAciertos, textoIntentos;

    //imagenes
    int[] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parejas_animales);
        cargarDatos();
        init();
    }

    //Método para cargar el tablero del juego con las imagenes.
    private void cargarTablero() {
        //Asignamos los ImageButton.
        imb0 = findViewById(R.id.imageButton_00);
        imb1 = findViewById(R.id.imageButton_01);
        imb2 = findViewById(R.id.imageButton_02);
        imb3 = findViewById(R.id.imageButton_03);
        imb4 = findViewById(R.id.imageButton_04);
        imb5 = findViewById(R.id.imageButton_05);
        imb6 = findViewById(R.id.imageButton_06);
        imb7 = findViewById(R.id.imageButton_07);
        imb8 = findViewById(R.id.imageButton_08);
        imb9 = findViewById(R.id.imageButton_09);
        imb10 = findViewById(R.id.imageButton_10);
        imb11 = findViewById(R.id.imageButton_11);

        //Llenamos el array con los ImageButton.
        tablero[0] = imb0;
        tablero[1] = imb1;
        tablero[2] = imb2;
        tablero[3] = imb3;
        tablero[4] = imb4;
        tablero[5] = imb5;
        tablero[6] = imb6;
        tablero[7] = imb7;
        tablero[8] = imb8;
        tablero[9] = imb9;
        tablero[10] = imb10;
        tablero[11] = imb11;
    }

    //Método principal que se va a encargar de agrupar todos los métodos necesario en orden para iniciar la partida.
    private void init() {
        cargarTablero();
        cargarImagenes();
        cargarTexto();
        arrayDesordenado = barajar(imagenes.length); //Cargamos en este array el array de imagenes desordenado que nos devuelve el método "barajar".

        for (int i = 0; i < tablero.length; i++) { //Bucle for para cargar el fondo en cada ImageButton.
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP); //Centramos la imagen en el ImageButton.
            tablero[i].setImageResource(fondo); //Asignamos la imagen.
        }

        for (int i = 0; i < tablero.length; i++) { //Bucle for para habilitar todos los ImageButton y asignar un escuchador de evento.
            final int j = i;
            tablero[i].setEnabled(true); //Se habilita el botón.
            tablero[i].setOnClickListener(new View.OnClickListener() { //Se asigna escuchar de evento.
                @Override
                public void onClick(View view) {
                    if (!bloqueo) { //Al hacer clic sobre el botón una estructura condicional evalua si el botón no se encuentra bloqueado.
                        //Al no estar bloqueado el botón se genera la animación.
                        //Un objeto Animator infla el layout que contiene definida las caracteristicas de la animación.
                        @SuppressLint("ResourceType") Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.giro360);
                        animator.setTarget(view); //Se define el contexto.
                        animator.start(); //Se inicia la animación.
                        comprobar(j, tablero[j]); //Se ejecuta el método comprobar.
                    }
                }
            });
        }

    }

    //Método que asigna a las etiquetas de "aciertoss" e "intentos" el valor correspondiente.
    private void cargarTexto() {
        textoAciertos = findViewById(R.id.textView_Aciertos);
        textoIntentos = findViewById(R.id.textView_Intentos);
        aciertos = 0;
        intentos = 0;
        textoAciertos.setText("Aciertos = " + aciertos);
        textoIntentos.setText("Intentos = " + intentos);
    }

    //Método que carga las imagenes de los animales en un array de enteros y el fondo en una variable a parte.
    private void cargarImagenes() {
        imagenes = new int[]{
                R.drawable.anec,
                R.drawable.cat,
                R.drawable.cavall,
                R.drawable.gos,
                R.drawable.porc,
                R.drawable.vaca
        };

        fondo = android.R.drawable.btn_star_big_on;
    }

    //Método al que se le pasa como párametro una longitud que en nuestro caso será la cantidad de imagenes y devuelve un array de enteros con sus elementos desordenados.
    private ArrayList<Integer> barajar(int longitud) {
        ArrayList<Integer> result = new ArrayList<Integer>(); //Se crea un nuevo array de enteros.
        for (int i = 0; i < longitud * 2; i++) { //Hacemos un bucle for con el doble de la longitud ya que el juego tiene las imagenes repetidas 2 veces.
            result.add(i % longitud); //Al agregar los valores al array creado estos no pueden ser mayor a la longitud, por lo tanto utilizamos el operador de modulo.
        }
        Collections.shuffle(result); //Desordenamos el array a devolver.
        return result;
    }

    //Método que recibe un entero y un ImageButton como parámetros, mediante este método comprobamos si dos imagenes son iguales.
    @SuppressLint("ResourceType")
    private void comprobar(int i, final ImageButton imgb) {
        if (primero == null) {
            primero = imgb; //Se asigna a la varible primero el ImageButton pasado por parametro.
            primero.setEnabled(false); //Se deshabilita el onClick en el boton.
            numeroPrimero = arrayDesordenado.get(i); //Cargamos en "numeroPrimero" el elemento "i" del "arrayDesordenado".
            handler.postDelayed(new Runnable() { //Con el objeto "handler" podemos crear un hilo al cual le podemos asignar un delay que nos retrase la aparicion de la imagen,
                @Override                        //con el fin de darle tiempo a la animacion a que se ejecute sin aparecer la imagen del animal.
                public void run() {
                    MediaPlayer mp; //Creamos un objeto mediaplayer para ejcutar los sonidos de los animales.
                    primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    primero.setImageResource(imagenes[arrayDesordenado.get(i)]); //Asignamos al ImageButton "primero" la imagen que nos asigne el "arrayDesordenado".
                    //Desarrollamos una estructura condicional "if" para asignar a cada animal su sonido dependiendo de la posicion que tenga en el array de imagenes.
                    if (imagenes[arrayDesordenado.get(i)] == imagenes[0]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.anec);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[1]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.gat);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[2]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.cavall);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[3]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.gos);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[4]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.porc);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[5]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.vaca);
                        mp.start();
                    }
                }
            },1000); //Le damos un delay de 1000 milisegundo que es el tiempo que dura la animacion.
        } else {
            bloqueo = true; //Si ya hay asignada una imagen a la variable primero, la variable condicional "bloqueo" pasa a ser true de esta manera el usuario no puede hacer clic sobre mas botones.
            numeroSegundo = arrayDesordenado.get(i); //Cargamos en "numeroSegundo" el elemento "i" del "arrayDesordenado".
            handler.postDelayed(new Runnable() { //Creamos un nuevo hilo con un delay para que realice la misma funcion que el descrito anteriormente pero en el segundo ImageButton.
                @Override
                public void run() {
                    MediaPlayer mp;
                    imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
                    imgb.setEnabled(false);
                    if (imagenes[arrayDesordenado.get(i)] == imagenes[0]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.anec);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[1]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.gat);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[2]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.cavall);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[3]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.gos);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[4]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.porc);
                        mp.start();
                    } else if (imagenes[arrayDesordenado.get(i)] == imagenes[5]) {
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.vaca);
                        mp.start();
                    }
                }
            },1000);
            //Esta estructura condicional verifica si las 2 imagenes de los botonos son iguales a traves de los numero asignados a las variables "numeroPrimero" y "numeroSegundo".
            if (numeroPrimero == numeroSegundo) {
                //Reinicia los valores de "primero" y bloqueo" para poder realizar la operacion de comprobacion en la siguiente jugada.
                primero = null;
                bloqueo = false;
                intentos++;
                aciertos++;
                textoIntentos.setText("Intentos = " + intentos); //Establece el numero de intentos en la etiqueta "Intentos".
                textoAciertos.setText("Aciertos = " + aciertos); ////Establece el numero de aciertos en la etiqueta "Aciertos".
                //Si aciertos es igual a la longitud del array de imagenes, mostramos un mensaje de exito al usuario por haber completado el juego
                if (aciertos == imagenes.length) {
                    //En el caso de que los intentos sean menor al record de intentos le indicamos al usuario que ha logrado un nuevo record y guardamos ese dato.
                    if (intentos < recordIntentos) {
                        Toast.makeText(getApplicationContext(), "Has ganado!! Felicidades has marcado un nuevo record con " + intentos + " intentos", Toast.LENGTH_LONG).show();
                        recordIntentos = intentos;
                        guardarDatos();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent k = new Intent(getApplicationContext(), MainActivity.class); //Luego de finalizar la partida y mostrar el mensaje, enviamos al usuario a la
                                startActivity(k);                                                   //pantalla principal.
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(getApplicationContext(), "Has ganado!! El número de intentos han sido: " + intentos, Toast.LENGTH_LONG).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent j = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(j);
                            }
                        }, 2000);

                    }
                }
            } else { //Si ambas imagenes no son iguales.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {//Con este delay damos tiempo a que terminen las animaciones del primer y segundo boton.
                        intentos++; //Sumamos intentos.
                        textoIntentos.setText("Intentos = " + intentos); //Mostramos en etiqueta el numero de intentos.
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);//Centramos imagen del primer boton.
                        primero.setImageResource(fondo);//Establecemos imagen de fondo en la variable "primero".
                        primero.setEnabled(true);//Habilitamos buton de la variable "primero".
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);//Centramos imagen del segundo boton.
                        imgb.setImageResource(fondo);//Establecemos imagen de fondo del segundo boton.
                        imgb.setEnabled(true);//Habilitamos el segundo boton.
                        //Reiniciamos variables.
                        bloqueo = false;
                        primero = null;

                    }
                }, 2000);
            }
        }
    }

    //Método que guarda los datos de la variable intentos en la variable recordIntentos.
    private void guardarDatos(){
        SharedPreferences preferences = getSharedPreferences("intentos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("recordIntentos", recordIntentos);
        editor.commit();
    }

    //Método que carga el valor de la variable recordIntentos, si esta es nula, le asigna un valor de 1000.
    private void cargarDatos(){
        SharedPreferences preferences = getSharedPreferences("intentos", Context.MODE_PRIVATE);
        recordIntentos = preferences.getInt("recordIntentos",1000);
    }
}