package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class userWorkBean {

    /**
     * data : {"data":{"type":"Purna Waktu","income":"1-3 Juta","work_time":"3 bulan-6 bulan","name":"公司名称","address":"四川省成都市双流区美岸路二段250号靠近南湖春天","income_date":"薪水","telephone":"15708319322"}}
     * status : 1
     */

    private DataBeanX data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
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
         * data : {"type":"Purna Waktu","income":"1-3 Juta","work_time":"3 bulan-6 bulan","name":"公司名称","address":"四川省成都市双流区美岸路二段250号靠近南湖春天","income_date":"薪水","telephone":"15708319322"}
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
             * type : Purna Waktu
             * income : 1-3 Juta
             * work_time : 3 bulan-6 bulan
             * name : 公司名称
             * address : 四川省成都市双流区美岸路二段250号靠近南湖春天
             * income_date : 薪水
             * telephone : 15708319322
             */

            private String type;
            private String income;
            private String work_time;
            private String name;
            private String address;
            private String income_date;
            private String telephone;

            public String getType() {
                if (type == null) {
                    type = "";
                }
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIncome() {
                if (income == null) {
                    income = "";
                }
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public String getWork_time() {
                if (work_time == null) {
                    work_time = "";
                }
                return work_time;
            }

            public void setWork_time(String work_time) {
                this.work_time = work_time;
            }

            public String getName() {
                if (name == null) {
                    name = "";
                }
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                if (address == null) {
                    address = "";
                }
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getIncome_date() {
                if (income_date == null) {
                    income_date = "";
                }
                return income_date;
            }

            public void setIncome_date(String income_date) {
                this.income_date = income_date;
            }

            public String getTelephone() {
                if (telephone == null) {
                    telephone = "";
                }
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }
    }
}
