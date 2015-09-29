package com.nansoft.prored.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.nansoft.prored.Adapter.EventoAdapter;
import com.nansoft.prored.Adapter.RedAdapter;
import com.nansoft.prored.Helper.MobileServiceHelper;
import com.nansoft.prored.Model.Evento;
import com.nansoft.prored.Model.Red;
import com.nansoft.prored.R;

import java.net.MalformedURLException;

public class EventoActivity extends AppCompatActivity {

    EventoAdapter adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizar);

        // buscamos el grid view
        GridView gridView = (GridView) findViewById(R.id.grid_id);

        // creamos el adapter de tipo red
        adapter = new EventoAdapter(this,R.layout.item_evento);

        // establecemos el adapter al grid
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Evento objEvento = adapter.getItem(position);

                Intent objIntent = new Intent(getApplicationContext(), PerfilUsuarioActivity.class);
                objIntent.putExtra("idEvento", objEvento.getId());
                startActivity(objIntent);

            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                CargarEventos();
            }

        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        CargarEventos();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_evento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void CargarEventos()
    {
        new AsyncTask<Void, Void, Boolean>() {
            MobileServiceClient mClient;
            MobileServiceTable<Evento> tableEvento;
            @Override
            protected void onPreExecute()
            {
                adapter.clear();
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
                try {

                    final MobileServiceList<Evento> result = tableEvento.execute().get();

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run()
                        {
                            // se agregan los registros en la base de datos
                            for (Evento evento : result)
                            {
                                adapter.add(evento);
                                adapter.notifyDataSetChanged();
                            }
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

                // guardamos en las preferencias de usuario la versión de base de datos que tenemos

                mSwipeRefreshLayout.setRefreshing(false);


            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute();
    }
}
