package com.nansoft.prored.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.nansoft.prored.Adapter.OpcionAdapter;
import com.nansoft.prored.Model.Opcion;
import com.nansoft.prored.R;

public class InfoEventoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_eventos);
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizar);
        mSwipeRefreshLayout.setEnabled(false);

        GridView gridView = (GridView) findViewById(R.id.grid_id);
        //adapter=new  ImageAdapter(numbers, this);
        final OpcionAdapter opcionAdapter = new OpcionAdapter(this,R.layout.item_evento);

        gridView.setAdapter(opcionAdapter);

        // se agrega opción de redes
        Opcion objOpcion = new Opcion();
        objOpcion.setId("1");
        objOpcion.setNombre("Eventos1");
        objOpcion.setUrlImagen("http://princesasofia.expohotels.com/wp-content/uploads/2014/12/eventos-empresa-5.jpg");

        // se agrega opción de eventos
        Opcion objOpcion1 = new Opcion();
        objOpcion1.setId("2");
        objOpcion1.setNombre("Eventos2");
        objOpcion1.setUrlImagen("http://www.tribulinux.com/wp-content/uploads/2008/07/eventos.jpg");

        // se agregan las opciones en el adapter
        opcionAdapter.add(objOpcion);
        opcionAdapter.add(objOpcion1);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Opcion objOpcionPresionado = opcionAdapter.getItem(position);

                Intent objIntent = new Intent(getApplicationContext(), MainActivity.class);

                switch (position) {
                    case 0:
                        objIntent = new Intent(getApplicationContext(), RedActivity.class);
                        startActivity(objIntent);
                        break;

                    case 1:
                        // implementar intent de eventos
                        break;
                }


                Toast.makeText(getApplicationContext(), objOpcionPresionado.getNombre(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
