package com.example.will.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("flow", "onCreate");
 }

    public void next(View view) {
        Intent it = new Intent(this, Main2Activity.class);
        it.putExtra("texto", "coxinha");
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            String msg = data.getStringExtra("return");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            Button b = findViewById(R.id.teuCu);
            b.setEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("flow", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("flow", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("flow", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("flow", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("flow", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("flow", "onRestart");
    }
}
