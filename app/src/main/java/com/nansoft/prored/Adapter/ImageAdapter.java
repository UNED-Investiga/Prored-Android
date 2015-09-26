package com.nansoft.prored.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nansoft.prored.R;

/**
 * Created by Susana on 17/09/2015.
 */

    public class ImageAdapter extends BaseAdapter {

        String[] textArray;
        Context contxt;

        public ImageAdapter(String[] textArr, Context context) {
            textArray = textArr;
            contxt=context;
        }

        @Override
        public int getCount() {

            return textArray.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // create a new LayoutInflater
            LayoutInflater inflater = (LayoutInflater) contxt
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;
            gridView = null;
            convertView = null;// avoids recycling of grid view
            if (convertView == null) {

                gridView = new View(contxt);
                // inflating grid view item
                gridView = inflater.inflate(R.layout.list_view_item, null);

                // set value into textview
                TextView textView = (TextView) gridView
                        .findViewById(R.id.textView_id);
                textView.setText(textArray[position]);

            }

            return gridView;
        }

    }
