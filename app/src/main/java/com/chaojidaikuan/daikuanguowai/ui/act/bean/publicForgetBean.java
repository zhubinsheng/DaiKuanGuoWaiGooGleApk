package com.chaojidaikuan.daikuanguowai.ui.act.bean;

public class publicForgetBean {

    /**
     * data : {"data":{"telephone":"15708319320","userpwd":"96e79218965eb72c92a549dd5a330112","id":"115032"}}
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
         * data : {"telephone":"15708319320","userpwd":"96e79218965eb72c92a549dd5a330112","id":"115032"}
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
             * telephone : 15708319320
             * userpwd : 96e79218965eb72c92a549dd5a330112
             * id : 115032
             */

            private String telephone;
            private String userpwd;
            private String id;

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getUserpwd() {
                return userpwd;
            }

            public void setUserpwd(String userpwd) {
                this.userpwd = userpwd;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
