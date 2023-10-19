package com.example.losperiodicos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactosActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private Toolbar toolbar;
    private ListView l;
    private ArrayList<Contacto> contactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        toolbar = findViewById(R.id.t_contactos);
        toolbar.setTitle("Contactos");
        setSupportActionBar(toolbar);
        showContacts();
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            this.contactos = listaContactos(); //Llamamos al método para obtener los contactos del movil.
            l = findViewById(R.id.listaContactos);
            AdaptadorListaContactos adatadorListaContactos = new AdaptadorListaContactos(this,contactos);
            l.setAdapter(adatadorListaContactos);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Hasta que otorgues el permiso, no se te podrán mostrar los contactos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    Método para obtener los contactos del dispositivo. Realiza una consulta a la tabla de contactos del dispositivo, seleccionando solo los contactos que tienen número
    telefónico. Los nombres de los contactos se guardan en una lista de contactos que nos devuelve el return.
     */
    private ArrayList<Contacto> listaContactos(){
        ArrayList<Contacto> contactosMovil = new ArrayList<Contacto>();

        String[] proyeccion = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER}; //Especificamos las columnas que queremos obtener.

        String seleccion = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?"; //Especificamos que solo queremos los contactos que tienen número telefonico.
        String[] seleccionArgs = {"1"}; //Argumentos de la selección, especificando que se quieren solo los contactos con número telefónico

        Cursor cursor = getContentResolver().query( //Realiza la consulta a la tabla de contactos.
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, //Tabla sobre la que realizamos el query.
                proyeccion, seleccion, seleccionArgs, null);

        while (cursor.moveToNext()){ //Recorriendo el resultado de la consulta.
            String nombre = cursor.getString(0); //Obtenemos el nombre del contacto en la posición 1.
            String telefono = cursor.getString(1); //Obtenemos el telefono del contacto en la posición 2.
            contactosMovil.add(new Contacto(nombre, telefono)); //Añadimos el contacto a la lista de contactos.
        }
        cursor.close();
        return contactosMovil;
    }
}