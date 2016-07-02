package com.wxine.android.auth;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wxine_online.wxine_online.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Leeeeee on 2016/5/9.
 */
public class RegisterActivity extends AppCompatActivity {
    private TextView login_text;
    private EditText name, password, repassword;
    private String Name, Password, Repassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        repassword = (EditText) findViewById(R.id.confirm_password);
        login_text = (TextView) findViewById(R.id.login_text);
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(l);
                RegisterActivity.this.finish();
            }
        });
    }

    public void main_register(View v) {
        Name = name.getText().toString();
        Password = password.getText().toString();
        Repassword = repassword.getText().toString();
        if (!Password.equals(Repassword)) {
            Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            MyAsyncTask task = new MyAsyncTask();
            task.execute(Name, Password);
        }
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://10.0.2.2:82/AndroidLogin/register.php");
                String urlParams = "name=" + name + "&password=" + password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("2")) {
                s = "用户名已存在，请重新输入";
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
            } else if (s.equals("1")) {
                s = "注册成功";
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                RegisterActivity.this.finish();
            }
        }
    }
}
