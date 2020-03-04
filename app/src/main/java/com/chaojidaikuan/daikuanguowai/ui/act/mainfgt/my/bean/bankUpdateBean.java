package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean;

public class bankUpdateBean {

    /**
     * status : 0
     * info : 银行卡错误
     */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int status;
    private String info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
