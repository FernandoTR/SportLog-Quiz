package com.example.fernandotorres.sportlog_quiz;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends ActionBarActivity {
    private SQLiteDatabase bd;
    EditText nombre;
    EditText email;
    EditText contraseña;
    EditText confirmarcontraseña;
    Button Registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre = (EditText)findViewById(R.id.Nombre);
        email = (EditText)findViewById(R.id.Email);
        contraseña = (EditText)findViewById(R.id.Contraseña);
        confirmarcontraseña=(EditText)findViewById(R.id.ConfirmarContraseña);
        Registrar = (Button)findViewById(R.id.button);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (validacion()){
                String txtNombre = nombre.getText().toString();
                String txtEmail = email.getText().toString();
                String txtContrasena =  contraseña.getText().toString();
                agregar(txtNombre, txtEmail, txtContrasena);
                //       }
            }
        });
    }
    public void agregar(String nom, String correo, String contrasena){
        bd = openOrCreateDatabase("bdregistro",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS registro(nombre VARCHAR," + " email VARCHAR, " + " contraseña VARCHAR);");
        bd.execSQL("INSERT INTO registro VALUES('"+nom+"','"+correo+"','"+contrasena+"');");
        bd.close();
        Toast.makeText(this, "Registro Guardado", Toast.LENGTH_LONG).show();
        Intent f = new Intent(Registro.this, Login.class);
        startActivity(f);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
