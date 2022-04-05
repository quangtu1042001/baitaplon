package com.example.baitaplondidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baitaplondidong.adapter.ChapTruyenAdapter;
import com.example.baitaplondidong.api.ApiChapTruyen;
import com.example.baitaplondidong.api.ApiDanhDauChap;
import com.example.baitaplondidong.interfaces.LayChapVe;
import com.example.baitaplondidong.object.ChapTruyen;
import com.example.baitaplondidong.object.TruyenTranh;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {
Button btnThemOrXoaThuvien, btnDocTruyen;
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
        setLongClick();
        new ApiChapTruyen(this, truyenTranh.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");
        //tạo dữ liệu ảo

        arrChap = new ArrayList<>();

    };
    private void anhXa(){
        btnDocTruyen = findViewById(R.id.btnDocTruyen);
        btnThemOrXoaThuvien = findViewById(R.id.btnThemOrXoaThuvien);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    };
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenTruyen());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);
        //lsvDanhSachChap.setAdapter(chapTruyenAdapter);
    };
    private void setClick(){
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("tenChap", arrChap.get(i).getTenChap());
                b.putString("idTruyen",arrChap.get(i).getIdTruyen());
                Intent intent = new Intent(ChapActivity.this, DocTruyenActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
        btnThemOrXoaThuvien.setOnClickListener(new View.OnClickListener() {
            OkHttpClient client = new OkHttpClient();
            @Override
            public void onClick(View view) {
                if (btnThemOrXoaThuvien.getText().equals("♡ Thêm vào thư viện")) {
                    try {
                        Request request = new Request.Builder().url("http://datalaptrinhungdungdidong.000webhostapp.com/addThuVien.php?id="
                                + truyenTranh.getId() +"&isMark=1").build();
                        Response response = client.newCall(request).execute();
                        //ResponseBody body = response.body();
                        System.out.print(truyenTranh.getId());
                        Toast.makeText(getApplicationContext(),"Đã thêm vào thư viện", Toast.LENGTH_SHORT).show();
                        btnThemOrXoaThuvien.setText("\uD83E\uDD0D Xóa khỏi thư viện");
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try {
                        Request request = new Request.Builder().url("http://datalaptrinhungdungdidong.000webhostapp.com/addThuVien.php?id="
                                + truyenTranh.getId() +"&isMark=0").build();
                        Response response = client.newCall(request).execute();
                        //ResponseBody body = response.body();
                        Toast.makeText(getApplicationContext(),"Đã xóa khỏi thư viện", Toast.LENGTH_SHORT).show();
                        btnThemOrXoaThuvien.setText("♡ Thêm vào thư viện");
                    }
                    catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    };
    private void setLongClick(){
        lsvDanhSachChap.setLongClickable(true);
        lsvDanhSachChap.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast.makeText(ChapActivity.this, body.string(), Toast.LENGTH_SHORT).show();
//                updateChap();
                new ApiDanhDauChap(arrChap,i,ChapActivity.this).execute();
                return true;
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Đang lấy chap về",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            arrChap.clear();
            JSONArray array = new JSONArray(data);
            for (int i=0; i<array.length(); i++) {
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this, 0, arrChap);
            lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Lỗi khi tải",Toast.LENGTH_SHORT).show();
    }
    public void updateChap(){
        new ApiChapTruyen(this, truyenTranh.getId()).execute();
    }
}