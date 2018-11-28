package com.example.will.projetofinal;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static android.content.Context.MODE_PRIVATE;

public class FacebookRequests
{
    private static FacebookRequests instance;
    
    private AccessToken token;
    
    public static FacebookRequests getInstance()
    {
        if (instance == null)
        {
            instance = new FacebookRequests();
        }
        return instance;
    }
    
    private FacebookRequests()
    {
        token = AccessToken.getCurrentAccessToken();
    }
    
    public void getUserEvents(final ICallbackReceiver callback)
    {
        Log.i("debug", "requesting events");
        new GraphRequest(
                token,
                "/" + token.getUserId() + "/events",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.i("debug", "request success");
                        Uri pictureUri = Profile.getCurrentProfile().getProfilePictureUri(300, 200);
                        callback.onEndProccess(response.getRawResponse());
                    }
                }
        ).executeAsync();
    }
}

