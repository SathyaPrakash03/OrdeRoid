package com.jacob.orderoid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jacob.orderoid.Data.ActiveUser;
import com.jacob.orderoid.Data.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.phonenumberInput) EditText phonenumberInput;

    @BindView(R.id.passwordInput) EditText passwordInput;

    @BindView(R.id.loginBtn) Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        // Database Initialization
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = database.getReference("User");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                user_table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(phonenumberInput.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(phonenumberInput.getText().toString()).getValue(User.class);


                            // Authenticate user
                            if (user.getPassword().equals(passwordInput.getText().toString())) {

                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                ActiveUser.currentUser = user;
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                finish();


                            } else {
                                Toast.makeText(LoginActivity.this, "You have entered the wrong password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Phone number not found", Toast.LENGTH_SHORT).show();
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
