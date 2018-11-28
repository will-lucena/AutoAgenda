package com.example.will.projetofinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    
    private ImageView picture;
    private TextView usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.login_button);
        picture = findViewById(R.id.facebookPicture);
        usernameLabel = findViewById(R.id.facebookUsername);
    
        usernameLabel.setText(Profile.getCurrentProfile().getName());
        Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(300, 200)).into(picture);
        
        loginButton.setReadPermissions("public_profile", "user_events");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("debug", loginResult.getAccessToken().getUserId());
                Log.i("debug", loginResult.getRecentlyGrantedPermissions().toString());
            }

            @Override
            public void onCancel() {
                // App code
                Log.i("debug", "arregou");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("debug", "deu ruim");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
