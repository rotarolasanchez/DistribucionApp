package com.example.shopper.distribucionapp.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Controller.GPSController;
import com.example.shopper.distribucionapp.Controller.DespachoEstadoDialog;
import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.Entity.DespachoEstadoEntity;
import com.example.shopper.distribucionapp.Entity.LoginEntity;
import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;
import com.example.shopper.distribucionapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Shopper on 07/04/2018.*/


public class ListaHojaDespachoView extends AppCompatActivity implements View.OnClickListener
{
    public Activity context2;
    DespachoEstadoEntity Destado = new DespachoEstadoEntity();
    LoginView Login;
    private ListView lista;
    Context context;
    DespachoEstadoDao DDespachos;
    static List<ListaHojaDespachoEntity> EDespachos;
    LoginEntity dSesion = new LoginEntity();
    Button btnconsultarlistahojadespacho,btnfechadespacho;
    public String fechadespacho;
    ProgressDialog pd;
    private static final int TIPO_DIALOGO3=0;
    private  int diadespacho,mesdespacho,anodespacho,hora,minutos;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha3;

    public EditText etfechadespacho;
    TextView resultado;
    public static EfficientAdapter adap;

    private GPSController gpsController;
    private Location mLocation;
    double latitude=0, longitude=0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Login =  new LoginView();

        gpsController = new GPSController(getApplicationContext());
        mLocation = gpsController.getLocation();
        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();
        context = this;
        DDespachos = new DespachoEstadoDao();
        EDespachos = new ArrayList<ListaHojaDespachoEntity>();
        setContentView(R.layout.listahojadespacho);
        adap = new EfficientAdapter(this,EDespachos);
        // Enlaces con elementos visuales del XML
        btnconsultarlistahojadespacho = (Button)findViewById(R.id.btnconsultarlistahojadespacho);
        btnfechadespacho = (Button)findViewById(R.id.btnfechadespacho);
        etfechadespacho = (EditText)findViewById(R.id.etfechadespacho);
        resultado = (TextView)findViewById(R.id.txtresultado);
        // Listener de los botones
        btnconsultarlistahojadespacho.setOnClickListener(this);
        btnfechadespacho.setOnClickListener(this);
        lista = (ListView) findViewById(android.R.id.list);

        EfficientAdapter adapter = new EfficientAdapter(this,EDespachos);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialog();
                dialogFragment.show(getSupportFragmentManager(),"un dialogo");
                for(int j=0;j<EDespachos.size();j++)
                {
                    if(position==j)
                    {
                        Destado.OrderDispatch=EDespachos.get(j).OrderDispatch;
                    }

                }

            }
        });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnconsultarlistahojadespacho:
                new ObtenerWebServiceListaDespacho().execute("");
                pd = ProgressDialog.show(this, "Por favor espere", "Consultando productos",true,false);
                break;
            case R.id.btnfechadespacho:
                if(v==btnfechadespacho)
                {
                    final Calendar c1 = Calendar.getInstance();
                    diadespacho = c1.get(Calendar.DAY_OF_MONTH);
                    mesdespacho = c1.get(Calendar.MONTH);

                    anodespacho = c1.get(Calendar.YEAR);
                    oyenteSelectorFecha3 = new DatePickerDialog.OnDateSetListener(){
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                        {
                            anodespacho = year;
                            diadespacho =dayOfMonth;
                            mesdespacho = monthOfYear;

                            etfechadespacho.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth  );
                            fechadespacho = etfechadespacho.getText().toString();
                            Destado.FechaDespacho=etfechadespacho.getText().toString();
                            Destado.Latitude_c=String.valueOf(latitude);
                            Destado.Longitude_c=String.valueOf(longitude);
                            Destado.Company="C001";

                        }};
                    showDialog(TIPO_DIALOGO3);
                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto"+etfechadespacho, Toast.LENGTH_SHORT);

                    toast2.show();
            }
            break;
            default:
                break;
        }
    }
    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha3,anodespacho,mesdespacho,diadespacho);


        }
        return null;
    }


    private class ObtenerWebServiceListaDespacho extends AsyncTask<String,Void,Object>
    {

        @Override
        protected Integer doInBackground(String... arg0)
        {
            try {
                ListaHojaDespachoDao cDespachos = new ListaHojaDespachoDao();
                EDespachos = cDespachos.obtenerDespachos(dSesion.usuarioSesion, etfechadespacho.getText().toString());

            }catch(Exception e)
            {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
            return 1;
        }


        protected void onPostExecute(Object result) {
            //Se elimina la pantalla de por favor espere
            super.onPostExecute(result);
            pd.dismiss();
            //Se muestra el mensaje con la rpta del servidor
            adap.notifyDataSetChanged();
            actualizar();
        }
    }
    public void actualizar(){
        if(!EDespachos.isEmpty()){
            adap = new EfficientAdapter(this, EDespachos);

           lista.setAdapter(adap);
        }else{
            Toast.makeText(context, "No se encontraron productos",
                    Toast.LENGTH_SHORT).show();
        }
    }



    public static class EfficientAdapter extends ArrayAdapter<ListaHojaDespachoEntity> {

        public Activity context;
        public List<ListaHojaDespachoEntity> detalles;

        public EfficientAdapter(Activity context, List<ListaHojaDespachoEntity> valores) {
            // Cache the LayoutInflate to avoid asking for a new one each time.
            super(context, R.layout.listahojadespacho_content, valores);
            this.context = context;
            this.detalles = valores;
        }

        static class ViewHolder {
            TextView lblcodigo;
            TextView lbldesc;
            TextView lblcant;
            TextView lblume;
        }

        public View getView(int position, View convertView, ViewGroup parent) {



            View view = null;
            if (convertView == null) {

                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.listahojadespacho_content, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.lblcodigo = (TextView) view.findViewById(R.id.lblcodigo);
                viewHolder.lbldesc = (TextView) view.findViewById(R.id.lbldesc);
                viewHolder.lblcant = (TextView) view.findViewById(R.id.lblcant);
                viewHolder.lblume = (TextView) view.findViewById(R.id.lblume);

                view.setTag(viewHolder);
                viewHolder.lblcodigo.setTag(this.detalles.get(position));
            } else {
                view = convertView;
                ((ViewHolder) view.getTag()).lblcodigo.setTag(this.detalles.get(position));
            }

            final ViewHolder holder = (ViewHolder) view.getTag();
            final ListaHojaDespachoEntity clienteNew = detalles.get(position);

            holder.lblcodigo.setText(clienteNew.Custid +"");
            holder.lbldesc.setText(clienteNew.Name+ "");
            holder.lblcant.setText(clienteNew.LegalNumber + "");
            holder.lblume.setText(clienteNew.State + "");

            //view.setTag(EDespachos.get(position).getShiptonum());




            return view;
        }




    }


}
