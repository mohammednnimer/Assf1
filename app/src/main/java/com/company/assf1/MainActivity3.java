package com.company.assf1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {


    private static TextView nameText;
   private static TextView priceText ;
    private static ImageView imageView ;
    private static NumberPicker numberPicker;


    static final String bayBroduct = "BayBroduct";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         nameText = findViewById(R.id.name);
         priceText = findViewById(R.id.price);
         imageView = findViewById(R.id.imageView);
         numberPicker = findViewById(R.id.number);

        Button bay=findViewById(R.id.bay);






        numberPicker.setMinValue(1);

        numberPicker.setWrapSelectorWheel(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        double price = intent.getDoubleExtra("Price", 0.0);
        String img = intent.getStringExtra("Img");
        String id= intent.getStringExtra("ID");



        bay.setText("Bay: $" + (price));

        int count=intent.getIntExtra("count",0);
        numberPicker.setMaxValue(count);


        nameText.setText(name);
        priceText.setText("$" + price);





       String imageName = img.replace(".png", "");
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        imageView.setImageResource(imageResId);



        numberPicker.setOnValueChangedListener((picker, old, newv) -> {
            bay.setText("Bay: $" + (newv * price));
        });





        bay.setOnClickListener(e->
        {



            MainActivity2 main=new MainActivity2();
                Product pro=new Product();
                pro.setName(name);
                pro.setImageUrl(img);
                pro.setPrice(price);
                bayProduct Pay=new bayProduct(pro,numberPicker.getValue());
                main.payProducts.add(Pay);




                Gson gson1 = new Gson();
                String json2 = gson1.toJson(main.payProducts);
                SharedPreferences pref = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor edito = pref.edit();
                edito.putString(bayBroduct, json2);
                edito.apply();






















            SharedPreferences prefs = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
            String json = prefs.getString(MainActivity.KEY_PRODUCTS, null);


             main.productList = new ArrayList<>();

            if (json != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Product>>() {}.getType();
                main.productList = gson.fromJson(json, type);

                for(int i=0;i<main.productList.size();i++)
                {
                    if(main.productList.get(i).getId().equals(id))
                    {

                        Log.d("TAG", "olddd: "+main.productList.get(i).getQuantity());


                        main.productList.get(i).setQuantity(main.productList.get(i).getQuantity()-numberPicker.getValue());

                        Log.d("TAG", "neewww: "+main.productList.get(i).getQuantity());

                        if(main.productList.get(i).getQuantity()==0)
                        {
                            main.productList.get(i).setAvailable(false);
                        }
                        break;

                    }


                }

                String json1 = gson.toJson(main.productList);

         prefs = getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(MainActivity.KEY_PRODUCTS, json1);
        editor.apply();
                Intent intent1 = new Intent(MainActivity3.this, MainActivity2.class);
                MainActivity3.this.startActivity(intent1);

            }else{


            }












        });














    }
}