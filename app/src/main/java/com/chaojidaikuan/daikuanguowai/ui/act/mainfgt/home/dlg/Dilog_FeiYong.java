package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;
import com.lykj.aextreme.afinal.common.BaseDialog;

/**
 * 费用详情
 */
public class Dilog_FeiYong extends BaseDialog implements BaseQuickAdapter.OnItemChildClickListener {
    private RecyclerView myRecyclerView;
    public OnBackCenter onBackTime;
    public HomeDashbordBean homeBean;

    public Dilog_FeiYong(Context context, HomeDashbordBean homeBean1, OnBackCenter onBackTime1) {
        super(context);
        this.onBackTime = onBackTime1;
        this.homeBean = homeBean1;
    }

    @Override
    protected int initLayoutId() {
        return R.layout.dilog_feiyong;
    }

    @Override
    protected void initWindow() {
        windowDeploy(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
    }

    TextView amount, amount_limit, service_fee, interestrate, qishuDaty, damount, deadline;

    @Override
    protected void initView() {
        setOnClickListener(R.id.center);
        amount = getView(R.id.amount);
        amount_limit = getView(R.id.amount_limit);
        service_fee = getView(R.id.service_fee);
        interestrate = getView(R.id.interestrate);
        qishuDaty = getView(R.id.qishuDaty);
        damount = getView(R.id.damount);//fq_list取
        deadline = getView(R.id.deadline);//fq_list取
    }

    @Override
    protected void initData() {
        amount.setText("Rp."+homeBean.getData().getData().getLoan().getAmount());
        amount_limit.setText("Rp."+homeBean.getData().getData().getAmount_limit() );
        service_fee.setText("Rp."+homeBean.getData().getData().getLoan().getService_fee());
        interestrate.setText("Rp."+homeBean.getData().getData().getLoan().getInterestrate() );
        qishuDaty.setText(homeBean.getData().getData().getLoan().getQs() + "期/" + homeBean.getData().getData().getLoan().getDays() + "天");
        if (homeBean.getData().getData().getLoan().getFq_list() != null && homeBean.getData().getData().getLoan().getFq_list().size() > 0) {
            damount.setText("Rp."+homeBean.getData().getData().getLoan().getFq_list().get(0).getDamount());
            deadline.setText(homeBean.getData().getData().getLoan().getFq_list().get(0).getDeadline());
        }

    }

    @Override
    protected void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.center://确认
                dismiss();
                onBackTime.onBackCenter();
                break;
        }
    }

    String stDate = "";
    private int indext = 0;

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        indext = position;
        adapter.notifyDataSetChanged();
    }

    public interface OnBackCenter {
        void onBackCenter();

    }
}
