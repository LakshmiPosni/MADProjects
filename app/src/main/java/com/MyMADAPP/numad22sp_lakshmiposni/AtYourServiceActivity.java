package com.MyMADAPP.numad22sp_lakshmiposni;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.MyMADAPP.numad22sp_lakshmiposni.databinding.ActivityAtYourServiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AtYourServiceActivity extends AppCompatActivity {

    ActivityAtYourServiceBinding binding;
    ArrayList<String> animeList;
    ArrayAdapter<String> listAdapter;
    Handler mainHandler = new Handler();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAtYourServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AnimeList();
        binding.loadDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new loadData().start();

            }

        });

    }

    private void AnimeList() {
        animeList = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animeList);
        binding.animeList.setAdapter(listAdapter);

    }

    class loadData extends Thread{

        String holdData = "";



        @Override
        public void run(){

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(AtYourServiceActivity.this);
                    progressDialog.setMessage("Loading Data...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                }
            });

            try {
                URL url = new URL("https://api.jikan.moe/v3/search/anime?q=naruto");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String eachLine;

                while((eachLine = bufferedReader.readLine()) != null){
                    holdData = holdData + eachLine;
                }

                if(!holdData.isEmpty()){
                    JSONObject jsonObject = new JSONObject(holdData);
                    JSONArray users = jsonObject.getJSONArray("results");
                    animeList.clear();
                    for(int i = 0; i<users.length(); i++){
                        JSONObject names = users.getJSONObject(i);
                        String name = names.getString("title");
                        animeList.add(name);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    listAdapter.notifyDataSetChanged();

                }
            });

        }
    }
}