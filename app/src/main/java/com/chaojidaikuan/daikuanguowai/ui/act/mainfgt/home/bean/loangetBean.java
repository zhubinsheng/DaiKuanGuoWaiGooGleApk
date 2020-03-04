package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean;

import java.util.List;

public class loangetBean {

    /**
     * data : {"loan":{"id":"35621","orderno":"19122636904883299655","memberid":"107591","idcard":"211381199609041810","telephone":"17713413684","damount":"3000.00","interest":"84.00","interestrate":"0.03","amount":"3084.00","handcharge":"0.00","days":"28","deadline":"2020-01-23 13:40:14","status":"1","overduefee":"6.00","refundtime":null,"refundinfo":null,"refundamount":"0.00","addtime":"2019-12-26 13:08:24","paiedtime":null,"shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":"2100.00","qs":"4","fq_list":[{"id":"336303","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"1","deadline":"2020-01-02 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336304","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"2","deadline":"2020-01-09 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336305","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"3","deadline":"2020-01-16 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336306","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"4","deadline":"2020-01-23 23:59:59","overdue_days":0,"overdue_fee":0}],"service_fee":900,"product":null}}
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
         * loan : {"id":"35621","orderno":"19122636904883299655","memberid":"107591","idcard":"211381199609041810","telephone":"17713413684","damount":"3000.00","interest":"84.00","interestrate":"0.03","amount":"3084.00","handcharge":"0.00","days":"28","deadline":"2020-01-23 13:40:14","status":"1","overduefee":"6.00","refundtime":null,"refundinfo":null,"refundamount":"0.00","addtime":"2019-12-26 13:08:24","paiedtime":null,"shenhestatus":"1","refusereason":null,"no":null,"discount":"0.00","url":null,"status1":"0","daozhang":"2100.00","qs":"4","fq_list":[{"id":"336303","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"1","deadline":"2020-01-02 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336304","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"2","deadline":"2020-01-09 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336305","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"3","deadline":"2020-01-16 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336306","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"4","deadline":"2020-01-23 23:59:59","overdue_days":0,"overdue_fee":0}],"service_fee":900,"product":null}
         */

        private LoanBean loan;

        public LoanBean getLoan() {
            return loan;
        }

        public void setLoan(LoanBean loan) {
            this.loan = loan;
        }

        public static class LoanBean {
            /**
             * id : 35621
             * orderno : 19122636904883299655
             * memberid : 107591
             * idcard : 211381199609041810
             * telephone : 17713413684
             * damount : 3000.00
             * interest : 84.00
             * interestrate : 0.03
             * amount : 3084.00
             * handcharge : 0.00
             * days : 28
             * deadline : 2020-01-23 13:40:14
             * status : 1
             * overduefee : 6.00
             * refundtime : null
             * refundinfo : null
             * refundamount : 0.00
             * addtime : 2019-12-26 13:08:24
             * paiedtime : null
             * shenhestatus : 1
             * refusereason : null
             * no : null
             * discount : 0.00
             * url : null
             * status1 : 0
             * daozhang : 2100.00
             * qs : 4
             * fq_list : [{"id":"336303","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"1","deadline":"2020-01-02 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336304","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"2","deadline":"2020-01-09 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336305","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"3","deadline":"2020-01-16 23:59:59","overdue_days":0,"overdue_fee":0},{"id":"336306","damount":"771.00","actual_damont":"0.00","status":"0","paytime":"","stages":"4","deadline":"2020-01-23 23:59:59","overdue_days":0,"overdue_fee":0}]
             * service_fee : 900
             * product : null
             */

            private String id;
            private String orderno;
            private String memberid;
            private String idcard;
            private String telephone;
            private String damount;
            private String interest;
            private String interestrate;
            private String amount;
            private String handcharge;
            private String days;
            private String deadline;
            private String status;
            private String overduefee;
            private Object refundtime;
            private Object refundinfo;
            private String refundamount;
            private String addtime;
            private Object paiedtime;
            private String shenhestatus;
            private Object refusereason;
            private Object no;
            private String discount;
            private Object url;
            private String status1;
            private String daozhang;
            private String qs;
            private int service_fee;
            private Object product;
            private List<FqListBean> fq_list;

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

            public String getDamount() {
                return damount;
            }

            public void setDamount(String damount) {
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

            public String getOverduefee() {
                return overduefee;
            }

            public void setOverduefee(String overduefee) {
                this.overduefee = overduefee;
            }

            public Object getRefundtime() {
                return refundtime;
            }

            public void setRefundtime(Object refundtime) {
                this.refundtime = refundtime;
            }

            public Object getRefundinfo() {
                return refundinfo;
            }

            public void setRefundinfo(Object refundinfo) {
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

            public Object getPaiedtime() {
                return paiedtime;
            }

            public void setPaiedtime(Object paiedtime) {
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

            public String getDaozhang() {
                return daozhang;
            }

            public void setDaozhang(String daozhang) {
                this.daozhang = daozhang;
            }

            public String getQs() {
                return qs;
            }

            public void setQs(String qs) {
                this.qs = qs;
            }

            public int getService_fee() {
                return service_fee;
            }

            public void setService_fee(int service_fee) {
                this.service_fee = service_fee;
            }

            public Object getProduct() {
                return product;
            }

            public void setProduct(Object product) {
                this.product = product;
            }

            public List<FqListBean> getFq_list() {
                return fq_list;
            }

            public void setFq_list(List<FqListBean> fq_list) {
                this.fq_list = fq_list;
            }

            public static class FqListBean {
                /**
                 * id : 336303
                 * damount : 771.00
                 * actual_damont : 0.00
                 * status : 0
                 * paytime :
                 * stages : 1
                 * deadline : 2020-01-02 23:59:59
                 * overdue_days : 0
                 * overdue_fee : 0
                 */

                private String id;
                private String damount;
                private String actual_damont;
                private String status;
                private String paytime;
                private String stages;
                private String deadline;
                private int overdue_days;
                private int overdue_fee;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getDamount() {
                    return damount;
                }

                public void setDamount(String damount) {
                    this.damount = damount;
                }

                public String getActual_damont() {
                    return actual_damont;
                }

                public void setActual_damont(String actual_damont) {
                    this.actual_damont = actual_damont;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPaytime() {
                    return paytime;
                }

                public void setPaytime(String paytime) {
                    this.paytime = paytime;
                }

                public String getStages() {
                    return stages;
                }

                public void setStages(String stages) {
                    this.stages = stages;
                }

                public String getDeadline() {
                    return deadline;
                }

                public void setDeadline(String deadline) {
                    this.deadline = deadline;
                }

                public int getOverdue_days() {
                    return overdue_days;
                }

                public void setOverdue_days(int overdue_days) {
                    this.overdue_days = overdue_days;
                }

                public int getOverdue_fee() {
                    return overdue_fee;
                }

                public void setOverdue_fee(int overdue_fee) {
                    this.overdue_fee = overdue_fee;
                }
            }
        }
    }
}
