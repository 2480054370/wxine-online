package com.wxine.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;

/**
 * Created by zz on 2016/6/14.
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar SettingsToolbar;
    private TableRow LoginAuthentication;
    private TableRow MessageAuthentication;
    private TableRow SecretAdmin;
    private TableRow NightMode;
    private TableRow AboutWangxin;
    private Switch ModeSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingsToolbar = (Toolbar) findViewById(R.id.SettingsToolbar);
        LoginAuthentication = (TableRow) findViewById(R.id.LoginAuthentication);
        MessageAuthentication = (TableRow) findViewById(R.id.MessageAuthentication);
        SecretAdmin = (TableRow) findViewById(R.id.SecretAdmin);
        NightMode = (TableRow) findViewById(R.id.NightMode);
        AboutWangxin = (TableRow) findViewById(R.id.AboutWangxin);
        ModeSwitchButton = (Switch) findViewById(R.id.ModeSwitchButton);

        LoginAuthentication.setOnClickListener(this);
        MessageAuthentication.setOnClickListener(this);
        SecretAdmin.setOnClickListener(this);
        NightMode.setOnClickListener(this);
        AboutWangxin.setOnClickListener(this);

        SettingsToolbar.setTitle("返回");
        setSupportActionBar(SettingsToolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginAuthentication:
                Intent Lintent = new Intent(this, SettingsAccount.class);
                startActivity(Lintent);
                break;
            case R.id.MessageAuthentication:
                Intent Mintent = new Intent(this, SettingsMessage.class);
                startActivity(Mintent);
                break;
            case R.id.SecretAdmin:
                Intent Sintent = new Intent(this, SettingsSecret.class);
                startActivity(Sintent);
                break;
            case R.id.NightMode:
                if (ModeSwitchButton.isChecked()) {
                    ModeSwitchButton.setChecked(false);
                    Toast.makeText(SettingsActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    ModeSwitchButton.setChecked(true);
                    Toast.makeText(SettingsActivity.this, "true", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.AboutWangxin:
                Intent Aintent = new Intent(this, SettingsAbountwx.class);
                startActivity(Aintent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
