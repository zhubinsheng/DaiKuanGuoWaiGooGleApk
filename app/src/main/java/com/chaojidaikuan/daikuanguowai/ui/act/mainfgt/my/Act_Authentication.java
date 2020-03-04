package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter.VpAdapter;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.Fgt_Authentication1;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.Fgt_Authentication2;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.Fgt_Authentication3;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.Fgt_Authentication4;
import com.chaojidaikuan.daikuanguowai.ui.view.NoScrollViewPager;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 认证页
 */
public class Act_Authentication extends BaseAct {
    @BindView(R.id.logon_back)
    ImageView logonBack;
    @BindView(R.id.myViewPager)
    NoScrollViewPager myViewPager;
    @BindView(R.id.tvTitle1)
    TextView tvTitle1;
    @BindView(R.id.tvTitle2)
    TextView tvTitle2;
    @BindView(R.id.tvTitle3)
    TextView tvTitle3;
    @BindView(R.id.tvTitle4)
    TextView tvTitle4;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public int initLayoutId() {
        return R.layout.act_authentication;
    }

    @Override
    public void initView() {
        hideHeader();
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    private List<BaseFragment> datasFgt = new ArrayList<>();
    private VpAdapter vpAdapter;

    @Override
    public void initData() {
        datasFgt.add(new Fgt_Authentication1());
        datasFgt.add(new Fgt_Authentication2());
        datasFgt.add(new Fgt_Authentication3());
        datasFgt.add(new Fgt_Authentication4());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), datasFgt);
        myViewPager.setAdapter(vpAdapter);
        myViewPager.setNoScroll(true);
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                swiche(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tvTitle.setText("Informasi Pekerjaan");
        if (getIntent().getStringExtra("indext") != null) {
            myViewPager.setCurrentItem(Integer.valueOf(getIntent().getStringExtra("indext")));
        }
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void swiche(int p) {
        if (p == 0) {
            tvTitle.setText("Informasi Pekerjaan");
            tvTitle1.setSelected(true);
            tvTitle2.setSelected(false);
            tvTitle3.setSelected(false);
            tvTitle4.setSelected(false);
        } else if (p == 1) {
            tvTitle.setText("Kontak Darurat");
            tvTitle1.setSelected(true);
            tvTitle2.setSelected(true);
            tvTitle3.setSelected(false);
            tvTitle4.setSelected(false);
        } else if (p == 2) {
            tvTitle.setText("Informasi Tambahan");
            tvTitle1.setSelected(true);
            tvTitle2.setSelected(true);
            tvTitle3.setSelected(true);
            tvTitle4.setSelected(false);
        } else if (p == 3) {
            tvTitle.setText("Informasi Identitas");
            tvTitle1.setSelected(true);
            tvTitle2.setSelected(true);
            tvTitle3.setSelected(true);
            tvTitle4.setSelected(true);
        }
    }

    @OnClick(R.id.logon_back)
    public void onViewClicked() {
        finish();
    }
}
