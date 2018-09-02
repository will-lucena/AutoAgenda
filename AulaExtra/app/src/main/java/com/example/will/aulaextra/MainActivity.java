package com.example.will.aulaextra;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    private final String adminEmail = "admin@admin.com";
    private final String adminPassword = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailField = findViewById(R.id.edtEmail);
        passwordField = findViewById(R.id.edtPassword);

    }

    public void login(View view)
    {
        String currentEmail = emailField.getText().toString();
        String currentPassword = passwordField.getText().toString();

        if (currentEmail.equals(""))
        {
            emailField.setError("Campo obrigatorio");
        }

        if (currentPassword.equals(""))
        {
            passwordField.setError("Campo obrigatorio");
        }

        if (currentEmail.equals(adminEmail) && currentPassword.equals(adminPassword))
        {
            Toast.makeText(this, "Bem vindo", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Achou errado otario", Toast.LENGTH_SHORT).show();
        }
    }

    public void sigin(View view) {
        Intent intent = new Intent();

        intent.putExtra("email", emailField.getText().toString());
        intent.putExtra("password", passwordField.getText().toString());

        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "cadastrado", Toast.LENGTH_SHORT).show();
    }
}
