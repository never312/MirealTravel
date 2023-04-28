package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EnterOrRegActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_or_reg);

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
                Intent linkToReg = new Intent(EnterOrRegActivity.this, RegActivity.class);
                startActivity(linkToReg);
            }
        });
    }





}
