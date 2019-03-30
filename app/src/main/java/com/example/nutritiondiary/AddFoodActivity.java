package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {

    Toolbar toolbar;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    private List<FoodNutritionList> foodNutritionLists;

    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    ImageView foodloading;

    EditText searchBox;

    String url,url2;

    TextView foodResults;

    String Childid,Parentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        toolbar = (Toolbar)findViewById(R.id.autotool);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            Childid = extras.getString("childid");
            Parentid = extras.getString("parentid");
        }

        recyclerView = (RecyclerView)findViewById(R.id.foodLists) ;

        foodNutritionLists = new ArrayList<>();

        searchBox = (EditText)findViewById(R.id.searchBox);

        foodResults = (TextView)findViewById(R.id.foodresults);

        foodloading = (ImageView)findViewById(R.id.foodloading);

        url = "https://api.nutritionix.com/v1_1/search/";

        url2 = "?results=0:20&fields=item_name,nf_serving_weight_grams,item_id,nf_calories,nf_protein,nf_total_fat,nf_total_carbohydrate,nf_dietary_fiber,nf_serving_weight_grams,nf_serving_size_qty,nf_serving_size_unit&appId=64a235a8&appKey=623f50e1ab950a1e19a9b331df52d920";

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
          adapter = new FoodNutritionAdapter(foodNutritionLists);
          adapter.notifyDataSetChanged();



          searchBox.setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View view, int i, KeyEvent keyEvent) {

                  if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){


                      if(searchBox.getText().toString().isEmpty()){

                          foodNutritionLists.clear();
                          adapter.notifyDataSetChanged();
                          foodResults.setVisibility(View.VISIBLE);
                          foodResults.setText("No results");

                      }else{

                          foodloading.setVisibility(View.VISIBLE);
                          Glide.with(AddFoodActivity.this).load(R.drawable.loading).into(foodloading);
                          foodNutritionLists.clear();
                          adapter.notifyDataSetChanged();
                          loadPost(url+searchBox.getText().toString()+url2);

                      }


                  }


                  return false;
              }
          });



    }


    public void loadPost(String url){


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject Jasonobject = new JSONObject(response);

                            JSONArray array = Jasonobject.getJSONArray("hits");


                            int length = array.length();

                            if(length <=0){

                                foodResults.setText("No results");

                                foodloading.setVisibility(View.GONE);

                            }else{

                                foodloading.setVisibility(View.GONE);

                                foodResults.setVisibility(View.GONE);

                                recyclerView.setVisibility(View.VISIBLE);
                            }

                            for(int i=0;i<length;i++){

                                String name = array.getJSONObject(i).getJSONObject("fields").getString("item_name");

                                String weight = array.getJSONObject(i).getJSONObject("fields").getString("nf_serving_weight_grams");

                                String quantity = array.getJSONObject(i).getJSONObject("fields").getString("nf_serving_size_qty");

                                String calories = array.getJSONObject(i).getJSONObject("fields").getString("nf_calories");

                                String fat = array.getJSONObject(i).getJSONObject("fields").getString("nf_total_fat");

                                String carbs = array.getJSONObject(i).getJSONObject("fields").getString("nf_total_carbohydrate");

                                String protein = array.getJSONObject(i).getJSONObject("fields").getString("nf_protein");

                                String measurement = array.getJSONObject(i).getJSONObject("fields").getString("nf_serving_size_unit");


                                FoodNutritionList foodNutritionList = new FoodNutritionList(name,weight,quantity,calories,fat,carbs,protein,measurement).withID("",Childid,Parentid);


                                foodNutritionLists.add(foodNutritionList);

                            }


                            adapter = new FoodNutritionAdapter(foodNutritionLists);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {

                            Toast.makeText(AddFoodActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddFoodActivity.this, "Connection Eror", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);


    }
}
