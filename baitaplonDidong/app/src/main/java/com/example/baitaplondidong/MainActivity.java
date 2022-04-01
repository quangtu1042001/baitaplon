package com.example.baitaplondidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
    private void setClik(){
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }

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

    public void update(View view) {
        new ApiLaytruyen(this).execute();

    }
}