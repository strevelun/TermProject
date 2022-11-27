package com.example.termproject.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnection {

    private static FirebaseConnection inst = null;

    public FirebaseConnection() {
        if(inst == null){
            inst = new FirebaseConnection();
        }
    }

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    public DatabaseReference DatabaseReference(String str){
        return database.getReference(str);
    }
}
