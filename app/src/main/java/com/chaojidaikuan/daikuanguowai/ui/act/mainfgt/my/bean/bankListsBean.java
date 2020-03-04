package com.chaojidaikuan.daikuanguowai.ui.act.mainfgt.my.bean;

import java.util.List;

public class bankListsBean {

    /**
     * status : 1
     * data : {"banks":[{"key":"002","val":"Bank Rakyat Indonesia"},{"key":"008","val":"Bank Mandiri"},{"key":"009","val":"Bank Negara Indonesia"},{"key":"011","val":"Bank Danamon"},{"key":"013","val":"Bank Permata"},{"key":"014","val":"BANK BCA"},{"key":"016","val":"BANK BII MAYBANK"},{"key":"019","val":"BANK PANIN"},{"key":"022","val":"BANK CIMB NIAGA"},{"key":"023","val":"BANK UOB INDONESIA"},{"key":"031","val":"CITIBANK"},{"key":"036","val":"BANK CCB INDONESIA"},{"key":"037","val":"BANK ARTHA GRAHA"},{"key":"042","val":"THE BANK OF TOKYO MITSUBISHI UFJ LTD"},{"key":"046","val":"BANK DBS INDONESIA"},{"key":"050","val":"STANDARD CHARTERED BANK"},{"key":"061","val":"ANZ PANIN BANK"},{"key":"067","val":"DEUTSCHE BANK AG"},{"key":"069","val":"BANK OF CHINA LIMITED"},{"key":"076","val":"BANK BUMI ARTA"},{"key":"087","val":"BANK HSBC"},{"key":"089","val":"BANK RABOBANK"},{"key":"097","val":"BANK MAYAPADA"},{"key":110,"val":"BANK BJB"},{"key":111,"val":"BANK DKI"},{"key":112,"val":"BPD DIY"},{"key":113,"val":"BANK JATENG"},{"key":114,"val":"BANK JATIM"},{"key":115,"val":"BPD JAMBI"},{"key":116,"val":"BPD ACEH"},{"key":117,"val":"BANK SUMUT"},{"key":119,"val":"BANK RIAU"},{"key":120,"val":"BANK SUMSEL"},{"key":121,"val":"BANK LAMPUNG"},{"key":122,"val":"BPD KALSEL"},{"key":124,"val":"BPD KALTIM"},{"key":125,"val":"BPD KALTENG"},{"key":126,"val":"BPD SULSEL"},{"key":127,"val":"BANK SULUT"},{"key":128,"val":"BPD NTB"},{"key":129,"val":"BPD BALI"},{"key":130,"val":"BANK NTT"},{"key":131,"val":"BANK MALUKU"},{"key":132,"val":"BPD PAPUA"},{"key":133,"val":"BANK BENGKULU"},{"key":134,"val":"BPD SULAWESI TENGAH"},{"key":135,"val":"BANK SULTRA"},{"key":146,"val":"BANK BOI INDONESIA"},{"key":147,"val":"BANK MUAMALAT"},{"key":151,"val":"BANK MESTIKA"},{"key":153,"val":"BANK SINARMAS"},{"key":157,"val":"BANK MASPION"},{"key":161,"val":"BANK GANESHA"},{"key":164,"val":"BANK ICBC"},{"key":167,"val":"BANK QNB KESAWAN"},{"key":200,"val":"BANK TABUNGAN NEGARA (BTN)"},{"key":212,"val":"BANK WOORI INDONESIA"},{"key":213,"val":"BANK TABUNGAN PENSIUNAN NASIONAL (BTPN)"},{"key":405,"val":"BANK VICTORIA SYARIAH"},{"key":422,"val":"BANK BRI SYARIAH"},{"key":425,"val":"BANK BJB SYARIAH"},{"key":426,"val":"BANK MEGA"},{"key":427,"val":"BNI Syariah"},{"key":441,"val":"BANK BUKOPIN"},{"key":451,"val":"BANK SYARIAH MANDIRI"},{"key":472,"val":"BANK JASA JAKARTA"},{"key":484,"val":"BANK HANA"},{"key":485,"val":"BANK MNC/BANK BUMIPUTERA"},{"key":494,"val":"BANK AGRO NIAGA"},{"key":498,"val":"BANK SBI"},{"key":501,"val":"BANK ROYAL INDONESIA"},{"key":503,"val":"BANK NOBU"},{"key":506,"val":"BANK SYARIAH MEGA"},{"key":513,"val":"BANK INA PERDANA"},{"key":521,"val":"BANK BUKOPIN SYARIAH"},{"key":523,"val":"BANK SAMPOERNA"},{"key":535,"val":"BANK KESEJAHTERAAN EKONOMI"},{"key":547,"val":"BANK BTN SYARIAH"},{"key":553,"val":"BANK MAYORA"},{"key":555,"val":"BANK INDEX SELINDO"},{"key":566,"val":"BANK VICTORIA"},{"key":600,"val":"BPR/LSB"},{"key":688,"val":"BPR KS"},{"key":699,"val":"BPR EKA"},{"key":789,"val":"INDOSAT DOMPETKU"},{"key":911,"val":"TELKOMSEL TCASH"},{"key":945,"val":"BANK AGRIS"},{"key":949,"val":"BANK CHINA TRUST INDONESIA"}]}
     */

    private int status;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BanksBean> banks;

        public List<BanksBean> getBanks() {
            return banks;
        }

        public void setBanks(List<BanksBean> banks) {
            this.banks = banks;
        }

        public static class BanksBean {
            /**
             * key : 002
             * val : Bank Rakyat Indonesia
             */

            private String key;
            private String val;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getVal() {
                return val;
            }

            public void setVal(String val) {
                this.val = val;
            }
        }
    }
}
