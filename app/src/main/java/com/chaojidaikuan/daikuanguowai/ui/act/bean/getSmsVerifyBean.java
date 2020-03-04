package com.chaojidaikuan.daikuanguowai.ui.act.bean;

public class getSmsVerifyBean {

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * data : {"data":{"version":"1.0","info":"1.Optimize delivery \r\n2.Optimize UI.\r\n3.Minor bug fixes.","versioncode":"2","created":"2016-03-25 16:14:07","force_update":"1","app_link":"http://res.cmd2019.cn/KSP_Mudah_Pinjam_V30.apk","code":"2","appstore_updated":"1"}}
     * status : 1
     */
    private String msg;
    private DataBeanX data;
    private int status;

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
         * data : {"version":"1.0","info":"1.Optimize delivery \r\n2.Optimize UI.\r\n3.Minor bug fixes.","versioncode":"2","created":"2016-03-25 16:14:07","force_update":"1","app_link":"http://res.cmd2019.cn/KSP_Mudah_Pinjam_V30.apk","code":"2","appstore_updated":"1"}
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
             * version : 1.0
             * info : 1.Optimize delivery
             2.Optimize UI.
             3.Minor bug fixes.
             * versioncode : 2
             * created : 2016-03-25 16:14:07
             * force_update : 1
             * app_link : http://res.cmd2019.cn/KSP_Mudah_Pinjam_V30.apk
             * code : 2
             * appstore_updated : 1
             */

            private String version;
            private String info;
            private String versioncode;
            private String created;
            private String force_update;
            private String app_link;
            private String code;
            private String appstore_updated;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getVersioncode() {
                return versioncode;
            }

            public void setVersioncode(String versioncode) {
                this.versioncode = versioncode;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getForce_update() {
                return force_update;
            }

            public void setForce_update(String force_update) {
                this.force_update = force_update;
            }

            public String getApp_link() {
                return app_link;
            }

            public void setApp_link(String app_link) {
                this.app_link = app_link;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getAppstore_updated() {
                return appstore_updated;
            }

            public void setAppstore_updated(String appstore_updated) {
                this.appstore_updated = appstore_updated;
            }
        }
    }
}
