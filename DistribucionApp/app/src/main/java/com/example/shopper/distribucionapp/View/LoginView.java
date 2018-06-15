package com.example.shopper.distribucionapp.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.Dao.LoginDao;
import com.example.shopper.distribucionapp.Entity.LoginEntity;
import com.example.shopper.distribucionapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shopper on 25/09/2017.
 */

public class LoginView extends AppCompatActivity implements View.OnClickListener {
    Spinner spncompania;
    EditText txtusu;
    Button btnlogin;
    private ProgressDialog pd;
    public LoginDao DLogin;
    private Context context;
    private String res,Imei;
    TelephonyManager manager;
    public String  Usuario;
    ObtenerWebServiceLogin hiloconexionLogin;
    TextView resultado,resultado2;
    LoginEntity dSesion = new LoginEntity();

    String result ;
    String IP = "http://rotarola.96.lt";
    String GET_BY_IMEI = IP + "/login_usuario.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;
        result = "";
        DLogin = new LoginDao();
        spncompania = (Spinner) findViewById(R.id.spncompania);
        txtusu = (EditText) findViewById(R.id.txtusu);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        resultado = (TextView)findViewById(R.id.txtresultado);
        resultado2 = (TextView)findViewById(R.id.txtresultado2);
        manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        btnlogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                hiloconexionLogin = new ObtenerWebServiceLogin();
                pd = ProgressDialog.show(context, "Por favor espere", "Consultando el acceso",true,false);
                Imei=hiloconexionLogin.obtenerImei();
                //Toast.makeText(context, "Mi imei:"+Imei, Toast.LENGTH_LONG).show();
                Usuario = txtusu.getText().toString();
                String cadenallamada = GET_BY_IMEI + "?cod_usuario=" + txtusu.getText().toString()+"&imei_usuario="+Imei;
                hiloconexionLogin.execute(cadenallamada, "1");
                Toast.makeText(context, "Paso por el case", Toast.LENGTH_LONG).show();
                break;
            default:

                break;

        }

    }

    private class ObtenerWebServiceLogin extends AsyncTask<String,Void,Object>
    {

        @Override
        protected String doInBackground(String... arg0)
        {
            try {
                result = DLogin.ValidarUsuario(Usuario,Imei);
            }catch(Exception e)
            {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
            return result;
        }


        protected void onPostExecute(Object result) {
            //resultado.setText(s);
            pd.dismiss();
            super.onPostExecute(result);
            if(result.toString().equals("1")){
                dSesion.usuarioSesion = txtusu.getText().toString();
                Toast.makeText(context, "Equipo autorizado", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MenuView.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(context, "Equipo no autorizado al MenuDialog", Toast.LENGTH_LONG).show();
            }
        }

        public String obtenerImei() {

            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();// Obtiene el imei tm.getDeviceId(); or
            resultado2.setText(imei);

            return imei;

        }
    }


    }
