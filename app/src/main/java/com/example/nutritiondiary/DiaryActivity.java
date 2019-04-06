package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

public class DiaryActivity extends AppCompatActivity {

    Toolbar toolbar;

    ImageView addFoods;

    String Childid,Parentid;

    FirebaseFirestore firebaseFirestore;


    List<MealList> mealLists;

    RecyclerView recyclerView;

    private FirebaseAuth mAuth;

    MealsAdapter mealsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        Bundle extras = getIntent().getExtras();
        if(extras != null){

            Childid = extras.getString("childid");
            Parentid = extras.getString("parentid");
        }

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addFoods = (ImageView)findViewById(R.id.addmeals);

        addFoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddFoodActivity.class);
                intent.putExtra("childid", Childid);
                intent.putExtra("parentid", Parentid);

                startActivity(intent);

            }
        });

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.mealsList);


        mealLists = new ArrayList<>();


        mealsAdapter = new MealsAdapter(mealLists);

        mAuth = FirebaseAuth.getInstance();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH);

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //int hour = calendar.get(Calendar.HOUR_OF_DAY);


        String s = String.valueOf(year)+String.valueOf(month)+String.valueOf(day+27);


        Query fireQuery = firebaseFirestore.collection("parents").document(Parentid).collection("Childs")
                .document(Childid).collection("Diary").document("2019330").collection("Meals");

        fireQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){}


                for(DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()){

                    if(documentChange.getType() == DocumentChange.Type.ADDED ){



                        String Diaryid = documentChange.getDocument().getId();

                        MealList mealList = documentChange.getDocument().toObject(MealList.class).withID(Diaryid,Childid,Parentid);

                        mealLists.add(mealList);

                        mealsAdapter.notifyDataSetChanged();


                    }

                }

            }
        });


        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mealsAdapter);


    }
}
