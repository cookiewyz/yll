package com.xiaoliwu.yll.json;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Special_Bean {

            /**
             * banner_image_url : http://img03.liwushuo.com/image/151019/r0atxmful.jpg-w300
             * cover_image_url : http://img01.liwushuo.com/image/151019/8fe266vsu.jpg-w720
             * created_at : 1445227932
             * id : 147
             * posts_count : 7
             * status : 0
             * subtitle : 天猫国际场
             * title : 双11什么值得买
             * updated_at : 1445227932
             */

            private String banner_image_url;
            private String cover_image_url;
            private int created_at;
            private int id;
            private int posts_count;
            private int status;
            private String subtitle;
            private String title;
            private int updated_at;

            public String getBanner_image_url() {
                return banner_image_url;
            }

            public void setBanner_image_url(String banner_image_url) {
                this.banner_image_url = banner_image_url;
            }

            public String getCover_image_url() {
                return cover_image_url;
            }

            public void setCover_image_url(String cover_image_url) {
                this.cover_image_url = cover_image_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getPosts_count() {
                return posts_count;
            }

            public void setPosts_count(int posts_count) {
                this.posts_count = posts_count;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }
    }
