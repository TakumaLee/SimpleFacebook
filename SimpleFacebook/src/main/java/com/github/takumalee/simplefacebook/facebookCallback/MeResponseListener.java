package com.github.takumalee.simplefacebook.facebookCallback;

import com.facebook.GraphResponse;
import com.github.takumalee.simplefacebook.response.MeResponse;

/**
 * Created by Nijugon on 2015/6/13.
 */
public interface MeResponseListener {
    void sus(MeResponse meResponse);

    void fail(GraphResponse response);
}
