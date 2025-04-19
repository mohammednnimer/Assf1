package com.company.assf1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Animation top, bottom;
    private TextView txt;
    private ImageView img;

    static final String PREF_NAME = "myPrefs";
    static final String KEY_PRODUCTS = "productList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txt);
        img = findViewById(R.id.image);

        bottom = AnimationUtils.loadAnimation(this, R.anim.botton);
        top = AnimationUtils.loadAnimation(this, R.anim.top);
        txt.startAnimation(bottom);
        img.startAnimation(top);

        SharedPreferences  pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        if(!pref.contains(KEY_PRODUCTS)) {

            List<Product> productList = new ArrayList<>();

            String[] names = {
                    "Casual T-Shirt", "Denim Jeans", "Hoodie Sweatshirt", "Leather Jacket",
                    "Summer Dress", "Formal Shirt", "Sport Shorts", "Winter Coat",
                    "Long Skirt", "Classic Blazer"};

            for (int i = 1; i <= 10; i++) {
                Product product = new Product(
                        "id" + i,
                        names[i - 1],
                        10.0 * i,
                        10 * i,
                        "p" + i + ".png",
                        true,
                        i % 2 == 0
                );
                productList.add(product);
            }

            Gson gson = new Gson();
            String json = gson.toJson(productList);
            SharedPreferences prefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_PRODUCTS, json);
            editor.apply();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        }, 4000); // 4 ثواني
    }
}
