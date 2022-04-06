package com.example.baitaplondidong.api;

import android.os.AsyncTask;

import com.example.baitaplondidong.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLaytruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayTruyenVe layTruyenVe;
    boolean isThuVien = false;

    public ApiLaytruyen(LayTruyenVe layTruyenVe) {
        this.layTruyenVe = layTruyenVe;
        this.layTruyenVe.batDau();
    }

    public ApiLaytruyen(LayTruyenVe layTruyenVe, boolean isThuVien) {
        this.layTruyenVe = layTruyenVe;
        this.isThuVien = isThuVien;
        this.layTruyenVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request;

        if(isThuVien)
        {
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/layThuVien.php")
                    .build();
        }
        else{
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/layTruyen.php")
                    .build();
        }
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if(data == null){
            this.layTruyenVe.biLoi();
        }
        else{
            this.layTruyenVe.ketThuc(data);
        }
    }
}
