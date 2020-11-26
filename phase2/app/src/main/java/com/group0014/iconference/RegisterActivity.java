package com.group0014.iconference;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

  private EditText email, password, phone, name;
  private Button btn_register, btn_back;
  private RadioGroup radioGroup;
  private RadioButton radioButton;
  private String type;

  FirebaseAuth auth;
  DatabaseReference reference;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    email = findViewById(R.id.email);
    password = findViewById(R.id.password);
    phone = findViewById(R.id.phone);
    name = findViewById(R.id.name);
    btn_register = findViewById(R.id.button_register);

    radioGroup = findViewById(R.id.radio_group_1);
    radioGroup.clearCheck();
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @SuppressLint("ResourceType")
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        radioButton = radioGroup.findViewById(checkedId);
        if (radioButton != null && checkedId > -1) {
          type = radioButton.getText().toString();
        }
      }
    });

    auth = FirebaseAuth.getInstance();

    btn_register.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        String useremail = email.getText().toString();
        String userepwd = password.getText().toString();
        String userphone = phone.getText().toString();
        String userename = name.getText().toString();

        if (TextUtils.isEmpty(useremail) || TextUtils.isEmpty(userepwd) || TextUtils
            .isEmpty(userphone) || TextUtils.isEmpty(userename)) {
          Toast.makeText(RegisterActivity.this, "All fields are required",
              Toast.LENGTH_SHORT).show();
        } else if (userepwd.length() < 6) {
          Toast.makeText(RegisterActivity.this, "Password must be at least 6 " +
              "characters", Toast.LENGTH_SHORT).show();
        } else {
          register(useremail, userepwd, userphone, userename, type);
        }
      }
    });
  }


  private void register(String email, String password, final String phone, final String name,
      final String type) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              FirebaseUser firebaseUser = auth.getCurrentUser();
              assert firebaseUser != null;
              String userid = firebaseUser.getUid();
              reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

              HashMap<String, String> hashMap = new HashMap<>();
              hashMap.put("id", userid);
              hashMap.put("name", name);
              hashMap.put("phone", phone);
              hashMap.put("type", type);

              reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                  if (task.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this,
                        MainActivity.class);
                    intent
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                  }
                }
              });
            } else {
              Toast.makeText(RegisterActivity.this, "You can't register with this email and" +
                  " password", Toast.LENGTH_SHORT).show();
            }
          }
        });
  }
}