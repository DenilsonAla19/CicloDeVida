package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.ProgressBar;

public class TerceraActividad extends AppCompatActivity {

    protected static final int TIMER_RUNTIME = 10000;
    protected boolean nbActivo;
    protected ProgressBar nProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_actividad);

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
                            actualizarProgress1(espera1);
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    onContinuar1();
                }
            }
        };
        timerThread.start();
    }

    public void actualizarProgress1(final int timePassed){
        if(null != nProgressBar){
            final int progress = nProgressBar.getMax() * timePassed
                    /TIMER_RUNTIME;
            nProgressBar.setProgress(progress);
        }
    }
    public void onContinuar1(){
        Log.d("mensajeFinal", "Carga Completa");
    }

}