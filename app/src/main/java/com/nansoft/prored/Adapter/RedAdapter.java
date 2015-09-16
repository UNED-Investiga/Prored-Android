package com.nansoft.prored.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.nansoft.prored.Model.Red;
import com.nansoft.prored.R;

/**
 * Created by Carlos on 16/09/2015.
 */
public class RedAdapter extends ArrayAdapter<Red>
{
    // variable que va identificar el layout asociado a la vista
    int mLayoutResourceId;

    // clase que permite ir almacenando cada vista
    ViewHolder holder;

    // contexto con el que se está trabajando
    Context mContext;

    // constructor
    public RedAdapter(Context context, int layoutResourceId)
    {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    // regresa la vista de cada elemento de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        // se obtiene el elemento actual
        final Red currentItem = getItem(position);

        // verificamos si la fila que se va dibujar no existe
        if (row == null)
        {
            // si es así la creamos
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            ViewHolder holder = new ViewHolder();

            // obtenemos la referencia a la imagen y el textview para después poder asignarles un valor
            holder.imgvLogoRed = (ImageView) row.findViewById(R.id.imgvLogoRed);
            holder.txtvNombreRed = (TextView) row.findViewById(R.id.txtNombreRed);

            // guardamos en memoria la vista
            row.setTag(holder);

        }

        // en caso contrario la recuperamos
        ViewHolder holder = (ViewHolder) row.getTag();

        // obtenemos el texto que viene en el objeto y se lo asignamos al item
        holder.txtvNombreRed.setText(currentItem.getNombre());

        // obtenemos la url de la imagen y mediante la librería Glide mostramso la url
        Glide.with(mContext)
                .load(currentItem.getUrlImage().trim())
                .asBitmap()
                .fitCenter()
                .placeholder(R.mipmap.cargar_imagen)
                .error(R.mipmap.sin_imagen)
                .into(holder.imgvLogoRed);

        return row;
        //aaa

    }

    // guarda el estado de cada vista la primera vez que se dibuja
    static class ViewHolder
    {
        protected ImageView imgvLogoRed;
        protected TextView txtvNombreRed;
    }
}
