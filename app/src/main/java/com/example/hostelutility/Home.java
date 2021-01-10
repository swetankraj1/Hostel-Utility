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
    ImageView profile, myChat, location, notice, contacts, dailyMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        btnLogoutt = findViewById(R.id.logoutt);
        profile = findViewById(R.id.myPro);
        myChat = findViewById(R.id.chat);
        location = findViewById(R.id.locate);
        notice = findViewById(R.id.note);
        contacts = findViewById(R.id.contact);
        dailyMenu = findViewById(R.id.dailyM);


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

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locationPage = new Intent(Home.this, MapsActivity.class);
                startActivity(locationPage);
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noticePage = new Intent(Home.this, NoticeActivity2.class);
                startActivity(noticePage);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactPage = new Intent(Home.this, ContactActivity.class);
                startActivity(contactPage);
            }
        });
        dailyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent menuPage = new Intent(Home.this, DailyMenu.class);
                startActivity(menuPage);
            }
        });


    }

    public void gotopdf(View view) {
        Intent intent = new Intent(Home.this,NoticeActivity2.class);
        startActivity(intent);
    }

    public void gotoanotherpdf(View view) {
        Intent daily = new Intent(Home.this,DailyMenu.class);
        startActivity(daily);
    }
}