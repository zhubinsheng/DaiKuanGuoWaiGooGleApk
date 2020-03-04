package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.home.bean;

import java.util.List;

public class HomeDashbordBean {

    /**
     * data : {"data":{"amount_limit":"2100.00","card_desc_cn":"铂金卡","card_desc_en":"Platinum Card","card_no":"XXXX 9370 0011 9267 XX","loan_amount_from":"2000","loan_amount_to":"30000","top_desc":"30秒到账，分期借款","top_hint":"凭身份证可借 . 日息最低0.03% . 最快5分钟到账","bottom_hint":"只面向22岁以上非学生人群","loan_desc":"借款金额 (元)","btn_desc":"立即提现","yue_fee_desc":"月费率","loan_qs_desc":"借款期数","fee_desc":"费用说明","view_desc":"查看详情","detail_total":"还款总额","detail_amount":"含借款金额","detail_service":"含服务费","detail_intrest":"含利息","detail_deadline":"借款期限","detail_needpay":"首期应还","detail_payday":"还款日","day_rate":"0.1","text_day_rate":"日利息","month_rate":"3","service_rate":"30","service_day":"7","loans":{"days":[{"val":"9","status":1},{"val":"21","status":1},{"val":"30","status":1},{"val":"60","status":1},{"val":"90","status":1}],"amount":[{"amount":"2000"},{"amount":"3000"},{"amount":"5000"},{"amount":"10000"},{"amount":"20000"}],"tip":"暂时未开放"},"yue_fee":"1.8%","loan_day_from":"9","loan_day_to":"30","ads":[{"url":"","image":"http://image.cmd2019.cn/ads/fzp.png"}],"latest_loads":[{"telephone":"134****2954","amount":"2000.00"},{"telephone":"135****1886","amount":"3000.00"},{"telephone":"159****8983","amount":"3000.00"},{"telephone":"180****7108","amount":"1600.00"},{"telephone":"182****9922","amount":"2000.00"},{"telephone":"135****6099","amount":"2000.00"},{"telephone":"139****6231","amount":"2000.00"},{"telephone":"137****6869","amount":"2000.00"},{"telephone":"175****5644","amount":"2600.00"},{"telephone":"177****3684","amount":"600.00"}],"waring":"不向学生提供业务","loan":{"id":"35621","orderno":"19122636904883299655","memberid":"107591","idcard":"211381199609041810","damount":"3000.00","interest":"84.00","interestrate":"0.03","amount":"3084.00","days":"28","step":[{"addtime":"2019-12-26 13:08:24","act":"提交贷款申请","desc":"进入审核系统，五分钟内出审批结果"},{"addtime":"2019-12-26 13:19:01","desc":"已审核通过,请尽快确认\n到账本金：0.07\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"},{"addtime":"2019-12-26 13:40:14","desc":"已审核通过,请尽快确认\n到账本金：2100\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"}],"deadline":"2020-01-23 13:40:14","refusetime":null,"status":"1","showconfirm":1,"showcontact":"0","next_loan_time":"0","h5_desc":"","h5_title":"","h5_url":"","contacturl":"","addtime":"2019-12-26 13:08:24","shenhestatus":"1","refusereason":null,"no":null,"status1":"0","daozhang":"2100.00","loan_days":"28","qs":4,"service_fee":900,"fq_list":[{"id":"336303","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-02 23:59:59","paytime":null,"stages":"1","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336304","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-09 23:59:59","paytime":null,"stages":"2","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336305","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-16 23:59:59","paytime":null,"stages":"3","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336306","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-23 23:59:59","paytime":null,"stages":"4","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0}],"pay_url":"/pay/cardpay2/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","pay_url2":"/pay/aliwxpay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99/time/1577342409.html","delay_pay_url":"/pay/xuqipay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","status_info":"已审核"},"authstatus":1,"step":6}}
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
         * data : {"amount_limit":"2100.00","card_desc_cn":"铂金卡","card_desc_en":"Platinum Card","card_no":"XXXX 9370 0011 9267 XX","loan_amount_from":"2000","loan_amount_to":"30000","top_desc":"30秒到账，分期借款","top_hint":"凭身份证可借 . 日息最低0.03% . 最快5分钟到账","bottom_hint":"只面向22岁以上非学生人群","loan_desc":"借款金额 (元)","btn_desc":"立即提现","yue_fee_desc":"月费率","loan_qs_desc":"借款期数","fee_desc":"费用说明","view_desc":"查看详情","detail_total":"还款总额","detail_amount":"含借款金额","detail_service":"含服务费","detail_intrest":"含利息","detail_deadline":"借款期限","detail_needpay":"首期应还","detail_payday":"还款日","day_rate":"0.1","text_day_rate":"日利息","month_rate":"3","service_rate":"30","service_day":"7","loans":{"days":[{"val":"9","status":1},{"val":"21","status":1},{"val":"30","status":1},{"val":"60","status":1},{"val":"90","status":1}],"amount":[{"amount":"2000"},{"amount":"3000"},{"amount":"5000"},{"amount":"10000"},{"amount":"20000"}],"tip":"暂时未开放"},"yue_fee":"1.8%","loan_day_from":"9","loan_day_to":"30","ads":[{"url":"","image":"http://image.cmd2019.cn/ads/fzp.png"}],"latest_loads":[{"telephone":"134****2954","amount":"2000.00"},{"telephone":"135****1886","amount":"3000.00"},{"telephone":"159****8983","amount":"3000.00"},{"telephone":"180****7108","amount":"1600.00"},{"telephone":"182****9922","amount":"2000.00"},{"telephone":"135****6099","amount":"2000.00"},{"telephone":"139****6231","amount":"2000.00"},{"telephone":"137****6869","amount":"2000.00"},{"telephone":"175****5644","amount":"2600.00"},{"telephone":"177****3684","amount":"600.00"}],"waring":"不向学生提供业务","loan":{"id":"35621","orderno":"19122636904883299655","memberid":"107591","idcard":"211381199609041810","damount":"3000.00","interest":"84.00","interestrate":"0.03","amount":"3084.00","days":"28","step":[{"addtime":"2019-12-26 13:08:24","act":"提交贷款申请","desc":"进入审核系统，五分钟内出审批结果"},{"addtime":"2019-12-26 13:19:01","desc":"已审核通过,请尽快确认\n到账本金：0.07\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"},{"addtime":"2019-12-26 13:40:14","desc":"已审核通过,请尽快确认\n到账本金：2100\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"}],"deadline":"2020-01-23 13:40:14","refusetime":null,"status":"1","showconfirm":1,"showcontact":"0","next_loan_time":"0","h5_desc":"","h5_title":"","h5_url":"","contacturl":"","addtime":"2019-12-26 13:08:24","shenhestatus":"1","refusereason":null,"no":null,"status1":"0","daozhang":"2100.00","loan_days":"28","qs":4,"service_fee":900,"fq_list":[{"id":"336303","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-02 23:59:59","paytime":null,"stages":"1","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336304","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-09 23:59:59","paytime":null,"stages":"2","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336305","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-16 23:59:59","paytime":null,"stages":"3","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336306","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-23 23:59:59","paytime":null,"stages":"4","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0}],"pay_url":"/pay/cardpay2/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","pay_url2":"/pay/aliwxpay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99/time/1577342409.html","delay_pay_url":"/pay/xuqipay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","status_info":"已审核"},"authstatus":1,"step":6}
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
             * amount_limit : 2100.00
             * card_desc_cn : 铂金卡
             * card_desc_en : Platinum Card
             * card_no : XXXX 9370 0011 9267 XX
             * loan_amount_from : 2000
             * loan_amount_to : 30000
             * top_desc : 30秒到账，分期借款
             * top_hint : 凭身份证可借 . 日息最低0.03% . 最快5分钟到账
             * bottom_hint : 只面向22岁以上非学生人群
             * loan_desc : 借款金额 (元)
             * btn_desc : 立即提现
             * yue_fee_desc : 月费率
             * loan_qs_desc : 借款期数
             * fee_desc : 费用说明
             * view_desc : 查看详情
             * detail_total : 还款总额
             * detail_amount : 含借款金额
             * detail_service : 含服务费
             * detail_intrest : 含利息
             * detail_deadline : 借款期限
             * detail_needpay : 首期应还
             * detail_payday : 还款日
             * day_rate : 0.1
             * text_day_rate : 日利息
             * month_rate : 3
             * service_rate : 30
             * service_day : 7
             * loans : {"days":[{"val":"9","status":1},{"val":"21","status":1},{"val":"30","status":1},{"val":"60","status":1},{"val":"90","status":1}],"amount":[{"amount":"2000"},{"amount":"3000"},{"amount":"5000"},{"amount":"10000"},{"amount":"20000"}],"tip":"暂时未开放"}
             * yue_fee : 1.8%
             * loan_day_from : 9
             * loan_day_to : 30
             * ads : [{"url":"","image":"http://image.cmd2019.cn/ads/fzp.png"}]
             * latest_loads : [{"telephone":"134****2954","amount":"2000.00"},{"telephone":"135****1886","amount":"3000.00"},{"telephone":"159****8983","amount":"3000.00"},{"telephone":"180****7108","amount":"1600.00"},{"telephone":"182****9922","amount":"2000.00"},{"telephone":"135****6099","amount":"2000.00"},{"telephone":"139****6231","amount":"2000.00"},{"telephone":"137****6869","amount":"2000.00"},{"telephone":"175****5644","amount":"2600.00"},{"telephone":"177****3684","amount":"600.00"}]
             * waring : 不向学生提供业务
             * loan : {"id":"35621","orderno":"19122636904883299655","memberid":"107591","idcard":"211381199609041810","damount":"3000.00","interest":"84.00","interestrate":"0.03","amount":"3084.00","days":"28","step":[{"addtime":"2019-12-26 13:08:24","act":"提交贷款申请","desc":"进入审核系统，五分钟内出审批结果"},{"addtime":"2019-12-26 13:19:01","desc":"已审核通过,请尽快确认\n到账本金：0.07\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"},{"addtime":"2019-12-26 13:40:14","desc":"已审核通过,请尽快确认\n到账本金：2100\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"}],"deadline":"2020-01-23 13:40:14","refusetime":null,"status":"1","showconfirm":1,"showcontact":"0","next_loan_time":"0","h5_desc":"","h5_title":"","h5_url":"","contacturl":"","addtime":"2019-12-26 13:08:24","shenhestatus":"1","refusereason":null,"no":null,"status1":"0","daozhang":"2100.00","loan_days":"28","qs":4,"service_fee":900,"fq_list":[{"id":"336303","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-02 23:59:59","paytime":null,"stages":"1","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336304","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-09 23:59:59","paytime":null,"stages":"2","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336305","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-16 23:59:59","paytime":null,"stages":"3","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336306","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-23 23:59:59","paytime":null,"stages":"4","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0}],"pay_url":"/pay/cardpay2/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","pay_url2":"/pay/aliwxpay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99/time/1577342409.html","delay_pay_url":"/pay/xuqipay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html","status_info":"已审核"}
             * authstatus : 1
             * step : 6
             */
            private String top_desc_fq;

            public String getTop_desc_fq() {
                return top_desc_fq;
            }

            public void setTop_desc_fq(String top_desc_fq) {
                this.top_desc_fq = top_desc_fq;
            }

            public String getTop_desc_dz() {
                return top_desc_dz;
            }

            public void setTop_desc_dz(String top_desc_dz) {
                this.top_desc_dz = top_desc_dz;
            }

            private String top_desc_dz;
            private String amount_limit;
            private String card_desc_cn;
            private String card_desc_en;
            private String card_no;
            private String loan_amount_from;
            private String loan_amount_to;
            private String top_desc;
            private String top_hint;

            public String getTop_hint_1() {
                if (top_hint_1 == null) {
                    top_hint_1 = "";
                }
                return top_hint_1;
            }

            public void setTop_hint_1(String top_hint_1) {
                this.top_hint_1 = top_hint_1;
            }

            public String getTop_hint_2() {
                if (top_hint_2 == null) {
                    top_hint_2 = "";
                }
                return top_hint_2;
            }

            public void setTop_hint_2(String top_hint_2) {
                this.top_hint_2 = top_hint_2;
            }

            public String getTop_hint_3() {
                if (top_hint_3 == null) {
                    top_hint_3 = "";
                }
                return top_hint_3;
            }

            public void setTop_hint_3(String top_hint_3) {
                this.top_hint_3 = top_hint_3;
            }

            private String top_hint_1;
            private String top_hint_2;
            private String top_hint_3;
            private String bottom_hint;
            private String loan_desc;
            private String btn_desc;
            private String yue_fee_desc;
            private String loan_qs_desc;
            private String fee_desc;
            private String view_desc;
            private String detail_total;
            private String detail_amount;
            private String detail_service;
            private String detail_intrest;
            private String detail_deadline;
            private String detail_needpay;
            private String detail_payday;
            private String day_rate;
            private String text_day_rate;
            private String month_rate;
            private String service_rate;
            private String service_day;
            private LoansBean loans;
            private String yue_fee;
            private String loan_day_from;
            private String loan_day_to;
            private String waring;
            private LoanBean loan;
            private int authstatus;
            private int step;
            private String refundamount;

            public String getRefundamount() {
                return refundamount;
            }

            public void setRefundamount(String refundamount) {
                this.refundamount = refundamount;
            }

            private List<AdsBean> ads;
            private List<LatestLoadsBean> latest_loads;

            public String getAmount_limit() {
                return amount_limit;
            }

            public void setAmount_limit(String amount_limit) {
                this.amount_limit = amount_limit;
            }

            public String getCard_desc_cn() {
                return card_desc_cn;
            }

            public void setCard_desc_cn(String card_desc_cn) {
                this.card_desc_cn = card_desc_cn;
            }

            public String getCard_desc_en() {
                return card_desc_en;
            }

            public void setCard_desc_en(String card_desc_en) {
                this.card_desc_en = card_desc_en;
            }

            public String getCard_no() {
                return card_no;
            }

            public void setCard_no(String card_no) {
                this.card_no = card_no;
            }

            public String getLoan_amount_from() {
                return loan_amount_from;
            }

            public void setLoan_amount_from(String loan_amount_from) {
                this.loan_amount_from = loan_amount_from;
            }

            public String getLoan_amount_to() {
                return loan_amount_to;
            }

            public void setLoan_amount_to(String loan_amount_to) {
                this.loan_amount_to = loan_amount_to;
            }

            public String getTop_desc() {
                return top_desc;
            }

            public void setTop_desc(String top_desc) {
                this.top_desc = top_desc;
            }

            public String getTop_hint() {
                return top_hint;
            }

            public void setTop_hint(String top_hint) {
                this.top_hint = top_hint;
            }

            public String getBottom_hint() {
                return bottom_hint;
            }

            public void setBottom_hint(String bottom_hint) {
                this.bottom_hint = bottom_hint;
            }

            public String getLoan_desc() {
                return loan_desc;
            }

            public void setLoan_desc(String loan_desc) {
                this.loan_desc = loan_desc;
            }

            public String getBtn_desc() {
                return btn_desc;
            }

            public void setBtn_desc(String btn_desc) {
                this.btn_desc = btn_desc;
            }

            public String getYue_fee_desc() {
                return yue_fee_desc;
            }

            public void setYue_fee_desc(String yue_fee_desc) {
                this.yue_fee_desc = yue_fee_desc;
            }

            public String getLoan_qs_desc() {
                return loan_qs_desc;
            }

            public void setLoan_qs_desc(String loan_qs_desc) {
                this.loan_qs_desc = loan_qs_desc;
            }

            public String getFee_desc() {
                return fee_desc;
            }

            public void setFee_desc(String fee_desc) {
                this.fee_desc = fee_desc;
            }

            public String getView_desc() {
                return view_desc;
            }

            public void setView_desc(String view_desc) {
                this.view_desc = view_desc;
            }

            public String getDetail_total() {
                return detail_total;
            }

            public void setDetail_total(String detail_total) {
                this.detail_total = detail_total;
            }

            public String getDetail_amount() {
                return detail_amount;
            }

            public void setDetail_amount(String detail_amount) {
                this.detail_amount = detail_amount;
            }

            public String getDetail_service() {
                return detail_service;
            }

            public void setDetail_service(String detail_service) {
                this.detail_service = detail_service;
            }

            public String getDetail_intrest() {
                return detail_intrest;
            }

            public void setDetail_intrest(String detail_intrest) {
                this.detail_intrest = detail_intrest;
            }

            public String getDetail_deadline() {
                return detail_deadline;
            }

            public void setDetail_deadline(String detail_deadline) {
                this.detail_deadline = detail_deadline;
            }

            public String getDetail_needpay() {
                return detail_needpay;
            }

            public void setDetail_needpay(String detail_needpay) {
                this.detail_needpay = detail_needpay;
            }

            public String getDetail_payday() {
                return detail_payday;
            }

            public void setDetail_payday(String detail_payday) {
                this.detail_payday = detail_payday;
            }

            public String getDay_rate() {
                return day_rate;
            }

            public void setDay_rate(String day_rate) {
                this.day_rate = day_rate;
            }

            public String getText_day_rate() {
                return text_day_rate;
            }

            public void setText_day_rate(String text_day_rate) {
                this.text_day_rate = text_day_rate;
            }

            public String getMonth_rate() {
                return month_rate;
            }

            public void setMonth_rate(String month_rate) {
                this.month_rate = month_rate;
            }

            public String getService_rate() {
                return service_rate;
            }

            public void setService_rate(String service_rate) {
                this.service_rate = service_rate;
            }

            public String getService_day() {
                return service_day;
            }

            public void setService_day(String service_day) {
                this.service_day = service_day;
            }

            public LoansBean getLoans() {
                return loans;
            }

            public void setLoans(LoansBean loans) {
                this.loans = loans;
            }

            public String getYue_fee() {
                return yue_fee;
            }

            public void setYue_fee(String yue_fee) {
                this.yue_fee = yue_fee;
            }

            public String getLoan_day_from() {
                return loan_day_from;
            }

            public void setLoan_day_from(String loan_day_from) {
                this.loan_day_from = loan_day_from;
            }

            public String getLoan_day_to() {
                return loan_day_to;
            }

            public void setLoan_day_to(String loan_day_to) {
                this.loan_day_to = loan_day_to;
            }

            public String getWaring() {
                return waring;
            }

            public void setWaring(String waring) {
                this.waring = waring;
            }

            public LoanBean getLoan() {
                return loan;
            }

            public void setLoan(LoanBean loan) {
                this.loan = loan;
            }

            public int getAuthstatus() {
                return authstatus;
            }

            public void setAuthstatus(int authstatus) {
                this.authstatus = authstatus;
            }

            public int getStep() {
                return step;
            }

            public void setStep(int step) {
                this.step = step;
            }

            public List<AdsBean> getAds() {
                return ads;
            }

            public void setAds(List<AdsBean> ads) {
                this.ads = ads;
            }

            public List<LatestLoadsBean> getLatest_loads() {
                return latest_loads;
            }

            public void setLatest_loads(List<LatestLoadsBean> latest_loads) {
                this.latest_loads = latest_loads;
            }

            public static class LoansBean {
                /**
                 * days : [{"val":"9","status":1},{"val":"21","status":1},{"val":"30","status":1},{"val":"60","status":1},{"val":"90","status":1}]
                 * amount : [{"amount":"2000"},{"amount":"3000"},{"amount":"5000"},{"amount":"10000"},{"amount":"20000"}]
                 * tip : 暂时未开放
                 */

                private String tip;
                private List<DaysBean> days;
                private List<AmountBean> amount;

                public String getTip() {
                    return tip;
                }

                public void setTip(String tip) {
                    this.tip = tip;
                }

                public List<DaysBean> getDays() {
                    return days;
                }

                public void setDays(List<DaysBean> days) {
                    this.days = days;
                }

                public List<AmountBean> getAmount() {
                    return amount;
                }

                public void setAmount(List<AmountBean> amount) {
                    this.amount = amount;
                }

                public static class DaysBean {
                    public boolean isDaysStatus() {
                        return DaysStatus;
                    }

                    public void setDaysStatus(boolean daysStatus) {
                        DaysStatus = daysStatus;
                    }

                    /**
                     * val : 9
                     * status : 1
                     */
                    private boolean DaysStatus;
                    private String val;
                    private int status;

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }
                }

                public static class AmountBean {
                    /**
                     * amount : 2000
                     */

                    private String amount;

                    public String getAmount() {
                        return amount;
                    }

                    public void setAmount(String amount) {
                        this.amount = amount;
                    }
                }
            }

            public static class LoanBean {
                /**
                 * id : 35621
                 * orderno : 19122636904883299655
                 * memberid : 107591
                 * idcard : 211381199609041810
                 * damount : 3000.00
                 * interest : 84.00
                 * interestrate : 0.03
                 * amount : 3084.00
                 * days : 28
                 * step : [{"addtime":"2019-12-26 13:08:24","act":"提交贷款申请","desc":"进入审核系统，五分钟内出审批结果"},{"addtime":"2019-12-26 13:19:01","desc":"已审核通过,请尽快确认\n到账本金：0.07\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"},{"addtime":"2019-12-26 13:40:14","desc":"已审核通过,请尽快确认\n到账本金：2100\n还款期数：4\n借款周期为28天,还款日期以放款日期为准保持良好得还款记录有助于提高您的个人信用。","act":"已审核通过"}]
                 * deadline : 2020-01-23 13:40:14
                 * refusetime : null
                 * status : 1
                 * showconfirm : 1
                 * showcontact : 0
                 * next_loan_time : 0
                 * h5_desc :
                 * h5_title :
                 * h5_url :
                 * contacturl :
                 * addtime : 2019-12-26 13:08:24
                 * shenhestatus : 1
                 * refusereason : null
                 * no : null
                 * status1 : 0
                 * daozhang : 2100.00
                 * loan_days : 28
                 * qs : 4
                 * service_fee : 900
                 * fq_list : [{"id":"336303","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-02 23:59:59","paytime":null,"stages":"1","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336304","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-09 23:59:59","paytime":null,"stages":"2","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336305","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-16 23:59:59","paytime":null,"stages":"3","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0},{"id":"336306","member_id":"107591","loan_id":"35621","damount":"771.00","actual_damont":"0.00","deadline":"2020-01-23 23:59:59","paytime":null,"stages":"4","status":"0","pay_id":"","overdue_days":0,"overdue_fee":0}]
                 * pay_url : /pay/cardpay2/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html
                 * pay_url2 : /pay/aliwxpay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99/time/1577342409.html
                 * delay_pay_url : /pay/xuqipay/orderno/19122636904883299655/token/44bcf5ed0c2256ca357c7626fdc16a99.html
                 * status_info : 已审核
                 */

                private String id;
                private String orderno;
                private String memberid;
                private String idcard;
                private String damount;
                private String interest;
                private String interestrate;
                private String amount;
                private String days;
                private String deadline;
                private Object refusetime;
                private String status;
                private int showconfirm;
                private String showcontact;
                private String next_loan_time;
                private String h5_desc;
                private String h5_title;
                private String h5_url;
                private String contacturl;
                private String addtime;
                private String shenhestatus;
                private Object refusereason;
                private Object no;
                private String status1;
                private String daozhang;
                private String loan_days;
                private int qs;
                private int service_fee;
                private String pay_url;
                private String pay_url2;
                private String delay_pay_url;
                private String status_info;
                private List<StepBean> step;
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

                public Object getRefusetime() {
                    return refusetime;
                }

                public void setRefusetime(Object refusetime) {
                    this.refusetime = refusetime;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public int getShowconfirm() {
                    return showconfirm;
                }

                public void setShowconfirm(int showconfirm) {
                    this.showconfirm = showconfirm;
                }

                public String getShowcontact() {
                    return showcontact;
                }

                public void setShowcontact(String showcontact) {
                    this.showcontact = showcontact;
                }

                public String getNext_loan_time() {
                    return next_loan_time;
                }

                public void setNext_loan_time(String next_loan_time) {
                    this.next_loan_time = next_loan_time;
                }

                public String getH5_desc() {
                    return h5_desc;
                }

                public void setH5_desc(String h5_desc) {
                    this.h5_desc = h5_desc;
                }

                public String getH5_title() {
                    return h5_title;
                }

                public void setH5_title(String h5_title) {
                    this.h5_title = h5_title;
                }

                public String getH5_url() {
                    return h5_url;
                }

                public void setH5_url(String h5_url) {
                    this.h5_url = h5_url;
                }

                public String getContacturl() {
                    return contacturl;
                }

                public void setContacturl(String contacturl) {
                    this.contacturl = contacturl;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
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

                public String getLoan_days() {
                    return loan_days;
                }

                public void setLoan_days(String loan_days) {
                    this.loan_days = loan_days;
                }

                public int getQs() {
                    return qs;
                }

                public void setQs(int qs) {
                    this.qs = qs;
                }

                public int getService_fee() {
                    return service_fee;
                }

                public void setService_fee(int service_fee) {
                    this.service_fee = service_fee;
                }

                public String getPay_url() {
                    return pay_url;
                }

                public void setPay_url(String pay_url) {
                    this.pay_url = pay_url;
                }

                public String getPay_url2() {
                    return pay_url2;
                }

                public void setPay_url2(String pay_url2) {
                    this.pay_url2 = pay_url2;
                }

                public String getDelay_pay_url() {
                    return delay_pay_url;
                }

                public void setDelay_pay_url(String delay_pay_url) {
                    this.delay_pay_url = delay_pay_url;
                }

                public String getStatus_info() {
                    return status_info;
                }

                public void setStatus_info(String status_info) {
                    this.status_info = status_info;
                }

                public List<StepBean> getStep() {
                    return step;
                }

                public void setStep(List<StepBean> step) {
                    this.step = step;
                }

                public List<FqListBean> getFq_list() {
                    return fq_list;
                }

                public void setFq_list(List<FqListBean> fq_list) {
                    this.fq_list = fq_list;
                }

                public static class StepBean {
                    /**
                     * addtime : 2019-12-26 13:08:24
                     * act : 提交贷款申请
                     * desc : 进入审核系统，五分钟内出审批结果
                     */

                    private String addtime;
                    private String act;
                    private String desc;

                    public String getAddtime() {
                        return addtime;
                    }

                    public void setAddtime(String addtime) {
                        this.addtime = addtime;
                    }

                    public String getAct() {
                        return act;
                    }

                    public void setAct(String act) {
                        this.act = act;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class FqListBean {
                    /**
                     * id : 336303
                     * member_id : 107591
                     * loan_id : 35621
                     * damount : 771.00
                     * actual_damont : 0.00
                     * deadline : 2020-01-02 23:59:59
                     * paytime : null
                     * stages : 1
                     * status : 0
                     * pay_id :
                     * overdue_days : 0
                     * overdue_fee : 0
                     */

                    private String id;
                    private String member_id;
                    private String loan_id;
                    private String damount;
                    private String actual_damont;
                    private String deadline;
                    private Object paytime;
                    private String stages;
                    private String status;
                    private String pay_id;
                    private int overdue_days;
                    private int overdue_fee;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getMember_id() {
                        return member_id;
                    }

                    public void setMember_id(String member_id) {
                        this.member_id = member_id;
                    }

                    public String getLoan_id() {
                        return loan_id;
                    }

                    public void setLoan_id(String loan_id) {
                        this.loan_id = loan_id;
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

                    public String getDeadline() {
                        return deadline;
                    }

                    public void setDeadline(String deadline) {
                        this.deadline = deadline;
                    }

                    public Object getPaytime() {
                        return paytime;
                    }

                    public void setPaytime(Object paytime) {
                        this.paytime = paytime;
                    }

                    public String getStages() {
                        return stages;
                    }

                    public void setStages(String stages) {
                        this.stages = stages;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public String getPay_id() {
                        return pay_id;
                    }

                    public void setPay_id(String pay_id) {
                        this.pay_id = pay_id;
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

            public static class AdsBean {
                /**
                 * url :
                 * image : http://image.cmd2019.cn/ads/fzp.png
                 */

                private String url;
                private String image;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }

            public static class LatestLoadsBean {
                /**
                 * telephone : 134****2954
                 * amount : 2000.00
                 */

                private String telephone;
                private String amount;

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }
            }
        }
    }
}
