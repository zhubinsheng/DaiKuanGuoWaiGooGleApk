package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.BaseAct;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.MyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act.Act_Web;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter.LoanAdapter1;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的账单
 */
public class Act_Bill extends BaseAct {
    @BindView(R.id.lv_myloan)
    RecyclerView lvMyLoan;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    public int initLayoutId() {
        return R.layout.act_bill;
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

    private int page = 1;
    List<MyLoanBean.DataBean.LoanListBean> datas = new ArrayList<>();

    @Override
    public void initData() {
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            datas.clear();
            page = 1;
            loanLists();
            refreshlayout.finishRefresh(2000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            page++;
            loanLists();
            refreshlayout.finishLoadMore(2000);
        });
        lvMyLoan.setLayoutManager(new LinearLayoutManager(this));
        lvMyLoan.setNestedScrollingEnabled(false);
        loanLists();
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

    @OnClick(R.id.logon_back)
    public void onViewClicked() {
        finish();
    }

    private LoanAdapter1 adapter1;

    /**
     * 获取我的账单
     */
    public void loanLists() {
        HttpHelper.loanLists(this, page + "", new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                try {
                    Gson gson = new Gson();
                    MyLoanBean entity = gson.fromJson(succeed, MyLoanBean.class);
                    if (entity.getStatus() == 1) {
                        for (int i = 0; i < entity.getData().getLoan_list().size(); i++) {
                            datas.add(entity.getData().getLoan_list().get(i));
                        }
                        if (adapter1 == null) {
                            adapter1 = new LoanAdapter1(datas);
                            adapter1.setOnItemChildClickListener((adapter, view, position) -> {
                                switch (view.getId()) {
                                    case R.id.tv_type:
                                        String Pay_url = datas.get(position).getPay_url();
                                        Intent intent = new Intent();
                                        intent.setClass(Act_Bill.this, Act_Web.class);
                                        intent.putExtra("url", ApiConstant.ROOT_URL + Pay_url);
                                        startActivity(intent);
                                        Debug.e("----------"+ApiConstant.ROOT_URL + Pay_url);
                                        break;
                                }
                            });
                            lvMyLoan.setAdapter(adapter1);
                        } else {
                            adapter1.notifyDataSetChanged();
                        }
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }
}
