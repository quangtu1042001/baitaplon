package com.example.baitaplondidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baitaplondidong.adapter.ChapTruyenAdapter;
import com.example.baitaplondidong.object.ChapTruyen;
import com.example.baitaplondidong.object.TruyenTranh;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity {
TextView txvTenTruyens;
ImageView imgAnhTruyens;
TruyenTranh truyenTranh;
ListView lsvDanhSachChap;
ArrayList<ChapTruyen> arrChap;
ChapTruyenAdapter chapTruyenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");
        //tạo dữ liệu ảo
        arrChap = new ArrayList<>();
        for (int i = 1; i<20; i++) {
            arrChap.add(new ChapTruyen("Chapter" + i , "Ngày đăng: 03-30-2022"));
        }
        chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
    };
    private void anhXa(){
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    };
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);
        lsvDanhSachChap.setAdapter(chapTruyenAdapter);
    };
    private void setClick(){};
}