package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.practica3.Adaptador.ListAdapterPersonas;
import com.example.practica3.Adaptador.Personas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText cnombre,csueldo,choras;
    private ListAdapterPersonas adapter;
    private ArrayList<Personas> items;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cnombre = findViewById(R.id.cnombre);
        csueldo = findViewById(R.id.choras);
        choras = findViewById(R.id.csalario);
        listView = findViewById(R.id.lista);
        items = new ArrayList<>();
    }

    public void Datos(View view){
        if(!cnombre.getText().toString().isEmpty() && !csueldo.getText().toString().isEmpty() && !choras.getText().toString().isEmpty()){
            String nombre = cnombre.getText().toString();
            double sueldo = Double.parseDouble(csueldo.getText().toString());
            int horas = Integer.parseInt(choras.getText().toString());
            double pago = sueldo * horas;
            items.add(new Personas(nombre,sueldo,horas));
            adapter = new ListAdapterPersonas(items,getApplicationContext());
            listView.setAdapter(adapter);
            Limpiar(view);
        }else{
            Toast.makeText(this,"Uno de los campos esta vacio",Toast.LENGTH_LONG).show();
        }
    }

    public void Limpiar(View view){
        cnombre.setText("");
        csueldo.setText("");
        choras.setText("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.buscador, menu);

        MenuItem searchItem = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
    /*
    public void filtrar(String texto){
        ArrayList<Personas> filtrarlista = new ArrayList<>();
        for(Personas personas : items){
            if(personas.getNombre().toLowerCase().contains(texto.toLowerCase())){
                filtrarlista.add(personas);
            }
        }

    }*/
}
