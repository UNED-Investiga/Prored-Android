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
import com.nansoft.prored.Model.Opcion;
import com.nansoft.prored.R;

/**
 * Created by Carlos on 20/09/2015.
 */
public class OpcionAdapter extends ArrayAdapter<Opcion>
{
    // variable que va identificar el layout asociado a la vista
    int mLayoutResourceId;

    // clase que permite ir almacenando cada vista
    ViewHolder holder;

    // contexto con el que se está trabajando
    Context mContext;

    // variable para almacenar la ruta de la imagen
    String rutaImagen;

    // objeto que permite acceder a los recursos del sistema
    Resources res;

    // constructor
    public OpcionAdapter(Context context, int layoutResourceId)
    {
        super(context, layoutResourceId);
        mContext = context;
        mLayoutResourceId = layoutResourceId;

        // se obtienen los recursos
        res = this.mContext.getResources();
    }

    // regresa la vista de cada elemento de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        // se obtiene el elemento actual
        final Opcion currentItem = getItem(position);

        // verificamos si la fila que se va dibujar no existe
        if (row == null)
        {
            // si es así la creamos
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            ViewHolder holder = new ViewHolder();

            // obtenemos la referencia a la imagen y el textview para después poder asignarles un valor
            holder.imgvLogoOpcion = (ImageView) row.findViewById(R.id.imgvLogoItemGrid);
            holder.txtvNombreOpcion = (TextView) row.findViewById(R.id.txtNombreItemGrid);

            // guardamos en memoria la vista
            row.setTag(holder);

        }

        // en caso contrario la recuperamos
        ViewHolder holder = (ViewHolder) row.getTag();

        // obtenemos el texto que viene en el objeto y se lo asignamos al item
        holder.txtvNombreOpcion.setText(currentItem.getNombre());



        Glide.with(mContext)
                .load(currentItem.getUrlImagen().trim())
                .asBitmap()
                .fitCenter()
                .placeholder(R.mipmap.cargar_imagen)
                .error(R.mipmap.sin_imagen)
                .into(holder.imgvLogoOpcion);



        return row;
        //aaa

    }

    // guarda el estado de cada vista la primera vez que se dibuja
    static class ViewHolder
    {
        protected ImageView imgvLogoOpcion;
        protected TextView txtvNombreOpcion;
    }
}
