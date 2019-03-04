package com.example.nutritiondiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodNutritionAdapter extends RecyclerView.Adapter<FoodNutritionAdapter.ViewHolder> {

    public List<FoodNutritionList> foodNutritionLists;

    public FoodNutritionAdapter(List<FoodNutritionList> foodNutritionLists) {
        this.foodNutritionLists = foodNutritionLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nutrition_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        //Toast.makeText(holder.mView.getContext(), String.valueOf(foodNutritionLists.size()), Toast.LENGTH_SHORT).show();


        holder.name.setText(foodNutritionLists.get(position).getName());
        holder.carbs.setText(foodNutritionLists.get(position).getCarbs());
        holder.calories.setText(foodNutritionLists.get(position).getCalories());
        holder.fat.setText(foodNutritionLists.get(position).getFat());
        holder.pro.setText(foodNutritionLists.get(position).getProtein());
        holder.quantity.setText(foodNutritionLists.get(position).getQuantity());
        holder.unit.setText(foodNutritionLists.get(position).getMeasurementunit());
        holder.weight.setText(foodNutritionLists.get(position).getWeight());


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
        TextView weight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;

            name = (TextView)mView.findViewById(R.id.nfname);
            calories = (TextView)mView.findViewById(R.id.nfcalories);
            carbs = (TextView)mView.findViewById(R.id.nfcarbs);
            fat = (TextView)mView.findViewById(R.id.nffat);
            pro = (TextView)mView.findViewById(R.id.nfprotein);
            quantity = (TextView)mView.findViewById(R.id.nfquantity);
            unit = (TextView)mView.findViewById(R.id.nfunit);
            weight = (TextView)mView.findViewById(R.id.nfweight);

        }
    }
}
