package com.example.fernandotorres.sportlog_quiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PantallaJuegoDos extends ActionBarActivity {
    EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_juego_dos);
        texto = (EditText)findViewById(R.id.texto);
    }
    public void boton1(View v)
    {
        Button b = (Button) v;
        texto.setText(texto.getText().toString() + b.getText().toString());
        b.setVisibility(View.INVISIBLE);
        String valida;
        valida = texto.getText().toString();
        if(valida.equals("BARCELONA")){
            Toast.makeText(this, "Es correcto", Toast.LENGTH_LONG).show();
            Intent i = new Intent(PantallaJuegoDos.this, PantallaNivelUno.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_juego_dos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
