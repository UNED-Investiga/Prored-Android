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
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        final Red currentItem = getItem(position);


        // verificamos si la fila que se va dibujar no existe
        if (row == null) {
            // si es así la creamos
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            ViewHolder holder = new ViewHolder();

            //holder.imgLogoEmpresaa = (ImageView) row.findViewById(R.id.imgvLogoEmpresaRuta);
            //holder.txtvTituloRuta = (TextView) row.findViewById(R.id.txtvTituloRuta);

            row.setTag(holder);

        }
        // en caso contrario la recuperamos
        ViewHolder holder = (ViewHolder) row.getTag();

        //holder.imgLogoEmpresaa.setImageResource(res.getIdentifier(currentItem.getUrlImagen(),
                //"drawable", mContext.getPackageName()));

        //holder.txtvTituloRuta.setText(currentItem.getNombreOpcion());
        return row;
        //a

    }

    // guarda el estado de cada vista la primera vez que se dibuja
    static class ViewHolder
    {
        protected ImageView imgLogoEmpresaa;
        protected TextView txtvTituloRuta;
        //protected TextView txtvCostoRuta;
    }
}
