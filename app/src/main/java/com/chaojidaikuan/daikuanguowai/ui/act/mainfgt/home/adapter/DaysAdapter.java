package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;

import java.util.List;

public class DaysAdapter extends BaseQuickAdapter<HomeDashbordBean.DataBeanX.DataBean.LoansBean.DaysBean, BaseViewHolder> {
    private Context mContext;

    public DaysAdapter(Context context, List<HomeDashbordBean.DataBeanX.DataBean.LoansBean.DaysBean> data) {
        super(R.layout.item_days, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDashbordBean.DataBeanX.DataBean.LoansBean.DaysBean item) {
        TextView item_day = helper.getView(R.id.item_day);
        item_day.setText(item.getVal() + "Hari");
        ImageView item_selected = helper.getView(R.id.item_selected);
        if (item.isDaysStatus()) {
            item_day.setSelected(true);
            item_selected.setVisibility(View.VISIBLE);
        } else {
            item_day.setSelected(false);
            item_selected.setVisibility(View.GONE);
        }


    }
}