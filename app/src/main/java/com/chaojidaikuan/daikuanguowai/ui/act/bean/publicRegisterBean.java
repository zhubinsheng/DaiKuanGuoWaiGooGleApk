package com.chaojidaikuan.daikuanguowai.ui.act.bean;

public class publicRegisterBean {

    /**
     * data : {"data":{"telephone":"19130694373","platform":"android","userpwd":"e10adc3949ba59abbe56e057f20f883e","token":"d7f4718c84f57fe26ea3c8cbb17159f9"}}
     * status : 1
     */

    private DataBeanX data;
    private int status;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBeanX {
        /**
         * data : {"telephone":"19130694373","platform":"android","userpwd":"e10adc3949ba59abbe56e057f20f883e","token":"d7f4718c84f57fe26ea3c8cbb17159f9"}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * telephone : 19130694373
             * platform : android
             * userpwd : e10adc3949ba59abbe56e057f20f883e
             * token : d7f4718c84f57fe26ea3c8cbb17159f9
             */

            private String telephone;
            private String platform;
            private String userpwd;
            private String token;

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getPlatform() {
                return platform;
            }

            public void setPlatform(String platform) {
                this.platform = platform;
            }

            public String getUserpwd() {
                return userpwd;
            }

            public void setUserpwd(String userpwd) {
                this.userpwd = userpwd;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
