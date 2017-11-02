package com.example.limaoi.gameone.bean;


import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by limaoi on 2017/10/18.
 * E-mail：autismlm20@vip.qq.com
 */

public class Circle extends BmobObject {

    private String username;
    private String nickname;
    private String dynamic; //动态
    private List<String> dynamicPictureUrl;
    private String changeTime;

    public Circle() {

    }

    public Circle(String nickname, String dynamic, List<String> dynamicPictureUrl, String changeTime) {
        this.nickname = nickname;
        this.dynamic = dynamic;
        this.dynamicPictureUrl = dynamicPictureUrl;
        this.changeTime = changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public void setDynamicPictureUrl(List<String> dynamicPictureUrl) {
        this.dynamicPictureUrl = dynamicPictureUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getDynamic() {
        return dynamic;
    }

    public List<String> getDynamicPictureUrl() {
        return dynamicPictureUrl;
    }

    public String getChangeTime() {
        return changeTime;
    }
}
