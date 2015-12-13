package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Time  {

    /**
     * code : 200
     * data : {"schedules":{"item":["8:00"],"post":["8:00","12:00"]}}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public static Time objectFromData(String str) {

        return new Gson().fromJson(str, Time.class);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * schedules : {"item":["8:00"],"post":["8:00","12:00"]}
         */

        private SchedulesEntity schedules;

        public static DataEntity objectFromData(String str) {

            return new Gson().fromJson(str, DataEntity.class);
        }

        public void setSchedules(SchedulesEntity schedules) {
            this.schedules = schedules;
        }

        public SchedulesEntity getSchedules() {
            return schedules;
        }

        public static class SchedulesEntity {
            /**
             * item : ["8:00"]
             * post : ["8:00","12:00"]
             */

            private List<String> item;
            private List<String> post;

            public static SchedulesEntity objectFromData(String str) {

                return new Gson().fromJson(str, SchedulesEntity.class);
            }

            public void setItem(List<String> item) {
                this.item = item;
            }

            public void setPost(List<String> post) {
                this.post = post;
            }

            public List<String> getItem() {
                return item;
            }

            public List<String> getPost() {
                return post;
            }
        }
    }
}
