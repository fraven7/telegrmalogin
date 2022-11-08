package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView textView,textView2;
    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();

        textView=findViewById(R.id.textviewreg);
        textView2=findViewById(R.id.buttonlogin);
        textView.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this,RegesterActivity.class));
        });
        textView2.setOnClickListener(view -> {
            binding.progressborlog.setVisibility(View.VISIBLE);


            firebaseAuth.signInWithEmailAndPassword(binding.edittextlogin.getText().toString(),binding.edittextposswordlog.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        binding.progressborlog.setVisibility(View.INVISIBLE);
                        String uid=firebaseAuth.getCurrentUser().getUid();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("uid",uid);
                        startActivity(intent);



                    }else{
                        binding.progressborlog.setVisibility(View.INVISIBLE);

                    }
                }


            });



        });
    }
}