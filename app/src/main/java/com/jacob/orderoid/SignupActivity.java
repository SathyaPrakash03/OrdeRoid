package com.jacob.orderoid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jacob.orderoid.Data.User;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.phonenumberInput) EditText phonenumberInput;
    @BindView(R.id.nameInput) EditText nameInput;
    @BindView(R.id.passwordInput) EditText passwordInput;
    @BindView(R.id.confirmPasswordInput) EditText confirmPasswordInput;
    @BindView(R.id.signupBtn) Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        // Database Init
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = database.getReference("User");

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignupActivity.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();

                user_table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Check if fields are empty
                        if (!TextUtils.isEmpty(phonenumberInput.getText().toString()) && !TextUtils.isEmpty(nameInput.getText().toString())
                                && !TextUtils.isEmpty(passwordInput.getText().toString())) {
                            if (dataSnapshot.child(phonenumberInput.getText().toString()).exists()) {
                                mDialog.dismiss();
                                Toast.makeText(SignupActivity.this, "This phonenumber is already associated with an account", Toast.LENGTH_SHORT).show();
                            } else {

                                // Check if both password fields matches
                                if (passwordInput.getText().toString().equals(confirmPasswordInput.getText().toString())) {
                                    mDialog.dismiss();
                                    User user = new User(nameInput.getText().toString(), passwordInput.getText().toString());

                                    user_table.child(phonenumberInput.getText().toString()).setValue(user);
                                    Toast.makeText(SignupActivity.this, "Sign up is successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                } else {
                                    mDialog.dismiss();
                                    passwordInput.setError("Password doesn't match");
                                    passwordInput.requestFocus();
                                    return;
                                }

                            }
                        }

                        else {
                            mDialog.dismiss();
                            Toast.makeText(SignupActivity.this, "You can't have any field empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

}
