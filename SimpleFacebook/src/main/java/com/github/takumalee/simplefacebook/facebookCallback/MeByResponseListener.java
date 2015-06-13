package com.github.takumalee.simplefacebook.facebookCallback;

import com.facebook.GraphResponse;

import java.util.HashMap;

/**
 * Created by Nijugon on 2015/6/13.
 */
public interface MeByResponseListener {
    void sus(HashMap<String, String> map);

    void fail(GraphResponse response);
}
