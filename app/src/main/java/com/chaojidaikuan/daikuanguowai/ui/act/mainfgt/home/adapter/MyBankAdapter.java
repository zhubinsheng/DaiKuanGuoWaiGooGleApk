package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankListsBean;

import java.util.List;

public class MyBankAdapter extends BaseQuickAdapter<bankListsBean.DataBean.BanksBean, BaseViewHolder> {

    public MyBankAdapter( List<bankListsBean.DataBean.BanksBean> data) {
        super(R.layout.item_mybank, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, bankListsBean.DataBean.BanksBean item) {
        helper.setText(R.id.tv_title, item.getVal());

    }
}
