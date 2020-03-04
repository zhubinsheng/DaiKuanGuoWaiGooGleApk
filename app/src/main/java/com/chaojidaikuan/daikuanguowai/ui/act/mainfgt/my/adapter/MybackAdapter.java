package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.BankBeen;

import java.util.List;

public class MybackAdapter extends BaseAdapter {
    private Context context;
    private List<BankBeen> data;
    public MybackAdapter(Context context, List<BankBeen> data){
        this.context=context;
        this.data=data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        ViewHolderAdd holderadd;
        if (i==data.size()-1){
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_myback_add,
                        null);

                holderadd = new ViewHolderAdd();
                /*得到各个控件的对象*/
                holderadd.iv_add = (ImageView) convertView.findViewById(R.id.iv_add);
                convertView.setTag(holderadd);//绑定ViewHolder对象
            } else {
                holderadd = (ViewHolderAdd) convertView.getTag();//取出ViewHolder对象
            }
            holderadd.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteLisener.Click("",1);
                }
            });

        }else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_myback,
                        null);

                holder = new ViewHolder();
                /*得到各个控件的对象*/
                holder.tv_back_name = (TextView) convertView.findViewById(R.id.tv_back_name);
                holder.tv_back_id = (TextView) convertView.findViewById(R.id.tv_back_id);
                holder.iv_delet = (ImageView) convertView.findViewById(R.id.iv_delet);
                holder.iv_card = (ImageView) convertView.findViewById(R.id.iv_card);
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
            holder.tv_back_id.setText(BankCard(data.get(i).getBankcard()));
            holder.tv_back_name.setText(data.get(i).getBankname());
            Glide.with(context).load(data.get(i).getImageurl()).into(holder.iv_card);
//            Glide.with(context).load(data.get(i).getImageurl())
//                    .into(new SimpleTarget<GlideDrawable>() {
//                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//                        @Override
//                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                            Log.e("resource",resource.toString());
//                            holder.iv_card.setBackground(resource);
//                        }
//                    });
            holder.iv_delet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteLisener.Click(data.get(i).getId(),2);
                }
            });
        }


        return convertView;
    }
    class ViewHolder {
        public TextView tv_back_id;
        public TextView tv_back_name;
        public ImageView iv_delet;
        public ImageView iv_card;

    }
    class ViewHolderAdd {
        public ImageView iv_add;

    }
    public setOnDeleteLisener onDeleteLisener;
    public interface setOnDeleteLisener{
        void Click(String cardid,int i);
    }
    public void setOnArtClick(setOnDeleteLisener onDeleteLisener){
        this.onDeleteLisener=onDeleteLisener;
    }
    //给银行卡号加*
    public static String BankCard(String aa){
//        String s=aa.substring(0, 4) + "  ****  ****  " + aa.substring(12,16);
        return aa;
    }
}
