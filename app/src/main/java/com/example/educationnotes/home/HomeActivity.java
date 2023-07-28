package com.example.educationnotes.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationnotes.R;
import com.example.educationnotes.bca.BcaActivity;
import com.example.educationnotes.logInActivity;
import com.example.educationnotes.SessionManager;

public class HomeActivity extends AppCompatActivity {
    ListView list;
    String[] fullName = new String[]{
            "Bachelor in Computer Application", "Bachelor of Business Administration",
            "Bachelor of Science", "Bachelor of Commerce",
            "Bachelor of Arts", "Bachelor of Education",
    };
    String[] className = new String[]{
            "BCA", "BBA",
            "BSc", "BCom",
            "BA", "BEd",
    };
    Integer[] imgid = new Integer[]{
            R.drawable.bcalogo, R.drawable.bbalogo,
            R.drawable.bsclogo, R.drawable.bcomlogo,
            R.drawable.balogo, R.drawable.bedlogo,
    };

    String reciveName;
    ImageButton logout;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);

        HomeListAdapter adapter = new HomeListAdapter(this, className, fullName, imgid);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        logout = findViewById(R.id.logOut);

        if (sessionManager.isLoggedIn()) {
            reciveName = sessionManager.getUserName();
            TextView user_name = findViewById(R.id.showName);
            user_name.setText("Hi " + reciveName);
        } else {
            Intent loginIntent = new Intent(HomeActivity.this, logInActivity.class);
            startActivity(loginIntent);
            finish();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                switch (position) {
                    case 0:
                        handleCourseSelection("BCA");
                        break;
                    case 1:
                        handleCourseSelection("BBA");
                        break;
                    case 2:
                        handleCourseSelection("BSc");
                        break;
                    case 3:
                        handleCourseSelection("BCom");
                        break;
                    case 4:
                        handleCourseSelection("BA");
                        break;
                    case 5:
                        handleCourseSelection("BEd");
                        break;
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutUser();
                Intent loginIntent = new Intent(HomeActivity.this, logInActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private void handleCourseSelection(String courseName) {
        Toast.makeText(getApplicationContext(), "Welcome To " + courseName + " Department", Toast.LENGTH_SHORT).show();

        if ("BCA".equals(courseName)) {
            Intent bcaIntent = new Intent(HomeActivity.this, BcaActivity.class);
            startActivity(bcaIntent);
        } else {
            // Implement actions for other courses here
        }
    }
}
