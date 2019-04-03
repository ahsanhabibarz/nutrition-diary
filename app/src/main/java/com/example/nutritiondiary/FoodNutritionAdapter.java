package com.example.nutritiondiary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodNutritionAdapter extends RecyclerView.Adapter<FoodNutritionAdapter.ViewHolder> {

    public List<MealList> foodNutritionLists;

    public FoodNutritionAdapter(List<MealList> foodNutritionLists) {
        this.foodNutritionLists = foodNutritionLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        //Toast.makeText(holder.mView.getContext(), String.valueOf(foodNutritionLists.size()), Toast.LENGTH_SHORT).show();

        final String Childid = foodNutritionLists.get(position).PostID2;

        final String Parentid = foodNutritionLists.get(position).PostID3;

        final int expos = position;


        holder.name.setText(foodNutritionLists.get(position).getName());
//        holder.carbs.setText(foodNutritionLists.get(position).getCarbs());
        holder.calories.setText(foodNutritionLists.get(position).getCalories()+" kcal");
//        holder.fat.setText(foodNutritionLists.get(position).getFat());
//        holder.pro.setText(foodNutritionLists.get(position).getProtein());
        holder.quantity.setText(String.valueOf(foodNutritionLists.get(position).getQuantity()));
        holder.unit.setText(foodNutritionLists.get(position).getUnit());

        if((foodNutritionLists.get(position).getWeight())<= 0){

            holder.weight.setVisibility(View.GONE);
            holder.gramweight.setVisibility(View.GONE);

        }else{

            holder.weight.setText(String.valueOf(foodNutritionLists.get(position).getWeight())+"g");

        }


        holder.addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.mView.getContext(), EditFoodActivity.class);
                intent.putExtra("childid", Childid);
                intent.putExtra("parentid", Parentid);

                intent.putExtra("name", foodNutritionLists.get(expos).getName());
                intent.putExtra("calories", foodNutritionLists.get(expos).getCalories());
                intent.putExtra("carbs", foodNutritionLists.get(expos).getCarbs());
                intent.putExtra("fat", foodNutritionLists.get(expos).getFat());
                intent.putExtra("protein", foodNutritionLists.get(expos).getProtein());
                intent.putExtra("fiber", foodNutritionLists.get(expos).getFiber());
                intent.putExtra("quantity", foodNutritionLists.get(expos).getQuantity());
                intent.putExtra("suger", foodNutritionLists.get(expos).getSuger());
                intent.putExtra("unit", foodNutritionLists.get(expos).getUnit());
                intent.putExtra("weight", foodNutritionLists.get(expos).getWeight());


                holder.mView.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return foodNutritionLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView name;
        TextView calories;
        TextView carbs;
        TextView fat;
        TextView pro;
        TextView quantity;
        TextView unit;
        ImageView gramweight;
        TextView weight;
        ImageView addMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            name = mView.findViewById(R.id.fmname);
            calories = mView.findViewById(R.id.fmcalories);
            gramweight = mView.findViewById(R.id.fmgramweight);

            addMeal = (ImageView)mView.findViewById(R.id.addMealfmdl);

//            carbs = (TextView)mView.findViewById(R.id.nfcarbs);
//            fat = (TextView)mView.findViewById(R.id.nffat);
//            pro = (TextView)mView.findViewById(R.id.nfprotein);
            quantity = mView.findViewById(R.id.fmquantity);
            unit = mView.findViewById(R.id.fmunit);
            weight = mView.findViewById(R.id.fmweight);

        }
    }
}
