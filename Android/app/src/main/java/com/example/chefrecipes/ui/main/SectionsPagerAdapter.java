package com.example.chefrecipes.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chefrecipes.HomeFragment;
import com.example.chefrecipes.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_home, R.string.tab_bites, R.string.tab_breakfast, R.string.tab_Desert};
    private final Context mContext;
    Fragment one, two, three,four;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 0:
                if(one == null)
                    one = new HomeFragment();
                return one;
            case 1:
                if(two == null)
                    two= new HomeFragment();
                return two;
            case 2:
                if(three == null)
                    three= new HomeFragment();
                return three;
            case 3:
                if(four == null)
                    three= new HomeFragment();
                return three;
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}