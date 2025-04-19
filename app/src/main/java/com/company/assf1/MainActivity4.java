package com.company.assf1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        MainActivity2 main=new MainActivity2();



        SharedPreferences prefs = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(MainActivity3.bayBroduct, null);

        main.payProducts = new ArrayList<>();

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<bayProduct>>() {}.getType();


            main.payProducts = gson.fromJson(json, type);

        }else{

        }


        for(int i=0;i<main.payProducts.size();i++)
        {

            main.payProducts.get(i).product.setPrice(main.payProducts.get(i).quntite);


            main.payProducts.get(i).product.ispay=true;
        }

        ArrayList<Product> newn=new ArrayList<>();
        for(int i=0;i<main.payProducts.size();i++)
        {
          newn.add(main.payProducts.get(i).product);
        }

        RecyclerView recyclerView = findViewById(R.id.product_recycler);
        ProductAdapter adapter = new ProductAdapter(newn, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));






























    }
}