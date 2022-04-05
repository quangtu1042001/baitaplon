package com.example.baitaplondidong.api;

import android.os.AsyncTask;

import com.example.baitaplondidong.interfaces.LayAnhVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiLayAnh extends AsyncTask<Void,Void,Void> {
    String data, tenChap, idTruyen;
    LayAnhVe LayAnhVe;

    public ApiLayAnh(LayAnhVe LayAnhVe, String tenChap, String idTruyen) {
        this.LayAnhVe = LayAnhVe;
        this.tenChap = tenChap;
        this.idTruyen = idTruyen;
        this.LayAnhVe.batDau();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(30, TimeUnit.SECONDS);    // socket timeout
        Request request = new Request.Builder()
                .url("http://datalaptrinhungdungdidong.000webhostapp.com/testLayAnh.php?tenChap="+ tenChap+"&idTruyen="+idTruyen)
                .build();
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
            this.LayAnhVe.biLoi();
        }
        else{
            this.LayAnhVe.ketThuc(data);
        }
    }
}
