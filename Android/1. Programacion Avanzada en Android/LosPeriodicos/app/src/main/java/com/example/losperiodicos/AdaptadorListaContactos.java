package com.example.losperiodicos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdaptadorListaContactos extends ArrayAdapter<Contacto> {
    private ArrayList<Contacto> listadoContactos = new ArrayList<>();

    public AdaptadorListaContactos(@NonNull Context context, @NonNull ArrayList<Contacto> lista) {
        super(context, R.layout.list_item, lista);
        this.listadoContactos = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View viewItem = inflater.inflate(R.layout.list_item,null);

        TextView txtNom = viewItem.findViewById(R.id.lblTitulo);
        TextView txtTelefono = viewItem.findViewById(R.id.lblSubtitulo);

        txtNom.setText(this.listadoContactos.get(position).getNombre());
        txtTelefono.setText(this.listadoContactos.get(position).getTelefono());

        return viewItem;
    }
}
