package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EnterOrRegActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_or_reg);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //Обозначаем поля ввода логина и пароля и сохраняем в переменные (Позже исправить на бд)
        EditText loginEdit = (EditText) findViewById(R.id.login_edit_text);
        EditText passEdit = (EditText) findViewById(R.id.pass_edit_text);


        ImageView enterPic = (ImageView) findViewById(R.id.enter_pic);
        ImageView regPic = (ImageView) findViewById(R.id.reg_pic);

        enterPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = loginEdit.getText().toString();
                String password = passEdit.getText().toString();

                if (DatabaseConnection.authenticateUser(username, password)) {
                    Toast.makeText(getApplicationContext(), "Авторизация успешна", Toast.LENGTH_SHORT).show();
                    Intent linkToTourSearchActivity = new Intent(EnterOrRegActivity.this, TourSearchActivity.class);
                    startActivity(linkToTourSearchActivity);
                } else {
                    Toast.makeText(getApplicationContext(), "Неверное имя пользователя или пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });


        regPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkToReg = new Intent(EnterOrRegActivity.this, RegActivity.class);
                startActivity(linkToReg);
            }
        });
    }





}
