package com.example.iPrep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText board,mFullName,mEmail,mPassword,classes;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.signupEmail);
        mPassword = findViewById(R.id.signuppassword);
        classes = findViewById(R.id.classes);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        firebaseAuth = FirebaseAuth.getInstance();
        board = findViewById(R.id.board);

        if(firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(Register.this);
                pd.setMessage("Please wait..");
                pd.show();

                final String fullname = mFullName.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String mboard = board.getText().toString().trim();
                final String mclasses = classes.getText().toString().trim();


                if(TextUtils.isEmpty(fullname)){
                    mFullName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(mboard)){
                    board.setError("Board is required");
                    return;
                }
                if(TextUtils.isEmpty(mclasses)){
                    classes.setError("Class is required");
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }


                if(password.length() < 6){
                    mPassword.setError("Password is too short");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance("https://i-prep-82c62-default-rtdb.firebaseio.com").getReference().child("Users").child(userid);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("fullname",fullname);
                            hashMap.put("Board",mboard);
                            hashMap.put("classes",mclasses);


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        Intent intent =new Intent(Register.this,MainActivity2.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Toast.makeText(Register.this,"User Created.",Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor editor = getSharedPreferences("PREFS", MODE_PRIVATE).edit();
                                        editor.putString("classes", mclasses);
                                        editor.apply();
                                        startActivity(intent);
                                    }
                                }
                            });

                        }else {
                            pd.dismiss();
                            Toast.makeText(Register.this,"Error ! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}