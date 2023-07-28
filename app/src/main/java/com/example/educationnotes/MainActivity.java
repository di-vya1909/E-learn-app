package com.example.educationnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Splash screen duration in milliseconds
    private static final long SPLASH_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delay the navigation to the main activity using a Handler
        new Handler().postDelayed(() -> {
            // Start the main activity after the splash duration
            Intent intent = new Intent(MainActivity.this, logInActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}
