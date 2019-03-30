package com.example.nutritiondiary;

import androidx.appcompat.app.AppCompatActivity;
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

public class CareGiverActivity extends AppCompatActivity {


    String parentid;

    private RecyclerView childView;

    boolean authState = true;
    private RecyclerView.Adapter catAdapter;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private FirebaseAuth mAuth;

    private FirebaseFirestore firebaseFirestore;

    List<ChildList> childLists = null;

    private long doubleBackToExitPressedOnce;

    String s;

    List<Integer> calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver);


        Bundle extras = getIntent().getExtras();
        if(extras != null){

            parentid = extras.getString("parentid");
        }



        childView = (RecyclerView)findViewById(R.id.nRecyView);

        childView.setHasFixedSize(true);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);

        childLists = new ArrayList<>();

        childLists.clear();
        calories = new ArrayList<>();





        Query firestoreQuery = firebaseFirestore.collection("parents").document(parentid).collection("Childs").orderBy("name");

        firestoreQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(!queryDocumentSnapshots.isEmpty()){

                    for(DocumentChange documentChange: queryDocumentSnapshots.getDocumentChanges()){

                        if(documentChange.getType() == DocumentChange.Type.ADDED){


                            String Childid = documentChange.getDocument().getId();

                            ChildList childList = documentChange.getDocument().toObject(ChildList.class).withID(Childid,"",parentid);

                            childLists.add(childList);

                            catAdapter.notifyDataSetChanged();

                        }

                    }

                }

            }
        });


//        DividerItemDecoration divider = new DividerItemDecoration(childView.getContext(), DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.custom_divider));
//        childView.addItemDecoration(divider);


        childView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        catAdapter = new ChildListAdapter(childLists);
        catAdapter.notifyDataSetChanged();

        childView.setAdapter(catAdapter);


    }
}
