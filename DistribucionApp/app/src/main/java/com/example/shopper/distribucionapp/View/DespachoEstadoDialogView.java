package com.example.shopper.distribucionapp.View;

import android.app.AlertDialog;
import android.app.Dialog;
//import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

//import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.Entity.DespachoEstadoEntity;
import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;
import com.example.shopper.distribucionapp.Entity.LoginEntity;
import com.example.shopper.distribucionapp.R;
import com.example.shopper.distribucionapp.View.DespachoEstadoView;
import com.example.shopper.distribucionapp.View.ListaHojaDespachoView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import static com.example.shopper.distribucionapp.R.id.etfechadespacho;


/**
 * Created by Shopper on 28/03/2018.
 */

public class DespachoEstadoDialogView extends DialogFragment

{
    LoginEntity dSesion = new LoginEntity();
    DespachoEstadoEntity Destado = new DespachoEstadoEntity();
    ListaHojaDespachoView listaHojaDespachoView;
    Boolean Entregado,Reprogramado,Anulado,Pendiente;
    CheckBox Chkbox1,Chkbox2,Chkbox3,Chkbox4;
    DespachoEstadoDao despachoEstadoDao;
    int resultado,resultadohilo;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        despachoEstadoDao=new DespachoEstadoDao();
        listaHojaDespachoView = new ListaHojaDespachoView();

        resultado=0;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_despacho_estado,null);
        Chkbox1=(CheckBox) view.findViewById(R.id.chkbox1) ;
        Chkbox2=(CheckBox) view.findViewById(R.id.chkbox2) ;
        Chkbox3=(CheckBox) view.findViewById(R.id.chkbox3) ;
        Chkbox4=(CheckBox) view.findViewById(R.id.chkbox4) ;
        builder.setView(view)
                .setTitle("Elegir Estado de Despacho:")
                //.setIcon(android.R.drawable.btn_dialog)
                .setPositiveButton("Guardar Estado",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        new Thread(new Runnable() {
                            public void run() {

                                Entregado=Chkbox1.isChecked();
                                Reprogramado=Chkbox4.isChecked();
                                Anulado=Chkbox2.isChecked();
                                Pendiente=Chkbox3.isChecked();
                                int entregado=0;
                                int reprogramado=0;
                                int anulado=0;
                                int pendiente=0;
                                String latitude="";
                                String longitude="";
                                String orderDispatch="";
                                String usuarioSesion="";
                                String fechadespacho;
                                String company="";
                                entregado=boolToInt(Entregado);
                                reprogramado=boolToInt(Reprogramado);
                                anulado=boolToInt(Anulado);
                                pendiente=boolToInt(Pendiente);
                                latitude=Destado.Latitude_c.toString();
                                longitude=Destado.Longitude_c.toString();
                                orderDispatch=Destado.OrderDispatch.toString();
                                usuarioSesion=dSesion.usuarioSesion.toString();
                                fechadespacho=Destado.FechaDespacho.toString();
                                company=Destado.Company.toString();
                                despachoEstadoDao.ActualizarDespacho(entregado,reprogramado,anulado,pendiente,latitude,longitude
                                ,orderDispatch,usuarioSesion,fechadespacho,company);
                            }
                        }).start();
                    }
                })
                .setNegativeButton("Cancelar",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface,int i)
                            {

                            }
                        }
                );
        return builder.create();
    }
    public int boolToInt(boolean b)
    {
        return b ? 1 : 0;
    }
}
