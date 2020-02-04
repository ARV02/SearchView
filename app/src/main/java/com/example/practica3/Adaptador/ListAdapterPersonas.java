package com.example.practica3.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.practica3.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterPersonas extends ArrayAdapter<Personas> implements Filterable {
    private ArrayList<Personas> personas;
    private ArrayList<Personas> personasFull;
    Context mContext;
    private static class ViewHolder{
        public TextView nombres,salarios,horas;
    }
    public ListAdapterPersonas(ArrayList<Personas>items, Context context){
        super(context, R.layout.lista_personas,items);
        this.personas = items;
        this.mContext = context;
        personasFull = new ArrayList<>(personas);
    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Personas dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lista_personas, parent, false);
            viewHolder.nombres = convertView.findViewById(R.id.tnombre);
            viewHolder.salarios = convertView.findViewById(R.id.tsueldo);
            viewHolder.horas = convertView.findViewById(R.id.thoras);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;
        viewHolder.nombres.setText(dataModel.getNombre());
        viewHolder.salarios.setText(String.valueOf(dataModel.getSalario()));
        viewHolder.horas.setText(String.valueOf(dataModel.getHoras()));
        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filtrador;
    }
    private Filter filtrador = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Personas> datosFiltrados = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                datosFiltrados.addAll(personasFull);
            }else{
                String filtrosp = constraint.toString().toLowerCase().trim();
                for (Personas items : personasFull){
                    if (items.getNombre().toLowerCase().contains(filtrosp)){
                        datosFiltrados.add(items);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = datosFiltrados;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            personas.clear();
            personas.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}