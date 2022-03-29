package com.example.baitaplondidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.example.baitaplondidong.adapter.TruyenTranhAdapter;
import com.example.baitaplondidong.api.ApiLaytruyen;
import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.example.baitaplondidong.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLaytruyen(this).execute();
    }
    private void init(){
        truyenTranhArrayList = new ArrayList<>();

        adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    }
    private void anhXa(){ gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
    }
    private void setUp(){gdvDSTruyen.setAdapter(adapter) ;}
    private void setClik(){}

    @Override
    public void batDau() {
        Toast.makeText(this,"Dang lay ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length();i++)
            {
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi ket noi",Toast.LENGTH_SHORT).show();
    }
}