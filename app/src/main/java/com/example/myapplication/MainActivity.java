package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    DrawerLayout drawerLayout;
    DatabaseReference databaseReference;
    NavigationView navigationView;
    String uid="";
    View hearder;
    TextView textViewusername;
    TextView textViewemail;
    String fullname="";
    String name="";
    String email="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        imageView=findViewById(R.id.imageviewmenu);
        navigationView=findViewById(R.id.navigation1);
        textViewusername=hearder.findViewById(R.id.username);
        textViewemail=hearder.findViewById(R.id.emailandpassword);
        hearder=navigationView.getHeaderView(0);
        imageView.setOnClickListener(view -> {
            drawerLayout.openDrawer(Gravity.LEFT);

        });
        Intent intent=new Intent();
        uid=intent.getStringExtra("uid");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name=snapshot.child("name") .getValue().toString();
                fullname=snapshot.child("fullname") .getValue().toString();
                email=snapshot.child("email") .getValue().toString();

                textViewusername.setText(name+" "+fullname);
                textViewemail.setText(email);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}