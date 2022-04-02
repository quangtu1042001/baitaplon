package com.example.baitaplondidong.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class AnhTruyen implements Serializable {
    private String LinkAnh;

    public AnhTruyen(){

    }
    public AnhTruyen(JSONObject o) throws JSONException {
        LinkAnh = o.getString("linkAnh");
    }
    public AnhTruyen(String linkAnh){
        LinkAnh = linkAnh;
    }
    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String imgUrl) {
        this.LinkAnh = imgUrl;
    }
}
