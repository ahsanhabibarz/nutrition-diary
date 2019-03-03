package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class CareGiverParentsActivity extends AppCompatActivity {

    Toolbar toolbar;

    private RecyclerView parentView;

    private RecyclerView.Adapter parAdapter;

    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;

    private long doubleBackToExitPressedOnce;

    String s;

    List<ParentsList> parentList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_parents);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parentView = (RecyclerView)findViewById(R.id.parentsRecy);

        parentView.setHasFixedSize(true);

        parentList = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);



        Query firestoreQuery = firebaseFirestore.collection("caregivers").document(mAuth.getCurrentUser().getUid()).collection("parents");

        firestoreQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(!queryDocumentSnapshots.isEmpty()){

                    for(DocumentChange documentChange: queryDocumentSnapshots.getDocumentChanges()){

                        if(documentChange.getType() == DocumentChange.Type.ADDED){


                            String postid = documentChange.getDocument().getId();

                            ParentsList parentsList = documentChange.getDocument().toObject(ParentsList.class).withID(postid,"","");

                            parentList.add(parentsList);

                            parAdapter.notifyDataSetChanged();

                        }

                    }

                }

            }
        });



        parentView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        parAdapter = new CareGiverParentsAdapter(parentList);
        parAdapter.notifyDataSetChanged();

        parentView.setAdapter(parAdapter);

    }
}
