package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.MyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.utils.FormatTosepara;

import java.util.List;

public class LoanAdapter1 extends BaseQuickAdapter<MyLoanBean.DataBean.LoanListBean, BaseViewHolder> {
    public LoanAdapter1(@Nullable List<MyLoanBean.DataBean.LoanListBean> data) {
        super(R.layout.item_loan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyLoanBean.DataBean.LoanListBean item) {
        helper.setText(R.id.overduefee, "Rp " + FormatTosepara.formatTosepara(Float.valueOf(item.getDamount())))
                .setText(R.id.damount, "Rp " + FormatTosepara.formatTosepara(Float.valueOf(item.getDaozhang())) + "")
                .setText(R.id.qs, item.getDeadline())
                .setText(R.id.tv_time, item.getRefundtime());
        if (item.getPay_url() != null && !item.getPay_url().equals("")) {
            helper.setText(R.id.tv_type, "Segara pelunasan");
            if (!item.getPay_url().equals("")) {
                helper.getView(R.id.tv_type).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.tv_type);
            } else {
                helper.getView(R.id.tv_type).setVisibility(View.GONE);
            }
            helper.getView(R.id.tv_huankuanzhong).setVisibility(View.VISIBLE);//还款中
            helper.getView(R.id.tv_yiyuqi).setVisibility(View.GONE);//已逾期
            helper.getView(R.id.tv_yihuankuan).setVisibility(View.GONE);//已还款
        } else {
            helper.getView(R.id.tv_huankuanzhong).setVisibility(View.GONE);//还款中
            helper.getView(R.id.tv_yiyuqi).setVisibility(View.GONE);//已逾期
            helper.getView(R.id.tv_yihuankuan).setVisibility(View.VISIBLE);//已还款
//            helper.setText(R.id.overduefee, "Rp 0.000");
//            helper.getView(R.id.myImage).setVisibility(View.GONE);
            helper.setText(R.id.tv_type, "sudah dilunasi");
        }
        if (item.getOverdue_days() > 0) {
//            helper.getView(R.id.myImage).setVisibility(View.VISIBLE);
//            Glide.with(mContext).load(R.mipmap.repayment_pic_be_overdue).into((ImageView) helper.getView(R.id.myImage));
            helper.getView(R.id.tv_yiyuqi).setVisibility(View.VISIBLE);//已逾期
            helper.getView(R.id.tv_huankuanzhong).setVisibility(View.GONE);//还款中
            helper.setText(R.id.yuqitianshu, "Jumlah hari terlambat：" + item.getOverdue_days() + "hari");
            helper.setText(R.id.tv_type, "Pembayaran Pinjaman Segera");
            if (!item.getPay_url().equals("")) {
                helper.getView(R.id.tv_type).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.tv_type);
            } else {
                helper.getView(R.id.tv_type).setVisibility(View.GONE);
            }
        }
    }
}