package com.github.takumalee.simplefacebook.facebookCallback;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Created by Nijugon on 2015/6/13.
 */
public interface SimpleLoginListener {
    void onSuccess(LoginResult loginResult);

    void onCancel();

    void onError(FacebookException var1);
}
