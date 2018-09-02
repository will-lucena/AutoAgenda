package com.example.will.fragmentsexample;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeftFragment lf = new LeftFragment();
        RightFragment rf = new RightFragment();
        CenterFragment cf = new CenterFragment();
        BelowFragment bf = new BelowFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.fragment_left, lf);
        transaction.add(R.id.fragment_center, cf);
        transaction.add(R.id.fragment_right, rf);
        transaction.add(R.id.fragment_below, bf);

        transaction.commit();
    }
}
