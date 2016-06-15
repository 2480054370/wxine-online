package com.wxine.android;

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
 * Created by zz on 2016/6/15.
 */
public class SettingsMessage extends AppCompatActivity implements View.OnClickListener {

    private Toolbar MessageToolbar;
    private TableRow EmailNotice;
    private TableRow PhoneNotice;
    private Switch EmailSwitchButton;
    private Switch PhoneSwitchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_message);

        MessageToolbar = (Toolbar) findViewById(R.id.MessageToolbar);
        EmailNotice = (TableRow) findViewById(R.id.EmailNotice);
        PhoneNotice = (TableRow) findViewById(R.id.PhoneNotice);
        EmailSwitchButton = (Switch) findViewById(R.id.EmailSwitchButton);
        PhoneSwitchButton = (Switch) findViewById(R.id.PhoneSwitchButton);

        EmailNotice.setOnClickListener(this);
        PhoneNotice.setOnClickListener(this);

        MessageToolbar.setTitle("返回");
        setSupportActionBar(MessageToolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EmailNotice:
                if (EmailSwitchButton.isChecked()) {
                    EmailSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    EmailSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.PhoneNotice:
                if (PhoneSwitchButton.isChecked()) {
                    PhoneSwitchButton.setChecked(false);
                    Toast.makeText(SettingsMessage.this, "false", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneSwitchButton.setChecked(true);
                    Toast.makeText(SettingsMessage.this, "true", Toast.LENGTH_SHORT).show();
                }
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
