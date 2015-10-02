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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.nansoft.prored.Adapter.RedAdapter;
import com.nansoft.prored.Adapter.UsuarioAdapter;
import com.nansoft.prored.Helper.MobileServiceHelper;
import com.nansoft.prored.Model.Red;
import com.nansoft.prored.Model.Usuario;
import com.nansoft.prored.Model.UsuarioRed;
import com.nansoft.prored.R;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class PerfilUsuarioActivity extends AppCompatActivity {


    SwipeRefreshLayout mSwipeRefreshLayout;


    LinearLayout layout;

    ImageView imgvFotoUsuario;
    TextView txtvNombreUsuario;
    TextView txtvEmailUsuario;
    TextView txtvDireccionUsuario;
    TextView txtvCargoUsuario;
    TextView txtvBiografia;

    UsuarioAdapter usuarioAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_usuario);

        // Set up action bar.
        ActionBar bar = getSupportActionBar();
        bar.show();
        bar.setDisplayHomeAsUpEnabled(true);

        final String idEncargado  = getIntent().getExtras().getString("idEncargado");
        ListView lstvUsuariosAmigos = (ListView) findViewById(R.id.lstvRedes);

        View headerListView = getLayoutInflater().inflate(R.layout.informacion_usuario_header,null);

        usuarioAdapter = new UsuarioAdapter(this,R.layout.item_usuario);

        layout = (LinearLayout) headerListView.findViewById(R.id.layInfoUsuario);
        imgvFotoUsuario = (ImageView) headerListView.findViewById(R.id.imgvFotoUsuario);
        txtvNombreUsuario = (TextView) headerListView.findViewById(R.id.txtvNombreUsuario);
        txtvEmailUsuario = (TextView) headerListView.findViewById(R.id.txtvEmailUsuario);
        txtvDireccionUsuario = (TextView) headerListView.findViewById(R.id.txtvDireccionUsuario);
        txtvCargoUsuario = (TextView) headerListView.findViewById(R.id.txtvCargoUsuario);
        txtvBiografia = (TextView) headerListView.findViewById(R.id.txtvBiografia);



        lstvUsuariosAmigos.addHeaderView(headerListView);
        lstvUsuariosAmigos.setAdapter(usuarioAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swpActualizarInfoUsuario);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                CargarInformacionUsuario(idEncargado);
            }

        });

        lstvUsuariosAmigos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Usuario objUsuario = usuarioAdapter.getItem(position - 1);

                Intent objIntent = new Intent(PerfilUsuarioActivity.this,PerfilUsuarioActivity.class);
                objIntent.putExtra("idEncargado", objUsuario.getId());
                startActivity(objIntent);


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
        switch(item.getItemId())
        {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void CargarInformacionUsuario(final String pIdUsuario)
    {
        new AsyncTask<Void, Void, Boolean>() {

            MobileServiceClient mClient;
            MobileServiceTable<Usuario> tableUsuario;
            MobileServiceTable<UsuarioRed> tableUsuarioRed;
            ArrayList<Usuario> arrayListUsuario;

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
                    tableUsuarioRed = mClient.getTable("usuariored", UsuarioRed.class);

                    arrayListUsuario = new ArrayList<Usuario>();
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
                    // se limpia el adapter
                    usuarioAdapter.clear();

                    // se busca el usuario que coincida con el id enviado
                    final Usuario objUsuario = tableUsuario.lookUp(pIdUsuario).get();

                    // buscamos las redes con las que se relaciona el usuario
                    final MobileServiceList <UsuarioRed> lstUsuarioRed = tableUsuarioRed.where().field("idred").eq(pIdUsuario).execute().get();



                    Usuario objUsuarioAmigo;
                    for (UsuarioRed usuarioRed:lstUsuarioRed)
                    {
                        objUsuarioAmigo = new Usuario();
                        objUsuarioAmigo = tableUsuario.lookUp(usuarioRed.getIdUsuario()).get();
                        arrayListUsuario.add(objUsuarioAmigo);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // se llama la función encargada de cargar los datos en la vista
                            desplegarInformacionUsuario(objUsuario);

                            for (Usuario usuario :arrayListUsuario)
                            {

                                for (int i = 0; i < 20; i++) {
                                    usuarioAdapter.add(usuario);
                                }
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
        txtvNombreUsuario.setText(objUsuario.getNombre());

        setTitle(objUsuario.getNombre());
        // usar imgvFotoUsuario, txtvNombreUsuario, txtvEmailUsuario, txtvDireccionUsuario, txtvCargoUsuario,txtvBiografia;
    }
}
