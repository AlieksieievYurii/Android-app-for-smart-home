package com.whitedeveloper.controlhome.view.activitycreator;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.whitedeveloper.controlhome.R;

public class ActivityCreateNewElement extends AppCompatActivity {

    private ViewPager placeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_element);

        init();
    }

    private void init()
    {
        final TabLayout tabLayout = findViewById(R.id.tl_type_element);
        placeFragment = findViewById(R.id.vp_place_setttings);

        tabLayout.addTab(tabLayout.newTab().setText("Button"));
        tabLayout.addTab(tabLayout.newTab().setText("SeekBar"));
        tabLayout.addTab(tabLayout.newTab().setText("Sensor"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        placeFragment.setAdapter(pageAdapter);

        placeFragment.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                placeFragment.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void finishActivity() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
