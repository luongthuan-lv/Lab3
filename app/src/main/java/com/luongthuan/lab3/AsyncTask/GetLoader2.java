package com.luongthuan.lab3.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.luongthuan.lab3.Adapter.MyAdapter;
import com.luongthuan.lab3.Example1.Example;
import com.luongthuan.lab3.Example2.Example2;
import com.luongthuan.lab3.Model.Root;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetLoader2 extends AsyncTask<List<Root>,Integer,List<Root>> {
    List<Root> rootList;
    Context context;
    Root root;
    private Example2 example2;

    public GetLoader2(List<Root> rootList, Context context) {
        this.rootList = rootList;
        this.context = context;

    }

    @Override
    protected List<Root> doInBackground(List<Root>... lists) {
        for (int i = 0; i <lists[0].size() ; i++) {
            StringBuilder builder=new StringBuilder();
            root=lists[0].get(i);
            String link =root.getDate();

            try {
                URL url=new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                root.setDate(builder.toString());
                root.setImage(builder.toString());
                scanner.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rootList;
    }

    @Override
    protected void onPostExecute(List<Root> roots) {
        super.onPostExecute(roots);
        try {
            for (int i = 0; i <rootList.size() ; i++) {
                JSONObject jsonObject=new JSONObject(String.valueOf(roots.get(i).getDate()));
                Gson gson=new Gson();
                example2=gson.fromJson(String.valueOf(jsonObject),Example2.class);
                root=roots.get(i);
                root.setDate(example2.getDate());
                Log.e("hhhhhhhhhhhhhh",example2.getDate());
                root.setImage(example2.getLinks().getWpFeaturedmedia().get(0).getHref());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
