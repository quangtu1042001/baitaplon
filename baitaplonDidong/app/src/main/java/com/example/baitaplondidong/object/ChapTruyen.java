package com.example.baitaplondidong.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen {
    private String id, tenChap, ngayDang;
    public ChapTruyen(){}

    public ChapTruyen(String tenChap, String ngayDang) {
        this.tenChap = tenChap;
        this.ngayDang = ngayDang;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public ChapTruyen(JSONObject o) throws JSONException {
        id = o.getString("id");
        tenChap = o.getString("tenChap");
        ngayDang = o.getString("ngayNhap");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
