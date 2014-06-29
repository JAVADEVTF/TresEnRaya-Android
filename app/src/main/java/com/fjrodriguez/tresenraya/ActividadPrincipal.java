package com.fjrodriguez.tresenraya;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class ActividadPrincipal extends Activity {

    private TresEnRaya tresEnRaya;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        tresEnRaya = new TresEnRaya();
        textView = (TextView) findViewById(R.id.textViewMensaje);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void configurar (View view) {
        Intent intent = new Intent(this, ActividadPreferencias.class);
        startActivity(intent);
    }

    // TODO: Implementar la función de jugar con la máquina.
    // juegaría de forma aleatoria con la función Random.

    public void pulsadoBoton (View view) {
        String posBoton;
        int posX, posY;
        ImageButton imageButton = (ImageButton) findViewById(view.getId());

        posBoton = imageButton.getTag().toString();
        posX = posBoton.charAt(0) - '0';
        posY = posBoton.charAt(1) - '0';

        tresEnRaya.juegaJugador(tresEnRaya.jugador, posX, posY);
        if (tresEnRaya.jugador == tresEnRaya.JUGADOR1)
            imageButton.setBackgroundResource(R.drawable.imagen_x);
        else
            imageButton.setBackgroundResource(R.drawable.imagen_o);

        if (!tresEnRaya.haGanado(tresEnRaya.jugador, posX, posY))
            if (tresEnRaya.jugador == tresEnRaya.JUGADOR1) {
                // pasamos el turno al siguiente jugador.
                tresEnRaya.jugador = tresEnRaya.JUGADOR2;
                textView.setText("Juega jugador: " + tresEnRaya.jugador);
            }
            else {
                // pasamos el turno al siguiente jugador.
                tresEnRaya.jugador = tresEnRaya.JUGADOR1;
                textView.setText("Juega jugador: " + tresEnRaya.jugador);
            }
        else
            textView.setText("Ha ganado el jugador: " + tresEnRaya.jugador);
    }

    public void nuevaPartida (View view) {
        ImageButton imageButton;

        tresEnRaya.borrarTablero();
        tresEnRaya.jugador = tresEnRaya.JUGADOR1;
        textView.setText("Nueva Partida.");

        /* TODO: cambiar el parámetro de backgroundResource
            porque desaparece el botón. */
        imageButton = (ImageButton) findViewById(R.id.imageButton00);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton01);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton02);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton10);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton11);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton12);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton20);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton21);
        imageButton.setBackgroundResource(0);
        imageButton = (ImageButton) findViewById(R.id.imageButton22);
        imageButton.setBackgroundResource(0);

    }
}
