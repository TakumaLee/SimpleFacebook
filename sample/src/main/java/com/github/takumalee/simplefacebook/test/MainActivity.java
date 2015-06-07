package com.github.takumalee.simplefacebook.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.github.takumalee.simplefacebook.KeithSimpleFacebook;
import com.github.takumalee.simplefacebook.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.token)
    TextView token;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.userid)
    TextView userid;
    @InjectView(R.id.logout)
    Button logout;
    @InjectView(R.id.read)
    Button read;
    @InjectView(R.id.result)
    TextView result;
    String access_token;
    private Context context;
    private KeithSimpleFacebook keithSimpleFacebook;

    @OnClick(R.id.read)
    public void read(Button read) {
        keithSimpleFacebook.read(context, result, access_token);
    }

    @OnClick(R.id.logout)
    public void logout(Button logout) {
        keithSimpleFacebook.logOut(new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Utils.instance.toast(context, "logout");
            }

            @Override
            public void onCancel() {
                Utils.instance.toast(context, "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Utils.instance.toast(context, "onError" + e.toString());
            }
        });

    }

    @OnClick(R.id.login)
    public void login(Button login) {
        keithSimpleFacebook.login(this, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                access_token = loginResult.getAccessToken().getToken().toString();
                token.setText("access_token:" + access_token);
                String getUserId = loginResult.getAccessToken().getUserId();
                userid.setText("getUserId:" + getUserId);
                Utils.instance.toast(context, loginResult.toString());
            }

            @Override
            public void onCancel() {
                Utils.instance.toast(context, "onCancel");
            }

            @Override
            public void onError(FacebookException e) {
                Utils.instance.toast(context, "onError" + e.toString());
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = this;
        //getKey();

        keithSimpleFacebook = new KeithSimpleFacebook(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        keithSimpleFacebook.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keithSimpleFacebook.destroy();
        ButterKnife.reset(this);
    }


}
