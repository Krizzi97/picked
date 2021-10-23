package com.example.picked.Database;

import androidx.fragment.app.Fragment;

import com.example.picked.SingleFragmentActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PickedDatabase extends SingleFragmentActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("logPlant");


}