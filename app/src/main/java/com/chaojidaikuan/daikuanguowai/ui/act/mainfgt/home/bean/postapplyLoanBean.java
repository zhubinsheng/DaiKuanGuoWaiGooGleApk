package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean;

public class postapplyLoanBean {


    /**
     * data : {"data":true}
     * status : 1
     */
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private DataBean data;
    private int status;

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
         * data : true
         */

        private boolean data;
        private String info;
        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public boolean isData() {
            return data;
        }

        public void setData(boolean data) {
            this.data = data;
        }
    }
}
