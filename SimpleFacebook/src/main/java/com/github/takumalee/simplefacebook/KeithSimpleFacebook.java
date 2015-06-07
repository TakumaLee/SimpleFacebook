package com.github.takumalee.simplefacebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Nijugon on 2015/6/6.
 */
public class KeithSimpleFacebook {
    public static final String TAG = KeithSimpleFacebook.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private Context context;


    public KeithSimpleFacebook(Context context) {
        this.context = context;
        facebookSdkinit(context);

    }

    private void facebookSdkinit(Context context) {
        FacebookSdk.sdkInitialize(context);
        SimpleFacebookHolder.accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                if (currentAccessToken != null) {
                    Log.d("currentAccessToken:", currentAccessToken.toString());
                }


            }
        };
        SimpleFacebookHolder.callbackManager = CallbackManager.Factory.create();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void logOut(FacebookCallback<LoginResult> loginResult) {
        LoginManager.getInstance().registerCallback(SimpleFacebookHolder.callbackManager, loginResult);
        LoginManager.getInstance().logOut();
    }

    public void login(Activity activity, FacebookCallback<LoginResult> loginResult) {
        LoginManager.getInstance().registerCallback(SimpleFacebookHolder.callbackManager, loginResult);
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends"));
    }

    //https://graph.facebook.com/v2.3/me
    // ?access_token=CAACEdEose0cBADuUnvr60DjxyFZCoMlSE05bIt7xLe9HCc81EWY2Mpp5kZBtr7sJRo0vuJ4VTNBqRjlYwofMUmzcJZCMSjSsM4owLkdS9RsjUgJrVRr6SXffw25go7I8oETrvk9R2EjuQSB0P6M0FZBIkcz92jg8JnLZCu2TRafAvNJpb2ISbVZBRHxeagNt6yYrPtdaJ19dw85WZBU9WYi
    // &debug=all&format=json&method=get&pretty=0&suppress_http_code=1
    public void read(Context context, final TextView textView, String access_token) {
        if (access_token == null) {
            Utils.instance.toast(context, "access_token==null");
            return;
        }
        if (access_token.isEmpty()) {
            Utils.instance.toast(context, "access_token is Empty");
            return;

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Config.host.concat("me").concat("?access_token=").concat(access_token).concat("&format=json&method=get"), null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error.toString():", error.toString());
            }

        });

        addToRequestQueue(jsonObjReq, "read");
    }

    public void destroy() {
        SimpleFacebookHolder.accessTokenTracker.stopTracking();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        SimpleFacebookHolder.callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public static class SimpleFacebookHolder {
        protected static AccessTokenTracker accessTokenTracker;
        protected static CallbackManager callbackManager;
    }

}
