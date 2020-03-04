package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class FileBean {
    /**
     * code : SUCCESS
     * message : OK
     * data : {"name":"PRANATA KUSUMA ATMAJA","idNumber":"1408110310000001"}
     * extra : null
     * transactionId : 982c7bdfec4f108c
     * pricingStrategy : PAY
     */

    private String code;
    private String message;
    private DataBean data;
    private Object extra;
    private String transactionId;
    private String pricingStrategy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(String pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public static class DataBean {
        /**
         * name : PRANATA KUSUMA ATMAJA
         * idNumber : 1408110310000001
         */

        private String name;
        private String idNumber;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }
    }
}
