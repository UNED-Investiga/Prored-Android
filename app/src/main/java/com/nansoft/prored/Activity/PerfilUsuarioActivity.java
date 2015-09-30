package com.nansoft.prored.Activity;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.nansoft.prored.Helper.MobileServiceHelper;
import com.nansoft.prored.Model.Red;
import com.nansoft.prored.Model.Usuario;
import com.nansoft.prored.R;

import java.net.MalformedURLException;

public class PerfilUsuarioActivity extends AppCompatActivity {


    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_usuario);

        final String idEncargado  = getIntent().getExtras().getString("idEncargado");

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizarInfoUsuario);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                CargarInformacionUsuario(idEncargado);
            }

        });

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        CargarInformacionUsuario(idEncargado);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_perfil_usuario, menu);
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

    private void CargarInformacionUsuario(final String pIdUsuario)
    {
        new AsyncTask<Void, Void, Boolean>() {
            MobileServiceClient mClient;
            MobileServiceTable<Usuario> tableUsuario;
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
                    tableUsuario = mClient.getTable("User", Usuario.class);
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
                    final Usuario objUsuario = tableUsuario.lookUp(pIdUsuario).get();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // se llama la función encargada de cargar los datos en la vista
                            desplegarInformacionUsuario(objUsuario);
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
    private void desplegarInformacionUsuario(Usuario objUsuario)
    {
        // deplegar la informaciön del usuario
        TextView txtvNombreUsuario = (TextView) findViewById(R.id.txtvNombreUsuario);
        txtvNombreUsuario.setText(objUsuario.getNombre());


        // nombre usuario, biografía, lugar , cargo y email
    }
}
