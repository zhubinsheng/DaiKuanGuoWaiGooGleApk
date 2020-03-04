package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lykj.aextreme.afinal.common.BaseFragment;

import java.util.List;

public class VpAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;
    public VpAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
