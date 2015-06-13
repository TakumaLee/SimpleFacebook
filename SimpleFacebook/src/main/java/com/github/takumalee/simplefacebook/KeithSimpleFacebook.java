package com.github.takumalee.simplefacebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.github.takumalee.simplefacebook.facebookCallback.MeByResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.MeResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.SimpleLoginListener;
import com.github.takumalee.simplefacebook.graph.GraphImp;
import com.github.takumalee.simplefacebook.holder.SimpleFacebookHolder;
import com.github.takumalee.simplefacebook.utils.Utils;

/**
 * Created by Nijugon on 2015/6/6.
 */
public class KeithSimpleFacebook {
    public static final String TAG = KeithSimpleFacebook.class.getSimpleName();

    private Context context;
    private SimpleFacebookHolder simpleFacebookHolder;

    public KeithSimpleFacebook(Context context) {
        this.context = context;
        facebookSdkinit(context);

    }

    private void facebookSdkinit(Context context) {
        FacebookSdk.sdkInitialize(context);
        simpleFacebookHolder = new SimpleFacebookHolder();
        simpleFacebookHolder.setCallbackManager(CallbackManager.Factory.create());
        simpleFacebookHolder.setAccessTokenTracker(new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken != null) {
                    Utils.instance.logClaz(this, currentAccessToken.toString());
                } else {
                    Utils.instance.logClaz(this, "currentAccessToken is null");
                }
                simpleFacebookHolder.setAccessToken(currentAccessToken);
            }
        });
        simpleFacebookHolder.setGraph(new GraphImp(simpleFacebookHolder));


    }

    public void login(Activity activity, SimpleLoginListener loginResult) {
        simpleFacebookHolder.getGraph().login(activity, loginResult);
    }

    public void logout() {
        simpleFacebookHolder.getGraph().logout();
    }

    public void readMe(Context context, MeResponseListener meResponseListener) {
        simpleFacebookHolder.getGraph().readMe(context, meResponseListener);
    }

    public void readMeBy(Context context, MeByResponseListener meByResponseListener, Bundle parameters) {
        simpleFacebookHolder.getGraph().readMeBy(context, meByResponseListener, parameters);
    }
    //https://graph.facebook.com/v2.3/me
    // ?access_token=CAACEdEose0cBADuUnvr60DjxyFZCoMlSE05bIt7xLe9HCc81EWY2Mpp5kZBtr7sJRo0vuJ4VTNBqRjlYwofMUmzcJZCMSjSsM4owLkdS9RsjUgJrVRr6SXffw25go7I8oETrvk9R2EjuQSB0P6M0FZBIkcz92jg8JnLZCu2TRafAvNJpb2ISbVZBRHxeagNt6yYrPtdaJ19dw85WZBU9WYi
    // &debug=all&format=json&method=get&pretty=0&suppress_http_code=1

    public void destroy() {
        simpleFacebookHolder.setCallbackManager(null);
        simpleFacebookHolder.getAccessTokenTracker().stopTracking();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        simpleFacebookHolder.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }


}
