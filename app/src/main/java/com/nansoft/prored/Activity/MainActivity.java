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

        for (int i = 0; i <20; i++)
        {
            opcionAdapter.add(new Opcion("1","Opcion " + i,"na"));
        }


    }

}
