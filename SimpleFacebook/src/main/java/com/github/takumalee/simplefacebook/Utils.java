package com.github.takumalee.simplefacebook;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Nijugon on 2015/6/6.
 */
public enum Utils {
    instance;

    private void getKey(Context context) {

        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo("com.github.takumalee.simplefacebook.test", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String KeyResult = new String(Base64.encode(md.digest(), 0));//String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", KeyResult);

            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void toast(Context context, String msg) {

        SuperToast.create(context, msg, SuperToast.Duration.SHORT, Style.getStyle(Style.GREEN, SuperToast.Animations.FLYIN)).show();
    }
}
