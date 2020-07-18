package com.luongthuan.lab3.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.luongthuan.lab3.Example1.Example;
import com.luongthuan.lab3.Model.Root;
import com.luongthuan.lab3.Adapter.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetLoader extends AsyncTask<String, Integer, String> {
    List<Root> rootList=new ArrayList<>();
    RecyclerView rvContent;
    Context context;
    MyAdapter myAdapter;
    Example example;
    Root root;

    public GetLoader(RecyclerView rvContent, Context context) {
        this.rvContent = rvContent;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String data = "";
        try {
            String link=" http://dotplays.com/wp-json/wp/v2/search?search="+ URLEncoder.encode(strings[0],"UTF-8")+ "&_embed";
            URL url = new URL(link);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) {
                data += sc.nextLine();
            }
            sc.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray array = new JSONArray(s);
            Gson gson=new Gson();
            for (int i = 0; i < array.length(); i++) {

                example=gson.fromJson(String.valueOf(array.get(i)),Example.class);
//                JSONObject jsonObject = array.getJSONObject(i);
//                String title = jsonObject.getString("title");
//                String url = jsonObject.getString("url");
                root = new Root();
                root.setTitle(example.getTitle());
                root.setUrl(example.getUrl());
                root.setDate(example.getLinks().getSelf().get(0).getHref());
                root.setImage(example.getLinks().getSelf().get(0).getHref());
                rootList.add(root);
                Log.e("Count",root+"");

            }

            GetLoader2 getLoader2=new GetLoader2(rootList,context);
            getLoader2.execute(rootList);
            myAdapter=new MyAdapter(rootList,context);
            rvContent.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvContent.setLayoutManager(linearLayoutManager);
            rvContent.setAdapter(myAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
