package com.example.shopper.distribucionapp.View;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.shopper.distribucionapp.Controller.Constants;
import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Controller.ServiceGPSController;
import com.example.shopper.distribucionapp.R;

import java.util.ServiceConfigurationError;

/**
 * Created by icruz on 27/05/2016.
 */
public class MenuView extends AppCompatActivity {
    private ImageButton ibtn9,ibtn;
    ServiceGPSController serviceGPSController = new ServiceGPSController();
    IndicatorView indicatorView = new IndicatorView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mantenimiento);
        ibtn9 = (ImageButton) findViewById(R.id.ibtn9);
        ibtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListaHojaDespachoView.class);
                startActivity(i);

                startService(new Intent(getBaseContext(), ServiceGPSController.class));
                //Intent intentGPSService = new Intent(getApplicationContext(), ServiceGPSController.class);
                //startService(intentGPSService); //Iniciar servicio


            }});
        ibtn = (ImageButton) findViewById(R.id.ibtn);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(getApplicationContext(), IndicatorView.class);
                startActivity(j);

                //startService(new Intent(getBaseContext(),ServiceGPSController.class));
                //Intent intentGPSService = new Intent(getApplicationContext(), ServiceGPSController.class);
                //startService(intentGPSService); //Iniciar servicio



            }




        });

        // Filtro de acciones que serán alertadas
        IntentFilter filter = new IntentFilter(
                Constants.ACTION_RUN_ISERVICE);
        filter.addAction(Constants.ACTION_RUN_SERVICE);
        filter.addAction(Constants.ACTION_MEMORY_EXIT);
        filter.addAction(Constants.ACTION_PROGRESS_EXIT);

        // Crear un nuevo ResponseReceiver
        ResponseReceiver receiver =
                new ResponseReceiver();
        // Registrar el receiver y su filtro
        LocalBroadcastManager.getInstance(this).registerReceiver(
                receiver,
                filter);

    }

    public void ActualizaDespacho()
    {
        android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialogView();
        dialogFragment.show(getSupportFragmentManager(),"un dialogo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.salir: {
                CerrarSesion();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void CerrarSesion()
    {
        Intent intentMemoryService = new Intent(
                getApplicationContext(), ServiceGPSController.class);
        stopService(intentMemoryService); // Detener servicio
        System.exit(0);



    }

    private class ResponseReceiver extends BroadcastReceiver {

        // Sin instancias
        private ResponseReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
           /* switch (intent.getAction()) {
                case Constants.ACTION_RUN_SERVICE:
                    memoryUsageText.setText(intent.getStringExtra(Constants.EXTRA_MEMORY));
                    break;

                case Constants.ACTION_RUN_ISERVICE:
                    progressText.setText(intent.getIntExtra(Constants.EXTRA_PROGRESS, -1) + "");
                    break;

                case Constants.ACTION_MEMORY_EXIT:
                    memoryUsageText.setText("Memoria");
                    break;

                case Constants.ACTION_PROGRESS_EXIT:
                    progressText.setText("Progreso");
                    break;
            }*/
        }
    }
}
