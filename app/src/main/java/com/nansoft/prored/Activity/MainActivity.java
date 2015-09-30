package com.nansoft.prored.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.nansoft.prored.Adapter.OpcionAdapter;
import com.nansoft.prored.Model.Opcion;
import com.nansoft.prored.R;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizar);
        mSwipeRefreshLayout.setEnabled(false);

        GridView gridView = (GridView) findViewById(R.id.grid_id);
        //adapter=new  ImageAdapter(numbers, this);
        final OpcionAdapter opcionAdapter = new OpcionAdapter(this,R.layout.item_grid);

        gridView.setAdapter(opcionAdapter);

        // se agrega opción de redes
        Opcion objOpcion = new Opcion();
        objOpcion.setId("1");
        objOpcion.setNombre("Redes");
        objOpcion.setUrlImagen("http://thumbs.dreamstime.com/z/persona-que-se-ejecuta-con-la-carpeta-26499014.jpg");

        // se agrega opción de eventos
        Opcion objOpcion1 = new Opcion();
        objOpcion1.setId("2");
        objOpcion1.setNombre("Eventos");
        objOpcion1.setUrlImagen("http://thumbs.dreamstime.com/z/persona-que-se-ejecuta-con-la-carpeta-26499014.jpg");

        // se agregan las opciones en el adapter
        opcionAdapter.add(objOpcion);
        opcionAdapter.add(objOpcion1);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Opcion objOpcionPresionado = opcionAdapter.getItem(position);

                Intent objIntent = new Intent(getApplicationContext(),MainActivity.class);

                switch (position)
                {
                    case 0:
                        objIntent = new Intent(getApplicationContext(),RedActivity.class);
                        startActivity(objIntent);
                        break;

                    case 1:
                        // implementar intent de eventos
                        objIntent = new Intent(getApplicationContext(),InfoEventoActivity.class);
                        startActivity(objIntent);
                        break;
                }



                Toast.makeText(getApplicationContext(), objOpcionPresionado.getNombre(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
