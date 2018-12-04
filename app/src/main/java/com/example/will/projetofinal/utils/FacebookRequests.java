package com.example.will.projetofinal.utils;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

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
                        callback.onEndProccess(response.getRawResponse());
                    }
                }
        ).executeAsync();
    }
}

