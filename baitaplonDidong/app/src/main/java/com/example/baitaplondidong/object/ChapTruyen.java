package com.example.baitaplondidong.object;

import org.json.JSONException;
import org.json.JSONObject;

public class ChapTruyen {
    private String id;
    private String tenChap;
    private String ngayDang;


    private String isMark;

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    private String idTruyen;
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
        idTruyen = o.getString("idTruyen");
        isMark = o.getString("isMark");
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
