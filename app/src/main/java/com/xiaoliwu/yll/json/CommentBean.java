package com.xiaoliwu.yll.json;

/**
 * Created by Administrator on 2015/10/23.
 */
public class CommentBean {
    private String content;//内容
    private String avatar_url;//用户图像
    private String nickname;//用户名
    private long created_at;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }
}
