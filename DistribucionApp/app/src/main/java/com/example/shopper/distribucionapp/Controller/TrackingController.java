package com.example.shopper.distribucionapp.Controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.R;

/**
 * Created by Shopper on 10/07/2018.
 */

public class TrackingController extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despacho_estado);



    }

    public class HiloInsertarTracking extends AsyncTask<String,Void,Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            try {
                DespachoEstadoDao DEstadoDAO = new DespachoEstadoDao();
                DEstadoDAO.ActualizarDespacho(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3])
                        ,Integer.parseInt(params[4]),
                        params[5],params[6],params[7],params[8],params[9],params[10]);
            }catch(Exception e)
            {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer s) {
            //resultado.setText(s);
            //super.onPostExecute(s);
        }
    }


}
