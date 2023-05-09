package com.example.sqlite_remplazo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite_remplazo2.entidades.Correos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText RCO, RAS, RCT, RNO;
    Button BOTON_GUARDAR, BOTON_ELIMINAR;
    ArrayList<Correos> listaArrayCorreos;

    RecyclerView listaCorreos;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCorreos = findViewById(R.id.listaCorreos);
        listaCorreos.setLayoutManager(new LinearLayoutManager(this));

        listaArrayCorreos = new ArrayList<>();

        CorreosDB dbCorreos = new CorreosDB(this, "BaseDatos",null,1);
        RCO= (EditText) findViewById(R.id.Ingrese_Correo);
        RAS= (EditText) findViewById(R.id.Ingrese_Asunto);
        RCT= (EditText) findViewById(R.id.Ingrese_Contenido);
        RNO= (EditText) findViewById(R.id.Ingrese_Nombre);
        BOTON_GUARDAR= (Button) findViewById(R.id.Guarda);
        BOTON_ELIMINAR= (Button) findViewById(R.id.Elimina);

        BOTON_GUARDAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar(view);
            }
        });

        BOTON_ELIMINAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void Registrar(View view){
        CorreosDB admin = new CorreosDB(this, "BaseDatos",null,1);
        SQLiteDatabase BaseDatos = admin.getWritableDatabase();
        String correo = RCO.getText().toString();
        String asunto = RAS.getText().toString();
        String contenido = RCT.getText().toString();
        String nombre = RNO.getText().toString();

        if (!correo.isEmpty() && !asunto.isEmpty() && !contenido.isEmpty() && !nombre.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("correo", correo);
            registro.put("asunto", asunto);
            registro.put("contenido", contenido);
            registro.put("nombre", nombre);
            BaseDatos.insert("usuario", null, registro);
            BaseDatos.close();
            RCO.setText("");
            RAS.setText("");
            RCT.setText("");
            RNO.setText("");
            Toast.makeText(this,"Registro Almacenado Exitosamente", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Ingrese correctamente los datos", Toast.LENGTH_LONG).show();
        }
    }

}