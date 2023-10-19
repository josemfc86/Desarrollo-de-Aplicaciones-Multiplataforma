package com.example.losperiodicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.losperiodicos.BDSQLite.ConexionSQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.imageView);
        Picasso.get()
                .load("http://bit.ly/2fVot9z.")
                .error(R.mipmap.ic_launcher).into(imagen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* registrarPeriodicos(); Corro la primera vez la aplicación para registar algunos periodicos en la base de datos y luego comento el método para que no se sigan
                              registrando los mismo periodicos en la base de datos cada vez que se abra la aplicación.*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItemMedio) {
            Intent i = new Intent(this, RegistrarPeriodicoActivity.class);
            startActivity(i);
        } else if (id == R.id.menuItemContactos) {
            Intent i = new Intent(this, ContactosActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    /*Registro algunos periodicos en la base de datos la primera vez que se corre la aplicación.

    public void registrarPeriodicos() {
        ConexionSQLite conn = new ConexionSQLite(this, "bd_periodicos", null, 1);
        SQLiteDatabase bd = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", "El País");
        values.put("descripcion", "Noticias");
        bd.insert("periodicos", "nombre", values);

        values.put("nombre", "ABC");
        values.put("descripcion", "Noticias");
        bd.insert("periodicos", "nombre", values);

        values.put("nombre", "El Correo");
        values.put("descripcion", "Noticias");
        bd.insert("periodicos", "nombre", values);

        values.put("nombre", "Marca");
        values.put("descripcion", "Deportes");
        bd.insert("periodicos", "nombre", values);

        bd.close();
    }*/
}