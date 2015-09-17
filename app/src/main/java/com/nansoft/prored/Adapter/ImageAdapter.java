package com.nansoft.prored.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nansoft.prored.R;

/**
 * Created by PC on 17/09/2015.
 */
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private final String[] StrValues;

        public ImageAdapter(Context context, String[] StrValues) {
            this.context = context;
            this.StrValues = StrValues;
        }

        // getView that displays the data at the specified position in the data set.
        public View getView(int position, View convertView, ViewGroup parent) {
            // create a new LayoutInflater
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;
            gridView = null;
            convertView = null;// avoids recycling of grid view
            if (convertView == null) {

                gridView = new View(context);
                // inflating grid view item
                gridView = inflater.inflate(R.layout.list_view_item, null);

                // set value into textview
                TextView textView = (TextView) gridView
                        .findViewById(R.id.list_item_label);
                textView.setText(StrValues[position]);

            }

            return gridView;
        }

        // Total number of items contained within the adapter
        @Override
        public int getCount() {
            return StrValues.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

