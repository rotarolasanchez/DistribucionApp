package com.example.shopper.distribucionapp.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.shopper.distribucionapp.Dao.IndicatorDao;
import com.example.shopper.distribucionapp.Dao.ListaHojaDespachoDao;
import com.example.shopper.distribucionapp.Entity.IndicatorEntity;
import com.example.shopper.distribucionapp.Entity.LoginEntity;
import com.example.shopper.distribucionapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IndicadorIFPView extends AppCompatActivity implements View.OnClickListener {
    private PieChart pieChart;
    private BarChart barChart;
    private String [] months=new String []{"Entregado","Programado","Re-programado","Anulado"};
    private String [] Indices=new String []{"Total","Re-programado"};
    private String [] Indices2=new String []{"Porcentaje Fuera de Plazo","Porcentaje Despachos"};
    private int [] sale = new int []{25,20,38,10,15};
    private int [] sale2 = new int []{25,20};
    private int [] colors = new int []{Color.RED,Color.YELLOW,Color.GREEN,Color.BLUE,Color.LTGRAY};
    public String fechainicio,fechafin;
    ProgressDialog pd;
    private static final int TIPO_DIALOGO=0,TIPO_DIALOGO2=1;
    private  int diadespacho,mesdespacho,anodespacho,diadespacho2,mesdespacho2,anodespacho2;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha,oyenteSelectorFecha2;
    Button btnfechainicio,btnfechafin;
    EditText etfechainicio,etfechafin;
    IndicatorDao indicatorDao;
    LoginEntity dSesion = new LoginEntity();
    IndicatorEntity indicatorEntity;
    static List<IndicatorEntity> Eindicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador_ifpview);
        pieChart=(PieChart)findViewById(R.id.pieChart);
        barChart=(BarChart) findViewById(R.id.barChart);
        btnfechainicio = (Button)findViewById(R.id.btnfechainicio);
        btnfechafin = (Button)findViewById(R.id.btnfechafin);
        etfechainicio = (EditText)findViewById(R.id.etfechainicio);
        etfechafin = (EditText)findViewById(R.id.etfechafin);
        btnfechainicio.setOnClickListener(this);
        btnfechafin.setOnClickListener(this);
        indicatorDao = new IndicatorDao();
        indicatorEntity=new IndicatorEntity();
        Eindicator=new ArrayList<IndicatorEntity>();
        //createCharts();
        fechainicio="";
        fechafin="";
    }


    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animateY)
    {

        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend(chart);


        return chart;
    }
    private Chart getSameChart2(Chart chart,String description,int textColor,int background, int animateY)
    {

        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.setBackgroundColor(background);
        chart.animateY(animateY);
        legend2(chart);


        return chart;
    }
    private void legend (Chart chart)
    {
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i=0;i<Indices2.length;i++)
        {
            LegendEntry entry=new LegendEntry();
            entry.formColor=colors[i];
            entry.label=Indices2[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }
    private void legend2 (Chart chart)
    {
        Legend legend=chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        ArrayList<LegendEntry>entries=new ArrayList<>();
        for(int i=0;i<Indices.length;i++)
        {
            LegendEntry entry=new LegendEntry();
            entry.formColor=colors[i];
            entry.label=Indices[i];
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry>getBarEntries()
    {
        Double TotalGeneral=0.0;
        Double TotalProgramado=0.0;
        Double TotalReprogramado=0.0;
        Double TotalAnulado=0.0;
        Double TotalFueradePlazo=0.0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalGeneral=TotalGeneral+Double.valueOf(Eindicator.get(i).Total);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalProgramado=TotalProgramado+Double.valueOf(Eindicator.get(i).Programado);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalReprogramado=TotalReprogramado+Double.valueOf(Eindicator.get(i).Reprogramado);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalAnulado=TotalAnulado+Double.valueOf(Eindicator.get(i).Anulado);

        }
        int Valor=0;
        Double Prevalor=0.0;
        Prevalor=TotalGeneral;
        //Prevalor=100.00;
        Valor=(int)Math.round(Prevalor);
        entries.add(new BarEntry(0,Valor));
        TotalFueradePlazo=TotalReprogramado;
        int Valor1=0;
        Double Prevalor1=0.0;
        Prevalor1=TotalFueradePlazo;
        Valor1=(int)Math.round(Prevalor1);
        entries.add(new BarEntry(1,Valor1));
        return entries;

    }
    private Double Indice()
    {
        Double TotalGeneral=0.0;
        Double TotalProgramado=0.0;
        Double TotalReprogramado=0.0;
        Double TotalAnulado=0.0;
        Double TotalFueradePlazo=0.0;
        Double TotalIndice=0.0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalGeneral=TotalGeneral+Double.valueOf(Eindicator.get(i).Total);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalProgramado=TotalProgramado+Double.valueOf(Eindicator.get(i).Programado);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalReprogramado=TotalReprogramado+Double.valueOf(Eindicator.get(i).Reprogramado);

        }
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalAnulado=TotalAnulado+Double.valueOf(Eindicator.get(i).Anulado);

        }
        int Valor=0;
        Double Prevalor=0.0;
        //Prevalor=TotalGeneral;
        Prevalor=100.00;
        Valor=(int)Math.round(Prevalor);
        entries.add(new BarEntry(0,Valor));
        TotalFueradePlazo=TotalAnulado+TotalProgramado+TotalReprogramado;
        int Valor1=0;
        Double Prevalor1=0.0;
        Prevalor1=(TotalGeneral/TotalFueradePlazo)*100;
        Valor1=(int)Math.round(Prevalor1);
        entries.add(new BarEntry(1,Valor1));
        TotalIndice=TotalFueradePlazo/TotalGeneral;

        return TotalIndice;

    }
    private ArrayList<PieEntry>getPieEntries()
    {

        Double TotalEntregado=0.0;
        Double TotalProgramado=0.0;
        Double TotalReprogramado=0.0;
        Double TotalAnulado=0.0;
        Double TotalGeneral=0.0;
        int Valor1=0;
        Double Prevalor1=0.0;
        int Valor2=0;
        Double Prevalor2=0.0;
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalGeneral=TotalGeneral+Double.valueOf(Eindicator.get(i).Total);

        }

        //Prevalor2=(TotalGeneral)*100;
/*
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalEntregado=TotalEntregado+Double.valueOf(Eindicator.get(i).Entregado);

        }

        Prevalor1=(TotalEntregado/TotalGeneral)*100;
        Valor1=(int)Math.round(Prevalor1);
        //Valor1=(TotalEntregado/TotalGeneral);
        entries.add(new PieEntry(Valor1));
        Prevalor2=100.00-Prevalor1;
        Valor2=(int)Math.round(Prevalor2);
        //Valor1=(TotalEntregado/TotalGeneral);
        entries.add(new PieEntry(Valor2));
        */
/*        for(int i=0;i<Eindicator.size();i++)
        {
            TotalProgramado=TotalProgramado+Double.valueOf(Eindicator.get(i).Programado);

        }
        int Valor2=0;
        Double Prevalor2=0.0;
        Prevalor2=(TotalProgramado/TotalGeneral)*100;
        Valor2=(int)Math.round(Prevalor2);
        //Valor2=(TotalProgramado/TotalGeneral);
        entries.add(new PieEntry(Valor2));
        */
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalReprogramado=TotalReprogramado+Double.valueOf(Eindicator.get(i).Reprogramado);

        }
        int Valor3=0;
        Double Prevalor3=0.0;
        Prevalor3=(TotalReprogramado/TotalGeneral)*100;
        Valor3=(int)(int)Math.round(Prevalor3);
        //Valor3=(TotalReprogramado/TotalGeneral);
        entries.add(new PieEntry(Valor3));
        /*
        for(int i=0;i<Eindicator.size();i++)
        {
            TotalAnulado=TotalAnulado+Double.valueOf(Eindicator.get(i).Anulado);

        }
        */
        int Valor4=0;
        Double Prevalor4=0.0;
        //Prevalor4=(TotalAnulado/TotalGeneral)*100;
        Prevalor4=100-Prevalor3;
        Valor4=(int)Math.round(Prevalor4);
        //Valor4=(TotalAnulado/TotalGeneral);
        entries.add(new PieEntry(Valor4));

        return entries;
    }

    private ArrayList<PieEntry>getPieEntries2()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i=0;i<sale2.length;i++)
        {
            entries.add(new PieEntry(sale2[i]));
        }
        return entries;
    }

    private void axisX(XAxis axis)
    {
        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(Indices));
    }
    private void axisLeft(YAxis axis)
    {
        axis.setSpaceTop(30);
        axis.setAxisMinimum(0);
    }
    private void axisRight(YAxis axis)
    {
        axis.setEnabled(false);
    }

    public void createCharts()
    {
      barChart=(BarChart)getSameChart2(barChart,"Porcentaje Fuera de Plazo Requerido: 4% "
                        //+(Indice())*100
                ,Color.RED,Color.CYAN,3000);
        //barChart.setDescription("desc");
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData());
        barChart.invalidate();
        axisX(barChart.getXAxis());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());

        pieChart=(PieChart)getSameChart(pieChart,"Porcentaje Fuera de Plazo Requerido: 4%",Color.GRAY,Color.MAGENTA,3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setData(getPieData());
        pieChart.invalidate();
    }

    private DataSet getData(DataSet dataSet)
    {
        dataSet.setColors(colors);
        dataSet.setValueTextSize(Color.WHITE);
        dataSet.setValueTextSize(10);
        return dataSet;
    }

    private BarData getBarData()
    {
        BarDataSet barDataSet=(BarDataSet)getData(new BarDataSet(getBarEntries(),""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData= new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }
    private PieData getPieData()
    {
        PieDataSet pieDataSet=(PieDataSet)getData(new PieDataSet(getPieEntries(),""));

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_indicador, menu );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.Consultar: {
                /*if (Consultar()==1)
                {
                    createCharts();
                }


                */
                new ObtenerWebServiceIndicador().execute("");

                pd = ProgressDialog.show(this, "Por favor espere", "Consultando Indicador", true, false);
                /*int contador=2;
                for(int i=0;i<contador;i++)
                {
                    if(i==0)
                    {
                        new ObtenerWebServiceIndicador().execute("");

                        pd = ProgressDialog.show(this, "Por favor espere", "Consultando Indicador", true, false);
                        //Toast.makeText(context, "Lista Reprocesada", Toast.LENGTH_SHORT).show();
                    }
                    if(i==1)
                    {
                        createCharts();
                    }
                }*/



                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnfechainicio:
                if(view==btnfechainicio)
                {
                    final Calendar c1 = Calendar.getInstance();
                    diadespacho = c1.get(Calendar.DAY_OF_MONTH);
                    mesdespacho = c1.get(Calendar.MONTH);

                    anodespacho = c1.get(Calendar.YEAR);
                    oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener(){
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                        {
                            anodespacho = year;
                            diadespacho =dayOfMonth;
                            mesdespacho = monthOfYear;

                            etfechainicio.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth  );

                            //fechadespacho = etfechadespacho.getText().toString();
                            //Destado.FechaDespacho=etfechadespacho.getText().toString();
                            //Destado.Latitude_c=String.valueOf(latitude);
                            //Destado.Longitude_c=String.valueOf(longitude);
                            //Destado.Company="C001";
                            fechainicio=etfechainicio.getText().toString();

                        }};
                    showDialog(TIPO_DIALOGO);
                   /* Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto"+etfechainicio, Toast.LENGTH_SHORT);

                    toast2.show();*/
                }
                break;
            case R.id.btnfechafin:
                if(view==btnfechafin)
                {
                    final Calendar c1 = Calendar.getInstance();
                    diadespacho2 = c1.get(Calendar.DAY_OF_MONTH);
                    mesdespacho2 = c1.get(Calendar.MONTH);

                    anodespacho2 = c1.get(Calendar.YEAR);
                    oyenteSelectorFecha2 = new DatePickerDialog.OnDateSetListener(){
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                        {
                            anodespacho2 = year;
                            diadespacho2 =dayOfMonth;
                            mesdespacho2 = monthOfYear;

                            etfechafin.setText(year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth  );

                            //fechadespacho = etfechadespacho.getText().toString();
                            //Destado.FechaDespacho=etfechadespacho.getText().toString();
                            //Destado.Latitude_c=String.valueOf(latitude);
                            //Destado.Longitude_c=String.valueOf(longitude);
                            //Destado.Company="C001";
                            fechafin=etfechafin.getText().toString();
                        }};
                    showDialog(TIPO_DIALOGO2);
                    /*
                    Toast toast2 =
                            Toast.makeText(getApplicationContext(),
                                    "Toast por defecto"+etfechafin, Toast.LENGTH_SHORT);

                    toast2.show();
                    */
                }
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id){
        switch (id){
            case 0:
                return new DatePickerDialog(this,oyenteSelectorFecha,anodespacho,mesdespacho,diadespacho);
            case 1:
                return new DatePickerDialog(this,oyenteSelectorFecha2,anodespacho2,mesdespacho2,diadespacho2);

        }
        return null;
    }

    public int Consultar()
    {
        int resultado=1;
        new Thread(new Runnable() {
            public void run() {
                String usuarioSesion="";
                String fechaini="";
                String fechafin="";
                fechaini=etfechainicio.getText().toString();
                fechafin=etfechafin.getText().toString();
                usuarioSesion=dSesion.usuarioSesion.toString();

                indicatorDao.obtenerIndicadorNC(usuarioSesion,fechaini,fechafin);
            }
        }).start();

        return resultado;

    }

    private class ObtenerWebServiceIndicador extends AsyncTask<String,Void,Object>
    {

        @Override
        protected Integer doInBackground(String... arg0)
        {
            String usuarioSesion="";
            String fechaini="";
            String fechafin="";
            fechaini=etfechainicio.getText().toString();
            fechafin=etfechafin.getText().toString();
            usuarioSesion=dSesion.usuarioSesion.toString();
            try {
                ListaHojaDespachoDao cDespachos = new ListaHojaDespachoDao();
                Eindicator = indicatorDao.obtenerIndicadorNC(usuarioSesion,fechaini,fechafin);

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
            createCharts();
        }
    }
}
