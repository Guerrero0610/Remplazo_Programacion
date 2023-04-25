package com.example.myapplicationsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Edit1;
    Button Boton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Edit1 = findViewById(R.id.Ingresa);
        Boton1 = findViewById(R.id.Guarda);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Edit1.setText(preferences.getString("usuarios",""));

        Boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar(view);
            }
        });
    }
    public void guardar(View view){
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencias.edit();
        Obj_editor.putString("usuarios", Edit1.getText().toString());
        Obj_editor.commit();
    }
}