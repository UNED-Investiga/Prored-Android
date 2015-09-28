package com.nansoft.prored.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.nansoft.prored.Adapter.ImageAdapter;
import com.nansoft.prored.Adapter.OpcionAdapter;
import com.nansoft.prored.Model.Opcion;
import com.nansoft.prored.R;

public class MainActivity extends AppCompatActivity
{
    static final String[] numbers = new String[] { "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty one",
            "twenty two" };

    GridView gridView;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_id);
        //adapter=new  ImageAdapter(numbers, this);
        OpcionAdapter opcionAdapter = new OpcionAdapter(this,R.layout.item_grid);

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




    }

}
