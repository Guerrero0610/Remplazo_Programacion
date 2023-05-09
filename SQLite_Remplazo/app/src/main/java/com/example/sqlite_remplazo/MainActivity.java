package com.example.sqlite_remplazo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etc, etn, ett;
    Button BOTON_GUARDAR, BOTON_CONSULTAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etc = (EditText) findViewById(R.id.Ingresa_Cedula);
        etn = (EditText) findViewById(R.id.Ingresa_Nombre);
        ett = (EditText) findViewById(R.id.Ingresa_Telefono);
        BOTON_GUARDAR = (Button) findViewById(R.id.Guarda);
        BOTON_CONSULTAR = (Button) findViewById(R.id.Consulta);

        BOTON_GUARDAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar(view);
            }
        });

        BOTON_CONSULTAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar(view);
            }
        });
    }

    public void Registrar(View view){
        AdminBD admin = new AdminBD(this, "BaseDatos",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String cedula = etc.getText().toString();
        String nombre = etn.getText().toString();
        String telefono = ett.getText().toString();

        if (!cedula.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("cedula", cedula);
            registro.put("nombre", nombre);
            registro.put("telefono", telefono);
            BaseDatos.insert("usuario", null, registro);
            BaseDatos.close();
            etc.setText("");
            etn.setText("");
            ett.setText("");
            Toast.makeText(this,"Registro Almacenado Exitosamente", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Ingrese correctamente los datos", Toast.LENGTH_LONG).show();
        }
    }

    public void consultar(View view){
        AdminBD admin = new AdminBD(this, "BaseDatos", null, 1);
        SQLiteDatabase BasedeDatos = admin.getWritableDatabase();
        String cedula1 = etc.getText().toString();
        if (!cedula1.isEmpty()){
            Cursor fila = BasedeDatos.rawQuery("select nombre, telefono from usuario where cedula="+cedula1, null);

            if (fila.moveToFirst()){
                etn.setText(fila.getString(0));
                ett.setText(fila.getString(1));
                BasedeDatos.close();
            }
            else {
                Toast.makeText(this, "No se encontro el usuario", Toast.LENGTH_LONG).show();
            }
        }
    }
}