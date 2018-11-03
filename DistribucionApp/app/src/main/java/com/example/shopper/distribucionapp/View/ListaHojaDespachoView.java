package com.example.shopper.distribucionapp.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopper.distribucionapp.Controller.DespachoEstadoDialogController;
import com.example.shopper.distribucionapp.Controller.GPSController2;
import com.example.shopper.distribucionapp.Controller.ServiceGPSController;
import com.example.shopper.distribucionapp.Dao.DespachoEstadoDao;
import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.Dao.TrackingDao;
import com.example.shopper.distribucionapp.Entity.DespachoEstadoEntity;
import com.example.shopper.distribucionapp.Entity.ListaDespachoEntity;
import com.example.shopper.distribucionapp.Entity.LoginEntity;
import com.example.shopper.distribucionapp.Entity.ListaHojaDespachoEntity;
import com.example.shopper.distribucionapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    ImageButton ibtnconsultamapa,ibtnguardaestado;
    public String fechadespacho;
    ProgressDialog pd;
    private static final int TIPO_DIALOGO3=0;
    private  int diadespacho,mesdespacho,anodespacho,hora,minutos;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha3;
    public ListaDespachoEntity listaDespachoEntity;
    CheckBox Chkbox;
    public EditText etfechadespacho;
    TextView resultado;
    public static EfficientAdapter adap;

    private GPSController2 gpsController;

    //private Location mLocation;
    double latitude=0, longitude=0;
    private Location location;
    TrackingDao trackingDao;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Login =  new LoginView();
        ibtnconsultamapa = (ImageButton) findViewById(R.id.btnconsultamapacliente);
        ibtnguardaestado = (ImageButton) findViewById(R.id.btnactualizaestadodespacho);
        listaDespachoEntity = new ListaDespachoEntity();
        Chkbox=(CheckBox)findViewById(R.id.chkbox1) ;
        //gpsController = new GPSController2(getApplicationContext());
        //location = gpsController.getLocation(location);
        //latitude = location.getLatitude();
        //longitude = location.getLongitude();
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

       // ibtnconsultamapa.setAdapter(adapter);

        lista.

                setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                int i = 0;
                i=position;
                android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialogController();
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
                   /* Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto"+etfechadespacho, Toast.LENGTH_SHORT);

                    toast2.show();*/
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



    public class EfficientAdapter extends ArrayAdapter<ListaHojaDespachoEntity>
    {

        public Activity context;
        public List<ListaHojaDespachoEntity> detalles;

        public EfficientAdapter(Activity context, List<ListaHojaDespachoEntity> valores)
        {
            // Cache the LayoutInflate to avoid asking for a new one each time.
            super(context, R.layout.listahojadespacho_content, valores);
            this.context = context;
            this.detalles = valores;
        }

         class ViewHolder
         {
             TextView lblordendespacho;
            TextView lblcodigo;
            TextView lbldesc;
            TextView lblfactura;
            TextView lblestado;
             TextView lblcoordenada;
             CheckBox chkbox;
            ImageButton ibntconsultamapa;
             ImageButton ibtnactualizaestadodespacho;
        }

        public View getView(int position, View convertView, ViewGroup parent) {



            View view = null;
            if (convertView == null) {

                LayoutInflater inflator = context.getLayoutInflater();
                view = inflator.inflate(R.layout.listahojadespacho_content, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.lblcodigo = (TextView) view.findViewById(R.id.lblcodigo);
                viewHolder.lbldesc = (TextView) view.findViewById(R.id.lbldesc);
                viewHolder.lblfactura = (TextView) view.findViewById(R.id.lblfactura);
                viewHolder.lblestado = (TextView) view.findViewById(R.id.lblestado);
                //viewHolder.lblcoordenada = (TextView) view.findViewById(R.id.lblcoordenada);
                viewHolder.ibntconsultamapa = (ImageButton) view.findViewById(R.id.btnconsultamapacliente);
                viewHolder.chkbox=(CheckBox) view.findViewById(R.id.chkbox);
                viewHolder.lblordendespacho = (TextView) view.findViewById(R.id.lblordendespacho);
                //
                //viewHolder.ibntconsultamapa.
                viewHolder.ibntconsultamapa.setOnClickListener( new View.OnClickListener()
                {

                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(getContext(),   MapaView.class);
                        startActivity(i);
                        final int position = lista.getPositionForView((View) view.getParent());
                        final ListaHojaDespachoEntity clienteNew = detalles.get(position);
                        listaDespachoEntity.company=clienteNew.Company;
                        listaDespachoEntity.salesrepcode=clienteNew.Salesrepcode;
                        listaDespachoEntity.latitud=clienteNew.Latitude_c;
                        listaDespachoEntity.longitud=clienteNew.Longitude_c;



                        try {
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            listaDespachoEntity.fecha= format.parse((clienteNew.FechaDespacho));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        listaDespachoEntity.custid=clienteNew.Custid;
                        listaDespachoEntity.custnum=clienteNew.Custnum;
                        listaDespachoEntity.shiptonum=clienteNew.Shiptonum;



                    }
                });

                viewHolder.ibtnactualizaestadodespacho = (ImageButton) view.findViewById(R.id.btnactualizaestadodespacho);
                viewHolder.ibtnactualizaestadodespacho.setOnClickListener( new View.OnClickListener()
                {

                    @Override
                    public void onClick(View view) {
                        android.support.v4.app.DialogFragment dialogFragment = new DespachoEstadoDialogController();
                        dialogFragment.show(getSupportFragmentManager(),"un dialogo");
                        for(int j=0;j<EDespachos.size();j++)
                        {
                            final int position = lista.getPositionForView((View) view.getParent());
                            if(position==j)
                            {
                                Destado.OrderDispatch=EDespachos.get(j).OrderDispatch;
                            }

                        }
                    }
                });

                view.setTag(viewHolder);
                viewHolder.lblcodigo.setTag(this.detalles.get(position));
            } else {
                view = convertView;
                ((ViewHolder) view.getTag()).lblcodigo.setTag(this.detalles.get(position));
            }

            final ViewHolder holder = (ViewHolder) view.getTag();
            final ListaHojaDespachoEntity clienteNew = detalles.get(position);

            holder.lblordendespacho.setText("     "+ clienteNew.OrderDispatch + "");
            holder.lblcodigo.setText(clienteNew.Custid +"");
            holder.lbldesc.setText(clienteNew.Name+ "");
            holder.lblfactura.setText(clienteNew.LegalNumber + "");
            holder.lblestado.setText(clienteNew.State + "");
            //String resultado=Funcion(clienteNew.Latitude_c);

            //holder.lblcoordenada.setText(
                  //  Funcion(clienteNew.Latitude_c) + "");
            //Boolean.valueOf()
            holder.chkbox.setChecked(Funcion(clienteNew.Latitude_c));
            if (clienteNew.State.equals("Entregado"))
            {
                view.setBackgroundColor(Color.GREEN);
            } else {
                view.setBackgroundColor(Color.RED);
            }
                    //Boolean.valueOf(Funcion(clienteNew.Latitude_c)));

//                    setText(Boolean.valueOf(Funcion(clienteNew.Latitude_c))+ "");
            //view.setTag(EDespachos.get(position).getShiptonum());




            return view;
        }

        public Boolean Funcion(String clientenew)
        {
            Boolean indicador=false;
            //if(!(clientenew.equals(1))||!(clientenew.equals(null))||clientenew!="anyType{}")
            if(clientenew.equals("anyType{}"))
            {
                indicador=false;
            }
            else if(clientenew.equals(1))
            {
                indicador=false;
            }
            else
                indicador=true;

            return indicador;
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.main_menu, menu );
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salir: {
                CerrarSesion();
                return true;
            }
            case R.id.reprocesar: {
                String valor="";
                valor=Reprocesar();
                if(valor=="1")
                {
                    new ObtenerWebServiceListaDespacho().execute("");
                    pd = ProgressDialog.show(this, "Por favor espere", "Consultando productos",true,false);
                    //Toast.makeText(context, "Lista Reprocesada", Toast.LENGTH_SHORT).show();
                }

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
    public String Reprocesar()
    {
       String resultado="";



        new Thread(new Runnable() {
            public void run() {

        ListaHojaDespachoDao cDespachos = new ListaHojaDespachoDao();
                cDespachos.EliminarDespachos(dSesion.usuarioSesion, etfechadespacho.getText().toString());
                //new ObtenerWebServiceListaDespacho().execute("");
            }
        }).start();
    resultado="1";
    return  resultado;


    }



}
