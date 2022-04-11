package com.example.baitaplondidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;


import com.example.baitaplondidong.adapter.TruyenTranhAdapter;
import com.example.baitaplondidong.api.ApiLaytruyen;
//import com.example.baitaplondidong.fragment.FavoriteFragmet;
import com.example.baitaplondidong.fragment.FavoriteFragmet;
import com.example.baitaplondidong.fragment.HomeFragment;
import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.example.baitaplondidong.object.TruyenTranh;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;
    EditText edtTimKiem;

    private DrawerLayout mDrawerLayour;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayour = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayour, toolbar, R.string.nav_open, R.string.nav_close);
        mDrawerLayour.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home) {
                    Toast.makeText(MainActivity.this, "Đây là trang chủ", Toast.LENGTH_SHORT).show();
                    layTruyen();

                } else if (id == R.id.favorite_nav) {
                    Toast.makeText(MainActivity.this, "Đây là thư viện", Toast.LENGTH_SHORT).show();
                    layThuVien();
                }
                mDrawerLayour.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        init();
        anhXa();
        setUp();
        setClik();
    }


    private void init() {
        truyenTranhArrayList = new ArrayList<>();

        adapter = new TruyenTranhAdapter(this, 0, truyenTranhArrayList);
    }

    private void anhXa() {
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
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
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
            }
        });

        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTimKiem.getText().toString();
//                adapter.sortTruyen(s);
                s = s.toUpperCase();
                int k = 0;
                for (int i = 0; i < truyenTranhArrayList.size(); i++) {
                    TruyenTranh t = truyenTranhArrayList.get(i);
                    String ten = t.getTenTruyen().toUpperCase();
                    if (ten.indexOf(s) >= 0) {
                        truyenTranhArrayList.set(i, truyenTranhArrayList.get(k));
                        truyenTranhArrayList.set(k, t);
                        k++;
                    }
                }
                adapter = new TruyenTranhAdapter(MainActivity.this, 0, truyenTranhArrayList);
                adapter.notifyDataSetChanged();
                gdvDSTruyen.setAdapter(adapter);


            }

        });
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Dang lay ve", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "Loi ket noi", Toast.LENGTH_SHORT).show();
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

    private void layTruyen(){
        new ApiLaytruyen(this).execute();
    }

    private void layThuVien() {
        new ApiLaytruyen(this, true).execute();
    }
}