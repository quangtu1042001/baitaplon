package com.example.baitaplondidong.api;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.baitaplondidong.ChapActivity;
import com.example.baitaplondidong.object.ChapTruyen;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

public class ApiDanhDauChap extends AsyncTask<Void,Void,Void> {
    ArrayList<ChapTruyen> arrChap;
    int i;
    String data;
    ChapActivity chapActivity;

    public ApiDanhDauChap(ArrayList<ChapTruyen> arrChap, int i, ChapActivity chapActivity) {
        this.arrChap = arrChap;
        this.i = i;
        this.chapActivity = chapActivity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request;
        if(arrChap.get(i).getIsMark().equals("1"))
        {
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/danhDauChap.php?id="+arrChap.get(i).getId()+"&isMark=0")
                    .build();
        }
        else {
            request = new Request.Builder()
                    .url("http://datalaptrinhungdungdidong.000webhostapp.com/danhDauChap.php?id="+arrChap.get(i).getId()+"&isMark=1")
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
