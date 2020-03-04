package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.adapter.DaysAdapter;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.postapplyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.GlideImageLoader;
import com.chaojidaikuan.daikuanguowai.ui.view.ScrollTextViewView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.common.BaseFragment;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;

/**
 * 主页
 */
public class Fgt_Home extends BaseFragment {
    @BindView(R.id.tv_auto)
    ScrollTextViewView tvAuto;
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder1;
    @BindView(R.id.tv_Amount_limit)
    TextView tvAmountLimit;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.tv_Min_Amount_limit)
    TextView tvMinAmountLimit;
    @BindView(R.id.tv_Max_Amount_limit)
    TextView tvMaxAmountLimit;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    @BindView(R.id.service_fee)
    TextView serviceFee;
    @BindView(R.id.month_rate)
    TextView month_rate;
    @BindView(R.id.huankuanjine)
    TextView huankuanjine;
    @BindView(R.id.daozhangjine)
    TextView daozhangjine;
    @BindView(R.id.home_btCommit)
    TextView homeBtCommit;
    private AutoVerticalScrollTextViewUtil autoVerticalScrollTextViewUtil;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_home;
    }

    Unbinder unbinder;

    @Override
    public void initView() {
        hideHeader();
        unbinder = ButterKnife.bind(Fgt_Home.this, v);
        ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .init();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void initData() {
//        homeDashbord();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //拖动的进度
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAmountLimit.setText(progress + "");
                serviceFee.setText("Rp." + progress * 0.3);
                huankuanjine.setText(progress + "");
                daozhangjine.setText("" + (progress - (progress * 0.3)));
                amount = progress + "";
            }

            //开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //结束拖动
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        start();
    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onNoInterNet() {

    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    private List list = new ArrayList();
    private int indext = 0;
    private HomeDashbordBean entity;
    private String days = "", amount = "";
    private List<HomeDashbordBean.DataBeanX.DataBean.LoansBean.DaysBean> datas;

    /**
     * 首页数据
     */
    public void homeDashbord(String token) {
        HttpHelper.homeDashbord(getContext(),token, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                entity = gson.fromJson(succeed, HomeDashbordBean.class);
                if (entity.getStatus() == 1) {
                    if (entity.getData().getData().getLoan() != null) {
                        MyApplication.lognStatus = false;
                    } else {
                        MyApplication.lognStatus = true;
                    }
                    for (int i = 0; i < entity.getData().getData().getLatest_loads().size(); i++) {
                        list.add(entity.getData().getData().getLatest_loads().get(i).getTelephone() + "成功借款" + entity.getData().getData().getLatest_loads().get(i).getAmount() + "元");
                    }
                    autoVerticalScrollTextViewUtil = new AutoVerticalScrollTextViewUtil(tvAuto, (ArrayList<CharSequence>) list);
//                    设置滚动的时间间隔
                    autoVerticalScrollTextViewUtil.setDuration(5000);
                    autoVerticalScrollTextViewUtil.stop();
                    // 开启滚动
                    autoVerticalScrollTextViewUtil.start();
                    //设置图片加载器
                    List<String> image = new ArrayList<>();
                    for (int i = 0; i < entity.getData().getData().getAds().size(); i++) {
                        image.add(entity.getData().getData().getAds().get(i).getImage());
                    }
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setImages(image).start();
                    month_rate.setText("" + entity.getData().getData().getMonth_rate() + "%");
                    int num = (new Double(entity.getData().getData().getAmount_limit())).intValue();
                    seekBar.setMax(num);
                    seekBar.setProgress(num);
                    tvAmountLimit.setText(num + "");
                    serviceFee.setText("Rp." + Double.valueOf(entity.getData().getData().getAmount_limit()) * 0.3);
                    huankuanjine.setText(entity.getData().getData().getAmount_limit() + "");
                    daozhangjine.setText("" + (Double.valueOf(entity.getData().getData().getAmount_limit()) - (Double.valueOf(entity.getData().getData().getAmount_limit()) * 0.3)));
                    tvMaxAmountLimit.setText(entity.getData().getData().getAmount_limit() + "");
                    amount = entity.getData().getData().getAmount_limit() + "";
                    entity.getData().getData().getLoans().getDays().get(0).setDaysStatus(true);
                    days = entity.getData().getData().getLoans().getDays().get(0).getVal() + "";
                    datas = new ArrayList<>();
                    datas.addAll(entity.getData().getData().getLoans().getDays());
                    DaysAdapter adapter = new DaysAdapter(getContext(), datas);
                    indext = 0;
                    adapter.setOnItemClickListener((adapter1, view, position) -> {
                        if (position == indext) {
                            return;
                        }
                        days = datas.get(position).getVal() + "";
                        datas.get(position).setDaysStatus(true);
                        datas.get(indext).setDaysStatus(false);
                        indext = position;
                        adapter.notifyDataSetChanged();
                    });
                    myRecyclerView.setAdapter(adapter);
                    if (entity.getData().getData().getLoan() == null) {
                        homeBtCommit.setSelected(false);
                    } else {
                        switch (entity.getData().getData().getLoan().getStatus()) {
                            case "0"://审核中
                                homeBtCommit.setSelected(true);
                                break;
                            default:
                                homeBtCommit.setSelected(false);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {
                loding.dismiss();
                MyToast.show(context, error);
            }
        });
    }

    @OnClick(R.id.home_btCommit)
    public void onViewClicked() {
        if (entity == null) {
            return;
        }
        btCommit();
    }

    /**
     * 首页申请按钮
     */
    public void btCommit() {
//        if (entity.getData().getData().getMember() != null) {
//            if (entity.getData().getData().getLoan() == null || entity.getData().getData().getLoan().equals("null")) {//走获取额度
//                postapplyLoan(); //获取额度  postapplyLoan(); //获取额度
//            } else {
//                switch (entity.getData().getData().getLoan().getStatus()) {
//                    case "0"://审核中
//                        Toast.makeText(getContext(), entity.getData().getData().getBtn_desc(), Toast.LENGTH_LONG).show();
//                        break;
//                    case "1":
//                        if (entity.getData().getData().getLoan().getShenhestatus().equals("1") &&
//                                entity.getData().getData().getLoan().getStatus1().equals("0")) {//下一步
//                            Intent intent = new Intent();
//                            intent.putExtra("loan_id", entity.getData().getData().getLoan().getId() + "");
//                            intent.setClass(getContext(), Act_ConfirmationOfLoan.class);
//                            startActivityForResult(intent, 10);
//                        } else if ((entity.getData().getData().getLoan().getShenhestatus().equals("1") &&
//                                entity.getData().getData().getLoan().getStatus1().equals("1"))) {//放款中
//                            Toast.makeText(getContext(), entity.getData().getData().getBtn_desc(), Toast.LENGTH_LONG).show();
//                        } else {//被拒绝===下载页面
//                            String h5_url = entity.getData().getData().getLoan().getH5_url();
//                            Intent intent = new Intent();
//                            intent.setClass(getContext(), Act_Web.class);
//                            intent.putExtra("title", "no");
//                            intent.putExtra("url", h5_url);
//                            startActivityForResult(intent, 10);
//                        }
//                        break;
//                    case "2"://立即还款 h5
//                        String Pay_url = entity.getData().getData().getLoan().getPay_url();
//                        Intent intent = new Intent();
//                        intent.setClass(getContext(), Act_Web.class);
//                        intent.putExtra("url", ApiConstant.ROOT_URL + Pay_url);
//                        startActivity(intent);
//                        break;
//                    case "3":
//
//                        break;
//                    case "4":
//
//                        break;
//                    default:
//
//                        break;
//                }
//            }
//        } else {//认证界面
//            Toast.makeText(getActivity(), "请先完善认证资料", Toast.LENGTH_SHORT).show();
//            startAct(Act_AuthenticationHome.class);
//        }
    }

    /**
     * 获取额度
     */
    private postapplyLoanBean postapplyLoanBean;

    public void postapplyLoan() {
        if (TextUtils.isEmpty(days)) {
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            return;
        }
        HttpHelper.loanapplyLoan(getContext(), days, amount, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                postapplyLoanBean = gson.fromJson(succeed, postapplyLoanBean.class);
                if (entity.getStatus() == 1) {
//                    homeDashbord();//刷新首页显示状态
                    Toast.makeText(getContext(), postapplyLoanBean.getData().getInfo(), Toast.LENGTH_LONG).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
//            homeDashbord();
        }
    }

    // 计时器
    private Timer timer;
    int i = 0;

    /**
     * @param
     * @return void
     * @throws
     * @Description: 开始计时
     */
    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
//                    homeDashbord();
                    i++;
                }
            }, 0, 7000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
