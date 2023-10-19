package com.example.losperiodicos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.losperiodicos.BDSQLite.ConexionSQLite;
import com.example.losperiodicos.BDSQLite.PeriodicosTabla;

import java.util.ArrayList;


public class Fragment_ListaPeriodicos extends Fragment {

    private View vista;
    private ListView lv;
    ConexionSQLite conn;
    AdaptadorPeriodico adaptador = null;//Adaptador para el ListView
    private ArrayList<PeriodicosTabla> datosPeriodico = new ArrayList<PeriodicosTabla>();// ArrayList de objetos de tipo Periodico declarados

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ConexionSQLite conn = new ConexionSQLite(getContext(), "bd_periodicos", null, 1);

        vista = inflater.inflate(R.layout.fragment_lista_periodicos, container, false);
        lv = vista.findViewById(R.id.lv);
        consultarListaPeriodicos();
        //instanciación del objeto adaptador al cual le pasamos los datos a mostrar
        adaptador = new AdaptadorPeriodico(getContext(), datosPeriodico);
        //asignamos el adaptador a nuestro listview
        lv.setAdapter(adaptador);
        actualizaDatos();   //Obtiene los datos de la base de datos y actualiza el ListView
        registerForContextMenu(lv); //Asociamos el menú contextual al listview.
        // Inflate the layout for this fragment
        return vista;
    }


    public void consultarListaPeriodicos() {
        datosPeriodico.clear();  //Borra el arrayList
        ConexionSQLite conn = new ConexionSQLite(getContext(),"bd_periodicos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        PeriodicosTabla periodico = null;
        Cursor cursor = db.rawQuery("SELECT * FROM periodicos", null);
        while (cursor.moveToNext()) {
            periodico = new PeriodicosTabla();
            periodico.setId(Integer.parseInt(cursor.getString(0)));
            periodico.setNombre(cursor.getString(1));
            periodico.setDescripcion(cursor.getString(2));
            datosPeriodico.add(periodico);
        }
        cursor.close();
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int posicion;
        if(v.getId()==R.id.lv){
            posicion = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            menu.setHeaderTitle(lv.getAdapter().getItem(posicion).toString());
            getActivity().getMenuInflater().inflate(R.menu.menu_contextual, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemBorrar) {
            //Con en método getMenuInfo de MenuItem se obtiene un Contexto del menu, y este tiene almacenada
            // información sobre el componente asociado (registerForContextMenu) al Menu contextual en este caso el ListView.
            // Y desde este nuevo Objeto "info" puedo acceder a la POSICION y saber en cual se hizo el click prolongado.
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            //Llamamos al método de borrar en la base de datos y como ya tenemos la posición del item pulsado obtenemos el ID

            long resul = borrarPeriodico(datosPeriodico.get(info.position).getId());
            System.out.println(resul);
            if (resul>0) { //si ha ido correcto el borrado, lanzamos un mensaje
                Toast.makeText(getContext(), "El periodico " + datosPeriodico.get(info.position).getNombre() + " ha sido borrado", Toast.LENGTH_SHORT).show();
            }

            actualizaDatos();   //Actualiza la lista de datos y notifica al ListView
        }

        return super.onContextItemSelected(item);
    }

    public long borrarPeriodico(int id){
        ConexionSQLite conn = new ConexionSQLite(getContext(), "bd_periodicos", null, 1); //Abrimos la conexión.
        SQLiteDatabase db = conn.getWritableDatabase(); //Preparamos la base de datos para escribir en ella.
        long result = db.delete("periodicos","id = "+id, null); //Borramos el id.
        db.close(); //Cerramos la conexion.
        return result;
    }

    //Método que Actualiza los datos del ListView
    public void actualizaDatos() {
        consultarListaPeriodicos();    //Lee la base de datos
        adaptador.notifyDataSetChanged();    //Actualiza el ListView
    }

}