package com.example.shopper.distribucionapp.View;

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

import com.example.shopper.distribucionapp.R;

public class IndicatorMenuView extends AppCompatActivity {
    private ImageButton ibtn1,ibtn;
    IndicatorNCDView indicatorNCDView = new IndicatorNCDView();
    IndicadorIFPView indicatorIFPView = new IndicadorIFPView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_indicator_menu_view);
        ibtn1 = (ImageButton) findViewById(R.id.ibtn1);

        ibtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(getApplicationContext(), IndicatorNCDView.class);
                startActivity(j);

                //startService(new Intent(getBaseContext(),ServiceGPSController.class));
                //Intent intentGPSService = new Intent(getApplicationContext(), ServiceGPSController.class);
                //startService(intentGPSService); //Iniciar servicio
            }
        });
        ibtn = (ImageButton) findViewById(R.id.ibtn);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jj= new Intent(getApplicationContext(), IndicadorIFPView.class);
                startActivity(jj);

                //startService(new Intent(getBaseContext(),ServiceGPSController.class));
                //Intent intentGPSService = new Intent(getApplicationContext(), ServiceGPSController.class);
                //startService(intentGPSService); //Iniciar servicio



            }




        });
    }
}
