package com.example.baitaplondidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baitaplondidong.adapter.AnhTruyenAdapter;
import com.example.baitaplondidong.adapter.TruyenTranhAdapter;
import com.example.baitaplondidong.api.ApiLayAnh;
import com.example.baitaplondidong.interfaces.LayAnhVe;
import com.example.baitaplondidong.object.AnhTruyen;
import com.example.baitaplondidong.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe{
    ListView lvAnhTruyen;
    AnhTruyenAdapter anhTruyenAdapter;
    ArrayList<AnhTruyen> anhTruyenArrayList;
//    ImageView imgAnh;
//    ArrayList<String> arrUrlAnh;
//    int soTrang, soTrangDangDoc;
    String tenChap, idTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        new ApiLayAnh(this, tenChap,idTruyen).execute();
    }

    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        tenChap =  b.getString("tenChap");
        idTruyen = b.getString("idTruyen");
        anhTruyenArrayList = new ArrayList<>();
        anhTruyenAdapter = new AnhTruyenAdapter(this, 0, anhTruyenArrayList);

    }
    private void anhXa() {
        lvAnhTruyen = findViewById(R.id.lvAnhTruyen);
//        imgAnh = findViewById(R.id.lvAnhTruyen);
    }

    private void setUp() {
        lvAnhTruyen.setAdapter(anhTruyenAdapter);
//        docTheoTrang(0);
    }

    @Override
    public void batDau() {
        Toast.makeText(this,"Đang lấy dữ liệu",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            anhTruyenArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length();i++)
            {
                JSONObject o = arr.getJSONObject(i);
                anhTruyenArrayList.add(new AnhTruyen(o));
            }
            anhTruyenAdapter = new AnhTruyenAdapter(this,0,anhTruyenArrayList);
            lvAnhTruyen.setAdapter(anhTruyenAdapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi ket noi",Toast.LENGTH_SHORT).show();
    }
}