package com.github.takumalee.simplefacebook.graph;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.takumalee.simplefacebook.Permission;
import com.github.takumalee.simplefacebook.facebookCallback.MeByResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.MeResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.SimpleLoginListener;
import com.github.takumalee.simplefacebook.holder.SimpleFacebookHolder;
import com.github.takumalee.simplefacebook.response.MeResponse;
import com.github.takumalee.simplefacebook.utils.Utils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Nijugon on 2015/6/13.
 */
public class GraphImp implements Graph {
    private SimpleFacebookHolder simpleFacebookHolder;

    public GraphImp(SimpleFacebookHolder simpleFacebookHolder) {
        this.simpleFacebookHolder = simpleFacebookHolder;
    }

    @Override
    public void login(Activity activity, final SimpleLoginListener simpleLoginListener) {
        Permission.Builder builder = new Permission.Builder();
        builder.add(Permission.PUBLIC_PROFILE);
        LoginManager.getInstance().logInWithReadPermissions(activity, builder.create());
        LoginManager.getInstance().registerCallback(simpleFacebookHolder.getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (loginResult == null) {
                    LoginManager.getInstance().logOut();
                    return;
                }
                simpleFacebookHolder.setAccessToken(loginResult.getAccessToken());
                simpleLoginListener.onSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                simpleLoginListener.onCancel();
            }

            @Override
            public void onError(FacebookException e) {
                simpleLoginListener.onError(e);
            }
        });

    }

    @Override
    public void logout() {
        LoginManager.getInstance().logOut();
    }


    @Override
    public void readMe(final Context context, final MeResponseListener meResponseListener) {
        if (simpleFacebookHolder.getCallbackManager() == null) {
            Utils.instance.toast(context, "getCallbackManager==null must init in KeithSimpleFacebook.facebookSdkinit()");
            return;
        }

        GraphRequest request = GraphRequest.newMeRequest(simpleFacebookHolder.getAccessToken()
                ,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        if (response.getError() != null) {
                            meResponseListener.fail(response);
                            return;
                        }
                        try {
                            meResponseListener.sus(Utils.instance.getObjectMapper().readValue(object.toString(), MeResponse.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                            meResponseListener.fail(response);
                        }
                        /*
                        Utils.instance.toast(context, response.toString());
                        Utils.instance.logClaz(this, "GraphRequest:" + response.toString());
                        Utils.instance.logClaz(this, "GraphRequest:" + object.toString());
                        */
                    }
                });
        //Bundle parameters = new Bundle();
        //parameters.putString("fields", "id,name,link");
        //request.setParameters(parameters);
        //If you need any additional fields, or want to reduce the response payload for performance reasons,
        // you can add a fields parameter and request specific fields:
        request.executeAsync();
    }


    @Override
    public void readMeBy(final Context context, final MeByResponseListener meByResponseListener, final Bundle parameters) {
        if (simpleFacebookHolder.getCallbackManager() == null) {
            Utils.instance.toast(context, "getCallbackManager==null must init in KeithSimpleFacebook.facebookSdkinit()");
            return;
        }

        GraphRequest request = GraphRequest.newMeRequest(simpleFacebookHolder.getAccessToken()
                ,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        if (response.getError() != null) {
                            meByResponseListener.fail(response);
                            return;
                        }
                        try {
                            meByResponseListener.sus(Utils.instance.getObjectMapper().readValue(object.toString(), HashMap.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                            meByResponseListener.fail(response);
                        }
                        /*
                        Utils.instance.toast(context, response.toString());
                        Utils.instance.logClaz(this, "GraphRequest:" + response.toString());
                        Utils.instance.logClaz(this, "GraphRequest:" + object.toString());
                        */
                    }
                });
        // Bundle parameters = new Bundle();
        //parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        //If you need any additional fields, or want to reduce the response payload for performance reasons,
        // you can add a fields parameter and request specific fields:
        request.executeAsync();
    }
}
