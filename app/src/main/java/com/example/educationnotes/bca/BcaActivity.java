package com.example.educationnotes.bca;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationnotes.R;
import com.example.educationnotes.bca.fifthsem.BCA_Fifth_Sem_Activity;
import com.example.educationnotes.bca.fifthsem.UploadPdfFifthActivity;
import com.example.educationnotes.bca.firstsem.BCA_First_Sem_Activity;
import com.example.educationnotes.bca.firstsem.uploadPdfActivity;
import com.example.educationnotes.bca.fourthsem.BCA_Fourth_Sem_Activity;
import com.example.educationnotes.bca.fourthsem.UploadPdfFourthActivity;
import com.example.educationnotes.bca.secondsem.BCA_Second_Sem_Activity;
import com.example.educationnotes.bca.secondsem.UploadPdfSecondActivity;
import com.example.educationnotes.bca.sixthsem.BCA_Sixth_Sem_Activity;
import com.example.educationnotes.bca.sixthsem.UploadPdfSixthActivity;
import com.example.educationnotes.bca.thirdsem.BCA_Third_Sem_Activity;
import com.example.educationnotes.bca.thirdsem.UploadPdfThirdActivity;

public class BcaActivity extends AppCompatActivity {
    TextView first, second, third, fourth, fifth, sixth;
    TextView firstUpload, secondUpload, thirdUpload, fourthUpload, fifthUpload, sixthUpload;
    ImageView log_out;


    Button uploading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca);
//        log_out = (ImageView)findViewById(R.id.log_out);
//
//        log_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BcaActivity.this, logInActivity.class);
//                startActivity(intent);
//            }
//        });

        first = findViewById(R.id.firstSem);
        firstUpload = findViewById(R.id.firstSemUpload);
        second = findViewById(R.id.secondSem);
        secondUpload = findViewById(R.id.secondSemUpload);
        third = findViewById(R.id.thirdSem);
        thirdUpload = findViewById(R.id.thirdSemUpload);
        fourth = findViewById(R.id.fourthSem);
        fourthUpload = findViewById(R.id.fourthSemUpload);
        fifth = findViewById(R.id.fifthSem);
        fifthUpload = findViewById(R.id.fifthSemUpload);
        sixth = findViewById(R.id.sixthSem);
        sixthUpload = findViewById(R.id.sixthSemUpload);

        first.setOnClickListener(v -> startActivity(new Intent(BcaActivity.this, BCA_First_Sem_Activity.class)));
        firstUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, uploadPdfActivity.class);
                startActivity(intent);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, BCA_Second_Sem_Activity.class);
                startActivity(intent);
            }
        });
        secondUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, UploadPdfSecondActivity.class);
                startActivity(intent);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, BCA_Third_Sem_Activity.class);
                startActivity(intent);
            }
        });
        thirdUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, UploadPdfThirdActivity.class);
                startActivity(intent);
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, BCA_Fourth_Sem_Activity.class);
                startActivity(intent);
            }
        });
        fourthUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, UploadPdfFourthActivity.class);
                startActivity(intent);
            }
        });
        fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, BCA_Fifth_Sem_Activity.class);
                startActivity(intent);
            }
        });
        fifthUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, UploadPdfFifthActivity.class);
                startActivity(intent);
            }
        });
        sixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, BCA_Sixth_Sem_Activity.class);
                startActivity(intent);
            }
        });
        sixthUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BcaActivity.this, UploadPdfSixthActivity.class);
                startActivity(intent);
            }
        });
    }
}