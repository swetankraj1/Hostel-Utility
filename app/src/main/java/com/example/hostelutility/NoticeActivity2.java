package com.example.hostelutility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class NoticeActivity2 extends AppCompatActivity {


private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice2);

        pdfView = (PDFView) findViewById(R.id.pfd);
        pdfView.fromAsset("assessmentdetails.pdf").load();
    }
}