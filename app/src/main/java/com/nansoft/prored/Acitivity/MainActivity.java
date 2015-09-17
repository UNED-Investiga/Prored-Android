package com.nansoft.prored.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.nansoft.prored.Adapter.ImageAdapter;
import com.nansoft.prored.R;

public class MainActivity extends Activity {
    static final String[] numbers = new String[] { "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty one",
            "twenty two" };
    GridView gridView;
    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_id);
        adapter=new  ImageAdapter(numbers, this);
        gridView.setAdapter(adapter);
    }

}