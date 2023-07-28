package com.example.educationnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class RegisterActivity extends AppCompatActivity {
    Button user_login, user_register;
    EditText std_name, std_enroll, std_enroll_cof;
    AutoCompleteTextView student_class, year;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance("https://bca-2023-default-rtdb.firebaseio.com/").getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user_login = (Button)findViewById(R.id.logInStudent);
        user_register = (Button)findViewById(R.id.user_register);
        std_name = (EditText)findViewById(R.id.student_name);
        std_enroll = (EditText)findViewById(R.id.student_enrollment);
        student_class = (AutoCompleteTextView) findViewById(R.id.student_class);
        std_enroll_cof = (EditText)findViewById(R.id.student_enrollment_confirm);

        String[] std_class={
                "BCA", "BBA",
                "BSc","BA",
                "BCom","BEd"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,std_class);
        student_class=(AutoCompleteTextView)findViewById(R.id.student_class);
        student_class.setThreshold(1);
        student_class.setAdapter(adapter);

        String[] class_year={
                "First Year",
                "Second Year",
                "Third Year"};

        ArrayAdapter<String> year_adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,class_year);
        year  = (AutoCompleteTextView)findViewById(R.id.class_year);
        year.setThreshold(1);
        year.setAdapter(year_adapter);

        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = std_name.getText().toString();
                String enrollment = std_enroll.getText().toString();
                String stud_class = student_class.getText().toString();
                String class_year = year.getText().toString();
                String enrollment_con = std_enroll_cof.getText().toString();

                if(name.isEmpty() || enrollment.isEmpty() || stud_class.isEmpty() || class_year.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
                else if(!enrollment.equals(enrollment_con))
                {
                    Toast.makeText(RegisterActivity.this, "Enrollment Number Doesn't Match", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Students Data").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check the existing student
                            if(snapshot.hasChild(enrollment_con)){
                                Toast.makeText(RegisterActivity.this, "Student Already Registered", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                //Storing Data into firebase database
                                databaseReference.child("Students Data").child(enrollment_con).child("Student Enrollment").setValue(enrollment_con);
                                databaseReference.child("Students Data").child(enrollment_con).child("Student Name").setValue(name);
                                databaseReference.child("Students Data").child(enrollment_con).child("Student Class").setValue(stud_class);
                                databaseReference.child("Students Data").child(enrollment_con).child("Student Year").setValue(class_year);
                                Toast.makeText(RegisterActivity.this, "Welcome "+name, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,logInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, logInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}