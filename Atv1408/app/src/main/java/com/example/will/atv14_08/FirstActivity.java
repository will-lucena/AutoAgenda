package com.example.will.atv14_08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int value = 1;
        if (getIntent().getExtras() != null)
        {
            value = getIntent().getExtras().getInt("msg");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button = findViewById(R.id.firstButton);
        button.setText(String.valueOf(value));
    }

    public void loadImageActivity(View view) {
        Intent it = new Intent(this, ImageActivity.class);

        Button button = findViewById(R.id.firstButton);
        int value = Integer.parseInt(button.getText().toString());

        it.putExtra("msg", value+1);
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int value = data.getExtras().getInt("msg");

        Button button = findViewById(R.id.firstButton);
        button.setText(String.valueOf(value));
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
