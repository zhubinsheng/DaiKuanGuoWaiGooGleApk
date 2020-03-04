package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class PostFaceComparisonBean {

    /**
     * code : SUCCESS
     * message : OK
     * data : {"similarity":0,"firstFace":{"top":491.625,"left":1414.3125,"bottom":723.1875,"id":"1","right":1642.3125},"secondFace":{"top":83.203125,"left":113.671875,"bottom":189.84375,"id":"2","right":220.3125}}
     * extra : null
     * transactionId : 27712cbc41c54a48
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
         * similarity : 0.0
         * firstFace : {"top":491.625,"left":1414.3125,"bottom":723.1875,"id":"1","right":1642.3125}
         * secondFace : {"top":83.203125,"left":113.671875,"bottom":189.84375,"id":"2","right":220.3125}
         */

        private double similarity;
        private FirstFaceBean firstFace;
        private SecondFaceBean secondFace;

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public FirstFaceBean getFirstFace() {
            return firstFace;
        }

        public void setFirstFace(FirstFaceBean firstFace) {
            this.firstFace = firstFace;
        }

        public SecondFaceBean getSecondFace() {
            return secondFace;
        }

        public void setSecondFace(SecondFaceBean secondFace) {
            this.secondFace = secondFace;
        }

        public static class FirstFaceBean {
            /**
             * top : 491.625
             * left : 1414.3125
             * bottom : 723.1875
             * id : 1
             * right : 1642.3125
             */

            private double top;
            private double left;
            private double bottom;
            private String id;
            private double right;

            public double getTop() {
                return top;
            }

            public void setTop(double top) {
                this.top = top;
            }

            public double getLeft() {
                return left;
            }

            public void setLeft(double left) {
                this.left = left;
            }

            public double getBottom() {
                return bottom;
            }

            public void setBottom(double bottom) {
                this.bottom = bottom;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public double getRight() {
                return right;
            }

            public void setRight(double right) {
                this.right = right;
            }
        }

        public static class SecondFaceBean {
            /**
             * top : 83.203125
             * left : 113.671875
             * bottom : 189.84375
             * id : 2
             * right : 220.3125
             */

            private double top;
            private double left;
            private double bottom;
            private String id;
            private double right;

            public double getTop() {
                return top;
            }

            public void setTop(double top) {
                this.top = top;
            }

            public double getLeft() {
                return left;
            }

            public void setLeft(double left) {
                this.left = left;
            }

            public double getBottom() {
                return bottom;
            }

            public void setBottom(double bottom) {
                this.bottom = bottom;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public double getRight() {
                return right;
            }

            public void setRight(double right) {
                this.right = right;
            }
        }
    }
}
