package com.example.will.atv14_08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int value = getIntent().getExtras().getInt("msg");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Button button = findViewById(R.id.imageButton);
        button.setText(String.valueOf(value));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int value = data.getExtras().getInt("msg");

        Button button = findViewById(R.id.imageButton);
        button.setText(String.valueOf(value));
    }

    public void loadFirstActivity(View view) {
        Intent it = new Intent(this, FirstActivity.class);

        Button button = findViewById(R.id.imageButton);
        int value = Integer.parseInt(button.getText().toString());

        it.putExtra("msg", value+1);
        startActivityForResult(it, 1);
    }
}
