package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        EditText loginEdit = (EditText) findViewById(R.id.login_reg_text);
        EditText mailEdit = (EditText) findViewById(R.id.mail_reg_text);
        EditText passEdit = (EditText) findViewById(R.id.pass_reg_text);

        String login = loginEdit.getText().toString();
        String mail = mailEdit.getText().toString();
        String pass = passEdit.getText().toString();

        ImageView regPic = (ImageView) findViewById(R.id.reg_pic);

        regPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Прописать переход на главную поисковую страницу
                //перед этим предварительно показав пользователю
                //всплывающее окно с успешной регистрацией

                Intent linkToTourSearch = new Intent(RegActivity.this, TourSearch.class);
                startActivity(linkToTourSearch);
            }
        });


    }
}