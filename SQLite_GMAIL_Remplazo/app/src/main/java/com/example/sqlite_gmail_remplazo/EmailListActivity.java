package com.example.sqlite_gmail_remplazo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EmailListActivity extends AppCompatActivity implements EmailListAdapter.OnEmailClickListener {
    private RecyclerView recyclerView;
    private EmailListAdapter emailListAdapter;
    private List<Email> emailList = new ArrayList<>();
    private EmailDatabaseHelper emailDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_list);

        emailDatabaseHelper = new EmailDatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emailListAdapter = new EmailListAdapter(emailList, this);
        recyclerView.setAdapter(emailListAdapter);
        loadEmails();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEmailDialog();
            }
        });

        // Crea una instancia de Email y establece sus propiedades
        Email email = new Email();
        email.setSender("ejemplo@ejemplo.com");
        email.setSubject("Asunto del correo electrónico");
        email.setDate("2023-05-07");
        email.setTime("12:00");
        email.setContent("Contenido del correo electrónico");
        int icon = R.drawable.email_icon;
        email.setIcon(icon);

        // Agrega el correo electrónico a la base de datos
        emailDatabaseHelper.addEmail(email);
    }

    private void loadEmails() {
        emailList.clear();
        emailList.addAll(emailDatabaseHelper.getAllEmails());
        emailListAdapter.notifyDataSetChanged();
    }

    private void showAddEmailDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nuevo correo");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_email, null);
        final EditText senderEditText = view.findViewById(R.id.senderEditText);
        final EditText subjectEditText = view.findViewById(R.id.subjectEditText);
        final EditText dateEditText = view.findViewById(R.id.dateEditText);
        final EditText timeEditText = view.findViewById(R.id.timeEditText);
        final EditText contentEditText = view.findViewById(R.id.contentEditText);
        final ImageView iconImageView = view.findViewById(R.id.iconImageView);

        iconImageView.setImageResource(R.drawable.email_icon);

        builder.setView(view);

        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sender = senderEditText.getText().toString().trim();
                String subject = subjectEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String time = timeEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();
                int icon = R.drawable.email_icon;

                if (sender.isEmpty() || subject.isEmpty() || date.isEmpty() || time.isEmpty() || content.isEmpty()) {
                    Toast.makeText(EmailListActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Email email = new Email(0, sender, subject, date, time, content, icon);
                    emailDatabaseHelper.addEmail(email);
                    loadEmails();
                    Toast.makeText(EmailListActivity.this, "Correo guardado exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onEmailClick(int position) {
        final Email email = emailList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(email.getSubject());
        builder.setMessage(email.getContent());
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                emailDatabaseHelper.deleteEmail(email);
                loadEmails();
                Toast.makeText(EmailListActivity.this, "Correo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
