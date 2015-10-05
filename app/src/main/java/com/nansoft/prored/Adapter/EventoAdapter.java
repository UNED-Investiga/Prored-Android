package com.nansoft.prored.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nansoft.prored.Model.Evento;
import com.nansoft.prored.R;

/**
 * Created by Carlos on 16/09/2015.
 */
public class EventoAdapter extends ArrayAdapter<Evento>
{
    // variable que va identificar el layout asociado a la vista
    int mLayoutResourceId;

    // clase que permite ir almacenando cada vista
    ViewHolder holder;

    // contexto con el que se está trabajando
    Context mContext;

    // constructor
    public EventoAdapter(Context context, int layoutResourceId)
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
        final Evento currentItem = getItem(position);

        // verificamos si la fila que se va dibujar no existe
        if (row == null)
        {
            // si es así la creamos
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            ViewHolder holder = new ViewHolder();

            holder.imgvLogoEvento = (ImageView) row.findViewById(R.id.imgvLogoEvento);
            holder.txtvNombreEvento = (TextView) row.findViewById(R.id.txtvNombreEvento);
            holder.txtvFechaEveno = (TextView) row.findViewById(R.id.txtvFechaEvento);

            row.setTag(holder);

        }
        // en caso contrario la recuperamos
        ViewHolder holder = (ViewHolder) row.getTag();

        holder.txtvNombreEvento.setText(currentItem.getNombre());

        holder.txtvFechaEveno.setText(currentItem.getFecha());

        Glide.with(mContext)
                .load(currentItem.getUrlImagen().trim())
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.descargar_imagen)
                .error(R.drawable.sin_imagen)
                .into(holder.imgvLogoEvento);



        return row;
        //aaa

    }

    // a

    // guarda el estado de cada vista la primera vez que se dibuja
    static class ViewHolder
    {
        protected ImageView imgvLogoEvento;
        protected TextView txtvNombreEvento;
        protected TextView txtvFechaEveno;
    }
}
