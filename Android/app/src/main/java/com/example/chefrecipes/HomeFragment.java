package com.example.chefrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private String URLstring = "http://10.0.2.2:8000/dishes/getall";;
    public ListView lv;
    ArrayList<Dish> dishArrayList;
    private DishAdapter dishAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home, container, false);
        lv=view.findViewById(R.id.maaryam);
//        retrieveJSON();
        fillList();
        return view;
    }

    private void fillList(){
        dishArrayList = new ArrayList<>();
        Dish dish_item1 = new Dish("title1","2.6$","Ingredients one , two , three , four","https://www.expatica.com/app/uploads/sites/5/2020/03/Boeuf-bourguignon-1920x1080.jpg");
        Dish dish_item2 = new Dish("title2","2.6$","Ingredients one , two , three , four","https://www.expatica.com/app/uploads/sites/5/2020/03/Boeuf-bourguignon-1920x1080.jpg");
        Dish dish_item3 = new Dish("title3","2.6$","Ingredients one , two , three , four","https://eatforum.org/content/uploads/2018/05/table_with_food_top_view_900x700.jpg");
        dishArrayList.add(dish_item1);
        dishArrayList.add(dish_item2);
        dishArrayList.add(dish_item3);
        setupListview();

    }

    private void retrieveJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            dishArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("data");

                            for (int i = 0; i < dataArray.length(); i++) {

                                Dish dish_item = new Dish();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                dish_item.setTitle(dataobj.getString("dish_title"));
                                dish_item.setImageUrl(dataobj.getString("dish_image"));
                                dish_item.setPrice(dataobj.getString("dish_price"));
                                dish_item.setIngredients(dataobj.getString("dish_ingredients"));

                                dishArrayList.add(dish_item);

                            }

                            setupListview();

                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        dishAdapter = new DishAdapter(view.getContext(), dishArrayList);
        lv.setAdapter(dishAdapter);
    }

}
