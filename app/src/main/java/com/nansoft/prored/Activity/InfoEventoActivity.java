package com.nansoft.prored.Activity;


import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.nansoft.prored.Helper.MobileServiceHelper;
import com.nansoft.prored.Model.Evento;
import com.nansoft.prored.Model.Usuario;
import com.nansoft.prored.R;

import java.net.MalformedURLException;

public class InfoEventoActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_eventos);

        // Set up action bar.
        ActionBar bar = getSupportActionBar();
        bar.show();
        bar.setDisplayHomeAsUpEnabled(true);

        // se obtiene el id del elemento seleccionado
        final String idEvento  = getIntent().getExtras().getString("idEvento");


        // obtenemos la referencia al swipe refresh layout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizarInfoEvento);

        // establecemos los colores de carga
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        // establecemos la acción que se debe realizar cuando el usuario desliza el dedo hacia abajo para actualizar
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                CargarInformacionEvento(idEvento);
            }

        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        CargarInformacionEvento(idEvento);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId())
        {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void CargarInformacionEvento(final String pIdEvento)
    {
        new AsyncTask<Void, Void, Boolean>() {
            MobileServiceClient mClient;
            MobileServiceTable<Evento> tableEvento;
            @Override
            protected void onPreExecute()
            {
                try
                {
                    // se crea el objeto para conectar el mobile services
                    mClient =  new MobileServiceClient(
                            MobileServiceHelper.URL_MOBILE_SERVICES,
                            MobileServiceHelper.KEY_MOBILE_SERVICES,
                            getApplicationContext());

                    // se obtiene la referencia de la tabla
                    tableEvento = mClient.getTable("Evento", Evento.class);
                }
                catch  (MalformedURLException exception)
                {
                    Toast.makeText(getApplicationContext(), "Error al conectar con Mobile Services", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Error al obtener referencia de la tabla",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            protected Boolean doInBackground(Void... params)
            {
                try
                {
                    // se busca el usuario que coincida con el id enviado
                    final Evento objEvento = tableEvento.lookUp(pIdEvento).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // se llama la función encargada de cargar los datos en la vista
                            desplegarInformacionEvento(objEvento);
                        }
                    });


                    return true;
                } catch (final Exception exception) {

                    return false;
                }

            }

            @Override
            protected void onPostExecute(Boolean success)
            {

                mSwipeRefreshLayout.setRefreshing(false);

            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute();
    }

    // trabajar en esta función
    private void desplegarInformacionEvento(Evento objEvento)
    {
        // deplegar la informaciön del evento
        TextView txtvNombreEvento = (TextView) findViewById(R.id.txtvNombreEvento);
        txtvNombreEvento.setText(objEvento.getNombre());
        TextView txtvFecha =(TextView) findViewById(R.id.txtvFecha);
        txtvFecha.setText(objEvento.getFecha());
        TextView txtvLugar =(TextView) findViewById(R.id.txtvLugar);
        txtvLugar.setText(objEvento.getDireccion());
        TextView txtvCosto =(TextView) findViewById(R.id.txtvCosto);
        txtvCosto.setText(objEvento.getCosto());
        TextView txtvDescripción =(TextView) findViewById(R.id.txtvDescripcion);
        txtvDescripción.setText(objEvento.getDescripcion());
        ImageView imgFotoEvento = (ImageView) findViewById(R.id.imgFotoEvento);

        Glide.with(this)
                .load(objEvento.getUrlImagen().trim())
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.cargar_imagen)
                .error(R.drawable.sin_imagen)
                .into(imgFotoEvento);

        // nombre usuario, biografía, lugar , cargo y email
    }

}
