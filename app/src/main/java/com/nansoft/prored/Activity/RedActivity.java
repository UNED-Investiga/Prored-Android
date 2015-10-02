package com.nansoft.prored.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
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
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOrder;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.nansoft.prored.Adapter.RedAdapter;
import com.nansoft.prored.Helper.MobileServiceHelper;
import com.nansoft.prored.Model.Red;
import com.nansoft.prored.R;

import java.net.MalformedURLException;

public class RedActivity extends AppCompatActivity
{
    RedAdapter redAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up action bar.
        ActionBar bar = getSupportActionBar();
        bar.show();
        bar.setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizar);


        // buscamos el grid view
        GridView gridView = (GridView) findViewById(R.id.grid_id);

        // creamos el adapter de tipo red
        redAdapter = new RedAdapter(this,R.layout.item_grid);

        // establecemos el adapter al grid
        gridView.setAdapter(redAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Red objRed = redAdapter.getItem(position);

                Intent objIntent = new Intent(getApplicationContext(), PerfilUsuarioActivity.class);
                objIntent.putExtra("idEncargado", objRed.getId_Encargado());
                startActivity(objIntent);

            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                CargarRedes();
            }

        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        /*
        // agregamos los elementos
        Red objRed = new Red();
        objRed.setId("1");
        objRed.setNombre("Red de Anita");
        objRed.setDescripcion("Descripción");
        objRed.setId_Encargado("1");
        objRed.setUrlImage("http://1.bp.blogspot.com/-J-koubZYgAM/Ti7e2Xh_eWI/AAAAAAAABM8/slu8BbBOAW8/s1600/Jennifer-Aniston-wallpaper-15.jpg");

        // agregamos el objeto en el grid view
        redAdapter.add(objRed);

        Red objRed1 = new Red();
        objRed1.setId("2");
        objRed1.setNombre("Red de Maynor");
        objRed1.setDescripcion("Descripción");
        objRed1.setId_Encargado("1");
        objRed1.setUrlImage("http://1.bp.blogspot.com/-J-koubZYgAM/Ti7e2Xh_eWI/AAAAAAAABM8/slu8BbBOAW8/s1600/Jennifer-Aniston-wallpaper-15.jpg");

        // agregamos el objeto en el grid view
        redAdapter.add(objRed1);
        */

        CargarRedes();
    }

    private void CargarRedes()
    {
        new AsyncTask<Void, Void, Boolean>() {
            MobileServiceClient mClient;
            MobileServiceTable<Red> tableRed;
            @Override
            protected void onPreExecute()
            {
                redAdapter.clear();
                try
                {
                    // se crea el objeto para conectar el mobile services
                    mClient =  new MobileServiceClient(
                            MobileServiceHelper.URL_MOBILE_SERVICES,
                            MobileServiceHelper.KEY_MOBILE_SERVICES,
                            getApplicationContext());

                    // se obtiene la referencia de la tabla
                    tableRed = mClient.getTable("Red", Red.class);
                }
                catch  (MalformedURLException exception)
                {
                    Toast.makeText(getApplicationContext(),"Error al conectar con Mobile Services",Toast.LENGTH_SHORT).show();
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

                    final MobileServiceList<Red> result = tableRed.execute().get();

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run()
                            {
                                // se agregan los registros en la base de datos
                                for (Red ruta : result)
                                {
                                    redAdapter.add(ruta);
                                    redAdapter.notifyDataSetChanged();
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_red, menu);
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
}
