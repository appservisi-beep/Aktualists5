package com.appservisi.aktalist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.appservisi.aktalist.fragmentler.Tab1Fragment;
import com.appservisi.aktalist.fragmentler.Tab2Fragment;
import com.appservisi.aktalist.fragmentler.Tab3Fragment;

/**
 * Created by suraj on 23/6/17.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 :
                Tab1Fragment tab1Fragment=new Tab1Fragment();
               // tab1Fragment.ali();
                return tab1Fragment;
            case 1 :
                Tab2Fragment tab2Fragment=new Tab2Fragment();
                return tab2Fragment;
            case 2 :
                Tab3Fragment tab3Fragment=new Tab3Fragment();
                return tab3Fragment;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}
