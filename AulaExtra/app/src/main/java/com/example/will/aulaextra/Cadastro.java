package com.example.will.aulaextra;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        emailField = findViewById(R.id.edtEmailCadastro);
        passwordField = findViewById(R.id.edtPasswordCadastro);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        String email = params.getString("email");
        String password = params.getString("password");

        emailField.setText(email);
        passwordField.setText(password);

    }

    public void create(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", emailField.getText().toString());
        intent.putExtra("password", passwordField.getText().toString());

        startActivityForResult(intent, 10);
    }
}
