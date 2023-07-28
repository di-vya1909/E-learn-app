package com.example.educationnotes.bca.fourthsem;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.educationnotes.R;
import com.google.android.material.tabs.TabLayout;

public class BCA_Fourth_Sem_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca_fourth_sem);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        // Create an instance of your custom FragmentPagerAdapter
        BCA_Fourth_Sem_Activity.PagerAdapter pagerAdapter = new BCA_Fourth_Sem_Activity.PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Connect the TabLayout with the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        // Set content descriptions for each tab
        tabLayout.getTabAt(0).setContentDescription("Text Notes");
        tabLayout.getTabAt(1).setContentDescription("E-Books");
        tabLayout.getTabAt(2).setContentDescription("Question Papers");
    }

    // Your custom FragmentPagerAdapter implementation
    private static class PagerAdapter extends FragmentPagerAdapter {

        private final String[] tabTitles = {"Text Notes", "E-Books", "Question Papers"};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Return the appropriate Fragment for each tab
            switch (position) {
                case 0:
                    return new TextNotesFourthFragment();
                case 1:
                    return new EBooksFourthFragment();
                case 2:
                    return new QuestionPapersFourthFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Return the title for each tab
            return tabTitles[position];
        }
    }
}