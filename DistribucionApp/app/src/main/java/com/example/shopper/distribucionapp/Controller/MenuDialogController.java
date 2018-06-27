package com.example.shopper.distribucionapp.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.R;
import com.example.shopper.distribucionapp.View.ListaHojaDespachoView;
import com.example.shopper.distribucionapp.View.MenuDialogView;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Shopper on 14/06/2018.
 */

public class MenuDialogController extends
        //AppCompatActivity
        DialogFragment
{
    MenuDialogView menuDialogView;
    int resultado;
    Context context;
    ImageButton ActualizaEstado,ConsultaMapa;
    OnSimpleDialogListener Listener;
    public interface OnSimpleDialogListener
    {
        void onPossitiveButtonClick();
        void onNegativeButtonClick();
    }
    //OnSetTitleListener listener;

    //public MenuDialogController(){}
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {



        menuDialogView = new MenuDialogView();


        resultado=0;
        //final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        //LayoutInflater factory = LayoutInflater.from(context);
        //final View view=factory.inflate(R.layout.menudialog,null);
        //alertDialog.setView(view);

        //ConsultaMapa.setEnabled(true);
        //ActualizaEstado.setEnabled(true);

        //alertDialog.setNeutralButton("Here")
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.menudialog,null);

        //ConsultaMapa=(ImageButton) view.findViewById(R.id.btnconsultamapacliente);
        //ActualizaEstado=(ImageButton) view.findViewById(R.id.btnconsultamapacliente);


        builder.setView(view)
               .setPositiveButton("Guardar Estado",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Intent myIntent = new Intent(context,MenuDialogView.class);
                       // startActivity(myIntent);
                       // dialogInterface.cancel();

                    }
                })

                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i)
                            {
                                //Listener.onNegativeButtonClick();
                                //menuDialogView.DialogFragment();
                            }
                        }

                );

        return builder.create();

    //return createMenuController();

    }
/*
    public AlertDialog createMenuController()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.menudialog,null);
        builder.setView(view)
         .setPositiveButton("Guardar Estado",new DialogInterface.OnClickListener(){

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            Listener.onPossitiveButtonClick();
            //menuDialogView.DialogFragment();

        }
    })

            .setNegativeButton("Cancelar",new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface,int i)
                        {
                            Listener.onNegativeButtonClick();
                            //menuDialogView.DialogFragment();
                        }
                    }

            );


        return builder.create();
    }


    public interface OnSimpleDialogListener
    {
        void onPossitiveButtonClick();
        void onNegativeButtonClick();
    }

    @Override
    public void  onAttach(Activity activity)
    {
        super.onAttach(activity);
        try
        {
            Listener=(OnSimpleDialogListener)activity;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    activity.toString()+" no implemento OnSimpleDialogListener"
            );
        }
    }
*/
}
