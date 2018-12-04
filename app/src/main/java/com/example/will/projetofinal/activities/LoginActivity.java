package com.example.will.projetofinal.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.projetofinal.utils.BundleKeys;
import com.example.will.projetofinal.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

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
        
        if ((boolean)getIntent().getExtras().get(BundleKeys.is_logged_in.toString()))
        {
            usernameLabel.setText(Profile.getCurrentProfile().getName());
            Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(300, 200)).into(picture);
        }
        
        loginButton.setReadPermissions("public_profile", "user_events");
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Bundle extras = getIntent().getExtras();
                extras.putBoolean(BundleKeys.is_logged_in.toString(), true);
                getIntent().putExtras(extras);
                setResult(RESULT_OK, getIntent());
                finish();
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
