package com.chaojidaikuan.daikuanguowai.ui.http;

import android.content.Context;
import android.content.Intent;

import com.chaojidaikuan.daikuanguowai.commt.MyApplication;
import com.chaojidaikuan.daikuanguowai.ui.act.Act_LogOn;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.MyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.codeLoginBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.getSmsVerifyBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicForgetBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicRegisterBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.publicaddressBean;
import com.chaojidaikuan.daikuanguowai.ui.act.bean.userModify_passwordBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.HomeDashbordBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.loangetBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean.postapplyLoanBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.MyUserInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userContactBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userExtendInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userSaveExtendInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userSaveInfoBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userSaveWorkBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean.userWorkBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankListsBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.bankUpdateBean;
import com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean.userGetBean;
import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

public class HttpHelper {

    /**
     * 验证码发送
     */
    public static void getSmsVerify(Context context, String telephone, String type, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("type", type);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.getSmsVerify(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        getSmsVerifyBean entity = gson.fromJson(succeed, getSmsVerifyBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 更新
     */
    public static void checkforupdate(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "android");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.checkforupdate(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        Debug.e("-------------succeed===" + succeed);
                        getSmsVerifyBean entity = gson.fromJson(succeed, getSmsVerifyBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError("获取失败！");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 验证码登录
     */
    public static void codeLogin(Context context, String telephone, String code, String address, String from, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("from", "android_" + from);
        map.put("code", code);
        map.put("address", address);
        map.put("tag", "20");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.codeLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        codeLoginBean entity = gson.fromJson(succeed, codeLoginBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 密码登录
     */
    public static void login(Context context, String telephone, String password, String address, String apppath, String from, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("from", "android_" + from);
        map.put("password", password);
        map.put("address", address);
        map.put("apppath", apppath);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        codeLoginBean entity = gson.fromJson(succeed, codeLoginBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 注册
     */
    public static void publicRegister(Context context, String telephone, String password, String repassword, String varf, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("password", password);
        map.put("repassword", repassword);
        map.put("varf", varf);
        map.put("from", "android");
        map.put("tag", "20");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.publicRegister(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        publicRegisterBean entity = gson.fromJson(succeed, publicRegisterBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 找回密码
     */
    public static void publicForget(Context context, String telephone, String password, String varf, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("password", password);
        map.put("varf", varf);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.publicForget(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        publicForgetBean entity = gson.fromJson(succeed, publicForgetBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 首页数据
     */
    public static void homeDashbord(Context context, String Token, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        if (Token.equals("")) {
            httpService.homeDashbord(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(String succeed) {
                            Gson gson = new Gson();
                            HomeDashbordBean entity = gson.fromJson(succeed, HomeDashbordBean.class);
                            if (choseLoginStatis(entity.getStatus(), context)) {
                                return;
                            }
                            if (entity.getStatus() == 1) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.onFailure(httpFailureMsg());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            httpService.homeDashbord(map, Token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(String succeed) {
                            Gson gson = new Gson();
                            HomeDashbordBean entity = gson.fromJson(succeed, HomeDashbordBean.class);
                            if (choseLoginStatis(entity.getStatus(), context)) {
                                return;
                            }
                            if (entity.getStatus() == 1) {
                                callBack.onSucceed(succeed);
                            } else {
                                callBack.onError(entity.getMsg());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            callBack.onFailure(httpFailureMsg());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    /**
     * 我的银行卡
     */
    public static void bankLists(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.bankLists(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        bankListsBean entity = gson.fromJson(succeed, bankListsBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 添加我的银行卡
     */
    public static void bankUpdate(Context context, String telephone, String username, String idcard, String bankno, String bankname, String bank_key, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("telephone", telephone);
        map.put("username", username);
        map.put("idcard", idcard);
        map.put("bankno", bankno);
        map.put("bankname", bankname);
        map.put("bank_key", bank_key);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.bankUpdate(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        bankUpdateBean entity = gson.fromJson(succeed, bankUpdateBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getInfo());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 银行选择
     */
    public static void bankValidBanks(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.bankValidBanks(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        Debug.e("------------银行选择===succeed==" + succeed);
                        bankListsBean entity = gson.fromJson(succeed, bankListsBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 获取额度
     */
    public static void loanapplyLoan(Context context, String days, String amount, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("days", days);
        map.put("amount", amount);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.loanapplyLoan(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        postapplyLoanBean entity = gson.fromJson(succeed, postapplyLoanBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 借款信息
     */
    public static void loanget(Context context, String loan_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("loan_id", loan_id);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.loanget(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        loangetBean entity = gson.fromJson(succeed, loangetBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Debug.e("-----------借款信息===succeed==" + e.getMessage());
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 获取工作信息
     */
    public static void userWork(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userWork(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        userWorkBean entity = gson.fromJson(succeed, userWorkBean.class);
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 保存工作信息
     * type 工作类型
     * income每月收入金额
     * work_time 工作时间长
     * name 公司名称
     * address 公司地址
     * income_date 薪水收据日期
     * telephone 公司电话号码
     */
    public static void userSaveWork(Context context,
                                    String type,
                                    String income,
                                    String work_time,
                                    String name,
                                    String address,
                                    String income_date,
                                    String telephone,
                                    final HttpUtilsCallBack<String> callBack) {
        OkHttpUtils
                .post()
                .url(ApiConstant.ROOT_URL + ApiConstant.userSaveWork)
                .addHeader("token", MyApplication.getLognBean().getMember().getToken())
                .addParams("type", type)
                .addParams("income", income)
                .addParams("work_time", work_time)
                .addParams("name", name)
                .addParams("address", address)
                .addParams("income_date", income_date)
                .addParams("telephone", telephone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        userSaveWorkBean entity = gson.fromJson(response, userSaveWorkBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(response);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }
                });
    }

    /**
     * 保存个人信息
     */
    public static void userSaveInfo(Context context,
                                    String username1, String telephone1, String relationship1,
                                    String username2, String telephone2, String relationship2,
                                    String username3, String telephone3, String relationship3,
                                    String othercontacts, String phone_records,
                                    final HttpUtilsCallBack<String> callBack) {
        Debug.e("--------手机号-othercontacts===" + othercontacts);
        Debug.e("--------通话记录-phone_records===" + phone_records);
        OkHttpUtils
                .post()
                .url(ApiConstant.ROOT_URL + ApiConstant.userSaveContact)
                .addHeader("token", MyApplication.getLognBean().getMember().getToken())
                .addParams("username1", username1)
                .addParams("telephone1", telephone1)
                .addParams("relationship1", relationship1)
                .addParams("username2", username2)
                .addParams("telephone2", telephone2)
                .addParams("relationship2", relationship2)
                .addParams("username3", username3)
                .addParams("telephone3", telephone3)
                .addParams("relationship3", relationship3)
                .addParams("othercontacts", othercontacts)
                .addParams("phone_records", phone_records)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Debug.e("-------------response===" + response);
                        Gson gson = new Gson();
                        userSaveInfoBean entity = gson.fromJson(response, userSaveInfoBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(response);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }
                });
    }

    /**
     * 获取紧急联系人
     */
    public static void userContact(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userContact(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        userContactBean entity = gson.fromJson(succeed, userContactBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 获取其它信息
     */
    public static void userExtendInfo(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userExtendInfo(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        userExtendInfoBean entity = gson.fromJson(succeed, userExtendInfoBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 保存其他信息
     * degree 学历
     * marriage  婚姻
     * children 孩子人数
     * bank_name 银行名称
     * bank_no 银行账号
     */
    public static void userSaveExtendInfo(Context context,
                                          String degree, String marriage, String children,
                                          String bank_name, String bank_no,
                                          final HttpHelper.HttpUtilsCallBack<String> callBack) {
        OkHttpUtils
                .post()
                .url(ApiConstant.ROOT_URL + ApiConstant.userSaveExtendInfo)
                .addHeader("token", MyApplication.getLognBean().getMember().getToken())
                .addParams("degree", degree)
                .addParams("marriage", marriage)
                .addParams("children", children)
                .addParams("bank_name", bank_name)
                .addParams("bank_no", bank_no)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        userSaveExtendInfoBean entity = gson.fromJson(response, userSaveExtendInfoBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(response);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }
                });
    }

    /**
     * 获取认证状态
     */
    public static void userGet(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userGet(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        userGetBean entity = gson.fromJson(succeed, userGetBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 保存个人信息
     * idcard 身份证号
     * username  身份证姓名
     * ocr_info 孩子人数
     * idcardimg1 身份证正面
     * idcardimg2 身份证反面
     * idcardimg3 手持身份证
     */
    public static void userSaveInfo(Context context,
                                    String idcard, String username, String idcardimg1,
                                    String idcardimg2, String idcardimg3,
                                    final HttpHelper.HttpUtilsCallBack<String> callBack) {
        OkHttpUtils
                .post()
                .url(ApiConstant.ROOT_URL + ApiConstant.userSaveInfo)
                .addHeader("token", MyApplication.getLognBean().getMember().getToken())
                .addParams("idcard", idcard)
                .addParams("username", username)
                .addParams("idcardimg1", idcardimg1)
                .addParams("idcardimg2", idcardimg2)
                .addParams("idcardimg3", idcardimg3)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        userSaveExtendInfoBean entity = gson.fromJson(response, userSaveExtendInfoBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(response);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }
                });
    }


    /**
     * 获取个人信息
     */
    public static void userInfo(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userInfo(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        MyUserInfoBean entity = gson.fromJson(succeed, MyUserInfoBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 修改密码
     */
    public static void userModify_password(Context context, String password, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("password", password);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userModify_password(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        userModify_passwordBean entity = gson.fromJson(succeed, userModify_passwordBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 获取我的账单
     */
    public static void loanLists(Context context, String page, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("limit", "10");
        map.put("page", page);
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.loanLists(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        MyLoanBean entity = gson.fromJson(succeed, MyLoanBean.class);
                        if (choseLoginStatis(entity.getStatus(), context)) {
                            return;
                        }
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 获取我的借款合同
     */
    public static void userContract(Context context, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userContract(map, MyApplication.getLognBean().getMember().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        Debug.e("----------succeed==" + succeed);
//                        MyLoanBean entity = gson.fromJson(succeed, MyLoanBean.class);
//                        if (choseLoginStatis(entity.getStatus(), context)) {
//                            return;
//                        }
//                        if (entity.getStatus() == 1) {
//                            callBack.onSucceed(succeed);
//                        } else {
//                            callBack.onError(entity.getMsg());
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
//    /**
//     * 确认借款
//     */
//    public static void userapplyConfirm(Context context, String orderno, final HttpUtilsCallBack<String> callBack) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("orderno", orderno);
//        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
//        httpService.userapplyConfirm(map, MyApplication.getLognBean().getMember().getToken())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(String succeed) {
//                        Gson gson = new Gson();
//                        Debug.e("------------userapplyConfirm==="+succeed);
////                        loangetBean entity = gson.fromJson(succeed, loangetBean.class);
////                        if (entity.getStatus() == 1) {
////                            callBack.onSucceed(succeed);
////                        } else {
////                            callBack.onError("获取失败！");
////                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFailure(httpFailureMsg());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }


    /**
     * 统计app下载
     */
    public static void userstatistics(Context context, String device_id, final HttpUtilsCallBack<String> callBack) {
        HashMap<String, String> map = new HashMap<>();
        map.put("device_id", device_id);
        map.put("type", "1");
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.userstatistics(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
//                        MyLoanBean entity = gson.fromJson(succeed, MyLoanBean.class);
//                        if (entity.getStatus() == 1) {
//                            callBack.onSucceed(succeed);
//                        } else {
//                            callBack.onError(entity.getMsg());
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    /**
     * 地址选择
     */
    public static void publicaddress(HttpUtilsCallBack<String> callBack) {
        HttpService httpService = RetrofitFactory.getRetrofit(15l, 15l).create(HttpService.class);
        httpService.publicaddress()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String succeed) {
                        Gson gson = new Gson();
                        publicaddressBean entity = gson.fromJson(succeed, publicaddressBean.class);
                        if (entity.getStatus() == 1) {
                            callBack.onSucceed(succeed);
                        } else {
                            callBack.onError(entity.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure(httpFailureMsg());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public interface HttpUtilsCallBack<T> {
        public void onFailure(String failure);

        public void onSucceed(T succeed);

        public void onError(String error);
    }

    private static String httpFailureMsg() {
        if (NetUtils.isConnected()) {
//            return "很抱歉，系统繁忙，请稍后重试。";
            return "Silakan Cek Link Internet";
        } else {
//            return "检查网络连接情况，请稍后重试。";
            return "Silakan Cek Link Internet";
        }
    }

    /**
     * 判断各种状态状态
     */
    public static boolean choseLoginStatis(int messgecode, Context context) {
        if (messgecode == 401) {
            if (Utils.isFastClick() == false) {//防点击过快
                return true;
            }
            ACache aCache = ACache.get(context);
            aCache.put("lognbean", "");
            Intent intent = new Intent(context, Act_LogOn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}