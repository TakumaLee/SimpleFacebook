package com.github.takumalee.simplefacebook.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.takumalee.simplefacebook.SimpleFacebook;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.github.takumalee.simplefacebook.R.layout.activity_main);
        (findViewById(R.id.button_Login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleFacebook.getInstance().logInWithReadPermissions(this, FBApplication.permissions);
            }
        });
        (findViewById(R.id.button_Logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.github.takumalee.simplefacebook.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.github.takumalee.simplefacebook.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
