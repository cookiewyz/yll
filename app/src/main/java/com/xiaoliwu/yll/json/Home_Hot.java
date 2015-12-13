package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/26.
 */
public class Home_Hot {
    /**
     * code : 200
     * data : {"hot_words":["戒指","手机壳","双肩包","钱包","情侣","手表","泳衣","杯子","手链","樱花","手工","项链"],"placeholder":"选份礼物给最爱的人"}
     * message : OK
     */
        private String placeholder;
        private List<String> hot_words;

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public void setHot_words(List<String> hot_words) {
            this.hot_words = hot_words;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public List<String> getHot_words() {
            return hot_words;
        }
    }
