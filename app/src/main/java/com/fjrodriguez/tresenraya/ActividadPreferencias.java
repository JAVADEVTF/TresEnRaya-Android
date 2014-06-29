package com.fjrodriguez.tresenraya;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class ActividadPreferencias extends Activity {

    protected SharedPreferences preferences;
    protected CheckBox checkBoxJugador;
    protected RadioButton radioButtonX, radioButtonO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_preferencias);
        preferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        checkBoxJugador = (CheckBox) findViewById(R.id.checkBoxJugador);
        checkBoxJugador.setChecked(preferences.getBoolean("jugadorEmpieza", true));

        radioButtonX = (RadioButton) findViewById(R.id.radioButtonX);
        radioButtonX.setSelected(preferences.getBoolean("juegaConX", true));

        radioButtonO = (RadioButton) findViewById(R.id.radioButtonO);
        radioButtonO.setSelected(preferences.getBoolean("juegaConO", false));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actividad_preferencias, menu);
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

    public void guardarPrefs(View view) {
        // TODO: Corregir fallo al grabar el estado de los radioButtons.
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("jugadorEmpieza", checkBoxJugador.isChecked());
        editor.putBoolean("juegaConX", radioButtonX.isSelected());
        editor.putBoolean("juegaConO", radioButtonO.isSelected());

        Log.d("debug", "Radio buttonX value is " + String.valueOf(radioButtonX.isSelected()));
        Log.d("debug", "Radio buttonO value is " + String.valueOf(radioButtonO.isSelected()));
        editor.commit();
    }
}
