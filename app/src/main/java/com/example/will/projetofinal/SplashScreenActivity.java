package com.example.will.projetofinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class SplashScreenActivity extends AppCompatActivity implements ICallbackReceiver {
    
    private Intent intent;
    private Bundle extras;
    private LoadingFragment fragment;
    private boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        isLoggedIn = accessToken != null && !accessToken.isExpired();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        extras = new Bundle();
        extras.putBoolean(BundleKeys.is_logged_in.toString(), isLoggedIn);
        if (isLoggedIn)
        {
            fragment = (LoadingFragment) getSupportFragmentManager().findFragmentById(R.id.loadingMessages);
        }
        else
        {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtras(extras);
            startActivityForResult(intent, 1);
        }
    }
    
    @Override
    public void onEndProccess(String result) {
        Log.i("debug", "callback received");
        launchMainActivity(result);
    }

    @Override
    public boolean isLoggedin() {
        return isLoggedIn;
    }

    private void launchMainActivity(String json)
    {
        Log.i("debug", "launching activity");
        intent = new Intent(this, MainActivity.class);
        extras.putString(BundleKeys.facebook_events_json.toString(), json);
        extras.putBoolean(BundleKeys.is_logged_in.toString(), true);
        intent.putExtras(extras);
        Log.i("debug", "go");
        startActivity(intent);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            FacebookRequests.getInstance().getUserEvents(this);
        }
    }
}
