package com.example.baitaplondidong.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class TruyenTranh implements Serializable {
    private String tenTruyen, tenChap, LinkAnh, id, isMark;

    private String tacGia, trangThai, Nguon;

    public TruyenTranh(){

    }

    public TruyenTranh(String tacGia, String trangThai, String nguon) {
        this.tacGia = tacGia;
        this.trangThai = trangThai;
        Nguon = nguon;
    }

    public TruyenTranh(JSONObject o) throws JSONException {

        tacGia = o.getString("tacGia");
        trangThai = o.getString("trangThai");
        Nguon = o.getString("Nguon");

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

    public String getTacGia() {
        return "Tác giả: " + tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTrangThai() {
        return "Trạng thái: " + trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getNguon() {
        return "Nguồn: " + Nguon;
    }

    public void setNguon(String nguon) {
        Nguon = nguon;
    }
}
