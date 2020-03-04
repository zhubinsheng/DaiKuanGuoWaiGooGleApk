package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

public class MyUserInfoBean {

    /**
     * data : {"member":{"username":"PRANATA KUSUMA ATMAJA","idcard":"1408110310000001","telephone":"15708319320","idcardimg1":"icon_020191230202242","idcardimg2":"icon_020191230202242","idcardimg3":"icon_120191230202242"}}
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
         * member : {"username":"PRANATA KUSUMA ATMAJA","idcard":"1408110310000001","telephone":"15708319320","idcardimg1":"icon_020191230202242","idcardimg2":"icon_020191230202242","idcardimg3":"icon_120191230202242"}
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
             * username : PRANATA KUSUMA ATMAJA
             * idcard : 1408110310000001
             * telephone : 15708319320
             * idcardimg1 : icon_020191230202242
             * idcardimg2 : icon_020191230202242
             * idcardimg3 : icon_120191230202242
             */

            private String username;
            private String idcard;
            private String telephone;
            private String idcardimg1;
            private String idcardimg2;
            private String idcardimg3;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
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

            public String getIdcardimg1() {
                return idcardimg1;
            }

            public void setIdcardimg1(String idcardimg1) {
                this.idcardimg1 = idcardimg1;
            }

            public String getIdcardimg2() {
                return idcardimg2;
            }

            public void setIdcardimg2(String idcardimg2) {
                this.idcardimg2 = idcardimg2;
            }

            public String getIdcardimg3() {
                return idcardimg3;
            }

            public void setIdcardimg3(String idcardimg3) {
                this.idcardimg3 = idcardimg3;
            }
        }
    }
}
