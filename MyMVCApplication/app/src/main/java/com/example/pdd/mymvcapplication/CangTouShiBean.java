package com.example.pdd.mymvcapplication;

import java.util.List;
import java.util.List;

public class CangTouShiBean {
        /**
         * showapi_res_code : 0
         * showapi_res_error :
         * showapi_res_body : {"ret_code":0,"list":["北风勇士马，晚水独芙蓉。吾将宝非宝，英雄徒自强。","朝骑五花马，太华三芙蓉。吾将宝非宝，天子贵文强。","请歌牵白马，菡萏金芙蓉。大位天下宝，自从冒顿强。","青丝系五马，秀出九芙蓉。迈德惟家宝，日来知自强。","北买党项马，美女夸芙蓉。河宗来献宝，十年思自强。","青丝系五马，大嫂采芙蓉。药妙灵仙宝，不独有文强。"]}
         */

        private int showapi_res_code;
        private String showapi_res_error;
        private ShowapiResBodyBean showapi_res_body;


        @Override
        public String toString() {
            return "CangTouShiBean{" +
                    "showapi_res_code=" + showapi_res_code +
                    ", showapi_res_error='" + showapi_res_error + '\'' +
                    ", showapi_res_body=" + showapi_res_body +
                    '}';
        }

        public int getShowapi_res_code() {
            return showapi_res_code;
        }

        public void setShowapi_res_code(int showapi_res_code) {
            this.showapi_res_code = showapi_res_code;
        }

        public String getShowapi_res_error() {
            return showapi_res_error;
        }

        public void setShowapi_res_error(String showapi_res_error) {
            this.showapi_res_error = showapi_res_error;
        }

        public ShowapiResBodyBean getShowapi_res_body() {
            return showapi_res_body;
        }

        public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
            this.showapi_res_body = showapi_res_body;
        }

        public static class ShowapiResBodyBean<List> {
            /**
             * ret_code : 0
             * list : ["北风勇士马，晚水独芙蓉。吾将宝非宝，英雄徒自强。","朝骑五花马，太华三芙蓉。吾将宝非宝，天子贵文强。","请歌牵白马，菡萏金芙蓉。大位天下宝，自从冒顿强。","青丝系五马，秀出九芙蓉。迈德惟家宝，日来知自强。","北买党项马，美女夸芙蓉。河宗来献宝，十年思自强。","青丝系五马，大嫂采芙蓉。药妙灵仙宝，不独有文强。"]
             */

            private int ret_code;
            private List list;

            @Override
            public String toString() {
                return "ShowapiResBodyBean{" +
                        "ret_code=" + ret_code +
                        ", list=" + list +
                        '}';
            }
            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }
            public List getList() {
                return list;
            }
            public void setList(List list) {
                this.list = list;
            }
        }
    }




