package com.example.mirealtravel;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView prof_login = (TextView) findViewById(R.id.login_from_db);
        prof_login.setText(DatabaseConnection.getCurrentUser());

        TextView prof_email = (TextView) findViewById(R.id.email_from_db);
        prof_email.setText(DatabaseConnection.getEmail(DatabaseConnection.getCurrentUser()));

        ImageView exit_btn = (ImageView) findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkToEnterActivity = new Intent(ProfileActivity.this, EnterOrRegActivity.class);
                startActivity(linkToEnterActivity);
            }
        });

        ImageView pass_edit_btn = (ImageView) findViewById(R.id.pass_edit_btn);
        pass_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // дописать активити смены пароля
            }
        });

    }
}