package com.example.hostelutility;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    Button btnLogoutt;
    ImageView profile, myChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        btnLogoutt = findViewById(R.id.logoutt);
        profile = findViewById(R.id.myPro);
        myChat = findViewById(R.id.chat);

        btnLogoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(Home.this, LoginActivity.class);
                startActivity(intToMain);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilePage = new Intent(Home.this, MyProfile.class);
                startActivity(profilePage);
            }
        });

        myChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatPage = new Intent(Home.this, Chat.class);
                startActivity(chatPage);
            }
        });
    }
}