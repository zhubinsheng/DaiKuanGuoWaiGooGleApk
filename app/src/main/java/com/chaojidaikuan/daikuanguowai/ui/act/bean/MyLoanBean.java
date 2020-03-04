package com.chaojidaikuan.daikuanguowai.ui.act.bean;

import java.util.List;

public class MyLoanBean {


    /**
     * data : {"loan_list":[{"id":"29","orderno":"20011732584939135146","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":1000000,"interest":"0.00","interestrate":"0.03","amount":"1000000.00","handcharge":"0.00","days":"7","deadline":"2020-01-23 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 13:52:13","refundinfo":"{\"refundtime\":\"2020-01-17 13:52:13\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-17 11:43:04","paiedtime":"2020-01-17 12:24:37","shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"27","orderno":"20011731527545952825","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":1000000,"interest":"0.00","interestrate":"0.03","amount":"1000000.00","handcharge":"0.00","days":"7","deadline":"2020-01-23 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 11:43:00","refundinfo":"{\"refundtime\":\"2020-01-17 11:43:00\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-17 11:25:27","paiedtime":"2020-01-17 11:42:40","shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"26","orderno":"20011729458382792143","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":0,"interest":"0.00","interestrate":"0.00","amount":null,"handcharge":"0.00","days":"9","deadline":"2020-01-25 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 11:15:58","refundinfo":"{\"refundtime\":\"2020-01-17 11:15:58\",\"act\":\"后台操作还款\"}","refundamount":"0.00","addtime":"2020-01-17 10:50:58","paiedtime":null,"shenhestatus":"0","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":0,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"25","orderno":"20011729172653434910","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":0,"interest":"0.00","interestrate":"0.00","amount":null,"handcharge":"0.00","days":"9","deadline":"2020-01-25 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 10:50:28","refundinfo":"{\"refundtime\":\"2020-01-17 10:50:28\",\"act\":\"后台操作还款\"}","refundamount":"0.00","addtime":"2020-01-17 10:46:12","paiedtime":null,"shenhestatus":"0","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":0,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"24","orderno":"20011727498206749579","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":0,"interest":"0.00","interestrate":"0.00","amount":null,"handcharge":"0.00","days":"9","deadline":"2020-01-25 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 10:33:57","refundinfo":"{\"refundtime\":\"2020-01-17 10:33:57\",\"act\":\"后台操作还款\"}","refundamount":"0.00","addtime":"2020-01-17 10:18:18","paiedtime":null,"shenhestatus":"0","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":0,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"23","orderno":"20011726037379207465","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":0,"interest":"0.00","interestrate":"0.00","amount":null,"handcharge":"0.00","days":"9","deadline":"2020-01-25 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 10:18:05","refundinfo":"{\"refundtime\":\"2020-01-17 10:18:05\",\"act\":\"后台操作还款\"}","refundamount":"0.00","addtime":"2020-01-17 09:53:57","paiedtime":null,"shenhestatus":"0","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":0,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"22","orderno":"20011725961567959651","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":1000000,"interest":"0.00","interestrate":"0.03","amount":"1000000.00","handcharge":"0.00","days":"7","deadline":"2020-01-24 09:53:26","status":"4","overduefee":0,"refundtime":"2020-01-17 09:53:38","refundinfo":"{\"refundtime\":\"2020-01-17 09:53:38\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-17 09:52:41","paiedtime":null,"shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"21","orderno":"20011725872259199785","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":1000000,"interest":"0.00","interestrate":"0.03","amount":"1000000.00","handcharge":"0.00","days":"7","deadline":"2020-01-23 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 09:52:33","refundinfo":"{\"refundtime\":\"2020-01-17 09:52:33\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-17 09:51:12","paiedtime":"2020-01-17 09:52:11","shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"20","orderno":"20011686885876154123","memberid":"162030","idcard":"546590421@","telephone":"81338750099","productid":"3","damount":1000000,"interest":"28000.00","interestrate":"0.03","amount":"1028000.00","handcharge":"0.00","days":"7","deadline":"2020-01-23 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-17 09:49:26","refundinfo":"{\"refundtime\":\"2020-01-17 09:49:26\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-16 23:01:25","paiedtime":"2020-01-17 01:22:48","shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0},{"id":"19","orderno":"20011663807383426215","memberid":"162030","idcard":"546590421@","telephone":"17713413684","productid":"3","damount":1000000,"interest":"28000.00","interestrate":"0.03","amount":"1028000.00","handcharge":"0.00","days":"7","deadline":"2020-01-22 23:59:59","status":"4","overduefee":0,"refundtime":"2020-01-16 23:01:21","refundinfo":"{\"refundtime\":\"2020-01-16 23:01:21\",\"act\":\"后台操作还款\"}","refundamount":"1000000.00","addtime":"2020-01-16 16:36:47","paiedtime":"2020-01-16 22:04:32","shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"1","daozhang":700000,"qs":"0","fq_list":[],"overdue_days":0,"pay_url":"","need_pay":0}]}
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
        private List<LoanListBean> loan_list;

        public List<LoanListBean> getLoan_list() {
            return loan_list;
        }

        public void setLoan_list(List<LoanListBean> loan_list) {
            this.loan_list = loan_list;
        }

        public static class LoanListBean {
            /**
             * id : 29
             * orderno : 20011732584939135146
             * memberid : 162030
             * idcard : 546590421@
             * telephone : 81338750099
             * productid : 3
             * damount : 1000000
             * interest : 0.00
             * interestrate : 0.03
             * amount : 1000000.00
             * handcharge : 0.00
             * days : 7
             * deadline : 2020-01-23 23:59:59
             * status : 4
             * overduefee : 0
             * refundtime : 2020-01-17 13:52:13
             * refundinfo : {"refundtime":"2020-01-17 13:52:13","act":"后台操作还款"}
             * refundamount : 1000000.00
             * addtime : 2020-01-17 11:43:04
             * paiedtime : 2020-01-17 12:24:37
             * shenhestatus : 1
             * refusereason : null
             * no : null
             * discount : 0.00
             * url : null
             * status1 : 1
             * daozhang : 700000
             * qs : 0
             * fq_list : []
             * overdue_days : 0
             * pay_url :
             * need_pay : 0
             */

            private String id;
            private String orderno;
            private String memberid;
            private String idcard;
            private String telephone;
            private String productid;
            private int damount;
            private String interest;
            private String interestrate;
            private String amount;
            private String handcharge;
            private String days;
            private String deadline;
            private String status;
            private int overduefee;
            private String refundtime;
            private String refundinfo;
            private String refundamount;
            private String addtime;
            private String paiedtime;
            private String shenhestatus;
            private Object refusereason;
            private Object no;
            private String discount;
            private Object url;
            private String status1;
            private int daozhang;
            private String qs;
            private int overdue_days;
            private String pay_url;
            private int need_pay;
            private List<?> fq_list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrderno() {
                return orderno;
            }

            public void setOrderno(String orderno) {
                this.orderno = orderno;
            }

            public String getMemberid() {
                return memberid;
            }

            public void setMemberid(String memberid) {
                this.memberid = memberid;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public int getDamount() {
                return damount;
            }

            public void setDamount(int damount) {
                this.damount = damount;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }

            public String getInterestrate() {
                return interestrate;
            }

            public void setInterestrate(String interestrate) {
                this.interestrate = interestrate;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getHandcharge() {
                return handcharge;
            }

            public void setHandcharge(String handcharge) {
                this.handcharge = handcharge;
            }

            public String getDays() {
                return days;
            }

            public void setDays(String days) {
                this.days = days;
            }

            public String getDeadline() {
                return deadline;
            }

            public void setDeadline(String deadline) {
                this.deadline = deadline;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getOverduefee() {
                return overduefee;
            }

            public void setOverduefee(int overduefee) {
                this.overduefee = overduefee;
            }

            public String getRefundtime() {
                return refundtime;
            }

            public void setRefundtime(String refundtime) {
                this.refundtime = refundtime;
            }

            public String getRefundinfo() {
                return refundinfo;
            }

            public void setRefundinfo(String refundinfo) {
                this.refundinfo = refundinfo;
            }

            public String getRefundamount() {
                return refundamount;
            }

            public void setRefundamount(String refundamount) {
                this.refundamount = refundamount;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getPaiedtime() {
                return paiedtime;
            }

            public void setPaiedtime(String paiedtime) {
                this.paiedtime = paiedtime;
            }

            public String getShenhestatus() {
                return shenhestatus;
            }

            public void setShenhestatus(String shenhestatus) {
                this.shenhestatus = shenhestatus;
            }

            public Object getRefusereason() {
                return refusereason;
            }

            public void setRefusereason(Object refusereason) {
                this.refusereason = refusereason;
            }

            public Object getNo() {
                return no;
            }

            public void setNo(Object no) {
                this.no = no;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }

            public String getStatus1() {
                return status1;
            }

            public void setStatus1(String status1) {
                this.status1 = status1;
            }

            public int getDaozhang() {
                return daozhang;
            }

            public void setDaozhang(int daozhang) {
                this.daozhang = daozhang;
            }

            public String getQs() {
                return qs;
            }

            public void setQs(String qs) {
                this.qs = qs;
            }

            public int getOverdue_days() {
                return overdue_days;
            }

            public void setOverdue_days(int overdue_days) {
                this.overdue_days = overdue_days;
            }

            public String getPay_url() {
                if(pay_url==null){
                    pay_url="";
                }
                return pay_url;
            }

            public void setPay_url(String pay_url) {
                this.pay_url = pay_url;
            }

            public int getNeed_pay() {
                return need_pay;
            }

            public void setNeed_pay(int need_pay) {
                this.need_pay = need_pay;
            }

            public List<?> getFq_list() {
                return fq_list;
            }

            public void setFq_list(List<?> fq_list) {
                this.fq_list = fq_list;
            }
        }
    }
}
