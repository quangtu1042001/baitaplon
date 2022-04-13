package com.example.baitaplondidong;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.baitaplondidong.adapter.TruyenTranhAdapter;
import com.example.baitaplondidong.api.ApiLaytruyen;
import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.example.baitaplondidong.object.TruyenTranh;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThuVienActivity extends AppCompatActivity implements LayTruyenVe {

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;

    private DrawerLayout mDrawerLayour;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuvien);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        mDrawerLayour = findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayour, toolbar, R.string.nav_open, R.string.nav_close);
        mDrawerLayour.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Intent intent = new Intent(ThuVienActivity.this, MainActivity.class);
                    startActivity(intent);

                } else if (id == R.id.favorite_nav) {
                    Toast.makeText(ThuVienActivity.this, "Đây là thư viện rồi !", Toast.LENGTH_SHORT).show();
                    layThuVien();
                }
                else
                {
                    Intent intent = new Intent(ThuVienActivity.this, GioiThieuActivity.class);
                    startActivity(intent);
                }
                mDrawerLayour.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        navigationView.getMenu().getItem(1).setChecked(true);


        init();
        anhXa();
        setUp();
        setClik();
        layThuVien();
    }


    private void init() {
        truyenTranhArrayList = new ArrayList<>();

        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }

    private void anhXa() {
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen1);
    }

    private void setUp() {
        gdvDSTruyen.setAdapter(adapter);
    }

    private void setClik() {
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TruyenTranh truyenTranh = truyenTranhArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(ThuVienActivity.this, ChapActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Lấy tuyện", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this, "Lỗi kết nối !", Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        new ApiLaytruyen(this).execute();

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayour.isDrawerOpen(GravityCompat.START)){
            mDrawerLayour.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void layThuVien() {
        new ApiLaytruyen(this, true).execute();
    }
}