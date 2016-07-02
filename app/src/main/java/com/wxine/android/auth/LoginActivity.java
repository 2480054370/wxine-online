package com.wxine.android.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.example.wxine_online.wxine_online.R;
import com.wxine.android.MainActivity;
import com.wxine.android.MyApplication;
import com.wxine.android.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Leeeeee on 2016/6/22.
 */
public class LoginActivity extends AppCompatActivity {
    private User user;
    private MyApplication app;
    private Checkable rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private TextView register_text;
    private EditText name, password;
    private String Name, Password;
    private String NAME = null, PASSWORD = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        rememberPass = (Checkable) findViewById(R.id.remember_pass);
        register_text = (TextView) findViewById(R.id.register_text);
        register_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(r);
                LoginActivity.this.finish();
            }
        });

        check_remember();
    }

    public void check_remember() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemeber = pref.getBoolean("remember_password", false);
        if (isRemeber) {
            //将账号和密码都设置到文本框中
            String loginusername = pref.getString("loginusername", "");
            String loginpassword = pref.getString("loginpassword", "");
            name.setText(loginusername);
            password.setText(loginpassword);
            rememberPass.isChecked();
        }
    }

    public void main_login(View v) {
        Name = name.getText().toString();
        Password = password.getText().toString();
        MyAsyncTask async = new MyAsyncTask();
        async.execute(Name, Password);
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data = "";
            org.json.JSONObject jsonObject = null;
            int tmp;

            try {
                CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                URL url = new URL("http://10.0.2.2:82/AndroidLogin/login.php");
                String urlParams = "name=" + name + "&password=" + password;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();        //输出流
                os.write(urlParams.getBytes());
                httpURLConnection.connect();
                //从headers中取出来，并分割，为什么要分割，Chrome打开F12自己看看就明白了
                String[] aaa = httpURLConnection.getHeaderField("Set-Cookie").split(";");
                app.saveUser(aaa[0],name,password,true);
                app.setSessionId(aaa[0]);
                //app.sessionId = aaa[0];
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();          //输入流
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
            String err = null;
            if (s.toString().equals("{\"user_data\":[]}")) {
                Toast.makeText(LoginActivity.this, "账号或者密码错误，请重新输入", Toast.LENGTH_SHORT).show();
            } else {
                editor = pref.edit();
                if (rememberPass.isChecked()) {   //检查复选框是否被选中
                    editor.putBoolean("remember_password", true);
                    editor.putString("loginusername", name.getText().toString());
                    editor.putString("loginpassword", password.getText().toString());
                } else {
                    editor.clear();
                }
                editor.commit();
                app.login = true;
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                LoginActivity.this.finish();

//                try {
//                    JSONObject root = new JSONObject(s);
//                    JSONObject user_data = root.getJSONObject("user_data");
//                    NAME = user_data.getString("name");
//                    PASSWORD = user_data.getString("password");
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    i.putExtra("name", NAME);
//                    i.putExtra("err", err);
//                    startActivity(i);
//                    LoginActivity.this.finish();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    err = "Exception: " + e.getMessage();
//                }
            }
        }
    }
}
