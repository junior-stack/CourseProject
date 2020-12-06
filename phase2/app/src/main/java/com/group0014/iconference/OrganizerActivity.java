package com.group0014.iconference;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

public class OrganizerActivity extends AppCompatActivity {

  Button logout;
  Button messageManager;
  FirebaseAuth auth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_organizer);

    auth = FirebaseAuth.getInstance();

    logout = findViewById(R.id.btn_logout);
    messageManager = findViewById(R.id.btn_message_manager);

    logout.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        auth.signOut();
        startActivity(new Intent(OrganizerActivity.this, StartActivity.class));
        finish();
      }
    });

    messageManager.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(OrganizerActivity.this, MessageActivity.class));
        finish();
      }
    });

  }
}