package com.fjrodriguez.tresenraya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class ActividadPrincipal extends Activity {

    private TresEnRaya tresEnRaya;
    private TextView textView;
    static private Random r;
    private int[][] idBotones = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        tresEnRaya = new TresEnRaya();
        textView = (TextView) findViewById(R.id.textViewMensaje);
        r = new Random();

        // Guardamos los id de los botones.
        idBotones[0][0] = R.id.button;
        idBotones[0][1] = R.id.button2;
        idBotones[0][2] = R.id.button3;
        idBotones[1][0] = R.id.button4;
        idBotones[1][1] = R.id.button5;
        idBotones[1][2] = R.id.button6;
        idBotones[2][0] = R.id.button7;
        idBotones[2][1] = R.id.button8;
        idBotones[2][2] = R.id.button9;


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

    // TODO: Falla cuando el usuario pulsa el último botón que no se ha jugado.
    public void pulsadoBoton (View view) {
        String posBoton;
        int posX, posY;
        Button button;

        // Comprobamos si ha terminado la partida.
        if (tresEnRaya.finalizaPartida()) {
            textView.setText("Ha finalizado la partida! Pulse nueva partida.");
            return;
        }

        tresEnRaya.jugador = tresEnRaya.JUGADOR1;
        button = (Button) findViewById(view.getId());
        posBoton = button.getTag().toString();
        posX = posBoton.charAt(0) - '0';
        posY = posBoton.charAt(1) - '0';
        // Comprobamos que la posición no se ha jugado antes.
        if (tresEnRaya.juegaJugador(tresEnRaya.jugador, posX, posY))
            button.setText("X");
        else {
            textView.setText("No se puede jugar en esta posición.");
            return;
        }

        if (tresEnRaya.haGanado(tresEnRaya.jugador, posX, posY)) {
            textView.setText("Ha ganado el jugador: " + tresEnRaya.jugador + ". Pulse nueva partida.");
            return;
        }

        // Hacemos que juegue la máquina.
        tresEnRaya.jugador = tresEnRaya.JUGADOR2;
        do {
            posX = r.nextInt(3);
            posY = r.nextInt(3);
        } while (!tresEnRaya.juegaJugador(tresEnRaya.jugador, posX, posY));
        button = (Button) findViewById(idBotones[posX][posY]);
        button.setText("O");

        if (tresEnRaya.haGanado(tresEnRaya.jugador, posX, posY))
            textView.setText("Ha ganado el jugador: " + tresEnRaya.jugador + ". Pulse nueva partida.");

    }

    public void nuevaPartida (View view) {
        Button button;

        tresEnRaya.borrarTablero();
        tresEnRaya.jugador = tresEnRaya.JUGADOR1;
        textView.setText("Nueva Partida.");

        button = (Button) findViewById(R.id.button);
        button.setText("");
        button = (Button) findViewById(R.id.button2);
        button.setText("");
        button = (Button) findViewById(R.id.button3);
        button.setText("");
        button = (Button) findViewById(R.id.button4);
        button.setText("");
        button = (Button) findViewById(R.id.button5);
        button.setText("");
        button = (Button) findViewById(R.id.button6);
        button.setText("");
        button = (Button) findViewById(R.id.button7);
        button.setText("");
        button = (Button) findViewById(R.id.button8);
        button.setText("");
        button = (Button) findViewById(R.id.button9);
        button.setText("");

    }
}
