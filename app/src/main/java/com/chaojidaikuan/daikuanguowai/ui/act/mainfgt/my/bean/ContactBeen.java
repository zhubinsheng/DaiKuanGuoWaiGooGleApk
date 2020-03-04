package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean;

/**
 * Created by zhanglizhi on 2018/7/23.
 */

public class ContactBeen {
    public String name;        //联系人姓名
    public String telephone;    //电话号码
    public String relation;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ContactBeen() {
    }
    public ContactBeen(String name, String telPhone) {
        this.name = name;
        this.telephone = telPhone;
    }
    public ContactBeen(String name, String telPhone, String relation) {
        this.name = name;
        this.telephone = telPhone;
        this.relation=relation;
    }

}
