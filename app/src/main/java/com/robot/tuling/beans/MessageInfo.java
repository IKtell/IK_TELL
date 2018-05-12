package com.robot.tuling.beans;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;


public class MessageInfo implements Comparable, Serializable {
    public String data;

    public String likeNum;

    public String commentNum;

    public String uuid;

    public Date date;

    public MessageInfo(String data, String likeNum,
                       String commentNum, String uuid,
                       Date date){
        this.data = data;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
        this.uuid = uuid;
        this.date = date;
    }
    
    public String getData(){
        return data;
    }
    
    public String getLikeNum(){
        return likeNum;
    }
    
    public String getCommentNum(){
        return commentNum;
    }
    
    public Date getDate(){
        return date;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        MessageInfo s = (MessageInfo) (o);
        int result = 0;
        result = (int) (this.getDate().getDate()- s.getDate().getDate());
        return result;
    }
}
