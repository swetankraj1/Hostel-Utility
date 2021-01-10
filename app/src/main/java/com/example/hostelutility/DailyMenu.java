package com.example.hostelutility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class DailyMenu extends AppCompatActivity {

    private PDFView pdf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_menu);

        pdf1 = (PDFView) findViewById(R.id.pddf);
        pdf1.fromAsset("dailymenu.pdf").load();
    }
}