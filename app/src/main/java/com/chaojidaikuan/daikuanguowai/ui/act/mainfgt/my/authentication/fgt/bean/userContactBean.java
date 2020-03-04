package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.authentication.fgt.bean;

import java.util.List;

public class userContactBean {

    /**
     * data : {"contactinfo":[{"seq":1,"username":"姓名1","telephone":"11212111","relationship":"1"},{"seq":2,"username":"姓名2","telephone":"33322","relationship":"2"},{"seq":2,"username":"姓名3","telephone":"66666","relationship":"3"}]}
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
        private List<ContactinfoBean> contactinfo;

        public List<ContactinfoBean> getContactinfo() {
            return contactinfo;
        }

        public void setContactinfo(List<ContactinfoBean> contactinfo) {
            this.contactinfo = contactinfo;
        }

        public static class ContactinfoBean {
            /**
             * seq : 1
             * username : 姓名1
             * telephone : 11212111
             * relationship : 1
             */

            private int seq;
            private String username;
            private String telephone;
            private String relationship;

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getRelationship() {
                return relationship;
            }

            public void setRelationship(String relationship) {
                this.relationship = relationship;
            }
        }
    }
}
