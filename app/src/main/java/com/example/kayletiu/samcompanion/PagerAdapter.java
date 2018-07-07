package com.example.kayletiu.samcompanion;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Mharjorie Sandel on 07/07/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numofTabs;

    public PagerAdapter(FragmentManager fm, int NoOfTabs){
        super(fm);
        this.numofTabs = NoOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                News t1 = new News();
                return t1;

            case 1:
                Home t2 = new Home();
                return t2;

            case 2:
                Quotes t3 = new Quotes();
                return t3;

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
