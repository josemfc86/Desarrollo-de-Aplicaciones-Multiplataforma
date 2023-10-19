package com.example.losperiodicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.losperiodicos.BDSQLite.ConexionSQLite;

public class RegistrarPeriodicoActivity extends AppCompatActivity {

    EditText campoNombre;
    RadioButton generalista, deportes, politica;
    Button anadir;
    String descripcion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_periodico);

        campoNombre = (EditText) findViewById(R.id.et_nombre);
        generalista = (RadioButton) findViewById(R.id.rb_generalista);
        deportes = (RadioButton) findViewById(R.id.rb_deportes);
        politica = (RadioButton) findViewById(R.id.rb_politica);
    }

    public void onClick(View view) {
        registrarPeriodicos();
    }

    public String tipoMedio(){


        RadioGroup rg = findViewById(R.id.radioGroup);     //Obtiene enlace con el radioboton
        int idRadioButton = rg.getCheckedRadioButtonId();   //Obtiene ek id del radiobutton que está seleccionado
        RadioButton radioButton = findViewById(idRadioButton);    //Crea objeto RadioButton con el id de radio botón seleccionado
        descripcion = radioButton.getText().toString();     //Obtiene el texto del radio button que esta seleccionado

        return descripcion;
    }

    public void registrarPeriodicos() {
        ConexionSQLite conn = new ConexionSQLite(this, "bd_periodicos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (campoNombre.getText().toString().isEmpty())  {
            Toast.makeText(this, "Debe rellenar el campo 'Nombre'", Toast.LENGTH_SHORT).show();     //Mensaje
        }else{
            values.put("nombre", campoNombre.getText().toString());
            values.put("descripcion", tipoMedio());
            db.insert("periodicos", "id", values);
            db.close();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);

        }

    }
}