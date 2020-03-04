package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class userSaveInfoBean {

    /**
     * data : {"contactinfo":""}
     * status : 1
     */

    private DataBean data;
    private int status;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * contactinfo :
         */

        private String contactinfo;

        public String getContactinfo() {
            return contactinfo;
        }

        public void setContactinfo(String contactinfo) {
            this.contactinfo = contactinfo;
        }
    }
}
