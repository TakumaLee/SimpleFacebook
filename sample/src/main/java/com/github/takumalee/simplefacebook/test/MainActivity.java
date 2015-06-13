package com.github.takumalee.simplefacebook.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.github.takumalee.simplefacebook.Builder.FieldsBuilder;
import com.github.takumalee.simplefacebook.KeithSimpleFacebook;
import com.github.takumalee.simplefacebook.entities.Fields;
import com.github.takumalee.simplefacebook.facebookCallback.MeResponseListener;
import com.github.takumalee.simplefacebook.facebookCallback.SimpleLoginListener;
import com.github.takumalee.simplefacebook.response.MeResponse;
import com.github.takumalee.simplefacebook.utils.Utils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.meResponse)
    TextView meResponse;
    @InjectView(R.id.read)
    Button read;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.logout)
    Button logout;
    @InjectView(R.id.result)
    TextView result;
    @InjectView(R.id.tvreadMeBy)
    TextView tvreadMeBy;
    @InjectView(R.id.readMeBy)
    Button readMeBy;


    private Context context;
    private KeithSimpleFacebook keithSimpleFacebook;

    @OnClick(R.id.readMeBy)
    public void readMeBy(final Button readMeBy) {
        FieldsBuilder fieldsBuilder = new FieldsBuilder();
        ArrayList<Fields> fields = new ArrayList<Fields>();
        fields.add(Fields.bio);
        fields.add(Fields.accounts);
        fields.add(Fields.activities);
        fieldsBuilder.buildReadMeByBundle(fields);

        /*
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");

        keithSimpleFacebook.readMeBy(this, new MeByResponseListener() {
            @Override
            public void sus(HashMap<String, String> map) {
                readMeBy.setText(Utils.instance.writeValueAsString(map));
            }

            @Override
            public void fail(GraphResponse response) {
                readMeBy.setText(Utils.instance.writeValueAsString(response));
            }
        },parameters);
        */
    }

    @OnClick(R.id.read)
    public void read(Button read) {
        keithSimpleFacebook.readMe(this, new MeResponseListener() {
            @Override
            public void sus(MeResponse obmeResponse) {
                meResponse.setText(Utils.instance.writeValueAsString(obmeResponse));
            }

            @Override
            public void fail(GraphResponse response) {
                meResponse.setText(Utils.instance.writeValueAsString(response));
            }
        });
    }

    @OnClick(R.id.logout)
    public void logout(Button logout) {
        keithSimpleFacebook.logout();
    }

    @OnClick(R.id.login)
    public void login(Button login) {
        keithSimpleFacebook.login(this, new SimpleLoginListener() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Utils.instance.toast(context, loginResult.toString());
            }

            @Override
            public void onCancel() {
                Utils.instance.toast(context, "onCancel");
            }

            @Override
            public void onError(FacebookException var1) {
                Utils.instance.toast(context, var1.toString());
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
