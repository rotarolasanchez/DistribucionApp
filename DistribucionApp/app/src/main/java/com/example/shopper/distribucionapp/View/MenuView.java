package com.example.shopper.distribucionapp.View;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Controller.DespachoEstadoDialog;
import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Dao.MapsActivity;
import com.example.shopper.distribucionapp.R;
import com.example.shopper.distribucionapp.View.ListaHojaDespachoView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by icruz on 27/05/2016.
 */
public class MenuView extends AppCompatActivity {
    private ImageButton ibtn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_mantenimiento);
        ibtn9 = (ImageButton) findViewById(R.id.ibtn9);
        ibtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), ListaHojaDespachoView.class);
                startActivity(i);
            }
        });

    }

    public void IniciaMapa()
    {
    Intent i= new Intent(getApplicationContext(), MapsActivity.class);
    startActivity(i);
    }

    public void ActualizaDespacho()
    {
        android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialog();
        dialogFragment.show(getSupportFragmentManager(),"un dialogo");
    }
}
