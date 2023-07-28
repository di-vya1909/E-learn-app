package com.example.educationnotes.bca.firstsem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.educationnotes.R;
import com.google.android.material.tabs.TabLayout;

public class BCA_First_Sem_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bca_first_sem);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        // Create an instance of your custom FragmentPagerAdapter
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
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
                    return new textNotesFragment();
                case 1:
                    return new eBooksFragment();
                case 2:
                    return new questionPapersFragment();
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