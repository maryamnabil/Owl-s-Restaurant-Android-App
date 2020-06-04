package com.example.chefrecipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Dish> dishesArrayList;

    // Constructor
    public DishAdapter(Context context, ArrayList<Dish> dishesArrayList) {

        this.context = context;
        this.dishesArrayList = dishesArrayList;
    }
    @Override
    public int getCount() {
        return dishesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dishesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dish, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.dish_image);
            holder.tvtitle = (TextView) convertView.findViewById(R.id.dish_title);
            holder.tvingredients = (TextView) convertView.findViewById(R.id.dish_ingredients);
            holder.tvprice = (TextView) convertView.findViewById(R.id.dish_price);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        Picasso.get().load(dishesArrayList.get(position).getImageUrl()).into(holder.iv);
        holder.tvprice.setText(dishesArrayList.get(position).getPrice());
        holder.tvtitle.setText(dishesArrayList.get(position).getTitle());
        holder.tvingredients.setText(dishesArrayList.get(position).getIngredients());
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvtitle, tvingredients, tvprice;
        protected ImageView iv;
    }
}
