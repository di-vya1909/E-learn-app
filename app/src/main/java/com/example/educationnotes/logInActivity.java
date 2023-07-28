package com.example.educationnotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educationnotes.home.HomeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class logInActivity extends AppCompatActivity {
    Button user_login, user_signUp;
    EditText user_name, user_enroll;
    DatabaseReference databaseReference;
    SessionManager sessionManager; // SessionManager instance

    // Constant for Firebase database child reference
    private static final String DB_CHILD_STUDENTS = "Students Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Initialize the SessionManager
        sessionManager = new SessionManager(this);

        // Check if the user is already logged in
        if (sessionManager.isLoggedIn()) {
            // User is already logged in, redirect to HomeActivity
            Intent homeIntent = new Intent(logInActivity.this, HomeActivity.class);
            homeIntent.putExtra("KEY_NAME", sessionManager.getUserName());
            startActivity(homeIntent);
            finish();
        }

        // Get a reference to the Firebase database
        databaseReference = FirebaseDatabase.getInstance("https://bca-2023-default-rtdb.firebaseio.com/").getReference();

        user_login = findViewById(R.id.user_logIn);
        user_signUp = findViewById(R.id.registerStudent);
        user_name = findViewById(R.id.student_name);
        user_enroll = findViewById(R.id.student_enrollment);

        user_login.setOnClickListener(view -> {
            String name = user_name.getText().toString();
            String enroll = user_enroll.getText().toString();

            if (name.isEmpty() || enroll.isEmpty()) {
                Toast.makeText(logInActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
            } else {
                databaseReference.child(DB_CHILD_STUDENTS).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot != null && snapshot.hasChild(enroll)) {
                            // Enrollment exists in the database
                            String getUserName = snapshot.child(enroll).child("Student Name").getValue(String.class);

                            if (getUserName != null && getUserName.equals(name)) {
                                // Name matches the database
                                Toast.makeText(logInActivity.this, "Welcome Back " + name, Toast.LENGTH_SHORT).show();

                                // Save the login status and user name in the session
                                sessionManager.setLoggedIn(true);
                                sessionManager.setUserName(name);

                                Intent intent = new Intent(logInActivity.this, HomeActivity.class);
                                intent.putExtra("KEY_NAME", name);
                                startActivity(intent);
                                finish();
                            } else {
                                // Name doesn't match the database
                                Toast.makeText(logInActivity.this, "Wrong Name Entered", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Enrollment doesn't exist in the database
                            Toast.makeText(logInActivity.this, "Enrollment doesn't Exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled, if needed
                    }
                });
            }
        });

        user_signUp.setOnClickListener(view -> {
            Intent intent = new Intent(logInActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        user_signUp.setOnClickListener(view -> {
            Intent intent = new Intent(logInActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
