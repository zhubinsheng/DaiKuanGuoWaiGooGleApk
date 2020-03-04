package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.adapter.MyBankAdapter;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankListsBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的银行信息
 */
public class Act_MyBank extends BaseAct {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.et_Search)
    EditText etSearch;

    @Override
    public int initLayoutId() {
        return R.layout.act_mybank;
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

    private List<bankListsBean.DataBean.BanksBean> datas = new ArrayList<>();

    @Override
    public void initData() {
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyBankAdapter(datas);
        myRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent();
            intent.putExtra("bankName", datas.get(position).getVal());
            setResult(10, intent);
            finish();
        });
        bankValidBanks();
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    private MyBankAdapter adapter;
    bankListsBean entity;

    /**
     * 我的银行卡选择
     */
    public void bankValidBanks() {
        HttpHelper.bankValidBanks(this, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, bankListsBean.class);
                if (entity.getStatus() == 1) {
                    datas.addAll(entity.getData().getBanks());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btSearch, R.id.logon_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btSearch://搜索
                loding.show();
                datas.clear();
                for (int i = 0; i < entity.getData().getBanks().size(); i++) {
                    if (countStr(entity.getData().getBanks().get(i).getVal(), etSearch.getText().toString()) > 0) {
                        datas.add(entity.getData().getBanks().get(i));
                    }
                }
                Debug.e("----------size===" + datas.size());
                adapter.notifyDataSetChanged();
                loding.dismiss();
                break;
            case R.id.logon_back:
                finish();
                break;
        }
    }

    private static int counter = 0;

    /**
     * 判断str1中包含str2的个数
     *
     * @param str1
     * @param str2
     * @return counter
     */
    public static int countStr(String str1, String str2) {
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) +
                    str2.length()), str2);
            return counter;
        }
        return 0;
    }
}
