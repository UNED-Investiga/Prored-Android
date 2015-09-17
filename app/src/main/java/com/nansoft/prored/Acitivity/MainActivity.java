package com.nansoft.prored.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.nansoft.prored.Adapter.GridAdapter;
import com.nansoft.prored.R;

public class MainActivity extends Activity {
    static final String[] numbers = new String[] { "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty one",
            "twenty two" };
    GridView gridView;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_id);
        adapter=new  GridAdapter(numbers, this);
        gridView.setAdapter(adapter);

    }

}


