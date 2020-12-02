package com.group0014.iconference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

  EditText email, password;
  Button btn_login;

  FirebaseAuth auth;
  FirebaseUser firebaseUser;
  DatabaseReference databaseReference;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    auth = FirebaseAuth.getInstance();
    email = findViewById(R.id.email);
    password = findViewById(R.id.password);
    btn_login = findViewById(R.id.button_login);

    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        .child(firebaseUser.getUid());

    btn_login.setOnClickListener(v -> {
      String txt_email = email.getText().toString();
      String txt_password = password.getText().toString();

      if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
        Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
      } else {
        auth.signInWithEmailAndPassword(txt_email, txt_password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                      User user = snapshot.getValue(User.class);
                      if (user != null) {
                        if (user.getType().equals("Attendee")) {
                          Intent intent = new Intent(LoginActivity.this, AttendeeActivity.class);
                          intent.addFlags(
                              Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(intent);
                          finish();
                        }

                        if (user.getType().equals("Organizer")) {
                          Intent intent = new Intent(LoginActivity.this, OrganizerActivity.class);
                          intent.addFlags(
                              Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(intent);
                          finish();
                        }

                        if (user.getType().equals("Speaker")) {
                          Intent intent = new Intent(LoginActivity.this, SpeakerActivity.class);
                          intent.addFlags(
                              Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(intent);
                          finish();
                        }

                        if (user.getType().equals("Admin")) {
                          Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                          intent.addFlags(
                              Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                          startActivity(intent);
                          finish();
                        }
                      } else {
                        Toast
                            .makeText(LoginActivity.this, "User doesn't exists!",
                                Toast.LENGTH_SHORT)
                            .show();
                      }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                      Toast
                          .makeText(LoginActivity.this, "Can't find user type!", Toast.LENGTH_SHORT)
                          .show();
                    }
                  });

//                  Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                  startActivity(intent);
//                  finish();
                } else {
                  Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                }
              }
            });
      }
    });
  }
}