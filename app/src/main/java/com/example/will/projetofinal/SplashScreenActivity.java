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
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        
        extras = new Bundle();
        extras.putBoolean(BundleKeys.is_logged_in.toString(), isLoggedIn);
        if (isLoggedIn)
        {
            Log.i("debug", "ok");
            intent = new Intent(this, MainActivity.class);
            FacebookRequests.getInstance().getUserEvents(this);
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
