package com.example.comunicaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
Button botonCargar;
EditText Url;
String direccion;
String UrlImagen = "https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/styles/480/public/media/image/2017/01/219984-navegador-web-mas-rapido-que-chrome.jpg?itok=YKWO-2u_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CargarImagensegundoPlano(); //Cargamos la imagen en segundo plano.
        botonCargar = findViewById(R.id.b_Cargar);
        Url = findViewById(R.id.et_URL);


      botonCargar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
                //Nos aseguramos que la URl empieze correctamente.
               direccion = Url.getText().toString();
                if (!direccion.startsWith ("https://")){
                    direccion = "https://" + direccion;
                    Url.setText(direccion);
                }
                //Le enviamos la URl a la WebViewActivity
                enviarDatos(botonCargar);

          }
      });
    }

    //Método que envía la URL a a la actividad que contiene el WebView.
    public void enviarDatos(View v){
        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
        i.putExtra("URL", direccion);
        startActivity(i);
    }

    //Método para cargar la imagen en segundo plano.
    public void CargarImagensegundoPlano(){
        CargarImagen cargarImagen = new CargarImagen();
        cargarImagen.execute(UrlImagen);
    }

    //Clase privada para gestionar la imagen en segundo plano.
    private class CargarImagen extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        @Override
        protected Bitmap doInBackground(String... param) {
            String imagenURL = param[0];
            Bitmap bitmap = null;
            try {
                URL url = new URL(imagenURL);
                // Descargamos la imagen desde una URL
                InputStream input = url.openStream();
                 // Decodificamos el Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) { e.printStackTrace();
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap result) {
            // Recibimos el resultado de doInBackground (el Bitmap)
            // y lo usamos para cargar la imagen a la UI
            ImageView iv = (ImageView) findViewById(R.id.imageView);
            iv.setImageBitmap(result);
        }
    }
}

