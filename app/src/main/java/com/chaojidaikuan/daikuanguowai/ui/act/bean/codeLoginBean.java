package com.chaojidaikuan.daikuanguowai.ui.act.bean;

public class codeLoginBean {

    /**
     * data : {"member":{"token":"c2c4b7a9ef3d09b6e702c41d533a1157","contact_url":"http://211.149.193.27:8099/api/public/service?token=c2c4b7a9ef3d09b6e702c41d533a1157&name=15708319320&avatar=","qy_url":""}}
     * status : 1
     */

    private DataBean data;
    private int status;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
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
         * member : {"token":"c2c4b7a9ef3d09b6e702c41d533a1157","contact_url":"http://211.149.193.27:8099/api/public/service?token=c2c4b7a9ef3d09b6e702c41d533a1157&name=15708319320&avatar=","qy_url":""}
         */

        private MemberBean member;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public static class MemberBean {
            /**
             * token : c2c4b7a9ef3d09b6e702c41d533a1157
             * contact_url : http://211.149.193.27:8099/api/public/service?token=c2c4b7a9ef3d09b6e702c41d533a1157&name=15708319320&avatar=
             * qy_url :
             */

            private String token;
            private String contact_url;
            private String qy_url;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getContact_url() {
                return contact_url;
            }

            public void setContact_url(String contact_url) {
                this.contact_url = contact_url;
            }

            public String getQy_url() {
                return qy_url;
            }

            public void setQy_url(String qy_url) {
                this.qy_url = qy_url;
            }
        }
    }
}
