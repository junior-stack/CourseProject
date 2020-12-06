package com.group0014.iconference;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group0014.iconference.Adapter.MessageAdapter;
import com.group0014.iconference.Model.User;
import com.group0014.iconference.Model.Message;
import com.group0014.iconference.Model.AllMethods;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Text;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

  FirebaseAuth auth;
  FirebaseDatabase database;
  DatabaseReference messagedb;
  MessageAdapter messageAdapter;
  User u;
  List<Message> messages;

  RecyclerView rvMessage;
  EditText etMessage;
  ImageButton imageButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message);

    init();
  }

  private void init() {
    auth = FirebaseAuth.getInstance();
    database = FirebaseDatabase.getInstance();
    u = new User();

    rvMessage = (RecyclerView) findViewById(R.id.rvMessage);
    etMessage = (EditText) findViewById(R.id.etMessage);
    imageButton = (ImageButton) findViewById(R.id.btn_send);
    imageButton.setOnClickListener(this);
    messages = new ArrayList<>();
  }

  @Override
  public void onClick(View v) {
    if (!TextUtils.isEmpty(etMessage.getText().toString())) {
      Message message = new Message(etMessage.getText().toString(), u.getName());
      etMessage.setText("");
      messagedb.push().setValue(message);
    } else {
      Toast.makeText(getApplicationContext(), "You cannot send blank message", Toast.LENGTH_SHORT)
          .show();
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    final FirebaseUser currentUser = auth.getCurrentUser();

    u.setUid(currentUser.getUid());
    u.setEmail(currentUser.getEmail());

    database.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(
        new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
            u = snapshot.getValue(User.class);
            u.setUid(currentUser.getUid());
            AllMethods.name = u.getName();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
        });

    messagedb = database.getReference("messages");
    messagedb.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
        Message message = snapshot.getValue(Message.class);
        message.setKey(snapshot.getKey());
        messages.add(message);
        displayMessages(messages);
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {
        Message message = snapshot.getValue(Message.class);
        message.setKey(snapshot.getKey());
        List<Message> newMessages = new ArrayList<Message>();

        for (Message m : messages) {
          if (m.getKey().equals(message.getKey())) {
            newMessages.add(message);
          } else {
            newMessages.add(m);
          }
        }

        messages = newMessages;
        displayMessages(messages);
      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        Message message = snapshot.getValue(Message.class);
        message.setKey(snapshot.getKey());
        List<Message> newMessages = new ArrayList<Message>();

        for (Message m : messages) {
          if (!m.getKey().equals(message.getKey())) {
            newMessages.add(m);
          }
        }

        messages = newMessages;
        displayMessages(messages);
      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {

      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    messages = new ArrayList<>();

  }

  private void displayMessages(List<Message> messages) {
    rvMessage.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
    messageAdapter = new MessageAdapter(MessageActivity.this, messages, messagedb);
    rvMessage.setAdapter(messageAdapter);

  }
}