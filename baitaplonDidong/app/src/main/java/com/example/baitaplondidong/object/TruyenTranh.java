package com.example.baitaplondidong.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TruyenTranh implements Serializable {
    private String tenTruyen,tenChap,LinkAnh, id, isMark;

    public TruyenTranh(){

    }

    public TruyenTranh(JSONObject o) throws JSONException {
        isMark = o.getString("isMark");
        id = o.getString("id");
        tenTruyen = o.getString("tenTruyen");
        tenChap = o.getString("tenChap");
        LinkAnh = o.getString("linkAnh");
    }

    public TruyenTranh(String tenTruyen, String tenChap, String linkAnh, String isMark) {
        this.isMark = isMark;
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.id = id;
        LinkAnh = linkAnh;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getLinkAnh() {
        return LinkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        LinkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsMark() {
        return isMark;
    }

    public void setIsMark(String isMark) {
        this.isMark = isMark;
    }
}
