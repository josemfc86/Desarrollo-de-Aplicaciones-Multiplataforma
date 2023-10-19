package com.example.juegomemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Variables para los componentes de la vista.
    Button jugar;
    Button salir;
    int recordIntentos;
    TextView recordIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugar = findViewById(R.id.button_jugar);
        salir = findViewById(R.id.button_salir);
        recordIn = findViewById(R.id.tv_recordIntentos);

        //Cargamos el dato del record de intentos en la actividad principal.
        cargarDatos();
        if (recordIntentos == 1000){
            recordIn.setText("Record de intentos = ");
        }else{
            recordIn.setText("Record de intentos = "+recordIntentos);
        }

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Cargamos el método para iniciar el juego en el botón Jugar.
                IniciarJuego();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Para finalizar la aplicación con exito hacemos un llamado a la actividad principal
                //pero en el momento del llamado hacemos que se borren todas la actividades que tengamos.
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }

    //Método para iniciar juego.
    private void IniciarJuego() {
        Intent i = new Intent(this, ParejasAnimales.class);
        startActivity(i);
    }

    //Método para cargar el dato de record de intentos.
    private void cargarDatos(){
        SharedPreferences preferences = getSharedPreferences("intentos", Context.MODE_PRIVATE);
        recordIntentos = preferences.getInt("recordIntentos",1000);
    }
}