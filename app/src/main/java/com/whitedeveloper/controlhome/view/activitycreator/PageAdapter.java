package com.whitedeveloper.controlhome.view.activitycreator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentButton;
import com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentSeekBar;
import com.whitedeveloper.controlhome.view.activitycreator.fragments.FragmentTextViewSensor;


public class PageAdapter extends FragmentStatePagerAdapter {
    private int num;

    PageAdapter(FragmentManager fm,int num) {
        super(fm);
        this.num = num;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: return new FragmentButton();
            case 1: return new FragmentSeekBar();
            case 2: return new FragmentTextViewSensor();
        }
        return null;
    }

    @Override
    public int getCount() {
        return num;
    }
}
