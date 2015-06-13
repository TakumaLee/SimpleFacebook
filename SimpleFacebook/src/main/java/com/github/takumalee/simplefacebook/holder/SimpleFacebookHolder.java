package com.github.takumalee.simplefacebook.holder;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginResult;
import com.github.takumalee.simplefacebook.graph.Graph;

/**
 * Created by Nijugon on 2015/6/13.
 */
public class SimpleFacebookHolder {
    private AccessTokenTracker accessTokenTracker;
    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> facebookCallback;
    private AccessToken accessToken;
    private Graph Graph;

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokenTracker getAccessTokenTracker() {
        return accessTokenTracker;
    }

    public void setAccessTokenTracker(AccessTokenTracker accessTokenTracker) {
        this.accessTokenTracker = accessTokenTracker;
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public void setCallbackManager(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    public FacebookCallback<LoginResult> getFacebookCallback() {
        return facebookCallback;
    }

    public void setFacebookCallback(FacebookCallback<LoginResult> facebookCallback) {
        this.facebookCallback = facebookCallback;
    }


    public com.github.takumalee.simplefacebook.graph.Graph getGraph() {
        return Graph;
    }

    public void setGraph(com.github.takumalee.simplefacebook.graph.Graph graph) {
        Graph = graph;
    }
}
