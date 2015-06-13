package com.github.takumalee.simplefacebook.graph;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.github.takumalee.simplefacebook.facebookCallback.MeByResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.MeResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.SimpleLoginListener;


/**
 * Created by Nijugon on 2015/6/13.
 */
public interface Graph {

        void login(Activity activity, SimpleLoginListener loginResult);

        void logout();

        void readMe(final Context context, final MeResponseListener meResponseListener);

        void readMeBy(final Context context, final MeByResponseListener meByResponseListener, final Bundle parameters);

}
