package com.example.nutritiondiary;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CareGiverParentsAdapter extends RecyclerView.Adapter<CareGiverParentsAdapter.ViewHolder> {

    public List<ParentsList> parentsLists;

    public CareGiverParentsAdapter(List<ParentsList> parentsLists) {
        this.parentsLists = parentsLists;
    }

    @NonNull
    @Override
    public CareGiverParentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_model,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CareGiverParentsAdapter.ViewHolder holder, final int position) {

        holder.name.setText(parentsLists.get(position).getName());

        holder.phone.setText(parentsLists.get(position).getPhone());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.mView.getContext(), CareGiverActivity.class);
                intent.putExtra("parentid", parentsLists.get(position).getUid());
                holder.mView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return parentsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView name;
        TextView phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;


            name = (TextView) mView.findViewById(R.id.parentName);
            phone = (TextView) mView.findViewById(R.id.parentsPhone);


        }
    }
}
