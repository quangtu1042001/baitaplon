package com.example.baitaplondidong.api;

import android.os.AsyncTask;

import com.example.baitaplondidong.ChapActivity;
import com.example.baitaplondidong.object.ChapTruyen;
import com.example.baitaplondidong.object.TruyenTranh;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

public class ApiThemVaoThuVien extends AsyncTask<Void,Void,Void> {
    TruyenTranh truyenTranh;
    String data;
    ChapActivity chapActivity;
    public ApiThemVaoThuVien(TruyenTranh truyenTranh, ChapActivity chapActivity) {
        this.truyenTranh = truyenTranh;
        this.chapActivity = chapActivity;
    }
    /**
     * @param voids
     * @deprecated
     */
    @Override
    protected Void doInBackground(Void... voids) {
        //OkHttpClient client = new OkHttpClient();
        OkHttpClient client = new OkHttpClient();
        Request request;
        if(truyenTranh.getIsMark().equals("1"))
        {
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/addThuVien.php?id="+truyenTranh.getId()+"&isMark=0")
                    .build();
        }
        else {
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/addThuVien.php?id="+truyenTranh.getId()+"&isMark=1")
                    .build();
        }
        data = null;
        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data == null)
        {
            this.chapActivity.biLoi();
        }
        else{
            this.chapActivity.updateChap();
        }
    }
}
