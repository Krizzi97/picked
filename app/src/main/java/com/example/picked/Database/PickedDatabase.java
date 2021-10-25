package com.example.picked.Database;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import timber.log.Timber;

public class PickedDatabase  {

    // Write a message to the database
    FirebaseDatabase database;
//    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public PickedDatabase (FirebaseDatabase database) {
        this.database = database;
    }

    public String getMultiplePlants(){
        final String[] result = new String[1];
        DatabaseReference ref = database.getReference("Plants");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Timber.d("Error getting data" + task.getException());
                }
                else {
                    result[0] = String.valueOf(task.getResult().getValue());
                }
            }
        });
        return result[0];
    }



    // source: https://github.com/firebase/snippets-android/blob/8184cba2c40842a180f91dcfb4a216e721cc6ae6/firestore/app/src/main/java/com/google/example/firestore/DocSnippets.java#L755-L768
//    public void getMultipleDocs(String region, String area, String season, String part) {
//        db.collection("examplePlants")
//                .whereEqualTo("region", region).whereEqualTo("area", area).whereEqualTo("season", season).whereEqualTo("part", part)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Timber.d(document.getId() + " => " + document.getData());
//                    }
//                } else {
//                    Timber.d("Error getting documents: " + task.getException());
//                }
//            }
//        });
//    }

    // source: https://github.com/firebase/snippets-android/blob/8184cba2c40842a180f91dcfb4a216e721cc6ae6/firestore/app/src/main/java/com/google/example/firestore/DocSnippets.java#L755-L768
//    public void getAllDocs() {
//        db.collection("examplePlants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Timber.d(document.getId() + " => " + document.getData());
//                    }
//                } else {
//                    Timber.d("Error getting documents: " + task.getException());
//                }
//            }
//        });
//    }

//    public void exampleData() {
//        CollectionReference explants = db.collection("examplePlants");
//
//        Map<String, Object> data1 = new HashMap<>();
//        data1.put("name", "apple fruit");
//        data1.put("region", "midwest");
//        data1.put("area", "full sun");
//        data1.put("season", "fall");
//        data1.put("part", "fruit");
//        explants.document("apple fruit").set(data1);
//
//        Map<String, Object> data2 = new HashMap<>();
//        data2.put("name", "american persimmon fruit");
//        data2.put("region", "midwest");
//        data2.put("area", "full sun");
//        data2.put("season", "fall");
//        data2.put("part", "fruit");
//        explants.document("american persimmon fruit").set(data2);
//
//        Map<String, Object> data3 = new HashMap<>();
//        data3.put("name", "burdock roots");
//        data3.put("region", "midwest");
//        data3.put("area", "full sun");
//        data3.put("season", "fall");
//        data3.put("part", "underground");
//        explants.document("burdock roots").set(data3);
//
//        Map<String, Object> data4 = new HashMap<>();
//        data4.put("name", "angelica root");
//        data4.put("region", "midwest");
//        data4.put("area", "near water");
//        data4.put("season", "fall");
//        data4.put("part", "underground");
//        explants.document("angelica root").set(data4);
//
//    }
}