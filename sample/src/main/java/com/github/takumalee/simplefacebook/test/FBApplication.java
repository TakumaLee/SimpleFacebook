package com.github.takumalee.simplefacebook.test;

import android.app.Application;

import com.github.takumalee.simplefacebook.Permission;
import com.github.takumalee.simplefacebook.SimpleFacebook;

/**
 * Created by TakumaLee on 15/5/21.
 */
public class FBApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        SimpleFacebook.initialize(getApplicationContext());
    }

    public static Permission[] permissions = new Permission[] {
        Permission.USER_PHOTOS,
        Permission.EMAIL,
        Permission.PUBLISH_ACTION,
        Permission.USER_ABOUT_ME,
        Permission.USER_BIRTHDAY,
        Permission.PUBLIC_PROFILE,
        Permission.USER_BIRTHDAY,
        Permission.USER_STATUS
    };

}
