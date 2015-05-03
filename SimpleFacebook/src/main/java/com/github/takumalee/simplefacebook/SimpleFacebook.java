package com.github.takumalee.simplefacebook;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by TakumaLee on 15/5/2.
 */
public class SimpleFacebook {
    private static final String TAG = SimpleFacebook.class.getSimpleName();

    private CallbackManager callbackManager;

    public SimpleFacebook() {
        callbackManager = CallbackManager.Factory.create();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static class SingletonHolder {
        public static SimpleFacebook INSTANCE = new SimpleFacebook();
    }
    public SimpleFacebook getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void initSingleton(Context context) {
        FacebookSdk.sdkInitialize(context);
    }

    public void activateApp(Context context) {
        AppEventsLogger.activateApp(context);
    }

    public void deactivateApp(Context context) {
        AppEventsLogger.deactivateApp(context);
    }

    public void login() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.v(TAG, "onSuccess()");
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }
}
