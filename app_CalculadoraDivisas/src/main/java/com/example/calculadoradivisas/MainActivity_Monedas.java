package com.example.calculadoradivisas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity_Monedas extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText et1, et2;

    protected static final int TIMER_RUNTIME = 10000;
    protected boolean nbActivo;
    protected ProgressBar nProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_monedas);

        et1 = (EditText)findViewById(R.id.monedaActual);
        et2 = (EditText)findViewById(R.id.monedaCambio);
        spinner1 = (Spinner)findViewById(R.id.spinnerMonto);
        spinner2 = (Spinner)findViewById(R.id.spinnerResultado);

        String [] opciones = {"Nuevo Sol","Dólar","Euro","Libra","Rupia","Real","Peso","Yuan","Yen"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        nProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Thread timerThread = new Thread() {
            @Override
            public void run() {
                nbActivo = true;
                try {
                    int espera1 = 0;
                    while (nbActivo && (espera1 < TIMER_RUNTIME)) {
                        sleep(200);
                        if (nbActivo) {
                            espera1 += 200;
                            actualizarProgress(espera1);
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    onContinuar();
                }
            }
        };
        timerThread.start();
    }

    public void actualizarProgress(final int timePassed){
        if(null != nProgressBar){
            final int progress = nProgressBar.getMax() * timePassed
                    /TIMER_RUNTIME;
            nProgressBar.setProgress(progress);
        }
    }
    public void onContinuar(){
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar");
        Looper.prepare();
        Toast.makeText(this, "CARGA COMPLETA", Toast.LENGTH_LONG).show();
        Looper.loop();
    }

    //Método del botón
    public void clickConvertir(View view){
        String monedaActualSt = spinner1.getSelectedItem().toString();
        String monedaCambioSt = spinner2.getSelectedItem().toString();

        float valorCambioDb = Float.parseFloat(et1.getText().toString());
        double resultadoFinal = procesarConversion(monedaActualSt, monedaCambioSt, valorCambioDb);

        if (monedaActualSt != monedaCambioSt){
            if (resultadoFinal > 0){
                et2.setText(String.valueOf(resultadoFinal));
            }
        } else {
            Toast.makeText(this, "No se puede convertir al mismo tipo de moneda", Toast.LENGTH_LONG).show();
        }
    }

    private double procesarConversion(String monedaActual, String monedaCambio, float valorCambio){
        double resultadoConversion = 0;
        switch (monedaActual){
            case "Nuevo Sol":
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteSolesADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteSolesAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteSolesALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteSolesARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteSolesAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteSolesAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteSolesAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteSolesAYenes(valorCambio);
                }
                break;
            case "Dólar":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteDolaresASoles(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteDolaresAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteDolaresALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteDolaresARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteDolaresAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteDolaresAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteDolaresAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteDolaresAYenes(valorCambio);
                }
                break;
            case "Euro":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteEurosASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteEurosADolares(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteEurosALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteEurosARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteEurosAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteEurosAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteEurosAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteEurosAYenes(valorCambio);
                }
                break;
            case "Libra":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteLibrasASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteLibrasADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteLibrasAEuros(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteLibrasARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteLibrasAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteLibrasAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteLibrasAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteLibrasAYenes(valorCambio);
                }
                break;
            case "Rupia":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteRupiasASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteRupiasADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteRupiasAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteRupiasALibras(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteRupiasAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteRupiasAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteRupiasAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteRupiasAYenes(valorCambio);
                }
                break;
            case "Real":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteRealesASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteRealesADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteRealesAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteRealesALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteRealesARupias(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteRealesAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteRealesAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteRealesAYenes(valorCambio);
                }
                break;
            case "Peso":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = conviertePesosASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = conviertePesosADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = conviertePesosAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = conviertePesosALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = conviertePesosARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = conviertePesosAReales(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = conviertePesosAYuanes(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = conviertePesosAYenes(valorCambio);
                }
                break;
            case "Yuan":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteYuanesASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteYuanesADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteYuanesAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteYuanesALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteYuanesARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteYuanesAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteYuanesAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yen")){
                    resultadoConversion = convierteYuanesAYenes(valorCambio);
                }
                break;
            case "Yen":
                if (monedaCambio.equals("Nuevo Sol")){
                    resultadoConversion = convierteYenesASoles(valorCambio);
                }
                if (monedaCambio.equals("Dólar")){
                    resultadoConversion = convierteYenesADolares(valorCambio);
                }
                if (monedaCambio.equals("Euro")){
                    resultadoConversion = convierteYenesAEuros(valorCambio);
                }
                if (monedaCambio.equals("Libra")){
                    resultadoConversion = convierteYenesALibras(valorCambio);
                }
                if (monedaCambio.equals("Rupia")){
                    resultadoConversion = convierteYenesARupias(valorCambio);
                }
                if (monedaCambio.equals("Real")){
                    resultadoConversion = convierteYenesAReales(valorCambio);
                }
                if (monedaCambio.equals("Peso")){
                    resultadoConversion = convierteYenesAPesos(valorCambio);
                }
                if (monedaCambio.equals("Yuan")){
                    resultadoConversion = convierteYenesAYuanes(valorCambio);
                }
                break;
        }
        return resultadoConversion;
    }

    // Convierte soles a dólares
    private double convierteSolesADolares(float soles) {
        return (soles*0.26);
    }
    // Convierte soles a euros
    private double convierteSolesAEuros(float soles) {
        return (soles*0.24);
    }
    // Convierte soles a libras
    private double convierteSolesALibras(float soles) {
        return (soles*0.20);
    }
    // Convierte soles a rupias
    private double convierteSolesARupias(float soles) {
        return (soles*20.15);
    }
    // Convierte soles a reales
    private double convierteSolesAReales(float soles) {
        return (soles*1.30);
    }
    // Convierte soles a pesos
    private double convierteSolesAPesos(float soles) {
        return (soles*5.37);
    }
    // Convierte soles a yuanes
    private double convierteSolesAYuanes(float soles) {
        return (soles*1.69);
    }
    // Convierte soles a yenes
    private double convierteSolesAYenes(float soles) {
        return (soles*32.09);
    }


    // Convierte dólares a soles
    private double convierteDolaresASoles(float dolares) {
        return (dolares*3.78);
    }
    // Convierte dólares a euros
    private double convierteDolaresAEuros(float dolares) {
        return (dolares*0.91);
    }
    // Convierte dólares a libras
    private double convierteDolaresALibras(float dolares) {
        return (dolares*0.76);
    }
    // Convierte dólares a rupias
    private double convierteDolaresARupias(float dolares) {
        return (dolares*76.41);
    }
    // Convierte dólares a reales
    private double convierteDolaresAReales(float dolares) {
        return (dolares*4.91);
    }
    // Convierte dólares a pesos
    private double convierteDolaresAPesos(float dolares) {
        return (dolares*20.28);
    }
    // Convierte dólares a yuanes
    private double convierteDolaresAYuanes(float dolares) {
        return (dolares*6.37);
    }
    // Convierte dólares a yenes
    private double convierteDolaresAYenes(float dolares) {
        return (dolares*120.87);
    }


    // Convierte euros a soles
    private double convierteEurosASoles(float euros) {
        return (euros*4.15);
    }
    // Convierte dólares a dólares
    private double convierteEurosADolares(float euros) {
        return (euros*1.10);
    }
    // Convierte dólares a libras
    private double convierteEurosALibras(float euros) {
        return (euros*0.83);
    }
    // Convierte dólares a rupias
    private double convierteEurosARupias(float euros) {
        return (euros*83.92);
    }
    // Convierte dólares a reales
    private double convierteEurosAReales(float euros) {
        return (euros*5.40);
    }
    // Convierte dólares a pesos
    private double convierteEurosAPesos(float euros) {
        return (euros*22.26);
    }
    // Convierte dólares a yuanes
    private double convierteEurosAYuanes(float euros) {
        return (euros*7.00);
    }
    // Convierte dólares a yenes
    private double convierteEurosAYenes(float euros) {
        return (euros*132.72);
    }


    // Convierte libras a soles
    private double convierteLibrasASoles(float libras) {
        return (libras*4.99);
    }
    // Convierte libras a dólares
    private double convierteLibrasADolares(float libras) {
        return (libras*1.32);
    }
    // Convierte libras a euros
    private double convierteLibrasAEuros(float libras) {
        return (libras*1.20);
    }
    // Convierte libras a rupias
    private double convierteLibrasARupias(float libras) {
        return (libras*100.91);
    }
    // Convierte libras a reales
    private double convierteLibrasAReales(float libras) {
        return (libras*6.49);
    }
    // Convierte libras a pesos
    private double convierteLibrasAPesos(float libras) {
        return (libras*26.76);
    }
    // Convierte libras a yuanes
    private double convierteLibrasAYuanes(float libras) {
        return (libras*8.42);
    }
    // Convierte libras a yenes
    private double convierteLibrasAYenes(float libras) {
        return (libras*159.54);
    }


    // Convierte rupias a soles
    private double convierteRupiasASoles(float rupias) {
        return (rupias*0.049);
    }
    // Convierte rupias a dólares
    private double convierteRupiasADolares(float rupias) {
        return (rupias*0.013);
    }
    // Convierte rupias a euros
    private double convierteRupiasAEuros(float rupias) {
        return (rupias*0.012);
    }
    // Convierte rupias a libras
    private double convierteRupiasALibras(float rupias) {
        return (rupias*0.0099);
    }
    // Convierte rupias a reales
    private double convierteRupiasAReales(float rupias) {
        return (rupias*0.064);
    }
    // Convierte rupias a pesos
    private double convierteRupiasAPesos(float rupias) {
        return (rupias*0.27);
    }
    // Convierte rupias a yuanes
    private double convierteRupiasAYuanes(float rupias) {
        return (rupias*0.083);
    }
    // Convierte rupias a yenes
    private double convierteRupiasAYenes(float rupias) {
        return (rupias*1.58);
    }


    // Convierte reales a soles
    private double convierteRealesASoles(float reales) {
        return (reales*0.78);
    }
    // Convierte reales a dólares
    private double convierteRealesADolares(float reales) {
        return (reales*0.21);
    }
    // Convierte reales a euros
    private double convierteRealesAEuros(float reales) {
        return (reales*0.19);
    }
    // Convierte reales a libras
    private double convierteRealesALibras(float reales) {
        return (reales*0.16);
    }
    // Convierte reales a rupias
    private double convierteRealesARupias(float reales) {
        return (reales*15.78);
    }
    // Convierte reales a pesos
    private double convierteRealesAPesos(float reales) {
        return (reales*4.16);
    }
    // Convierte reales a yuanes
    private double convierteRealesAYuanes(float reales) {
        return (reales*1.31);
    }
    // Convierte reales a yenes
    private double convierteRealesAYenes(float reales) {
        return (reales*24.99);
    }


    // Convierte pesos a soles
    private double conviertePesosASoles(float pesos) {
        return (pesos*0.19);
    }
    // Convierte pesos a dólares
    private double conviertePesosADolares(float pesos) {
        return (pesos*0.049);
    }
    // Convierte pesos a euros
    private double conviertePesosAEuros(float pesos) {
        return (pesos*0.045);
    }
    // Convierte pesos a libras
    private double conviertePesosALibras(float pesos) {
        return (pesos*0.037);
    }
    // Convierte pesos a rupias
    private double conviertePesosARupias(float pesos) {
        return (pesos*3.77);
    }
    // Convierte pesos a reales
    private double conviertePesosAReales(float pesos) {
        return (pesos*0.24);
    }
    // Convierte pesos a yuanes
    private double conviertePesosAYuanes(float pesos) {
        return (pesos*0.31);
    }
    // Convierte pesos a yenes
    private double conviertePesosAYenes(float pesos) {
        return (pesos*5.96);
    }

    // Convierte yuanes a soles
    private double convierteYuanesASoles(float yuanes) {
        return (yuanes*0.59);
    }
    // Convierte yuanes a dólares
    private double convierteYuanesADolares(float yuanes) {
        return (yuanes*0.16);
    }
    // Convierte yuanes a euros
    private double convierteYuanesAEuros(float yuanes) {
        return (yuanes*0.14);
    }
    // Convierte yuanes a libras
    private double convierteYuanesALibras(float yuanes) {
        return (yuanes*0.12);
    }
    // Convierte yuanes a rupias
    private double convierteYuanesARupias(float yuanes) {
        return (yuanes*12.03);
    }
    // Convierte yuanes a reales
    private double convierteYuanesAReales(float yuanes) {
        return (yuanes*0.77);
    }
    // Convierte yuanes a pesos
    private double convierteYuanesAPesos(float yuanes) {
        return (yuanes*3.18);
    }
    // Convierte yuanes a yenes
    private double convierteYuanesAYenes(float yuanes) {
        return (yuanes*18.94);
    }


    // Convierte yenes a soles
    private double convierteYenesASoles(float yenes) {
        return (yenes*0.031);
    }
    // Convierte yenes a dólares
    private double convierteYenesADolares(float yenes) {
        return (yenes*0.0083);
    }
    // Convierte yenes a euros
    private double convierteYenesAEuros(float yenes) {
        return (yenes*0.0075);
    }
    // Convierte yenes a libras
    private double convierteYenesALibras(float yenes) {
        return (yenes*0.0063);
    }
    // Convierte yenes a rupias
    private double convierteYenesARupias(float yenes) {
        return (yenes*0.63);
    }
    // Convierte yenes a reales
    private double convierteYenesAReales(float yenes) {
        return (yenes*0.041);
    }
    // Convierte yenes a pesos
    private double convierteYenesAPesos(float yenes) {
        return (yenes*0.17);
    }
    // Convierte yenes a yuanes
    private double convierteYenesAYuanes(float yenes) {
        return (yenes*0.053);
    }
}