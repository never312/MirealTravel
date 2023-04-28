package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Обозначаем поля ввода логина и пароля и сохраняем в переменные (Позже исправить на бд)
        EditText loginEdit = (EditText) findViewById(R.id.login_edit_text);
        EditText passEdit = (EditText) findViewById(R.id.pass_edit_text);

        String login = loginEdit.getText().toString();
        String pass = passEdit.getText().toString();

        ImageView enterPic = (ImageView) findViewById(R.id.enter_pic);
        ImageView regPic = (ImageView) findViewById(R.id.reg_pic);

        enterPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // прописать два варианта:
                // 1)если пользователь авторизован то направить его на следующее activity
                // 2)если нет, то показать всплывающее окно неверного логина или пароля
            }
        });

        regPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // направить на activity для регистрации
            }
        });
    }





}
