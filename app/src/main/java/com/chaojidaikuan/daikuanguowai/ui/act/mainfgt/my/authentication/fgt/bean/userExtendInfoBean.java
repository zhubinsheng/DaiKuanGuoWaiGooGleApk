package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class userExtendInfoBean {

    /**
     * data : {"data":{"degree":"Sarjana dan Lebih Tinggi","marriage":"Belum Menikah","children":"1 Anak","bank_name":"Bank Rakyat Indonesia","bank_no":"62179967"}}
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
         * data : {"degree":"Sarjana dan Lebih Tinggi","marriage":"Belum Menikah","children":"1 Anak","bank_name":"Bank Rakyat Indonesia","bank_no":"62179967"}
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
             * degree : Sarjana dan Lebih Tinggi
             * marriage : Belum Menikah
             * children : 1 Anak
             * bank_name : Bank Rakyat Indonesia
             * bank_no : 62179967
             */

            private String degree;
            private String marriage;
            private String children;
            private String bank_name;
            private String bank_no;

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public String getMarriage() {
                return marriage;
            }

            public void setMarriage(String marriage) {
                this.marriage = marriage;
            }

            public String getChildren() {
                return children;
            }

            public void setChildren(String children) {
                this.children = children;
            }

            public String getBank_name() {
                return bank_name;
            }

            public void setBank_name(String bank_name) {
                this.bank_name = bank_name;
            }

            public String getBank_no() {
                return bank_no;
            }

            public void setBank_no(String bank_no) {
                this.bank_no = bank_no;
            }
        }
    }
}
