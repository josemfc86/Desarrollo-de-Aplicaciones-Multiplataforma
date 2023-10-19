package com.example.losperiodicos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.losperiodicos.BDSQLite.PeriodicosTabla;

import java.util.ArrayList;

public class AdaptadorPeriodico extends ArrayAdapter<PeriodicosTabla> {

    private ArrayList<PeriodicosTabla> datos;//la clase PeriodicosTabla almacena los datos de cada línea

    public AdaptadorPeriodico(@NonNull Context context, @NonNull ArrayList<PeriodicosTabla> datosEnviados) {
        super(context, R.layout.list_item, datosEnviados);
        datos = datosEnviados;
    }


    //Este  método  se  llama  una  vez  por  cada  elemento  del  listview
    @NonNull
    @Override
    public View getView(int position,@Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        //Definimos un View y lo inflamos con el Layout
        View item = inflater.inflate(R.layout.list_item, null);

        //Obtenemos las referencias a los elementos del list_item y les introducimos los datos
        TextView lblTitulo =  (TextView) item.findViewById(R.id.lblTitulo);
        lblTitulo.setText(datos.get(position).getNombre());
        TextView lblSubtitulo = (TextView) item.findViewById(R.id.lblSubtitulo);
        lblSubtitulo.setText(datos.get(position).getDescripcion());
        return (item);
    }
}
