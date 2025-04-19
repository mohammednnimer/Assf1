package com.company.assf1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    static ArrayList<bayProduct> payProducts=new ArrayList<>();

    static  List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        SharedPreferences  prefs = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(MainActivity.KEY_PRODUCTS, null);

        productList = new ArrayList<>();

        if (json != null) {
            Gson  gson = new Gson();
            Type type = new TypeToken<List<Product>>() {}.getType();
            productList = gson.fromJson(json, type);

        }else{

        }


        for(int i=0;i<productList.size();i++)
        {

            if(!productList.get(i).isAvailable())
            {
                productList.remove(i);
                i--;

            }


        }

        RecyclerView recyclerView = findViewById(R.id.product_recycler);
        ProductAdapter adapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        FloatingActionButton flb=findViewById(R.id.shope);


        flb.setOnClickListener(er->
        {


            Intent intent1 = new Intent(MainActivity2.this, MainActivity4.class);
            MainActivity2.this.startActivity(intent1);



        });





        SearchView searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;  }





            @Override
            public boolean onQueryTextChange(String newText) {

                productList = new ArrayList<>();
                String json = prefs.getString(MainActivity.KEY_PRODUCTS, null);

                if (json != null) {
                    Gson  gson = new Gson();
                    Type type = new TypeToken<List<Product>>() {}.getType();
                    productList = gson.fromJson(json, type);


                }else{


                }
                String se = searchView.getQuery().toString();

                if(se=="")
                {
                    Log.d("TAG", "onQueryTextSubmit: mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
                }
                for(int i=0;i<productList.size();i++)
                {


                    Log.d("TAG", productList.get(i).getName()+": "+se);
                    Log.d("TAG", productList.get(i).getName().toString().toLowerCase().contains(se.toLowerCase()) + "");


                    if(!productList.get(i).getName().toString().toLowerCase().contains(se.toLowerCase()))
                    {
                        productList.remove(i);
                        i--;
                    }

                }


                RecyclerView recyclerView = findViewById(R.id.product_recycler);
                ProductAdapter adapter = new ProductAdapter(productList, MainActivity2.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));


                return false;
            }

        });










    }


}










