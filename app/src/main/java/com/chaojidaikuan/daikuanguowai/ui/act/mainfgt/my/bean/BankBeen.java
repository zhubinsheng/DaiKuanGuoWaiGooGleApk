package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean;

/**
 * Created by zhanglizhi on 2018/7/24.
 */

public class BankBeen {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //用户名
    private String username;
    //卡号
    private String bankcard;
    //卡所属银行
    private String bankname;
    //手机号
    private String telephone;
    //身份证
    private String idcard;
    //银行卡图片
    private String imageurl;
    //id
    private String id;

}
