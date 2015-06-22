package com.example.fernandotorres.sportlog_quiz;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;




public class Login extends ActionBarActivity {
    private SQLiteDatabase bd;

    static String NAME = "";
    static String PASS = "";

    EditText editName;
    EditText editPass;
    Button btnLogin;
    Button vtnRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editName = (EditText) findViewById(R.id.Usuario);
        editPass = (EditText) findViewById(R.id.Contraseña);
        btnLogin = (Button) findViewById(R.id.button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNombre = editName.getText().toString();
                String txtPassword = editPass.getText().toString();
                bd = openOrCreateDatabase("bdregistro", MODE_PRIVATE,null);
                Cursor r = bd.rawQuery("Select * from registro where nombre = '"+ txtNombre + "' and contraseña='" + txtPassword + "';",null);
                r.moveToFirst();
                NAME = r.getString(r.getColumnIndex("nombre"));
                PASS = r.getString(r.getColumnIndex("contraseña"));
                r.close();
                bd.close();
                if (txtNombre.equals(NAME) && txtPassword.equals(PASS)) {
                    SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("NAME", editName.getText().toString());
                    editor.putString("PASS", editPass.getText().toString());

                    editor.commit();
                    Intent i = new Intent(Login.this, Principal.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "El usuario o contraseña no es correcto", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void vtnregistro(View c){
        Intent f = new Intent(Login.this, Registro.class);
        startActivity(f);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
