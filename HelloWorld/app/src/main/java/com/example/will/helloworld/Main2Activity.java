package com.example.will.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        String msg = params.getString("texto");

        TextView t = findViewById(R.id.suaMae);
        t.setText(msg);

    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent();
        it.putExtra("return", "pastel de flango");
        setResult(RESULT_OK, it);
        finish();
    }
}
