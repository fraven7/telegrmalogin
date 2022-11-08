package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityRegesterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegesterActivity extends AppCompatActivity {
    ActivityRegesterBinding binding;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding=ActivityRegesterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("user");
        binding.textviewlog.setOnClickListener(view -> {
            binding.progressborreg.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(binding.edittextemailreg.getText().toString(),binding.edittextpasswordreg.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        binding.progressborreg.setVisibility(View.INVISIBLE);
                        String uid=firebaseAuth.getCurrentUser().getUid();
                        User user=new User();
                        user.setEmail(binding.edittextemailreg.getText().toString());
                        user.setPassword(binding.edittextpasswordreg.getText().toString());
                        user.setFullname(binding.edittextemailreg.getText().toString());
                        user.setName(binding.edittextemailreg.getText().toString());
                        user.setEmail(binding.edittextemailreg.getText().toString());
                        databaseReference.child(uid).setValue(user);
                        finish();
                    }
                }
            });

            startActivity(new Intent(RegesterActivity.this,LoginActivity.class));
        });


    }
}