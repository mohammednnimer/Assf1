package com.company.assf1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private static String name="Name";

    private static String Prec="Price";

    private static String img="Img";


    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        if(product.ispay)
        {
            holder.priceTextView.setText("Number of purchases: " + product.getPrice());


        }else{
            holder.priceTextView.setText("$" + product.getPrice());
        }



        String imageName = product.getImageUrl().replace(".png", "");
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        holder.imageView.setImageResource(imageResId);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity3.class);
                intent.putExtra("ID",product.getId());
                intent.putExtra(name,product.getName());
                intent.putExtra(Prec,product.getPrice());
                intent.putExtra(img,product.getImageUrl());
                intent.putExtra("count",product.getQuantity());
                context.startActivity(intent);




           }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;

        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            nameTextView = itemView.findViewById(R.id.Name);
            priceTextView = itemView.findViewById(R.id.Price);
            card=itemView.findViewById(R.id.card_view);

        }
    }
}
