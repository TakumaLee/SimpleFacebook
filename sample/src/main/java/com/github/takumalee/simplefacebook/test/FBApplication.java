package com.github.takumalee.simplefacebook.test;

import android.app.Application;

import com.github.takumalee.simplefacebook.Permission;
import com.github.takumalee.simplefacebook.SimpleFacebook;

import java.util.Collection;

/**
 * Created by TakumaLee on 15/5/21.
 */
public class FBApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        SimpleFacebook.initialize(getApplicationContext());
    }

    public static Collection<String> getPermissions() {
        return new Permission.Builder()
                .add(Permission.USER_PHOTOS)
                .add(Permission.EMAIL)
                .add(Permission.USER_ABOUT_ME)
                .add(Permission.USER_BIRTHDAY)
                .add(Permission.PUBLIC_PROFILE)
                .add(Permission.USER_STATUS)
                .create();
    }

}
