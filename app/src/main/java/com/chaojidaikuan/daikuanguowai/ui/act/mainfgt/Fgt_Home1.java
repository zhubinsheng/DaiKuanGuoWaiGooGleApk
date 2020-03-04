package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaojidaikuan.daikuanguowai.R;
import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.Act_LogOn;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act.Act_AgreeWeb;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act.Act_ConfirmationOfLoan;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.act.Act_Web;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.postapplyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg.Dilog_FeiYong;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg.Dilog_TestingDate;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.dlg.Dilog_WeiDengLV;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.Act_AuthenticationHome;
import com.chaojidaikuan.daikuanguowai.ui.http.ApiConstant;
import com.chaojidaikuan.daikuanguowai.ui.http.HttpHelper;
import com.chaojidaikuan.daikuanguowai.ui.utils.FormatTosepara;
import com.chaojidaikuan.daikuanguowai.ui.utils.GlideImageLoader;
import com.chaojidaikuan.daikuanguowai.ui.view.ScrollTextViewView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lykj.aextreme.afinal.common.BaseFragment;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.mingle.widget.LoadingView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
public class Fgt_Home1 extends BaseFragment implements Dilog_TestingDate.OnBackCenter {
    @BindView(R.id.tv_auto)
    ScrollTextViewView tvAuto;
    @BindView(R.id.banner_auto)
    Banner bannerAuto;
    @BindView(R.id.tv_describle)
    TextView tv_describle;
    @BindView(R.id.tv_limitmoney)
    TextView tv_limitmoney;
    @BindView(R.id.bt_tvtv)
    TextView bt_tvtv;
    Unbinder unbinder;
    @BindView(R.id.myGif)
    ImageView myGif;
    @BindView(R.id.ll_button)
    LinearLayout ll_button;
    @BindView(R.id.loan_qs_desc)
    TextView loanQsDesc;
    @BindView(R.id.qishu)
    TextView qishu;
    @BindView(R.id.yue_fee_desc)
    TextView yueFeeDesc;
    @BindView(R.id.yue_fee)
    TextView yueFee;
    @BindView(R.id.fee_desc)
    TextView feeDesc;
    @BindView(R.id.view_desc)
    TextView viewDesc;
    @BindView(R.id.xieyiRadio)
    TextView xieyiRadio;
    @BindView(R.id.userXieyi)
    TextView userXieyi;
    @BindView(R.id.tv_card_no)
    TextView tvCardNo;
    @BindView(R.id.ll_card)
    LinearLayout llCard;
    @BindView(R.id.lijijieqian)
    LinearLayout lijijieqian;
    @BindView(R.id.huankuanxiansi)
    LinearLayout huankuanxiansi;
    @BindView(R.id.my_shouwtubiao)
    LinearLayout myShouwtubiao;
    @BindView(R.id.top_desc_dz)
    TextView top_desc_dz;
    @BindView(R.id.top_desc_fq)
    TextView top_desc_fq;
    @BindView(R.id.waring)
    TextView waring;
    @BindView(R.id.top_hint_1)
    TextView top_hint_1;
    @BindView(R.id.top_hint_2)
    TextView top_hint_2;
    @BindView(R.id.top_hint_3)
    TextView top_hint_3;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_showData)
    LinearLayout llShowData;
    @BindView(R.id.loadView)
    LoadingView loadView;
    @BindView(R.id.tv_days)
    TextView tv_days;
    @BindView(R.id.tv_refundamount)
    TextView tv_refundamount;
    @BindView(R.id.tv_deadline)
    TextView tv_deadline;
    private View rootView;
    private AutoVerticalScrollTextViewUtil autoVerticalScrollTextViewUtil;
    private List list = new ArrayList();
    Unbinder unbinder1;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_home1;
    }

    @Override
    public void initView() {
        hideHeader();
        unbinder = ButterKnife.bind(Fgt_Home1.this, v);
        ImmersionBar.with(this)
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .init();
        mRefreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        mRefreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true);
        //设置 Header 为 Material风格
        mRefreshLayout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(false));
        //设置 Footer 为 球脉冲
        mRefreshLayout.setRefreshFooter(new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            if (MyApplication.getLognBean() != null) {
                homeDashbord(MyApplication.getLognBean().getMember().getToken());
            } else {
                homeDashbord("");
            }
            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
        });
        llShowData.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        if (MyApplication.getLognBean() != null && MyApplication.getLognBean().getMember().getToken() != null) {
            homeDashbord(MyApplication.getLognBean().getMember().getToken());
            start();
        } else {
            homeDashbord("");
        }
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

    boolean myGifStatus = false;
    private HomeDashbordBean entity;

    /**
     * 首页数据
     */
    public void homeDashbord(String token) {
        HttpHelper.homeDashbord(getContext(), token, new HttpHelper.HttpUtilsCallBack<String>() {
            @Override
            public void onFailure(String failure) {
                MyToast.show(context, failure);
                loding.dismiss();
            }

            @Override
            public void onSucceed(String succeed) {
                loding.dismiss();
                Gson gson = new Gson();
                try {
                    entity = gson.fromJson(succeed, HomeDashbordBean.class);
                    if (entity.getStatus() == 1) {
                        llShowData.setVisibility(View.VISIBLE);
                        loadView.setVisibility(View.GONE);
                        if (entity.getData().getData().getAuthstatus() == 1) {
                            if (entity.getData().getData().getLoan() != null) {//判断认证可否修改
                                MyApplication.lognStatus = false;
                            } else {
                                MyApplication.lognStatus = true;
                            }
                        } else {
                            MyApplication.lognStatus = false;
                        }
                        top_desc_dz.setText(entity.getData().getData().getTop_desc_dz());
                        top_desc_fq.setText(entity.getData().getData().getTop_desc_fq());
                        if (entity.getStatus() == 1) {
                            LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
                            if (entity.getData().getData().getLoan() == null) {
                                bt_tvtv.setSelected(false);
                                myGif.setVisibility(View.GONE);
                                myGifStatus = false;
                                layoutParam.setMargins(0, 0, 0, 0);
                                tv_limitmoney.setPadding(0, 0, 0, 0);
                                tv_limitmoney.setTextSize(40);
                                unbinder = ButterKnife.bind(this, rootView);
                                tv_limitmoney.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(entity.getData().getData().getAmount_limit())));//设置额度
                            } else {
                                switch (entity.getData().getData().getLoan().getStatus()) {
                                    case "0"://审核中
                                        bt_tvtv.setSelected(true);
                                        myGif.setVisibility(View.VISIBLE);
                                        tv_limitmoney.setPadding(0, 40, 0, 0);
                                        tv_limitmoney.setText("Sedang di proses...");
                                        tv_limitmoney.setTextSize(20);
                                        layoutParam.setMargins(0, 0, 0, 20);
                                        break;
                                    default:
//                                            if (homeBean.getData().getData().getAmount_limit() != null) {
                                        bt_tvtv.setSelected(false);
                                        myGif.setVisibility(View.GONE);
                                        myGifStatus = false;
                                        layoutParam.setMargins(0, 0, 0, 0);
                                        tv_limitmoney.setPadding(0, 0, 0, 0);
                                        tv_limitmoney.setTextSize(40);
                                        unbinder = ButterKnife.bind(this, rootView);
                                        tv_limitmoney.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(entity.getData().getData().getAmount_limit())));//设置额度
//                                            }
                                        break;
                                }
                            }
                            if (entity.getData().getData().getLoan() != null
                                    && entity.getData().getData().getLoan().getStatus().equals("1")
                                    && entity.getData().getData().getLoan().getShenhestatus().equals("1")
                                    && !entity.getData().getData().getLoan().getStatus1().equals("1")) {
                                myShouwtubiao.setVisibility(View.GONE);
                                bannerAuto.setVisibility(View.GONE);
                                lijijieqian.setVisibility(View.VISIBLE);
                                loanQsDesc.setText(entity.getData().getData().getLoan_qs_desc());
                                qishu.setText(entity.getData().getData().getLoan().getQs() + "periode");
                                yueFeeDesc.setText(entity.getData().getData().getYue_fee_desc());
                                yueFee.setText(entity.getData().getData().getYue_fee());
                            } else {
                                myShouwtubiao.setVisibility(View.VISIBLE);
                                bannerAuto.setVisibility(View.VISIBLE);
                                lijijieqian.setVisibility(View.GONE);
                            }
                            ll_button.setGravity(Gravity.CENTER);
                            ll_button.setLayoutParams(layoutParam);
                            if (entity.getData().getData().getBtn_desc().equals("bayar")) {//显示还款显示
                                huankuanxiansi.setVisibility(View.VISIBLE);
                                tv_days.setText(entity.getData().getData().getLoan().getDays() + "Hari");
                                tv_refundamount.setText("Rp " + FormatTosepara.formatTosepara(Float.valueOf(entity.getData().getData().getLoan().getFq_list().get(0).getDamount())));
                                tv_deadline.setText(entity.getData().getData().getLoan().getFq_list().get(0).getDeadline());
                            } else {
                                huankuanxiansi.setVisibility(View.GONE);
                            }
                            bt_tvtv.setText(entity.getData().getData().getBtn_desc());
                            tv_describle.setText(entity.getData().getData().getLoan_desc());
                            if (entity.getData().getData().getTop_hint_1().equals("")) {
                                top_hint_1.setVisibility(View.GONE);
                            } else {
                                top_hint_1.setVisibility(View.VISIBLE);
                                top_hint_1.setText(entity.getData().getData().getTop_hint_1());
                            }
                            if (entity.getData().getData().getTop_hint_2().equals("")) {
                                top_hint_2.setVisibility(View.GONE);
                            } else {
                                top_hint_2.setVisibility(View.VISIBLE);
                                top_hint_2.setText(entity.getData().getData().getTop_hint_2());
                            }
                            if (entity.getData().getData().getTop_hint_3().equals("")) {
                                top_hint_3.setVisibility(View.GONE);
                            } else {
                                top_hint_3.setVisibility(View.VISIBLE);
                                top_hint_3.setText(entity.getData().getData().getTop_hint_3());
                            }

                            if (list.size() == 0) {
                                for (int i = 0; i < entity.getData().getData().getLatest_loads().size(); i++) {
                                    list.add(entity.getData().getData().getLatest_loads().get(i).getTelephone() + "Berhasil meminjam uang" + "   Rp " + FormatTosepara.formatTosepara(Float.valueOf(entity.getData().getData().getLatest_loads().get(i).getAmount())));
                                }
                                autoVerticalScrollTextViewUtil = new AutoVerticalScrollTextViewUtil(tvAuto, (ArrayList<CharSequence>) list);
                                // 设置滚动的时间间隔
                                autoVerticalScrollTextViewUtil.setDuration(5000);
                                autoVerticalScrollTextViewUtil.stop();
                                // 开启滚动
                                autoVerticalScrollTextViewUtil.start();
                            }
//                        entity.getData().getData().getAds()
                            if (entity.getData().getData().getLoan() != null) {
                                MyApplication.oderno = entity.getData().getData().getLoan().getId();
                                MyApplication.oderid = entity.getData().getData().getLoan().getOrderno();
                            }
                            final List<String> images = new ArrayList<>();
                            final List<String> urls = new ArrayList<>();
                            for (int i = 0; i < entity.getData().getData().getAds().size(); i++) {
                                images.add(entity.getData().getData().getAds().get(i).getImage());
                                urls.add(entity.getData().getData().getAds().get(i).getUrl());
                            }
//                        top_desc_dz.setText(entity.getData().getData().getTop_desc());
                            waring.setText(entity.getData().getData().getBottom_hint() + "");
                            viewDesc.setText(entity.getData().getData().getView_desc());
                            bannerAuto.setImageLoader(new GlideImageLoader());
                            bannerAuto.setImages(images);
                            bannerAuto.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            bannerAuto.isAutoPlay(true);
                            bannerAuto.setDelayTime(5000);
                            bannerAuto.start();
                            bannerAuto.setOnBannerListener(position -> {
                                if (urls.get(position).equals("") || urls.get(position) == null) {
                                    return;
                                }
                                Debug.e("--------------" + urls.get(position));
                                Intent intent = new Intent(getActivity(), Act_Web.class);
                                intent.putExtra("url", urls.get(position));
                                startActivity(intent);
                            });
                        }

                    }
                } catch (Exception e) {
                    if (timer != null) {
                        timer.cancel();
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

    /**
     * 首页申请按钮
     */
    public void btCommit() {
        if (MyApplication.getLognBean() == null) {
            Dilog_WeiDengLV weiDengLV = new Dilog_WeiDengLV(getContext());
            weiDengLV.setOnBackTime(() -> {
                startAct(Act_LogOn.class);
            });
            weiDengLV.setTouchCancle(true);
            weiDengLV.show();
            return;
        }
        if (entity.getData().getData().getAuthstatus() == 1) {//已认证
            if (entity.getData().getData().getLoan() == null || entity.getData().getData().getLoan().equals("null")) {//走获取额度
                postapplyLoan(); //获取额度  postapplyLoan(); //获取额度
            } else {
                switch (entity.getData().getData().getLoan().getStatus()) {
                    case "0"://审核中
                        Toast.makeText(getContext(), entity.getData().getData().getBtn_desc(), Toast.LENGTH_LONG).show();
                        break;
                    case "1":
//                        if (xieyiRadio.isSelected() == false) {
//                            Toast.makeText(getContext(), "请选中借款协议！", Toast.LENGTH_LONG).show();
//                            return;
//                        }
                        if (entity.getData().getData().getLoan().getShenhestatus().equals("1") &&
                                entity.getData().getData().getLoan().getStatus1().equals("0")) {//下一步
                            Intent intent = new Intent();
                            intent.putExtra("loan_id", entity.getData().getData().getLoan().getId() + "");
                            intent.setClass(getContext(), Act_ConfirmationOfLoan.class);
                            startActivityForResult(intent, 10);
                        } else if ((entity.getData().getData().getLoan().getShenhestatus().equals("1") &&
                                entity.getData().getData().getLoan().getStatus1().equals("1"))) {//放款中
                            Toast.makeText(getContext(), entity.getData().getData().getBtn_desc(), Toast.LENGTH_LONG).show();
                        } else {//被拒绝===下载页面
                            String h5_url = entity.getData().getData().getLoan().getH5_url();
                            Intent intent = new Intent();
                            intent.setClass(getContext(), Act_Web.class);
                            intent.putExtra("title", "no");
                            intent.putExtra("url", h5_url);
                            startActivityForResult(intent, 10);
                        }
                        break;
                    case "2"://立即还款 h5
                        if (entity == null) {
                            return;
                        }
                        String Pay_url = entity.getData().getData().getLoan().getPay_url();
                        Intent intent = new Intent();
                        intent.setClass(getContext(), Act_Web.class);
                        intent.putExtra("url", ApiConstant.ROOT_URL + Pay_url);
                        startActivity(intent);
//                        Dilog_TestingDate dilog_testingDate = new Dilog_TestingDate(getContext());
//                        dilog_testingDate.setTouchCancle(true);
//                        dilog_testingDate.setOnBackTime(this);
//                        dilog_testingDate.show();
                        break;
                    case "3":

                        break;
                    case "4":

                        break;
                    default:

                        break;
                }
            }
        } else {//认证界面
//            Toast.makeText(getActivity(), "请先完善认证资料", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "Silahkan pertama kali menyempurnakan data sertifikasi", Toast.LENGTH_SHORT).show();
            startAct(Act_AuthenticationHome.class);
        }
    }

    /**
     * 获取额度
     */
    private postapplyLoanBean postapplyLoanBean;

    public void postapplyLoan() {
        HttpHelper.loanapplyLoan(getContext(), "1", entity.getData().getData().getAmount_limit(), new HttpHelper.HttpUtilsCallBack<String>() {
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
                    if (MyApplication.getLognBean() != null) {
                        homeDashbord(MyApplication.getLognBean().getMember().getToken());
                    } else {
                        homeDashbord("");
                    }
                    Toast.makeText(getContext(), postapplyLoanBean.getMsg(), Toast.LENGTH_LONG).show();
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
            if (MyApplication.getLognBean() != null) {
                homeDashbord(MyApplication.getLognBean().getMember().getToken());
            } else {
                homeDashbord("");
            }
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
                    if (MyApplication.getLognBean() != null) {
                        homeDashbord(MyApplication.getLognBean().getMember().getToken());
                    } else {
                        homeDashbord("");
                    }
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

    Dilog_FeiYong dilog_feiYong;

    @OnClick({R.id.bt_tvtv, R.id.view_desc, R.id.xieyiRadio, R.id.userXieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_tvtv:
                btCommit();
                break;
            case R.id.view_desc:
                if (entity == null) {
                    return;
                }
                dilog_feiYong = new Dilog_FeiYong(getContext(), entity, () -> {

                });
                dilog_feiYong.show();
                break;
            case R.id.xieyiRadio:
                if (xieyiRadio.isSelected()) {
                    xieyiRadio.setSelected(false);
                } else {
                    xieyiRadio.setSelected(true);
                }
                break;
            case R.id.userXieyi:
                startAct(Act_AgreeWeb.class);
                break;
        }

    }

    @Override
    public void onHuanKuan() {
        if (entity == null) {
            return;
        }
        String Pay_url = entity.getData().getData().getLoan().getPay_url();
        Intent intent = new Intent();
        intent.setClass(getContext(), Act_Web.class);
        intent.putExtra("url", ApiConstant.ROOT_URL + Pay_url);
        startActivity(intent);
    }

    @Override
    public void onYanqi() {
        if (entity == null) {
            return;
        }
        String Delay_pay_url = entity.getData().getData().getLoan().getDelay_pay_url();
        Intent intent = new Intent();
        intent.setClass(getContext(), Act_Web.class);
        intent.putExtra("url", ApiConstant.ROOT_URL + Delay_pay_url);
        startActivity(intent);
    }
}
