package com.givingback;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import customs.ConstantsClass;
import customs.UsersClass;

import static customs.MethodsClass.isValidEmail;

public class SignUpActivity extends AppCompatActivity {

    EditText nameEdit,ageEdit,phoneEdit,emailEdit,passEdit,cpassEdit;
    Button signup;
    String name,age,phone,email,pass,cpass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;

    SharedPreferences prefs;


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back_arrow);

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("users");

        prefs=getSharedPreferences(ConstantsClass.PREFERNCE_NAME,MODE_PRIVATE);

        windWidgets();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean status = checkAllValues();
                if(status){
                    Toast.makeText(SignUpActivity.this,"All Correct",Toast.LENGTH_SHORT).show();
                    createNewUserUsingFirebase();
                }

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                    UsersClass customUser = new UsersClass(user.getUid(),name,email,phone,age);
                    mDatabaseReference.push().setValue(customUser);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(ConstantsClass.PREF_NAME,name);
                    editor.putString(ConstantsClass.PREF_AGE,age);
                    editor.putString(ConstantsClass.PREF_EMAIL,email);
                    editor.putString(ConstantsClass.PREF_PHONE,phone);
                    editor.putString(ConstantsClass.PREF_UID,user.getUid());
                    editor.commit();

                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    private void createNewUserUsingFirebase() {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("", "createUserWithEmail:onComplete:" + task.isSuccessful());




                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Error",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void printDetails() {

/*        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Log.w("", "signInWithEmail:failed", task.getException());
                            Toast.makeText(SignUpActivity.this, "Fail",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
  */

    }

    private boolean checkAllValues() {
        windStringsFromView();
        if(name.isEmpty() || age.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                pass.isEmpty()|| cpass.isEmpty()){
            Toast.makeText(this,"Enter All Fields.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(email)){
            Toast.makeText(this,"Enter Valid Email.",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(cpass.equals(pass))){
            Toast.makeText(this,"Passwords don't match.",Toast.LENGTH_SHORT).show();
            return false;
        }
            return true;
    }

    private void windStringsFromView() {
        name=nameEdit.getText().toString();
        age=ageEdit.getText().toString();
        phone=phoneEdit.getText().toString();
        email=emailEdit.getText().toString();
        pass=passEdit.getText().toString();
        cpass=cpassEdit.getText().toString();

    }

    private void windWidgets() {
        nameEdit = (EditText) findViewById(R.id.signup_name);
        ageEdit = (EditText) findViewById(R.id.signup_age);
        phoneEdit = (EditText) findViewById(R.id.signup_phone);
        emailEdit= (EditText) findViewById(R.id.signup_email);
        passEdit= (EditText) findViewById(R.id.signup_pass);
        cpassEdit = (EditText) findViewById(R.id.signup_confirm_pass);
        signup = (Button) findViewById(R.id.signup_button);

    }


}
