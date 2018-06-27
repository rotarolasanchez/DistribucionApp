package com.example.shopper.distribucionapp.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.shopper.distribucionapp.Controller.DespachoEstadoDialog;
import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Controller.MenuDialogController;
import com.example.shopper.distribucionapp.R;


/**
 * Created by Shopper on 01/05/2018.
 */

public class MenuDialogView extends FragmentActivity
       // implements MenuDialogController.OnSimpleDialogListener


{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despacho_estado);
        android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialog();
        dialogFragment.show(getSupportFragmentManager(),"DespachoDialog");

    }
    /*
    public void primerpaso()
    {

    }
    */

/*

    @Override
    public void onPossitiveButtonClick() {
        //getSupportFragmentManager().findFragmentByTag("")

    }

    @Override
    public void onNegativeButtonClick() {

    }
*/
}


