package com.example.hostelutility;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mPhone, emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore fStore;
    String userID;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mFullName = findViewById(R.id.editTextTextPersonName);
        mPhone = findViewById(R.id.editTextPhone);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        btnSignUp = findViewById(R.id.button);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                final String name = mFullName.getText().toString();
                final String phone = mPhone.getText().toString();



                if(name.isEmpty()){
                    mFullName.setError("Please Enter your Full Name");
                    mFullName.requestFocus();
                }

                else if(email.isEmpty()){
                    emailId.setError("Please Enter Email Id");
                    emailId.requestFocus();
                }
                else if(!email.matches(emailPattern)){
                    emailId.setError("Please Enter Valid Email Address");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                }
                else if(phone.isEmpty()){
                    mPhone.setError("Please Enter Phone no.");
                    mPhone.requestFocus();
                }
                else if(!phone.matches(MobilePattern)){
                    mPhone.setError("Please Enter Valid Phone no.");
                    mPhone.requestFocus();
                }
                else if (email.isEmpty() &&  pwd.isEmpty() && name.isEmpty() && phone.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }


                   else if (!(email.isEmpty() && pwd.isEmpty())) {
                        mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please try Again!", Toast.LENGTH_SHORT).show();
                                } else {
                                    userID = mFirebaseAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fStore.collection("users").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("FullName",name);
                                    user.put("Email",email );
                                    user.put("PhoneNo",phone);
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "onSuccess: user profile is created for"+ userID);
                                        }
                                    });
                                    startActivity(new Intent(MainActivity.this, Home.class));
                                }
                            }
                        });
                    }

                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
