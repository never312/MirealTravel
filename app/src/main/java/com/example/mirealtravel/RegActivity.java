package com.example.mirealtravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


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
                String username = loginEdit.getText().toString();
                String email = mailEdit.getText().toString();
                String password = passEdit.getText().toString();

                if (DatabaseConnection.registerUser(username, email, password)) {
                    Toast.makeText(getApplicationContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    Intent linkToTourSearch = new Intent(RegActivity.this, TourSearchActivity.class);
                    startActivity(linkToTourSearch);
                } else {
                    Toast.makeText(getApplicationContext(), "Не удалось зарегистрировать пользователя", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}