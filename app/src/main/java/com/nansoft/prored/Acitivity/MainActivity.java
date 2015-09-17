package com.nansoft.prored.Acitivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nansoft.prored.Adapter.ImageAdapter;
import com.nansoft.prored.R;

public class MainActivity extends Activity {
    static final String[] StrValues = new String[] { "one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen", "twenty", "twenty one",
            "twenty two" };
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView1);
        // setting adapter on listview
        listView.setAdapter(new ImageAdapter(this, StrValues));
        //setting on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {//displaying toast
                Toast.makeText(
                        getApplicationContext(),
                        ((TextView) v.findViewById(R.id.list_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}

