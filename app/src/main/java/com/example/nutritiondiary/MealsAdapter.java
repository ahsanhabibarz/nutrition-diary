package com.example.nutritiondiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {

    public List<MealList> mealLists;

    public MealsAdapter(List<MealList> mealLists) {
        this.mealLists = mealLists;
    }

    @NonNull
    @Override
    public MealsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsAdapter.ViewHolder holder, int position) {

        holder.calories.setText(String.valueOf(mealLists.get(position).getCalories()));
        holder.name.setText(mealLists.get(position).getName());
        holder.fat.setText(String.valueOf(mealLists.get(position).getFat()));
        holder.carbs.setText(String.valueOf(mealLists.get(position).getCarbs()));
        holder.pro.setText(String.valueOf(mealLists.get(position).getProtein()));
        RequestOptions placeholderReq = new RequestOptions();
        placeholderReq.placeholder(R.drawable.appbar_shape);



        Glide.with(holder.mView.getContext()).setDefaultRequestOptions(placeholderReq).load(mealLists.get(position).getImage()).into(holder.image);

        int hour = mealLists.get(position).getTime();

        if(hour >= 0  && hour <= 4  ){

            holder.time.setText(String.valueOf(mealLists.get(position).getTime())+" AM");
            holder.mealtime.setText("Late Night Meal");

        } else if(hour > 4  && hour < 12  ){

            holder.time.setText(String.valueOf(mealLists.get(position).getTime())+" AM");
            holder.mealtime.setText("Breakfast");

        } else if(hour >= 12  && hour <= 4  ){

            holder.time.setText(String.valueOf(mealLists.get(position).getTime())+" PM");
            holder.mealtime.setText("Lunch");

        } else if(hour > 4  && hour <= 6  ){

            holder.time.setText(String.valueOf(mealLists.get(position).getTime())+" PM");
            holder.mealtime.setText("Evening Meal");

        } else {

            holder.time.setText(String.valueOf(mealLists.get(position).getTime())+" PM");
            holder.mealtime.setText("Dinner");

        }



    }

    @Override
    public int getItemCount() {
        return mealLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView calories,fat,carbs,pro,name,time,mealtime;

        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            calories = (TextView)mView.findViewById(R.id.caloriesblg);
            name = (TextView)mView.findViewById(R.id.foodname);
            image = (ImageView) mView.findViewById(R.id.mealimageblg);
            fat = (TextView)mView.findViewById(R.id.fatblg);
            carbs = (TextView)mView.findViewById(R.id.carbsblg);
            pro = (TextView)mView.findViewById(R.id.proteinblg);
            time = (TextView)mView.findViewById(R.id.timeblg);

            mealtime = (TextView)mView.findViewById(R.id.mealtime);
        }
    }
}
